package com.vlasov.calories.controllers;

import com.vlasov.calories.entities.Dish;
import com.vlasov.calories.services.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @PostMapping
    public ResponseEntity<Object> createDish(@RequestBody Dish dish) {
        return dishService.createResponse(dish);
    }

    @GetMapping
    public List<Dish> getAllDishes() {
        return dishService.getAllDishes();
    }
}
