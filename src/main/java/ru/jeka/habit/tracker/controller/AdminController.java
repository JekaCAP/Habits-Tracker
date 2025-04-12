package ru.jeka.habit.tracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jeka.habit.tracker.Service.HabitService;
import ru.jeka.habit.tracker.Service.UserService;
import ru.jeka.habit.tracker.model.Habit;
import ru.jeka.habit.tracker.model.AppUser;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final HabitService habitService;

    public AdminController(UserService userService, HabitService habitService) {
        this.userService = userService;
        this.habitService = habitService;
    }

    // Получение всех пользователей и их привычек (для админа)
    @GetMapping("/users")
    public List<AppUser> getAllUsers() {
        return userService.getAllUsers();
    }

    // Получение всех привычек всех пользователей
    @GetMapping("/habits")
    public List<Habit> getAllHabits() {
        return habitService.getAllHabits();
    }
}
