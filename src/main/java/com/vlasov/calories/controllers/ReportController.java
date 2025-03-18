package com.vlasov.calories.controllers;

import com.vlasov.calories.dto.DailyReport;
import com.vlasov.calories.entities.Meal;
import com.vlasov.calories.entities.User;
import com.vlasov.calories.repositories.UserRepository;
import com.vlasov.calories.services.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final MealService mealService;
    private final UserRepository userRepository;

    @GetMapping("/daily/{userId}")
    public HttpEntity<DailyReport> getDailyReport(@PathVariable Long userId, @RequestParam("date") LocalDate date) {
        return mealService.getDailyReport(userId, date);
    }

    @GetMapping("/calories-check/{userId}")
    public boolean checkCalorieLimit(@PathVariable Long userId, @RequestParam LocalDate date) {
        User user = userRepository.findById(userId).orElseThrow();
        return mealService.checkCalorieLimit(userId, date, user.getDailyCalorieIntake());
    }

    @GetMapping("/history/{userId}")
    public Map<LocalDate, List<Meal>> getMealHistory(@PathVariable Long userId) {
        return mealService.getMealHistory(userId);
    }
}

