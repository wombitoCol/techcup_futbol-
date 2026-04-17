package com.techcup_futbol.techcup_futbol.repository;

import com.techcup_futbol.techcup_futbol.model.User.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {
    Optional<UserType> findByName(String name);
}