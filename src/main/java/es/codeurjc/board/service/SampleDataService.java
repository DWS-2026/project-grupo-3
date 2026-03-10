package es.codeurjc.board.service;

import es.codeurjc.board.model.Plant;
import es.codeurjc.board.model.Product;
import es.codeurjc.board.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import jakarta.annotation.PostConstruct;



@Service
public class SampleDataService {

    @Autowired
    private PlantService plantService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProductService productService;

    private void addExampleProducts() throws Exception {
        Product product_ex1 = new Product("Fertilizante", "Nutre tus plantas de manera rápida y efectiva.", 10.99, true );
        productService.save(product_ex1);
        productService.addImageToProduct(product_ex1.getId(), "/static/assets/images/public/images_example_products/fertilizer.jpg");

        Product product_ex2 = new Product("Maceta", "Diseño moderno y perfecto para tus plantas.", 5.99, true);
        productService.save(product_ex2);
        productService.addImageToProduct(product_ex2.getId(), "/static/assets/images/public/images_example_products/flowerpot.jpg");

        Product product_ex3 = new Product("Insecticida Natural", "Protege tus plantas sin químicos dañinos.", 11.99, true);
        productService.save(product_ex3);
        productService.addImageToProduct(product_ex3.getId(), "/static/assets/images/public/images_example_products/naturalInsecticide.jpg");

        Product product_ex4 = new Product("Semillas", "Variedad de semillas para tu huerto casero.", 1.99, true);
        productService.save(product_ex4);
        productService.addImageToProduct(product_ex4.getId(), "/static/assets/images/public/images_example_products/seeds.jpg");

        Product product_ex5 = new Product("Guantes", "Cómodos y resistentes para trabajar tu jardín", 4.99, true);
        productService.save(product_ex5);
        productService.addImageToProduct(product_ex5.getId(), "/static/assets/images/public/images_example_products/gloves.jpg");

        Product product_ex6 = new Product("Regadera", "Liviana y práctica para el riego diario.", 8.99, true);
        productService.save(product_ex6);
        productService.addImageToProduct(product_ex6.getId(), "/static/assets/images/public/images_example_products/wateringCan.jpg");

        Product product_ex7 = new Product("Abono", "Mantén tus plantas saludables naturalmente.", 13.99, true);
        productService.save(product_ex7);
        productService.addImageToProduct(product_ex7.getId(), "/static/assets/images/public/images_example_products/manure.jpg");

        Product product_ex8 = new Product("Sustrato Universal", "Ideal para todo tipo de plantas de interior y exterior", 7.99, true);
        productService.save(product_ex8);
        productService.addImageToProduct(product_ex8.getId(), "/static/assets/images/public/images_example_products/universalSubstrate.jpg");

        Product product_ex9 = new Product("Tierra Compostada", "Perfecta para macetas y jardineras decorativas.", 9.99, true);
        productService.save(product_ex9);
        productService.addImageToProduct(product_ex9.getId(), "/static/assets/images/public/images_example_products/compostedLand.jpg");
    }

    private void addExamplePlants() throws Exception {
        for (int i = 0; i < 5; i++) {
            Plant plant = new Plant("Pepito", "Rosa-" + i, "Regar cada 5 días", "Necesita abono x y la tengo mucho cariño mimimimimimimj", false);
            plantService.save(plant);
            plantService.addImageToPlant(plant.getId(), "/static/assets/images/public/images_example_plants/rosebush.jpg");

        }
        for (int i = 0; i < 5; i++) {
            Plant plant = new Plant("Pepito", "Rosa2-" + i, "Regar cada 5 días", "Necesita abono x", false);
            plantService.save(plant);
            plantService.addImageToPlant(plant.getId(), "/static/assets/images/public/images_example_plants/rosebush.jpg");

        }
        Plant plant_ex1 = new Plant("anonimo", "Plantita ejemplo 1: Suculenta", "Regar cada 5 días",
                "Plantita que no requiere de mucha atención parecida al cactus.", true);
        plantService.save(plant_ex1);
        plantService.addImageToPlant(plant_ex1.getId(), "/static/assets/images/public/images_example_plants/succulent.webp");
        plantService.addImageToPlant(plant_ex1.getId(), "/static/assets/images/public/images_example_plants/succulent2.jpg");

        Plant plant_ex2 = new Plant("anonimo", "Plantita ejemplo 2: Rosal", "Regar cada 5 días",
                "Plantita que no requiere de mucha atención parecida al cactus.", true);
        plantService.save(plant_ex2);
        plantService.addImageToPlant(plant_ex2.getId(), "/static/assets/images/public/images_example_plants/rosebush.jpg");
    }

    private void addExampleUsers() throws Exception {
        User userEx1 = new User("hola@gmail.com",passwordEncoder.encode("hola"), "Holi", "Hola", "USER");
        User userEx2 = new User("pepe@gmail.com",passwordEncoder.encode("pepe"), "Pepe", "Soy Pepe", "USER");
        User userEx3 = new User("manolito@gmail.com",passwordEncoder.encode("manolito"), "Manolito", "Soy Manolito", "USER");
        User admin = new User("admin@gmail.com",passwordEncoder.encode("admin"), "Admin", "Soy Admin", "ADMIN");
        userService.saveUser(userEx1);
        userService.saveUser(userEx2);
        userService.saveUser(userEx3);
        userService.saveUser(admin);
    }
    @PostConstruct
    public void init() throws Exception {

        this.addExamplePlants();
        this.addExampleProducts();
        this.addExampleUsers();
    }
}

