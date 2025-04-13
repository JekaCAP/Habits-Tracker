package ru.jeka.habit.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.jeka.habit.tracker.Service.UserService;

@Controller
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home() {
        return "home";  // Это вернет шаблон home.html
    }

    @GetMapping("/register")
    public String register() {
        return "register";  // Это будет страница для регистрации
    }

    @GetMapping("/login")
    public String login() {
        return "login";  // Это будет страница для авторизации
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String email, Model model) {
        try {
            // Передаем данные в сервис для регистрации
            userService.registerUser(username, password, email);
            return "redirect:/login";  // После успешной регистрации перенаправляем на страницу логина
        } catch (IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());  // Если ошибка, возвращаем обратно на форму регистрации
            return "register";
        }
    }
}