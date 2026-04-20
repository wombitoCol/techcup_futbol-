package com.techcup_futbol.techcup_futbol.controllers;

import com.techcup_futbol.techcup_futbol.dto.Request.UserRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.UserResponseDTO;
import com.techcup_futbol.techcup_futbol.service.UserServiceImpl.AdminStaffService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/admin-staff")
@Tag(name = "Admin Staff", description = "CRUD operations related to administrative staff")
public class AdminStaffController {

    private final AdminStaffService adminStaffService;

    public AdminStaffController(AdminStaffService adminStaffService) {
        this.adminStaffService = adminStaffService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createAdminStaff(@Valid @RequestBody UserRequestDTO request) {
        UserResponseDTO response = adminStaffService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateAdminStaff(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDTO dto) {
        UserResponseDTO updated = adminStaffService.updateUser(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdminStaff(@PathVariable Long id) {
        adminStaffService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findAdminStaff(@PathVariable Long id) {
        UserResponseDTO adminStaff = adminStaffService.getUserById(id);
        return ResponseEntity.ok(adminStaff);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllAdminStaff() {
        List<UserResponseDTO> adminStaff = adminStaffService.getAllUsers();
        return ResponseEntity.ok(adminStaff);
    }
}