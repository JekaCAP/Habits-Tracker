package ru.jeka.habit.tracker.Service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.jeka.habit.tracker.repository.HabitRepository;
import ru.jeka.habit.tracker.repository.SubHabitRepository;
import ru.jeka.habit.tracker.repository.UserRepository;

@Service
public class DataResetService {

    private final UserRepository userRepository;
    private final HabitRepository habitRepository;
    private final SubHabitRepository subHabitRepository;

    public DataResetService(UserRepository userRepository, HabitRepository habitRepository, SubHabitRepository subHabitRepository) {
        this.userRepository = userRepository;
        this.habitRepository = habitRepository;
        this.subHabitRepository = subHabitRepository;
    }

    @Transactional
    public void resetData() {
        subHabitRepository.deleteAll();
        habitRepository.deleteAll();
        userRepository.deleteAll();
    }
}
