package ru.jeka.habit.tracker.Service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.jeka.habit.tracker.model.Habit;
import ru.jeka.habit.tracker.model.SubHabit;
import ru.jeka.habit.tracker.repository.HabitRepository;
import ru.jeka.habit.tracker.repository.SubHabitRepository;

import java.util.*;

@Service
public class HabitService {

    private final HabitRepository habitRepository;
    private final SubHabitRepository subHabitRepository;

    public HabitService(HabitRepository habitRepository, SubHabitRepository subHabitRepository) {
        this.habitRepository = habitRepository;
        this.subHabitRepository = subHabitRepository;
    }

    public SubHabit addSubHabit(Long habitId, SubHabit subHabit) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new IllegalArgumentException("Habit not found"));

        habit.addSubHabit(subHabit);

        subHabitRepository.save(subHabit);

        habitRepository.save(habit);

        return subHabit;
    }

    public List<Habit> getAllHabits() {
        return habitRepository.findAll();
    }

    public Habit addHabit(Habit habit) {
        // Проверка на уникальность имени привычки
        if (habitRepository.existsByName(habit.getName())) {
            throw new IllegalArgumentException("Привычка с таким именем уже существует");
        }
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

    public void completedSubHabit(Long habitId, Long subHabitId) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new IllegalArgumentException("Habit not found"));

        SubHabit subHabit = subHabitRepository.findById(subHabitId)
                .orElseThrow(() -> new IllegalArgumentException("SubHabit not found"));

        subHabit.incrementCompleted();
        subHabitRepository.save(subHabit);
    }

    public void deleteHabit(Long id) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Habit not found"));
        habitRepository.delete(habit);
    }

    public Habit getHabitById(Long id) {
        return habitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found"));
    }
}

