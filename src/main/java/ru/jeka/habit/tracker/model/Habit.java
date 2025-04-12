package ru.jeka.habit.tracker.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SubHabit> subHabits = new ArrayList<>();

    public void addSubHabit(SubHabit subHabit) {
        subHabits.add(subHabit);
        subHabit.setHabit(this);  // Устанавливаем родительскую привычку для подпривычки
    }

    public void incrementCompleted() {
        this.completedCount++;
    }
}