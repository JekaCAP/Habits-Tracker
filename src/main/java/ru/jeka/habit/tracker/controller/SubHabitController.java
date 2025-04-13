package ru.jeka.habit.tracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jeka.habit.tracker.Service.HabitService;
import ru.jeka.habit.tracker.Service.SubHabitService;
import ru.jeka.habit.tracker.model.SubHabit;

import java.util.List;

@RestController
@RequestMapping("/api/sub-habits")
public class SubHabitController {

    private final SubHabitService subHabitService;

    public SubHabitController(SubHabitService subHabitService) {
        this.subHabitService = subHabitService;
    }

    // Получить все подпривычки
    @GetMapping
    public List<SubHabit> getAllSubHabits() {
        return subHabitService.getAllSubHabits();
    }

    // Получить подпривычки по ID основной привычки
    @GetMapping("/habit/{habitId}")
    public List<SubHabit> getSubHabitsByHabitId(@PathVariable Long habitId) {
        return subHabitService.getSubHabitsByHabitId(habitId);
    }

    // Создать подпривычку и связать её с основной привычкой
    @PostMapping("/{habitId}")
    public ResponseEntity<SubHabit> addSubHabit(@PathVariable Long habitId, @RequestBody SubHabit subHabit) {
        SubHabit createdSubHabit = subHabitService.addSubHabit(habitId, subHabit);
        return ResponseEntity.ok(createdSubHabit);
    }

    // Увеличить completedCount подпривычки
    @PutMapping("/{subHabitId}/complete")
    public ResponseEntity<Void> completeSubHabit(@PathVariable Long subHabitId) {
        subHabitService.completeSubHabit(subHabitId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{subHabitId}")
    public ResponseEntity<Void> deleteSubHabit(@PathVariable Long subHabitId) {
        subHabitService.deleteSubHabit(subHabitId);
        return ResponseEntity.ok().build();
    }
}
