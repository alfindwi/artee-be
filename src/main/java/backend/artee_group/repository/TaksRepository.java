package backend.artee_group.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.artee_group.entity.Task;

public interface TaksRepository extends JpaRepository<Task, Long> {
}
