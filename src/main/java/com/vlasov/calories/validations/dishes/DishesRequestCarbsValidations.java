package com.vlasov.calories.validations.dishes;

import com.vlasov.calories.entities.Dish;
import com.vlasov.calories.validations.RequestValidations;
import com.vlasov.calories.validations.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DishesRequestCarbsValidations implements RequestValidations<Dish> {
    @Override
    public Optional<ValidationError> validate(Dish dish) {
        return (dish.getCarbs() < 0 || dish.getCarbs() > 300)
                ? Optional.of(new ValidationError("Некорректное количество углеводов"))
                : Optional.empty();
    }
}
