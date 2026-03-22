package es.codeurjc.board.service;

import es.codeurjc.board.model.Image;
import es.codeurjc.board.model.Plant;
import es.codeurjc.board.model.Product;
import es.codeurjc.board.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageService imageService;

    public Page<Product> findAll(Pageable page) {return productRepository.findAll(page);}

    public Product findById(long id) {return productRepository.findById(id).orElseThrow();}

    public void save(Product product) {productRepository.save(product);}

    public void deleteById(Long id) {productRepository.deleteById(id);}

    @Transactional
    public Product addImageToProduct(long id, Image image) {
        Product product = productRepository.findById(id).orElseThrow();

        product.addImage(image);

        return productRepository.save(product);
    }

    @Transactional
    public Product addImageToProduct(long id, String source) throws Exception {
        Product product = productRepository.findById(id).orElseThrow();
        Image newImage = new Image(source);
        product.addImage(newImage);

        return productRepository.save(product);
    }

    public void editProduct(String name, String description, double price, Long id) throws IOException {
        Product product = productRepository.findById(id).orElseThrow();
        if(name != null && !name.isBlank()){
            product.setName(name);
        }
        if(description != null && !description.isBlank()){
            product.setDescription(description);
        }
        if(price > 0){
            product.setPrice(price);
        }

        productRepository.save(product);
    }

    public void deleteImageFromProduct(long productId, long imageId) {

        Product product = productRepository.findById(productId).orElseThrow();

        product.getImages().removeIf(image -> image.getId() == imageId);

        productRepository.save(product);

    }

    public Page<Product> findByIsExample(boolean example, Pageable page) {
        return productRepository.findByExampleEquals(example, page);
    }

    public Product getProductByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + name));
    }
    public long numberOfProducts(){
        return productRepository.count();
    }
}
