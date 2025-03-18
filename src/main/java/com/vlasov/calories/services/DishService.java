package com.vlasov.calories.services;

import com.vlasov.calories.entities.Dish;
import com.vlasov.calories.entities.User;
import com.vlasov.calories.repositories.DishRepository;
import com.vlasov.calories.validations.RequestValidator;
import com.vlasov.calories.validations.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DishService {

    private final DishRepository dishRepository;
    private final RequestValidator requestValidator;

    public DishService(DishRepository dishRepository, @Qualifier("dishRequestValidator")  RequestValidator requestValidator) {
        this.dishRepository = dishRepository;
        this.requestValidator = requestValidator;
    }


    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public ResponseEntity<Object> createResponse(Dish dish) {
        List<ValidationError> validationErrors = requestValidator.validate(dish);
        if (validationErrors.isEmpty()) {
            return new ResponseEntity<>(dishRepository.save(dish), HttpStatus.CREATED);
        }
        String errorsDescription = validationErrors.stream()
                .map(ValidationError::getMessage)
                .collect(Collectors.joining(", "));

        return new ResponseEntity<>(Map.of("message", errorsDescription), HttpStatus.BAD_REQUEST);

    }
}
