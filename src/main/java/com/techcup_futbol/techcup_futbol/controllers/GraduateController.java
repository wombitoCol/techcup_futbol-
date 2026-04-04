package com.techcup_futbol.techcup_futbol.controllers;

import com.techcup_futbol.techcup_futbol.dto.Request.UserRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.UserResponseDTO;
import com.techcup_futbol.techcup_futbol.service.UserServiceImpl.GraduateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/graduates")
@Tag(name = "Graduates", description = "CRUD operations related to graduates")
public class GraduateController {

    private final GraduateService graduateService;

    public GraduateController(GraduateService graduateService) {
        this.graduateService = graduateService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createGraduate(@Valid @RequestBody UserRequestDTO request) {
        UserResponseDTO response = graduateService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateGraduate(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDTO dto) {
        UserResponseDTO updated = graduateService.updateUser(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGraduate(@PathVariable Long id) {
        graduateService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}