package es.codeurjc.board.service;

import es.codeurjc.board.model.*;
import es.codeurjc.board.repositories.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
public class SampleDataService  implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private PlantService plantService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ReviewsService reviewsService;

    private void addExampleProducts() throws Exception {
        Product product_ex1 = new Product("Fertilizante", "Nutre tus plantas de manera rápida y efectiva.", 10.99, true );
        productService.save(product_ex1);
        productService.addImageToProduct(product_ex1.getId(), "/static/assets/images/images_example_products/fertilizer.jpg");

        Product product_ex2 = new Product("Maceta", "Diseño moderno y perfecto para tus plantas.", 5.99, true);
        productService.save(product_ex2);
        productService.addImageToProduct(product_ex2.getId(), "/static/assets/images/images_example_products/flowerpot.jpg");

        Product product_ex3 = new Product("Insecticida Natural", "Protege tus plantas sin químicos dañinos.", 11.99, true);
        productService.save(product_ex3);
        productService.addImageToProduct(product_ex3.getId(), "/static/assets/images/images_example_products/naturalInsecticide.jpg");

        Product product_ex4 = new Product("Semillas", "Variedad de semillas para tu huerto casero.", 1.99, true);
        productService.save(product_ex4);
        productService.addImageToProduct(product_ex4.getId(), "/static/assets/images/images_example_products/seeds.jpg");

        Product product_ex5 = new Product("Guantes", "Cómodos y resistentes para trabajar tu jardín", 4.99, true);
        productService.save(product_ex5);
        productService.addImageToProduct(product_ex5.getId(), "/static/assets/images/images_example_products/gloves.jpg");

        Product product_ex6 = new Product("Regadera", "Liviana y práctica para el riego diario.", 8.99, true);
        productService.save(product_ex6);
        productService.addImageToProduct(product_ex6.getId(), "/static/assets/images/images_example_products/wateringCan.jpg");

        Product product_ex7 = new Product("Abono", "Mantén tus plantas saludables naturalmente.", 13.99, true);
        productService.save(product_ex7);
        productService.addImageToProduct(product_ex7.getId(), "/static/assets/images/images_example_products/manure.jpg");

        Product product_ex8 = new Product("Sustrato Universal", "Ideal para todo tipo de plantas de interior y exterior", 7.99, true);
        productService.save(product_ex8);
        productService.addImageToProduct(product_ex8.getId(), "/static/assets/images/images_example_products/universalSubstrate.jpg");

        Product product_ex9 = new Product("Tierra Compostada", "Perfecta para macetas y jardineras decorativas.", 9.99, true);
        productService.save(product_ex9);
        productService.addImageToProduct(product_ex9.getId(), "/static/assets/images/images_example_products/compostedLand.jpg");
    }

    private void addExamplePlants() throws Exception {
        for (int i = 0; i < 5; i++) {
            Plant plant = new Plant( "Rosa de Pepe-" + i, "Regar cada 5 días", "Necesita abono x y la tengo mucho cariño mimimimimimimj");
            plantService.save(plant,userService.getUser("Pepe"));
            plantService.addImageToPlant(plant.getId(), "/static/assets/images/images_example_plants/rosebush.jpg");

        }
        for (int i = 0; i < 5; i++) {
            Plant plant = new Plant("Margarita de manolito-" + i, "Regar cada 5 días", "Necesita abono x");
            plantService.save(plant,userService.getUser("Manolito"));
            plantService.addImageToPlant(plant.getId(), "/static/assets/images/images_example_plants/rosebush.jpg");

        }
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

    private void addExampleOrders() throws Exception {
        User userEx1 = userService.getUser("hola@gmail.com");
        User userEx2 = userService.getUser("pepe@gmail.com");

        Product fertilizante = productService.getProductByName("Fertilizante");
        Product maceta = productService.getProductByName("Maceta");
        Product regadera = productService.getProductByName("Regadera");
        Product semillas = productService.getProductByName("Semillas");

        List<OrderItems> items1 = new ArrayList<>();
        items1.add(new OrderItems(fertilizante, 2));
        items1.add(new OrderItems(maceta, 1));
        Order order1 = new Order(userEx1, items1);
        order1.setOrderDate(LocalDateTime.now().minusDays(2));
        order1.setStatus(OrderStatus.PENDING);
        orderService.save(order1);

        List<OrderItems> items2 = new ArrayList<>();
        items2.add(new OrderItems(regadera, 1));
        items2.add(new OrderItems(semillas, 5));
        Order order2 = new Order(userEx2, items2);
        order2.setOrderDate(LocalDateTime.now().minusDays(1));
        order2.setStatus(OrderStatus.SHIPPED);
        orderService.save(order2);

    }

    private void addExampleReviews() throws Exception {

        Reviews r1 = new Reviews(
                "Pepe",
                "Muy buena planta",
                "Me ha encantado, crece súper rápido 🌱",
                Reviews.ReviewType.PLANT
        );

        Reviews r2 = new Reviews(
                "Manolito",
                "Producto útil",
                "El fertilizante funciona genial",
                Reviews.ReviewType.PRODUCT
        );

        Reviews r3 = new Reviews(
                "Holi",
                "No me gustó",
                "Esperaba mejores resultados",
                Reviews.ReviewType.PRODUCT
        );

        Reviews r4 = new Reviews(
                "Pepe",
                "Recomendadísima",
                "Muy fácil de cuidar",
                Reviews.ReviewType.PLANT
        );

        reviewsService.save(r1);
        reviewsService.save(r2);
        reviewsService.save(r3);
        reviewsService.save(r4);
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            if(userService.numberOfUsers() == 0){
                addExampleUsers();
            }
            if(plantService.numberOfPlants() == 0){
                addExamplePlants();
            }
            if(productService.numberOfProducts() == 0){
                addExampleProducts();
            }
            if(orderService.numberOfOrders() == 0){
                addExampleOrders();
            }
            if(reviewsService.numberOfOrders() == 0){
                addExampleReviews();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}