package com.techcup_futbol.techcup_futbol.repository;

import com.techcup_futbol.techcup_futbol.model.User.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByName(String name);
}