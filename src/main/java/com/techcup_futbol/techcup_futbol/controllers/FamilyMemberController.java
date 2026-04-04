package com.techcup_futbol.techcup_futbol.controllers;

import com.techcup_futbol.techcup_futbol.dto.Request.UserRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.UserResponseDTO;
import com.techcup_futbol.techcup_futbol.service.UserServiceImpl.FamilyMemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/family-members")
@Tag(name = "Family Members", description = "CRUD operations related to family members")
public class FamilyMemberController {
 
    private final FamilyMemberService familyMemberService;

    public FamilyMemberController(FamilyMemberService familyMemberService) {
        this.familyMemberService = familyMemberService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createFamilyMember(@Valid @RequestBody UserRequestDTO request) {
        UserResponseDTO response = familyMemberService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateFamilyMember(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDTO dto) {
        UserResponseDTO updated = familyMemberService.updateUser(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFamilyMember(@PathVariable Long id) {
        familyMemberService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}