package com.vlasov.calories.validations;

import com.vlasov.calories.entities.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Qualifier("dishRequestValidator")
public class DishRequestValidator implements RequestValidator<Dish> {
    private final List<RequestValidations<Dish>> requestValidations;

    public List<ValidationError> validate(Dish request) {
        return requestValidations.stream()
                .map(validation -> validation.validate(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
