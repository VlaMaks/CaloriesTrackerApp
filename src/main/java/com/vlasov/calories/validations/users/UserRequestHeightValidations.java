package com.vlasov.calories.validations.users;

import com.vlasov.calories.entities.User;
import com.vlasov.calories.validations.RequestValidations;
import com.vlasov.calories.validations.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRequestHeightValidations implements RequestValidations<User> {
    @Override
    public Optional<ValidationError> validate(User user) {
        return (user.getHeight() < 40 || user.getHeight() > 280)
                ? Optional.of(new ValidationError("Некорректный рост"))
                : Optional.empty();
    }
}
