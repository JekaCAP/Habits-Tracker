package ru.jeka.habit.tracker.Service;

import jakarta.transaction.Transactional;
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

    @Transactional
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

    @Transactional
    public Habit addHabit(Habit habit) {
        if (habit == null || habit.getName() == null || habit.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Имя привычки не может быть пустым");
        }

        // Проверка на уникальность
        if (habitRepository.existsByName(habit.getName())) {
            throw new IllegalArgumentException("Привычка с таким именем уже существует");
        }

        return habitRepository.save(habit);
    }

    public void completeHabit(Long id) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Habit not found"));

        habit.incrementCompleted();
        habitRepository.save(habit);
    }

    @Transactional
    public void completedSubHabit(Long habitId, Long subHabitId) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new IllegalArgumentException("Habit not found"));

        SubHabit subHabit = subHabitRepository.findById(subHabitId)
                .orElseThrow(() -> new IllegalArgumentException("SubHabit not found"));

        subHabit.incrementCompleted();
        subHabitRepository.save(subHabit);  // Обновление подпривычки
    }

    @Transactional
    public void deleteHabit(Long id) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Habit not found"));

        habitRepository.delete(habit);  // Удаляем привычку из базы данных
    }

    public Habit getHabitById(Long id) {
        return habitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found"));
    }
}

