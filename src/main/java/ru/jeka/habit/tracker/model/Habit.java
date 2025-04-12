package ru.jeka.habit.tracker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int completedCount;

    // Связь "один ко многим" для подпривычек
    @OneToMany(mappedBy = "parentHabit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Habit> subHabits = new ArrayList<>();

    // Связь "многие к одному" для родительской привычки
    @ManyToOne
    @JoinColumn(name = "parent_habit_id")
    private Habit parentHabit;

    public void incrementCompleted() {
        completedCount++;
    }

    public void addSubHabit(Habit habit) {
        subHabits.add(habit);
        habit.setParentHabit(this);  // Устанавливаем родительскую привычку для подпривычки
    }
}
