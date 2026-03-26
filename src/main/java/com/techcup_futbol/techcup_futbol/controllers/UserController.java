package com.techcup_futbol.techcup_futbol.controller;

import com.techcup_futbol.techcup_futbol.model.User;
import com.techcup_futbol.techcup_futbol.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "CRUD operations related to users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Creates a user with player role by default.")
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }

    @PutMapping("/{id}/role")
    @Operation(summary = "Update user role", description = "Only admin can change a user role.")
    public ResponseEntity<User> updateRole(@PathVariable Long id, @RequestParam String role, @RequestParam boolean isAdmin) {
        User user = userService.updateRole(id, role, isAdmin);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}/inactive")
    @Operation(summary = "Deactivate a user", description = "Users are not deleted, only deactivated.")
    public ResponseEntity<Void> inactive(@PathVariable Long id) {
        if (userService.inactiveUser(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}