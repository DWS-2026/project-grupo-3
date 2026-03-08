package es.codeurjc.board.service;


import es.codeurjc.board.model.Username;
import es.codeurjc.board.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser (Username user){
        userRepository.save(user);
    }

    public boolean usernameExist(String username) {
        return userRepository.existsUsernamesByUsername(username);
    }

    public boolean seeIfUserIsLoggedIn(HttpServletRequest request){
        return request.getUserPrincipal() != null;
    }

    public boolean isUserAdmin(HttpServletRequest request){
        return request.isUserInRole("ADMIN");
    }
}
