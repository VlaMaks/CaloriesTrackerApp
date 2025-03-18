package com.vlasov.calories.utils;

import com.vlasov.calories.entities.CalorieControlGoal;
import com.vlasov.calories.entities.Sex;
import com.vlasov.calories.entities.User;
import org.springframework.stereotype.Component;

@Component
public class HarrisBenedictCalculator implements DailyCaloriesCalculator {
    @Override
    public double calculate(User user) {
        int sexCoef = user.getSex() == Sex.MALE ? 5 : -161;
        double physicalActivityCoef = 1.2;
        double bmr = 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() + sexCoef;

        return switch (user.getGoal()) {
            case CalorieControlGoal.WEIGHT_LOSS -> bmr * physicalActivityCoef - 500;
            case CalorieControlGoal.WEIGHT_MAINTENANCE -> bmr * physicalActivityCoef;
            case CalorieControlGoal.WEIGHT_GAIN -> bmr * physicalActivityCoef + 500;
        };
    }
}
