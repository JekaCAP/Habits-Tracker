package ru.jeka.habit.tracker.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.jeka.habit.tracker.Service.HabitService;
import ru.jeka.habit.tracker.Service.UserService;
import ru.jeka.habit.tracker.model.Habit;
import ru.jeka.habit.tracker.model.AppUser;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final HabitService habitService;

    // Конструктор для внедрения зависимости UserService
    public UserController(UserService userService, HabitService habitService) {
        this.userService = userService;
        this.habitService = habitService;
    }

    // Получение всех привычек текущего пользователя
    @GetMapping("/habits")
    public List<Habit> getUserHabits(@AuthenticationPrincipal AppUser user) {
        return user.getHabits();
    }

    // Создание новой привычки для текущего пользователя
    @PostMapping("/habits")
    public Habit addHabit(@RequestBody Habit habit, @AuthenticationPrincipal AppUser user) {
        habit.setUser(user);  // Связываем привычку с текущим пользователем
        return habitService.addHabit(habit);
    }
}
