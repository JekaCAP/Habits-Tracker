package ru.jeka.habit.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.jeka.habit.tracker.model.SubHabit;

import java.util.List;

@Repository
public interface SubHabitRepository extends JpaRepository<SubHabit, Long> {
    List<SubHabit> findByName(String name);
    List<SubHabit> findByHabitId(Long habitId); // Метод для поиска подпривычек по ID основной привычки
}
