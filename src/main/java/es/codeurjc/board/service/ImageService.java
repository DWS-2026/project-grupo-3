package es.codeurjc.board.service;

import java.io.IOException;
import es.codeurjc.board.model.Image;
import es.codeurjc.board.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.core.io.InputStreamResource;



@Service
public class ImageService {

		@Autowired
		private ImageRepository ImageRepository;
		public Image createImage(MultipartFile imageFile) throws IOException {
			Image image = new Image();
			try {
				image.setImageFile(new SerialBlob(imageFile.getBytes()));
			} catch (Exception e) {
				throw new IOException("Failed to create image", e);
			}
			ImageRepository.save(image);
			return image;
		}
		public Resource getImageFile(long id) throws SQLException {
			Image image = ImageRepository.findById(id).orElseThrow();
			if (image.getImageFile() != null) {
				return new InputStreamResource(image.getImageFile().getBinaryStream());
			} else {
				throw new RuntimeException("Image file not found");
			}
		}


}