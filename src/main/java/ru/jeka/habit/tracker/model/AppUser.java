package ru.jeka.habit.tracker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.jeka.habit.tracker.enums.Role;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "\"user\"")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String email;
    private String password;

    @Getter
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Habit> habits;

    public void addHabit(Habit habit) {
        habits.add(habit);
        habit.setUser(this);
    }
}
