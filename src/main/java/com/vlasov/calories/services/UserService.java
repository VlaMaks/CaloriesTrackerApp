package com.vlasov.calories.services;

import com.vlasov.calories.entities.User;
import com.vlasov.calories.repositories.UserRepository;
import com.vlasov.calories.utils.DailyCaloriesCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final DailyCaloriesCalculator dailyCaloriesCalculator;

    public User createUser(User user) {
        user.setDailyCalorieIntake(dailyCaloriesCalculator.calculate(user));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
