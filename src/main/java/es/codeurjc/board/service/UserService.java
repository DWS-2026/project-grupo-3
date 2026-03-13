package es.codeurjc.board.service;


import es.codeurjc.board.model.User;
import es.codeurjc.board.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser (User user){
        userRepository.save(user);
    }

    public boolean usernameExist(String username) {
        return userRepository.existsByUsername(username);
    }

    public Username findByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
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
}
