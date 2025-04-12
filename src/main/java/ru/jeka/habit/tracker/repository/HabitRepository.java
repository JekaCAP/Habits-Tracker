package ru.jeka.habit.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.jeka.habit.tracker.model.Habit;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {
}
