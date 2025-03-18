package com.vlasov.calories.repositories;

import com.vlasov.calories.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {

}

