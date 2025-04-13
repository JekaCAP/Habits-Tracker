package ru.jeka.habit.tracker.Service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import ru.jeka.habit.tracker.model.Habit;
import ru.jeka.habit.tracker.model.SubHabit;
import ru.jeka.habit.tracker.repository.HabitRepository;
import ru.jeka.habit.tracker.repository.SubHabitRepository;

import java.util.List;

@Service
public class SubHabitService {

    private final SubHabitRepository subHabitRepository;
    private final HabitRepository habitRepository;

    public SubHabitService(SubHabitRepository subHabitRepository, HabitRepository habitRepository) {
        this.subHabitRepository = subHabitRepository;
        this.habitRepository = habitRepository;
    }

    // Получить все подпривычки
    public List<SubHabit> getAllSubHabits() {
        return subHabitRepository.findAll();
    }

    // Получить подпривычки по ID основной привычки
    public List<SubHabit> getSubHabitsByHabitId(Long habitId) {
        return subHabitRepository.findByHabitId(habitId);
    }

    // Добавить подпривычку и связать с основной привычкой
    @Transactional
    public SubHabit addSubHabit(Long habitId, SubHabit subHabit) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new IllegalArgumentException("Habit not found"));

        subHabit.setHabit(habit);  // Связываем подпривычку с основной привычкой

        return subHabitRepository.save(subHabit);  // Сохраняем подпривычку
    }

    // Увеличить completedCount для подпривычки
    @Transactional
    public void completeSubHabit(Long subHabitId) {
        SubHabit subHabit = subHabitRepository.findById(subHabitId)
                .orElseThrow(() -> new IllegalArgumentException("SubHabit not found"));

        subHabit.incrementCompleted();
        subHabitRepository.save(subHabit);  // Сохраняем обновленную подпривычку
    }

    // Удалить подпривычку
    @Transactional
    public void deleteSubHabit(Long subHabitId) {
        SubHabit subHabit = subHabitRepository.findById(subHabitId)
                .orElseThrow(() -> new IllegalArgumentException("SubHabit not found"));

        subHabitRepository.delete(subHabit);  // Удаляем подпривычку
    }
}