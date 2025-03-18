package com.vlasov.calories.validations.users;

import com.vlasov.calories.entities.User;
import com.vlasov.calories.validations.RequestValidations;
import com.vlasov.calories.validations.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRequestAgeValidations implements RequestValidations<User> {
    @Override
    public Optional<ValidationError> validate(User user) {
        return (user.getAge() <= 0 || user.getAge() > 120)
                ? Optional.of(new ValidationError("Некорректный возраст"))
                : Optional.empty();
    }
}
