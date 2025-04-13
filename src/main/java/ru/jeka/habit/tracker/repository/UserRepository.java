package ru.jeka.habit.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jeka.habit.tracker.model.AppUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByUsername(String username);

}
