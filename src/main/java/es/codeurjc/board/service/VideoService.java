package es.codeurjc.board.service;

import es.codeurjc.board.model.Review;
import es.codeurjc.board.model.Video;
import es.codeurjc.board.repositories.ReviewsRepository;
import es.codeurjc.board.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class VideoService {

    private static final String UPLOAD_DIR = "videos";
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private ReviewsRepository reviewsRepository;


    public Video saveVideo(MultipartFile file, String username) throws IOException {
        String originalName = file.getOriginalFilename();
        if (originalName == null || originalName.isBlank()) {
            throw new IOException("Invalid file name");
        }
        // Eliminate possible paths in the originalName
        originalName = Paths.get(originalName).getFileName().toString();

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("video/")) {
            throw new IOException("Invalid file type");
        }

        int i = originalName.lastIndexOf('.');
        if (i < 0) {
            throw new IOException("File must have an extension");
        }
        String baseName = originalName.substring(0, i);
        String extension = originalName.substring(i).toLowerCase();

        // whitelist of permited extensions
        List<String> allowedExt = List.of(".mp4", ".webm", ".ogg");
        if (!allowedExt.contains(extension)) {
            throw new IOException("Unsupported file extension");
        }

        //sanitize to show (no used for the path)
        String displayName = baseName.replaceAll("[^A-Za-z0-9._-]", "_");
        if (displayName.length() > 100) {
            displayName = displayName.substring(0, 100);
        }
        displayName = displayName + extension;

        //sanitize username shorter for metadata (max 30)
        if (username == null) {
            username = "unknown";
        }
        username = username.replaceAll("[^A-Za-z0-9_-]", "_");
        if (username.length() > 30) {
            username = username.substring(0, 30);
        }

        // generate unique storage name
        String safeBaseName = baseName.replaceAll("[^A-Za-z0-9._-]", "_");
        String storageFileName = UUID.randomUUID().toString() + extension;

        Path uploadPath = Paths.get(UPLOAD_DIR).toAbsolutePath().normalize();
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path target = uploadPath.resolve(storageFileName).normalize();
        if (!target.startsWith(uploadPath)) {
            throw new IOException("Invalid path");
        }

        try (InputStream in = file.getInputStream()) {
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        }

        Video video = new Video(displayName, storageFileName, contentType);
        return videoRepository.save(video);

    }

    public Video findById(Long id) {
        return videoRepository.findById(id).orElseThrow();
    }

    public void delete(Long id) {
        Video video = findById(id);
        try {
            Path basePath = Paths.get(UPLOAD_DIR).toAbsolutePath().normalize();
            Path filePath = basePath.resolve(video.getStoredFileName()).normalize();

            Files.deleteIfExists(filePath);
        } catch (IOException ignored) {
        }

        videoRepository.deleteById(id);
    }

    public void addVideoToReview(Long reviewId, MultipartFile file) throws IOException {
        Review review = reviewsRepository.findById(reviewId).orElseThrow();

        if (review.getUser() == null) {
            throw new IllegalStateException("Review without user");
        }

        String username = review.getUser().getUsername();

        Video video = saveVideo(file, username);

        if (review.getVideo() != null) {
            delete(review.getVideo().getId());
        }

        review.setVideo(video);
        reviewsRepository.save(review);
    }

    public Resource getVideo(Long id) throws IOException {

        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found"));

        Path basePath = Paths.get(UPLOAD_DIR).toAbsolutePath().normalize();
        Path filePath = basePath.resolve(video.getStoredFileName()).normalize();

        if (!filePath.startsWith(basePath)) {
            throw new SecurityException("Path Traversal attempt detected");
        }

        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("Cannot read file");
        }

        return resource;
    }
}
