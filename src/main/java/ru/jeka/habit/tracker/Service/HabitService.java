package ru.jeka.habit.tracker.Service;

import org.springframework.stereotype.Service;
import ru.jeka.habit.tracker.model.Habit;
import ru.jeka.habit.tracker.repository.HabitRepository;

import java.util.*;

@Service
public class HabitService {

    private final HabitRepository habitRepository;

    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public List<Habit> getAllHabits() {
        return habitRepository.findAll();
    }

    public Habit addHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    public void completeHabit(Long id) {
        Optional<Habit> habitOptional = habitRepository.findById(id);
        if (habitOptional.isPresent()) {
            Habit habit = habitOptional.get();
            habit.incrementCompleted();
            habitRepository.save(habit);
        } else {
            throw new RuntimeException("Habit not found");
        }
    }

    public Habit getHabitById(Long id) {
        return habitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found"));
    }
}
