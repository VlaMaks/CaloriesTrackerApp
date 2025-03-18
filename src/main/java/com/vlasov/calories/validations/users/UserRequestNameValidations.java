package com.vlasov.calories.validations.users;

import com.vlasov.calories.entities.User;
import com.vlasov.calories.validations.RequestValidations;
import com.vlasov.calories.validations.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRequestNameValidations implements RequestValidations<User> {
    @Override
    public Optional<ValidationError> validate(User user) {
        return (user.getName() == null || user.getName().isBlank())
                ? Optional.of(new ValidationError("Некорректное имя"))
                : Optional.empty();
    }
}
