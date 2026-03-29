/* 
package com.techcup_futbol.techcup_futbol.controller;

import com.techcup_futbol.techcup_futbol.model.User.User;
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

    @PostMapping
    @Operation(summary = "Create a new user", description = "Registers a user. By default, it is created with the 'player' role.")
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deactivate a user", description = "Users are not deleted from the database, only deactivated.")
    public ResponseEntity<Void> inactive(@PathVariable Long id) {
        if (userService.inactiveUser(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
*/