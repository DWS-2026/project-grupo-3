package es.codeurjc.board.controller;
import es.codeurjc.board.model.Image;
import es.codeurjc.board.model.Product;
import es.codeurjc.board.modelAttributes.ButtonsHeader;
import es.codeurjc.board.service.ImageService;
import es.codeurjc.board.service.ProductService;
import es.codeurjc.board.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ProductController {

    @Autowired
    private ButtonsHeader btnsHeader;

    @Autowired
    private ProductService productService;

    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;


    @GetMapping("/Products/catalogProducts")
    public String catalogProducts(Model model, @PageableDefault(size = 6) Pageable page, HttpServletRequest request) {
        btnsHeader.hideBtnHeader(model, "productsOption");
        Pageable sortedPage = PageRequest.of(page.getPageNumber(), 6);
        Page<Product> productsPage;

        boolean isAdmin = userService.isUserAdmin(request);
        if(isAdmin) {
            productsPage = productService.findAll(sortedPage); // admin watch all images_example_products
        } else {
            productsPage = productService.findByIsExample(true, sortedPage); // normal user watch only example images_example_products
        }

        model.addAttribute("products", productsPage.getContent());
        model.addAttribute("hasPrev", productsPage.hasPrevious());
        model.addAttribute("prev", productsPage.getNumber() - 1);
        model.addAttribute("hasNext", productsPage.hasNext());
        model.addAttribute("next", productsPage.getNumber() + 1);

        return "/Products/catalogProducts";
    }

    @GetMapping("/Products/showOneProduct")
    public String oneProduct(Model model,  @RequestParam String search) {
        Product product = productService.findByNameProduct(search);
        model.addAttribute("products",product);
        return "/Products/catalogProducts";
    }

    @GetMapping("/Products/newProduct")
    public String newProduct() {
        return "Products/newProduct";
    }

    @PostMapping("/Products/new")
    public String newProduct(Model model, Product product, MultipartFile imageFile) throws IOException {
        product.setExample(true);
        productService.save(product);
        if (!imageFile.isEmpty()) {
            Image image = imageService.createImage(imageFile);
            productService.addImageToProduct(product.getId(), image);
        }
        return "redirect:/Products/catalogProducts";
    }

    @GetMapping("/Products/editProduct/{id}")
    public String editProduct(Model model, @PathVariable long id) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("productId", product.getId() );
        return "Products/editProduct";
    }

    @PostMapping("/Products/editProduct/{id}")
    public String editProduct(@PathVariable long id, @RequestParam String name, @RequestParam String description, @RequestParam double price, @RequestParam(required = false) MultipartFile imageFile) throws IOException {
        productService.editProduct(name, description, price, id);
        if (imageFile != null && !imageFile.isEmpty()) {
            Product product = productService.findById(id);
            if (!product.getImages().isEmpty()) {
                long oldImageId = product.getImages().get(0).getId();
                productService.deleteImageFromProduct(id, oldImageId);
            }
            Image image = imageService.createImage(imageFile);
            productService.addImageToProduct(id, image);
        }

        return "redirect:/Products/catalogProducts";
    }

    @PostMapping("/addImageToProduct/{id}")
    public String addImageToProduct(Model model, @PathVariable long id, MultipartFile newImage) throws IOException {

        if (!newImage.isEmpty()) {
            Image imageOne = imageService.createImage(newImage);
            productService.addImageToProduct(id, imageOne);
        }
        return "redirect:/Products/editProduct/" + id;
    }

    @PostMapping("/delete/image/{productId}/{imageId}")
    public String deleteImage(@PathVariable long productId,@PathVariable long imageId) {

        productService.deleteImageFromProduct(productId, imageId);

        return "redirect:/Products/catalogProducts";
    }

    @PostMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable long id) {
        productService.deleteById(id);
        return "redirect:/Admin/productManagement";
    }

    @PostMapping("/products/{id}/reactivate")
    public String reactivateProduct(@PathVariable long id) {
        productService.reactivateById(id);
        return "redirect:/Admin/productManagement";
    }


}