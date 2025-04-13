package ru.jeka.habit.tracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class SubHabit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int completedCount;

    @ManyToOne
    @JoinColumn(name = "habit_id") // Связь с родительской привычкой
    @JsonBackReference
    private Habit habit;

    public void incrementCompleted() {
        completedCount++;
    }

    public AppUser getUser() {
        return habit.getUsers();  // Получаем пользователя через привычку
    }
}
