package es.codeurjc.board.service;

import es.codeurjc.board.model.Review;
import es.codeurjc.board.model.Video;
import es.codeurjc.board.repositories.ReviewsRepository;
import es.codeurjc.board.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.util.UUID;

@Service
public class VideoService {

    private static final String UPLOAD_DIR = "src/main/resources/static/assets/videos/";
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private ReviewsRepository reviewsRepository;



    public Video saveVideo(MultipartFile file) throws IOException {
        String originalName = file.getOriginalFilename();

        String safeName = System.currentTimeMillis() + "_" + UUID.randomUUID();
        String extension = "";
        int i = originalName.lastIndexOf(".");
        if (i > 0) {
            extension = originalName.substring(i);
        }        String fileName = safeName + extension;

        Path uploadPath = Paths.get(UPLOAD_DIR);

        if (!file.getContentType().startsWith("video/")) {
            throw new IOException("Invalid file type");
        }

        if (file.getSize() > 50_000_000) {
            throw new IOException("File too large");
        }

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        Video video = new Video(originalName, filePath.toString(), file.getContentType());

        return videoRepository.save(video);
    }

    public Resource getVideo(Long id) throws IOException {
        Video video = videoRepository.findById(id).orElseThrow();

        Path path = Paths.get(video.getFilePath());
        return new UrlResource(path.toUri());
    }

    public Video findById(Long id) {
        return videoRepository.findById(id).orElseThrow();
    }

    public void delete(Long id) {
        Video video = findById(id);
        try {
            Files.deleteIfExists(Paths.get(video.getFilePath()));
        } catch (IOException ignored) {}

        videoRepository.deleteById(id);
    }

    public void addVideoToReview(Long reviewId, MultipartFile file) throws IOException {
        Review review = reviewsRepository.findById(reviewId).orElseThrow();

        Video video = saveVideo(file);

        review.setVideo(video);
        reviewsRepository.save(review);
    }
}