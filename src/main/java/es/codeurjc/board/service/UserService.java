package es.codeurjc.board.service;


import es.codeurjc.board.model.Plant;
import es.codeurjc.board.model.User;
import es.codeurjc.board.repositories.PlantRepository;
import es.codeurjc.board.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlantRepository plantService;

    public void saveUser (User user){
        userRepository.save(user);
    }

    public boolean usernameExist(String username) {
        return userRepository.existsUsernamesByUsername(username);
    }

    public boolean seeIfUserIsLoggedIn(HttpServletRequest request){
        return request.getUserPrincipal() != null;
    }

    public boolean isUserUser(HttpServletRequest request){
        return request.isUserInRole("USER");
    }

    public boolean isUserAdmin(HttpServletRequest request){
        return request.isUserInRole("ADMIN");
    }

    public User getUser(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findByUsername(principal.getName())
                .or(() -> userRepository.findByEmail(principal.getName()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;

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

}
