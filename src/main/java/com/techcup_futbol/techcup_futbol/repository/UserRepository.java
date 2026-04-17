package com.techcup_futbol.techcup_futbol.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.techcup_futbol.techcup_futbol.model.User.User;
import com.techcup_futbol.techcup_futbol.model.User.UserType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// Extiende de JpaRepository para tener el CRUD automático
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); 

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :roleName")
    List<User> findByRoleName(@Param("roleName") String roleName);
    
    List<User> findByGender(String gender);
    List<User> findByActive(Boolean isActive);
    Optional<User> findByNameContainingIgnoreCase(String name);

    List<User> findByBirthDate(LocalDate birthDay);
    Optional<User> findByPhoneNumber(Long phoneNumber);

    List<User> findAll();

}