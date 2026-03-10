package es.codeurjc.board.service;


import es.codeurjc.board.model.Username;
import es.codeurjc.board.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser (Username user){
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
}
