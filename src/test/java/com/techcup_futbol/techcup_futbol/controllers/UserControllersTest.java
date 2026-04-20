package com.techcup_futbol.techcup_futbol.controllers;

import com.techcup_futbol.techcup_futbol.dto.Request.UserRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.UserResponseDTO;
import com.techcup_futbol.techcup_futbol.model.User.UserType;
import com.techcup_futbol.techcup_futbol.service.UserServiceImpl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

// ─── StudentController ────────────────────────────────────────────────────────
@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock StudentService studentService;
    @InjectMocks StudentController studentController;

    UserRequestDTO request;
    UserResponseDTO response;

    @BeforeEach
    void setUp() {
        request = UserRequestDTO.builder()
                .email("student@test.edu").name("Juan").password("pass")
                .birthDate(LocalDate.of(2001, 1, 1)).gender("M").phone(300L).build();
        response = UserResponseDTO.builder()
                .email("student@test.edu").name("Juan").type(UserType.STUDENT).build();
    }

    @Test @DisplayName("createStudent: 201 CREATED")
    void create_returns201() {
        when(studentService.createUser(any())).thenReturn(response);
        ResponseEntity<UserResponseDTO> r = studentController.createStudent(request);
        assertThat(r.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(r.getBody().getName()).isEqualTo("Juan");
    }

    @Test @DisplayName("updateStudent: 200 OK")
    void update_returns200() {
        when(studentService.updateUser(eq(1L), any())).thenReturn(response);
        ResponseEntity<UserResponseDTO> r = studentController.updateStudent(1L, request);
        assertThat(r.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test @DisplayName("deleteStudent: 204 NO CONTENT")
    void delete_returns204() {
        doNothing().when(studentService).deleteUser(1L);
        ResponseEntity<Void> r = studentController.deleteStudent(1L);
        assertThat(r.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test @DisplayName("getAllStudents: 200 lista")
    void getAll_returns200() {
        when(studentService.getAllUsers()).thenReturn(List.of(response));
        ResponseEntity<List<UserResponseDTO>> r = studentController.getAllStudents();
        assertThat(r.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(r.getBody()).hasSize(1);
    }
}

// ─── ProfessorController ──────────────────────────────────────────────────────
@ExtendWith(MockitoExtension.class)
class ProfessorControllerTest {

    @Mock ProfessorService professorService;
    @InjectMocks ProfessorController professorController;

    UserRequestDTO request;
    UserResponseDTO response;

    @BeforeEach
    void setUp() {
        request = UserRequestDTO.builder()
                .email("prof@test.edu").name("Dra. Ana").password("pass")
                .birthDate(LocalDate.of(1980, 5, 1)).gender("F").phone(310L).build();
        response = UserResponseDTO.builder()
                .email("prof@test.edu").name("Dra. Ana").type(UserType.PROFESSOR).build();
    }

    @Test @DisplayName("createProfessor: 201 CREATED")
    void create_returns201() {
        when(professorService.createUser(any())).thenReturn(response);
        assertThat(professorController.createProfessor(request).getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test @DisplayName("updateProfessor: 200 OK")
    void update_returns200() {
        when(professorService.updateUser(eq(1L), any())).thenReturn(response);
        assertThat(professorController.updateProfessor(1L, request).getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test @DisplayName("deleteProfessor: 204 NO CONTENT")
    void delete_returns204() {
        doNothing().when(professorService).deleteUser(1L);
        assertThat(professorController.deleteProfessor(1L).getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test @DisplayName("getAllProfessors: 200 lista")
    void getAll_returns200() {
        when(professorService.getAllUsers()).thenReturn(List.of(response));
        assertThat(professorController.getAllProfessors().getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}

// ─── AdminStaffController ─────────────────────────────────────────────────────
@ExtendWith(MockitoExtension.class)
class AdminStaffControllerTest {

    @Mock AdminStaffService adminStaffService;
    @InjectMocks AdminStaffController adminStaffController;

    UserRequestDTO request;
    UserResponseDTO response;

    @BeforeEach
    void setUp() {
        request = UserRequestDTO.builder()
                .email("admin@test.edu").name("Luis").password("pass")
                .birthDate(LocalDate.of(1990, 3, 1)).gender("M").phone(320L).build();
        response = UserResponseDTO.builder()
                .email("admin@test.edu").name("Luis").type(UserType.ADMIN_STAFF).build();
    }

    @Test @DisplayName("createAdminStaff: 201 CREATED")
    void create_returns201() {
        when(adminStaffService.createUser(any())).thenReturn(response);
        assertThat(adminStaffController.createAdminStaff(request).getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test @DisplayName("updateAdminStaff: 200 OK")
    void update_returns200() {
        when(adminStaffService.updateUser(eq(1L), any())).thenReturn(response);
        assertThat(adminStaffController.updateAdminStaff(1L, request).getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test @DisplayName("deleteAdminStaff: 204 NO CONTENT")
    void delete_returns204() {
        doNothing().when(adminStaffService).deleteUser(1L);
        assertThat(adminStaffController.deleteAdminStaff(1L).getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test @DisplayName("getAllAdminStaff: 200 lista")
    void getAll_returns200() {
        when(adminStaffService.getAllUsers()).thenReturn(List.of(response));
        assertThat(adminStaffController.getAllAdminStaff().getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}

// ─── FamilyMemberController ───────────────────────────────────────────────────
@ExtendWith(MockitoExtension.class)
class FamilyMemberControllerTest {

    @Mock FamilyMemberService familyMemberService;
    @InjectMocks FamilyMemberController familyMemberController;

    UserRequestDTO request;
    UserResponseDTO response;

    @BeforeEach
    void setUp() {
        request = UserRequestDTO.builder()
                .email("fam@gmail.com").name("María").password("pass")
                .birthDate(LocalDate.of(1975, 7, 1)).gender("F").phone(315L).build();
        response = UserResponseDTO.builder()
                .email("fam@gmail.com").name("María").type(UserType.FAMILY_MEMBER).build();
    }

    @Test @DisplayName("createFamilyMember: 201 CREATED")
    void create_returns201() {
        when(familyMemberService.createUser(any())).thenReturn(response);
        assertThat(familyMemberController.createFamilyMember(request).getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test @DisplayName("updateFamilyMember: 200 OK")
    void update_returns200() {
        when(familyMemberService.updateUser(eq(1L), any())).thenReturn(response);
        assertThat(familyMemberController.updateFamilyMember(1L, request).getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test @DisplayName("deleteFamilyMember: 204 NO CONTENT")
    void delete_returns204() {
        doNothing().when(familyMemberService).deleteUser(1L);
        assertThat(familyMemberController.deleteFamilyMember(1L).getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test @DisplayName("getAllFamilyMembers: 200 lista")
    void getAll_returns200() {
        when(familyMemberService.getAllUsers()).thenReturn(List.of(response));
        assertThat(familyMemberController.getAllFamilyMembers().getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}

// ─── GraduateController ───────────────────────────────────────────────────────
@ExtendWith(MockitoExtension.class)
class GraduateControllerTest {

    @Mock GraduateService graduateService;
    @InjectMocks GraduateController graduateController;

    UserRequestDTO request;
    UserResponseDTO response;

    @BeforeEach
    void setUp() {
        request = UserRequestDTO.builder()
                .email("grad@alumni.edu").name("Pedro").password("pass")
                .birthDate(LocalDate.of(1998, 11, 1)).gender("M").phone(300L).build();
        response = UserResponseDTO.builder()
                .email("grad@alumni.edu").name("Pedro").type(UserType.GRADUATE).build();
    }

    @Test @DisplayName("createGraduate: 201 CREATED")
    void create_returns201() {
        when(graduateService.createUser(any())).thenReturn(response);
        assertThat(graduateController.createGraduate(request).getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test @DisplayName("updateGraduate: 200 OK")
    void update_returns200() {
        when(graduateService.updateUser(eq(1L), any())).thenReturn(response);
        assertThat(graduateController.updateGraduate(1L, request).getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test @DisplayName("deleteGraduate: 204 NO CONTENT")
    void delete_returns204() {
        doNothing().when(graduateService).deleteUser(1L);
        assertThat(graduateController.deleteGraduate(1L).getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test @DisplayName("getAllGraduates: 200 lista")
    void getAll_returns200() {
        when(graduateService.getAllUsers()).thenReturn(List.of(response));
        assertThat(graduateController.getAllGraduates().getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
