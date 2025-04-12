package ru.jeka.habit.tracker.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.jeka.habit.tracker.model.AppUser;
import ru.jeka.habit.tracker.repository.UserRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Получаем пользователя из базы данных
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);

        // Проверяем, если пользователя нет
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        AppUser appUser = optionalUser.get();

        // Создаем объект UserDetails для Spring Security
        return org.springframework.security.core.userdetails.User.withUsername(appUser.getUsername())
                .password(appUser.getPassword()) // Зашифрованный пароль
                .roles(appUser.getRole().name()) // Роль из базы данных
                .build();
    }
}
