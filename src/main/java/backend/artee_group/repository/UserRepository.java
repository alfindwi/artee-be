package backend.artee_group.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.artee_group.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
