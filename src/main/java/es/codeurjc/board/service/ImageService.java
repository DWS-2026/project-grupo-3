package es.codeurjc.board.service;

import java.io.IOException;
import java.io.InputStream;

import es.codeurjc.board.model.Image;
import es.codeurjc.board.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.core.io.InputStreamResource;

import java.util.Optional;


@Service
public class ImageService {

		@Autowired
		private ImageRepository imageRepository;
		
		public Image createImage(MultipartFile imageFile) throws IOException {
			Image image = new Image();
			try {
				image.setImageFile(new SerialBlob(imageFile.getBytes()));
			} catch (Exception e) {
				throw new IOException("Failed to create image", e);
			}
			imageRepository.save(image);
			return image;
		}
		public Resource getImageFile(long id) throws SQLException {
			Image image = imageRepository.findById(id).orElseThrow();
			if (image.getImageFile() != null) {
				return new InputStreamResource(image.getImageFile().getBinaryStream());
			} else {
				throw new RuntimeException("Image file not found");
			}
		}


    public void deleteById(long id) {
			imageRepository.deleteById(id);
    }

	public Optional<Image> findById(long id){
		return imageRepository.findById(id);
	}

	public void replaceImageFile(long id, InputStream inputStream) throws IOException {

        Image image = imageRepository.findById(id).orElseThrow();

        try {
            image.setImageFile(new SerialBlob(inputStream.readAllBytes()));
        } catch (Exception e) {
            throw new IOException("Failed to create image", e);
        }

        imageRepository.save(image);
    }
}