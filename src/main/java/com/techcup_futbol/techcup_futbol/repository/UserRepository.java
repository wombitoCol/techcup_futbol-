package com.techcup_futbol.techcup_futbol.repository;

import com.techcup_futbol.techcup_futbol.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// Extiende de JpaRepository para tener el CRUD automático
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); 
}