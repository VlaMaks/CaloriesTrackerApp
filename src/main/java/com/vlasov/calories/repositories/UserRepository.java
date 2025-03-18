package com.vlasov.calories.repositories;

import com.vlasov.calories.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}