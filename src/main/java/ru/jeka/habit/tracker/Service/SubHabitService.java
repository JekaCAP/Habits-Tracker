package ru.jeka.habit.tracker.Service;

import org.springframework.stereotype.Service;
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
    public SubHabit addSubHabit(Long habitId, SubHabit subHabit) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new IllegalArgumentException("Habit not found"));

        subHabit.setHabit(habit);  // Связываем подпривычку с основной привычкой

        return subHabitRepository.save(subHabit);
    }

    // Увеличить completedCount для подпривычки
    public void completeSubHabit(Long subHabitId) {
        SubHabit subHabit = subHabitRepository.findById(subHabitId)
                .orElseThrow(() -> new IllegalArgumentException("SubHabit not found"));

        subHabit.incrementCompleted();
        subHabitRepository.save(subHabit);
    }
}
