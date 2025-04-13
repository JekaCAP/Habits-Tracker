package ru.jeka.habit.tracker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private int completedCount;

    // Добавляем поле для связи с пользователем
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")  // Связь с таблицей User
    private AppUser users;  // Связь с пользователем

    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubHabit> subHabits;  // Подпривычки

    public void addSubHabit(SubHabit subHabit) {
        subHabits.add(subHabit);
        subHabit.setHabit(this);  // Устанавливаем родительскую привычку для подпривычки
    }

    public void incrementCompleted() {
        this.completedCount++;
    }
}