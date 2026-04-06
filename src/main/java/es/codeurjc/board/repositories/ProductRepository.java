package es.codeurjc.board.repositories;

import es.codeurjc.board.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable page);
    Page<Product> findByExampleEquals(boolean example, Pageable page);

    Page<Product> findByActiveTrue(Pageable page);
    Page<Product> findByActiveTrueAndExampleEquals(boolean example, Pageable page);

    Optional<Product> findById(long id);
    Optional<Product> findByName(String name);
}
