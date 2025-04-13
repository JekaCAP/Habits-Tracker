//package ru.jeka.habit.tracker.bootstrap;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import ru.jeka.habit.tracker.model.AppUser;
//import ru.jeka.habit.tracker.enums.Role;
//import ru.jeka.habit.tracker.repository.UserRepository;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        if (userRepository.findByUsername("admin") == null) {
//            AppUser admin = new AppUser();
//            admin.setUsername("admin");
//            admin.setPassword(passwordEncoder.encode("adminpassword")); // Пароль для админа
//            admin.setRole(Role.ADMIN); // Используем перечисление Role
//            userRepository.save(admin);
//        }
//    }
//}
