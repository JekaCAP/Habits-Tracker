package ru.jeka.habit.tracker.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.jeka.habit.tracker.enums.Role;
import ru.jeka.habit.tracker.model.AppUser;
import ru.jeka.habit.tracker.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    public AppUser findByUsername(String username) {
        Optional<AppUser> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> new RuntimeException("User not found"));
    }

    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public AppUser registerUser(String username, String password, String email) {
        // Проверка на существование пользователя с таким же username
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already taken");
        }

        // Проверка на существование пользователя с таким же email
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        // Создание нового пользователя
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);

        user.setRole(Role.USER);  // Роль по умолчанию

        // Сохранение пользователя в базе данных
        return userRepository.save(user);
    }
}
