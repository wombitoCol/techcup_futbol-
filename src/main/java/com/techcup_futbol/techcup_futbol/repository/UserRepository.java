package com.techcup_futbol.techcup_futbol.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techcup_futbol.techcup_futbol.model.User.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// Extiende de JpaRepository para tener el CRUD automático
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); 
    List<User> findByRole(String role);
    List<User> findByGender(String gender);
    List<User> findByActive(Boolean isActive);
    Optional<User> findByNameContainingIgnoreCase(String name);

    List<User> findByBirthDate(LocalDate birthDay);
    Optional<User> findByPhoneNumber(Long phoneNumber);

    List<User> findAll();

}