package com.vlasov.calories.services;

import com.vlasov.calories.dto.DailyReport;
import com.vlasov.calories.entities.Dish;
import com.vlasov.calories.entities.Meal;
import com.vlasov.calories.entities.User;
import com.vlasov.calories.repositories.DishRepository;
import com.vlasov.calories.repositories.MealRepository;
import com.vlasov.calories.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;
    private final DishRepository dishRepository;
    private final UserRepository userRepository;

    public ResponseEntity<DailyReport> getDailyReport(Long userId, LocalDate date) {
        DailyReport dailyReport = new DailyReport();

        List<Meal> byUserIdAndDate = mealRepository.findByUserIdAndDate(userId, date);
        List<Dish> listDish = byUserIdAndDate.stream().flatMap(meal -> meal.getDishes().stream()).toList();

        int amountCalories = listDish.stream().mapToInt(Dish::getCalories).sum();
        int mealsAmount = listDish.size();

        dailyReport.setAmountCalories(amountCalories);
        dailyReport.setDate(date);
        dailyReport.setMealsAmount(mealsAmount);
        dailyReport.setUserId(String.valueOf(userId));

        return new ResponseEntity<>(dailyReport, HttpStatus.OK);
    }


    public boolean checkCalorieLimit(Long userId, LocalDate date, double dailyCalorieIntake) {
        List<Meal> meals = mealRepository.findByUserIdAndDate(userId, date);
        int totalCalories = meals.stream().flatMap(meal -> meal.getDishes().stream()).mapToInt(Dish::getCalories).sum();
        return totalCalories <= dailyCalorieIntake;
    }

    public Map<LocalDate, List<Meal>> getMealHistory(Long userId) {
        List<Meal> byUserIdOrderByDateDesc = mealRepository.findByUserIdOrderByDateDesc(userId);
        return byUserIdOrderByDateDesc.stream().collect(Collectors.groupingBy(Meal::getDate));
    }

    public ResponseEntity<Object> createResponse(Meal meal) {
        Long userId = meal.getUser().getId();

        Optional<User> userOpt = userRepository.findById(userId);
        String message = "";
        if (userOpt.isEmpty()) {
            message = "Пользователь с id %d не найден".formatted(userId);
        }

        List<Long> listDishesId = meal.getDishes().stream().map(Dish::getId).toList();
        List<Dish> dishesFromBase = dishRepository.findAllById(listDishesId);

        if (dishesFromBase.size() != listDishesId.size()) {
            message += "Некоторые блюда не найдена в базе. Проверьте id";
        }

        if (!"".equals(message)) {
            return new ResponseEntity<>(Map.of("message", message), HttpStatus.BAD_REQUEST);
        }

        meal.setDishes(dishesFromBase);
        meal.setUser(userOpt.get());

        return new ResponseEntity<>(mealRepository.save(meal), HttpStatus.CREATED);
    }
}
