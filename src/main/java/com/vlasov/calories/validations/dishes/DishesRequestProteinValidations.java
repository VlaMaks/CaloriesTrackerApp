package com.vlasov.calories.validations.dishes;

import com.vlasov.calories.entities.Dish;
import com.vlasov.calories.validations.RequestValidations;
import com.vlasov.calories.validations.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DishesRequestProteinValidations implements RequestValidations<Dish> {
    @Override
    public Optional<ValidationError> validate(Dish dish) {
        return (dish.getProtein() < 0 || dish.getProtein() > 300)
                ? Optional.of(new ValidationError("Некорректное количество белка"))
                : Optional.empty();
    }
}
