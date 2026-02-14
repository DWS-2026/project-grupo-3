package es.codeurjc.board.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

	private static final Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "images");

	private Path createFilePath(long imageId,long plantId, Path folder) throws IOException {
		Path plantFolder = folder.resolve(String.valueOf(plantId));


		if (!Files.exists(plantFolder)) {
			Files.createDirectories(plantFolder);
		}


		return plantFolder.resolve(imageId + ".jpg");
	}


	public void saveImage(String folderName,long plantId, long imageId, MultipartFile image) throws IOException {

		Path folder = FILES_FOLDER.resolve(folderName);

		Files.createDirectories(folder);
		
		Path newFile = createFilePath(imageId, plantId,folder);

		image.transferTo(newFile);
	}

	public ResponseEntity<Object> createResponseFromImage(String folderName, long plantId, long imageId) throws MalformedURLException, IOException {

		Path folder = FILES_FOLDER.resolve(folderName);
		
		Path imagePath = createFilePath(imageId, plantId, folder);
		
		Resource file = new UrlResource(imagePath.toUri());
		
		if(!Files.exists(imagePath)) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(file);
		}		
	}
	public List<String> listImages(long plantId) throws IOException {

		Path folder = FILES_FOLDER.resolve("plants").resolve(String.valueOf(plantId));

		if (!Files.exists(folder)) {
			return List.of();
		}

		try (Stream<Path> paths = Files.list(folder)) {
			return paths
					.map(path -> path.getFileName().toString())
					.toList();
		}
	}

	public void deleteImage(String folderName, long imageId, long plantId) throws IOException {

		Path folder = FILES_FOLDER.resolve(folderName);

		Path imageFile = createFilePath(imageId, plantId, folder);
		
		Files.deleteIfExists(imageFile);				
	}

}