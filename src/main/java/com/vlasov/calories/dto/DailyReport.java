package com.vlasov.calories.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class DailyReport {
    private String userId;
    private int amountCalories;
    private LocalDate date;
    private int mealsAmount;
}
