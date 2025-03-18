package com.vlasov.calories.services;

import com.vlasov.calories.entities.Dish;
import com.vlasov.calories.entities.Meal;
import com.vlasov.calories.repositories.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;

    public List<Meal> getDailyReport(Long userId, LocalDate date) {
        return mealRepository.findByUserIdAndDate(userId, date);
    }

    public Meal createMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public boolean checkCalorieLimit(Long userId, LocalDate date, double dailyCalorieIntake) {
        List<Meal> meals = mealRepository.findByUserIdAndDate(userId, date);
        int totalCalories = meals.stream().flatMap(meal -> meal.getDishes().stream()).mapToInt(Dish::getCalories).sum();
        return totalCalories <= dailyCalorieIntake;
    }

    public List<Meal> getMealHistory(Long userId) {
        return mealRepository.findByUserIdOrderByDateDesc(userId);
    }
}
