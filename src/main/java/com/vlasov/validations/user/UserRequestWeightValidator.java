package com.vlasov.validations.user;

import com.vlasov.calories.entities.User;
import com.vlasov.validations.RequestValidator;
import com.vlasov.validations.ValidationError;

import java.util.Optional;


public class UserRequestWeightValidator implements RequestValidator<User> {
    @Override
    public Optional<ValidationError> validate(User user) {
        return Optional.empty();
    }
}
