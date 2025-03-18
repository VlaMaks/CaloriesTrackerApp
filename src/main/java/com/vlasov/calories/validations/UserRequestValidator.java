package com.vlasov.calories.validations;

import com.vlasov.calories.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Qualifier("userRequestValidator")
public class UserRequestValidator implements RequestValidator<User> {
    private final List<RequestValidations<User>> requestValidations;

    public List<ValidationError> validate(User request) {
        return requestValidations.stream()
                .map(validation -> validation.validate(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
