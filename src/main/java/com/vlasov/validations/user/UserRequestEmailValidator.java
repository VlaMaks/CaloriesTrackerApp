package com.vlasov.validations.user;

import com.vlasov.calories.entities.User;
import com.vlasov.validations.RequestValidator;
import com.vlasov.validations.ValidationError;

import java.util.Optional;
import java.util.regex.Pattern;


public class UserRequestEmailValidator implements RequestValidator<User> {
    @Override
    public Optional<ValidationError> validate(User user) {
        String email = user.getEmail();
        return (email == null || email.isBlank() ||
                !correctEmail(email))
                ? Optional.of(new ValidationError("Некорректный email"))
                : Optional.empty();
    }

    private boolean correctEmail(String email) {
        Pattern reg = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        return reg.matcher(email).matches();

    }
}
