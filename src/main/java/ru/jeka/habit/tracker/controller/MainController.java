package ru.jeka.habit.tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

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
}