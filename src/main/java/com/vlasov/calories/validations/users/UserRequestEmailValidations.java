package com.vlasov.calories.validations.users;

import com.vlasov.calories.entities.User;
import com.vlasov.calories.validations.RequestValidations;
import com.vlasov.calories.validations.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class UserRequestEmailValidations implements RequestValidations<User> {
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
