package es.codeurjc.board.repositories;

import es.codeurjc.board.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByUsername(String user);
    Optional<User> findByEmail(String email);
    boolean existsUsernamesByUsername(String username);

    boolean existsByUsername(String username);

    String username(String username);

}
