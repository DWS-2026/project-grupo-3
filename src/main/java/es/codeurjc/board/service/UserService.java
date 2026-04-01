package es.codeurjc.board.service;


import es.codeurjc.board.model.Image;
import es.codeurjc.board.model.Order;
import es.codeurjc.board.model.Plant;
import es.codeurjc.board.model.User;
import es.codeurjc.board.repositories.OrderRepository;
import es.codeurjc.board.repositories.PlantRepository;
import es.codeurjc.board.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlantRepository plantService;
    @Autowired
    private OrderRepository orderRepository;


    public List<User> findAll(){
        Sort sort = Sort.by(Sort.Direction.ASC, "username");
        return userRepository.findAll(sort);
    }


    public void saveUser (User user){
        userRepository.save(user);
    }

    public boolean userExist(String user) {
        return userRepository.existsByUsername(user);
    }

    public User findByUser(String user){
        return userRepository.findByUsername(user).orElse(null);
    }

    @Transactional
    public void addImageToUser(User user, Image image) {
        user.setProfilePhoto(image);
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        // Primero obtenemos el usuario
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            // Recorremos todos los pedidos del usuario y los eliminamos
            List<Order> orders = orderRepository.findByUserUsername(user.getUsername());
            for (Order order : orders) {
                orderRepository.delete(order);
            }

            // Ahora eliminamos el usuario
            userRepository.deleteById(id);
        }
    }

    public boolean seeIfUserIsLoggedIn(HttpServletRequest request){
        return request.getUserPrincipal() != null;
    }

    public boolean isUserUser(HttpServletRequest request){
        if(this.seeIfUserIsLoggedIn(request)){
        return request.isUserInRole("USER");
        }
        return false;
    }

    public boolean isUserAdmin(HttpServletRequest request){
        if(this.seeIfUserIsLoggedIn(request)){
            return request.isUserInRole("ADMIN");
        }
        return false;

    }

    public User getUser(HttpServletRequest request){
        if(this.seeIfUserIsLoggedIn(request)){
            Principal principal = request.getUserPrincipal();

            User user = userRepository.findByUsername(principal.getName())
                    .or(() -> userRepository.findByEmail(principal.getName()))
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return user;
        }
        return null;

    }

    public User getUser(String  username){
        User user = userRepository.findByUsername(username)
                .or(() -> userRepository.findByEmail(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;

    }

    public long getUserID(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findByUsername(principal.getName())
                .or(() -> userRepository.findByEmail(principal.getName()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.getId();

    }

    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean usernameExists(String username){
        return userRepository.existsByUsername(username);
    }

    public boolean emailExists(String email){
        return userRepository.existsByEmail(email);
    }

    public long numberOfUsers(){
        return userRepository.count();
    }

}
