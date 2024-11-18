package tech_5dhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech_5dhub.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User save(Optional<User> user);

    Optional<User> findByEmail(String email);

    User getByEmail(String email);
}
