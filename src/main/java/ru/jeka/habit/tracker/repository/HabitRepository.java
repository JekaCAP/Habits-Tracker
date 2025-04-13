package ru.jeka.habit.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.jeka.habit.tracker.model.Habit;

import java.util.List;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {
    boolean existsByName(String name);
    List<Habit> findByUsersId(Long userId);
}
