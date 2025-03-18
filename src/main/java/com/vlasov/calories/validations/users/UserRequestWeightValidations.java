package com.vlasov.calories.validations.users;

import com.vlasov.calories.entities.User;
import com.vlasov.calories.validations.RequestValidations;
import com.vlasov.calories.validations.ValidationError;
import org.springframework.stereotype.Component;


import java.util.Optional;

@Component
public class UserRequestWeightValidations implements RequestValidations<User> {
    @Override
    public Optional<ValidationError> validate(User user) {
        return (user.getWeight() < 3 || user.getWeight() > 300)
                ? Optional.of(new ValidationError("Некорректный вес"))
                : Optional.empty();
    }
}
