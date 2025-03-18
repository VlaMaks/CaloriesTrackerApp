package com.vlasov.calories.validations.dishes;

import com.vlasov.calories.entities.Dish;
import com.vlasov.calories.validations.RequestValidations;
import com.vlasov.calories.validations.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DishesRequestCaloriesValidations implements RequestValidations<Dish> {
    @Override
    public Optional<ValidationError> validate(Dish dish) {
        return (dish.getCalories() <= 0 || dish.getCalories() > 2000)
                ? Optional.of(new ValidationError("Некорректное количество калорий"))
                : Optional.empty();
    }
}
