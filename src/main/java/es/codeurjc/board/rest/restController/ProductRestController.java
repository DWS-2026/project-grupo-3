package es.codeurjc.board.rest.restController;

import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import es.codeurjc.board.model.Image;
import es.codeurjc.board.model.Product;
import es.codeurjc.board.rest.dto.ImageDTO;
import es.codeurjc.board.rest.dto.ProductDTO;
import es.codeurjc.board.rest.mapper.ImageMapper;
import es.codeurjc.board.rest.mapper.ProductMapper;
import es.codeurjc.board.service.ImageService;
import es.codeurjc.board.service.ProductService;
import es.codeurjc.board.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;
    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/")
    public Page<ProductDTO> getAllProducts(@PageableDefault(size = 6) Pageable page, HttpServletRequest request) {
        Pageable sortedPage = PageRequest.of(page.getPageNumber(), 6);
        if (userService.isUserAdmin(request)) {
            return productService.findAll(sortedPage).map(productMapper::toDTO);
        } else {
            return productService.findByIsExample(true, sortedPage).map(productMapper::toDTO);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(productMapper.toDTO(product));
    }

    @PostMapping("/")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO, HttpServletRequest request) {
        if (!userService.isUserAdmin(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Product product = productMapper.toDomain(productDTO);
        product.setExample(true);
        product.setActive(true);
        productService.save(product);
        URI location = fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(location).body(productMapper.toDTO(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> editProduct(@PathVariable long id, @RequestBody ProductDTO productDTO, HttpServletRequest request) throws IOException {
        if (!userService.isUserAdmin(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Product updatedProduct = productMapper.toDomain(productDTO);
        return ResponseEntity.ok(productMapper.toDTO(productService.replaceProduct(id, updatedProduct)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable long id, HttpServletRequest request) {
        if (!userService.isUserAdmin(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Product product = productService.findById(id);
        productService.deleteById(id);
        return ResponseEntity.ok(productMapper.toDTO(product));
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<ImageDTO> addImageToProduct(@PathVariable long id, @RequestParam MultipartFile newImage, HttpServletRequest request) throws IOException {
        if (!userService.isUserAdmin(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        productService.findById(id);
        if (newImage.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Image image = imageService.createImage(newImage);
        productService.addImageToProduct(id, image);

        URI location = fromCurrentContextPath()
                .path("/images/{imageId}/media")
                .buildAndExpand(image.getId())
                .toUri();

        return ResponseEntity.created(location).body(imageMapper.toDTO(image));
    }

    @DeleteMapping("/{productId}/image/{imageId}")
    public ResponseEntity<ImageDTO> deleteProductImage(@PathVariable long productId, @PathVariable long imageId, HttpServletRequest request) {
        if (!userService.isUserAdmin(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Product product = productService.findById(productId);
        Image image = imageService.findById(imageId).orElseThrow();
        productService.deleteImageFromProduct(productId, imageId);
        return ResponseEntity.ok(imageMapper.toDTO(image));
    }

}
