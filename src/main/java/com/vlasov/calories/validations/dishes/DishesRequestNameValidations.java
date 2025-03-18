package com.vlasov.calories.validations.dishes;

import com.vlasov.calories.entities.Dish;
import com.vlasov.calories.validations.RequestValidations;
import com.vlasov.calories.validations.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DishesRequestNameValidations implements RequestValidations<Dish> {
    @Override
    public Optional<ValidationError> validate(Dish dish) {
        return (dish.getName() == null || dish.getName().isBlank())
                ? Optional.of(new ValidationError("Некорректное название блюда"))
                : Optional.empty();
    }
}
