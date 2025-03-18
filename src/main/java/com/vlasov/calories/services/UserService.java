package com.vlasov.calories.services;

import com.vlasov.calories.entities.User;
import com.vlasov.calories.repositories.UserRepository;
import com.vlasov.calories.utils.DailyCaloriesCalculator;
import com.vlasov.calories.validations.RequestValidator;
import com.vlasov.calories.validations.UserRequestValidator;
import com.vlasov.calories.validations.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service

public class UserService {

    private final UserRepository userRepository;
    private final DailyCaloriesCalculator dailyCaloriesCalculator;
    private final RequestValidator userRequestValidator;

    public UserService(UserRepository userRepository,
                       DailyCaloriesCalculator dailyCaloriesCalculator,
                       @Qualifier("userRequestValidator") RequestValidator userRequestValidator) {
        this.userRepository = userRepository;
        this.dailyCaloriesCalculator = dailyCaloriesCalculator;
        this.userRequestValidator = userRequestValidator;
    }

    public User createUser(User user) {
        user.setDailyCalorieIntake(dailyCaloriesCalculator.calculate(user));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<Object> createResponse(User user) {
        List<ValidationError> validationErrors = userRequestValidator.validate(user);
        if (validationErrors.isEmpty()) {
            user.setDailyCalorieIntake(dailyCaloriesCalculator.calculate(user));
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
        }
        String errorsDescription = validationErrors.stream()
                .map(ValidationError::getMessage)
                .collect(Collectors.joining(", "));

        return new ResponseEntity<>(Map.of("message", errorsDescription), HttpStatus.BAD_REQUEST);

    }
}
