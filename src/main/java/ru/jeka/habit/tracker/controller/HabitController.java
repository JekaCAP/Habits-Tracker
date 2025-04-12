package ru.jeka.habit.tracker.controller;

import org.springframework.web.bind.annotation.*;
import ru.jeka.habit.tracker.Service.HabitService;
import ru.jeka.habit.tracker.model.Habit;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
public class HabitController {

    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @GetMapping
    public List<Habit> getAllHabits() {
        return habitService.getAllHabits();
    }

    @PostMapping
    public Habit addHabit(@RequestBody Habit habit) {
        return habitService.addHabit(habit);
    }

    @PutMapping("/{id}/complete")
    public void markHabitCompleted(@PathVariable long id) {
        habitService.completeHabit(id);
    }

    @DeleteMapping("/{id}")
    public void deleteHabit(@PathVariable long id) {
        habitService.deleteHabit(id);
    }
}
