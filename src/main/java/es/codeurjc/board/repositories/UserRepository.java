package es.codeurjc.board.repositories;

import es.codeurjc.board.model.Plant;
import es.codeurjc.board.model.Username;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Username,Long>{
    Optional<Username> findByUsername(String username);

    boolean existsUsernamesByUsername(String username);
}
