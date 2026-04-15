package com.techcup_futbol.techcup_futbol.controllers;


import com.techcup_futbol.techcup_futbol.dto.Request.TournamentRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Request.UserRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.TournamentResponseDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.UserResponseDTO;
import com.techcup_futbol.techcup_futbol.model.User.User;
import com.techcup_futbol.techcup_futbol.service.UserService;
import com.techcup_futbol.techcup_futbol.service.UserServiceImpl.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/api/users/students")
@Tag(name = "Users", description = "CRUD operations related to users")
public class StudentController {
    
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createStudent(@RequestBody UserRequestDTO request) {
        
        UserResponseDTO response = studentService.createUser(request);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDTO dto) {
        UserResponseDTO updated = studentService.updateUser(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllStudents() {
        List<UserResponseDTO> students = studentService.getAllUsers();
        return ResponseEntity.ok(students);
    }

    @GetMapping
    public ResponseEntity<UserResponseDTO> findStudent(@PathVariable Long id) {
        UserResponseDTO student = studentService.getUserById(id);
        return ResponseEntity.ok(student);
    }

}
