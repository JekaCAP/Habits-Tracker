package ru.jeka.habit.tracker.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.jeka.habit.tracker.Service.HabitService;
import ru.jeka.habit.tracker.Service.UserService;
import ru.jeka.habit.tracker.model.AppUser;
import ru.jeka.habit.tracker.model.Habit;

@Controller
public class AccountController {

    private final UserService userService;
    private final HabitService habitService;

    public AccountController(UserService userService, HabitService habitService) {
        this.userService = userService;
        this.habitService = habitService;
    }

    @GetMapping("/account")
    public String account(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // Получаем имя текущего пользователя
        AppUser user = userService.findByUsername(username);  // Забираем пользователя по имени

        model.addAttribute("username", user.getUsername());
        model.addAttribute("habits", user.getHabits());  // Передаем привычки пользователя

        return "account";  // Страница аккаунта
    }

    @PostMapping("/account/habits")
    public String addHabitFromForm(@RequestParam("habitName") String habitName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        AppUser user = userService.findByUsername(username);

        Habit habit = new Habit();
        habit.setName(habitName);
        habit.setUsers(user);  // Обязательно!
        habit.setCompletedCount(0);  // Безопасно

        habitService.addHabit(habit);

        return "redirect:/account";
    }
}
