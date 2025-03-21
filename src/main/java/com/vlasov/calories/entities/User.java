package com.vlasov.calories.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users_app")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private int age;
    private double weight;
    private int height;
    @Enumerated(EnumType.STRING)
    private CalorieControlGoal goal;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double dailyCalorieIntake;
    @Enumerated(EnumType.STRING)
    private Sex sex;

}
