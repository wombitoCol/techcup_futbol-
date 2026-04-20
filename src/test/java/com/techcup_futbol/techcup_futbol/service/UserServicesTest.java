package com.techcup_futbol.techcup_futbol.service;

import com.techcup_futbol.techcup_futbol.dto.Request.UserRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.UserResponseDTO;
import com.techcup_futbol.techcup_futbol.exception.ResourceNotFoundException;
import com.techcup_futbol.techcup_futbol.mappers.UserMapper;
import com.techcup_futbol.techcup_futbol.model.User.*;
import com.techcup_futbol.techcup_futbol.repository.UserRepository;
import com.techcup_futbol.techcup_futbol.service.UserServiceImpl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * NOTA: Los @Nested con @Mock/@InjectMocks no funcionan correctamente en JUnit5+Mockito.
 * Cada servicio se prueba en su propia clase plana con sus propios mocks.
 */
@ExtendWith(MockitoExtension.class)
class UserServicesTest {
    // Esta clase está vacía a propósito — cada servicio tiene su propia clase de test abajo.
}

// ─── StudentService ────────────────────────────────────────────────────────────

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock UserRepository userRepository;
    @Mock UserMapper userMapper;

    StudentService studentService;

    User student;
    UserRequestDTO requestDTO;
    UserResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        studentService = new StudentService(userRepository, userMapper);

        student = User.builder()
                .id(1L).email("student@uni.edu").name("Carlos López")
                .password("pass").type(UserType.STUDENT)
                .birthDate(LocalDate.of(2000, 1, 15))
                .gender("M").phoneNumber(3001234567L)
                .semester(4).academicProgram(AcademicProgram.SYSTEMS_ENGINEERING)
                .build();

        requestDTO = UserRequestDTO.builder()
                .email("student@uni.edu").password("pass").name("Carlos López")
                .birthDate(LocalDate.of(2000, 1, 15)).gender("M").phone(3001234567L)
                .semester(4).academicProgram(AcademicProgram.SYSTEMS_ENGINEERING)
                .build();

        responseDTO = UserResponseDTO.builder()
                .email("student@uni.edu").name("Carlos López").type(UserType.STUDENT)
                .build();
    }

    @Test @DisplayName("createUser: crea estudiante correctamente")
    void createUser_success() {
        when(userRepository.save(any(User.class))).thenReturn(student);
        when(userMapper.toDto(student)).thenReturn(responseDTO);
        UserResponseDTO result = studentService.createUser(requestDTO);
        assertThat(result.getName()).isEqualTo("Carlos López");
        verify(userRepository).save(any(User.class));
    }

    @Test @DisplayName("deleteUser: elimina estudiante existente")
    void deleteUser_success() {
        when(userRepository.existsById(1L)).thenReturn(true);
        doNothing().when(userRepository).deleteById(1L);
        assertThatCode(() -> studentService.deleteUser(1L)).doesNotThrowAnyException();
        verify(userRepository).deleteById(1L);
    }

    @Test @DisplayName("deleteUser: lanza excepción si no existe")
    void deleteUser_notFound() {
        when(userRepository.existsById(99L)).thenReturn(false);
        assertThatThrownBy(() -> studentService.deleteUser(99L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test @DisplayName("updateUser: actualiza estudiante correctamente")
    void updateUser_success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(student));
        when(userRepository.save(any(User.class))).thenReturn(student);
        when(userMapper.toDto(student)).thenReturn(responseDTO);
        UserResponseDTO result = studentService.updateUser(1L, requestDTO);
        assertThat(result).isNotNull();
    }

    @Test @DisplayName("updateUser: lanza excepción si no existe")
    void updateUser_notFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> studentService.updateUser(99L, requestDTO))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test @DisplayName("getAllUsers: retorna lista de estudiantes")
    void getAllUsers_returnsList() {
        when(userRepository.findByType(UserType.STUDENT)).thenReturn(List.of(student));
        when(userMapper.toDto(student)).thenReturn(responseDTO);
        List<UserResponseDTO> result = studentService.getAllUsers();
        assertThat(result).hasSize(1);
    }

    @Test @DisplayName("getUserById: retorna usuario por ID")
    void getUserById_found() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(student));
        when(userMapper.toDto(Optional.of(student))).thenReturn(responseDTO);
        UserResponseDTO result = studentService.getUserById(1L);
        assertThat(result).isNotNull();
    }
}

// ─── ProfessorService ──────────────────────────────────────────────────────────

@ExtendWith(MockitoExtension.class)
class ProfessorServiceTest {

    @Mock UserRepository userRepository;
    @Mock UserMapper userMapper;

    ProfessorService professorService;

    User professor;
    UserRequestDTO requestDTO;
    UserResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        professorService = new ProfessorService(userRepository, userMapper);

        professor = User.builder()
                .id(2L).email("prof@uni.edu").name("Dra. Ana Martínez")
                .password("securePass").type(UserType.PROFESSOR)
                .birthDate(LocalDate.of(1980, 5, 20))
                .gender("F").phoneNumber(3109876543L)
                .department("Ingeniería").contractType(ContractType.FULL_TIME)
                .build();

        requestDTO = UserRequestDTO.builder()
                .email("prof@uni.edu").password("securePass").name("Dra. Ana Martínez")
                .birthDate(LocalDate.of(1980, 5, 20)).gender("F").phone(3109876543L)
                .department("Ingeniería").contractType(ContractType.FULL_TIME)
                .build();

        responseDTO = UserResponseDTO.builder()
                .email("prof@uni.edu").name("Dra. Ana Martínez").type(UserType.PROFESSOR)
                .build();
    }

    @Test @DisplayName("createUser: crea profesor correctamente")
    void createUser_success() {
        when(userRepository.save(any(User.class))).thenReturn(professor);
        when(userMapper.toDto(professor)).thenReturn(responseDTO);
        assertThat(professorService.createUser(requestDTO).getName()).isEqualTo("Dra. Ana Martínez");
    }

    @Test @DisplayName("deleteUser: elimina profesor existente")
    void deleteUser_success() {
        when(userRepository.existsById(2L)).thenReturn(true);
        assertThatCode(() -> professorService.deleteUser(2L)).doesNotThrowAnyException();
    }

    @Test @DisplayName("deleteUser: lanza excepción si no existe")
    void deleteUser_notFound() {
        when(userRepository.existsById(99L)).thenReturn(false);
        assertThatThrownBy(() -> professorService.deleteUser(99L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test @DisplayName("updateUser: actualiza profesor correctamente")
    void updateUser_success() {
        when(userRepository.findById(2L)).thenReturn(Optional.of(professor));
        when(userRepository.save(any())).thenReturn(professor);
        when(userMapper.toDto(professor)).thenReturn(responseDTO);
        assertThat(professorService.updateUser(2L, requestDTO)).isNotNull();
    }

    @Test @DisplayName("updateUser: lanza excepción si no existe")
    void updateUser_notFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> professorService.updateUser(99L, requestDTO))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test @DisplayName("getAllUsers: retorna lista de profesores")
    void getAllUsers_returnsList() {
        when(userRepository.findByType(UserType.PROFESSOR)).thenReturn(List.of(professor));
        when(userMapper.toDto(professor)).thenReturn(responseDTO);
        assertThat(professorService.getAllUsers()).hasSize(1);
    }

    @Test @DisplayName("getUserById: retorna DTO del profesor")
    void getUserById_found() {
        when(userRepository.findById(2L)).thenReturn(Optional.of(professor));
        when(userMapper.toDto(Optional.of(professor))).thenReturn(responseDTO);
        assertThat(professorService.getUserById(2L)).isNotNull();
    }
}

// ─── AdminStaffService ─────────────────────────────────────────────────────────

@ExtendWith(MockitoExtension.class)
class AdminStaffServiceTest {

    @Mock UserRepository userRepository;
    @Mock UserMapper userMapper;

    AdminStaffService adminStaffService;

    User adminStaff;
    UserRequestDTO requestDTO;
    UserResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        adminStaffService = new AdminStaffService(userRepository, userMapper);

        adminStaff = User.builder()
                .id(3L).email("admin@uni.edu").name("Luis Pérez")
                .password("adminPass").type(UserType.ADMIN_STAFF)
                .birthDate(LocalDate.of(1990, 3, 10))
                .gender("M").phoneNumber(3201112233L).area("Deportes")
                .build();

        requestDTO = UserRequestDTO.builder()
                .email("admin@uni.edu").password("adminPass").name("Luis Pérez")
                .birthDate(LocalDate.of(1990, 3, 10)).gender("M").phone(3201112233L)
                .area("Deportes").build();

        responseDTO = UserResponseDTO.builder()
                .email("admin@uni.edu").name("Luis Pérez").type(UserType.ADMIN_STAFF)
                .build();
    }

    @Test @DisplayName("createUser: crea adminStaff correctamente")
    void createUser_success() {
        when(userRepository.save(any(User.class))).thenReturn(adminStaff);
        when(userMapper.toDto(adminStaff)).thenReturn(responseDTO);
        assertThat(adminStaffService.createUser(requestDTO).getName()).isEqualTo("Luis Pérez");
    }

    @Test @DisplayName("deleteUser: elimina adminStaff existente")
    void deleteUser_success() {
        when(userRepository.existsById(3L)).thenReturn(true);
        assertThatCode(() -> adminStaffService.deleteUser(3L)).doesNotThrowAnyException();
    }

    @Test @DisplayName("deleteUser: lanza excepción si no existe")
    void deleteUser_notFound() {
        when(userRepository.existsById(99L)).thenReturn(false);
        assertThatThrownBy(() -> adminStaffService.deleteUser(99L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test @DisplayName("updateUser: actualiza adminStaff correctamente")
    void updateUser_success() {
        when(userRepository.findById(3L)).thenReturn(Optional.of(adminStaff));
        when(userRepository.save(any())).thenReturn(adminStaff);
        when(userMapper.toDto(adminStaff)).thenReturn(responseDTO);
        assertThat(adminStaffService.updateUser(3L, requestDTO)).isNotNull();
    }

    @Test @DisplayName("updateUser: lanza excepción si no existe")
    void updateUser_notFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> adminStaffService.updateUser(99L, requestDTO))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test @DisplayName("getAllUsers: retorna lista de adminStaff")
    void getAllUsers_returnsList() {
        when(userRepository.findByType(UserType.ADMIN_STAFF)).thenReturn(List.of(adminStaff));
        when(userMapper.toDto(adminStaff)).thenReturn(responseDTO);
        assertThat(adminStaffService.getAllUsers()).hasSize(1);
    }
}

// ─── FamilyMemberService ───────────────────────────────────────────────────────

@ExtendWith(MockitoExtension.class)
class FamilyMemberServiceTest {

    @Mock UserRepository userRepository;
    @Mock UserMapper userMapper;

    FamilyMemberService familyMemberService;

    User familyMember;
    UserRequestDTO requestDTO;
    UserResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        familyMemberService = new FamilyMemberService(userRepository, userMapper);

        familyMember = User.builder()
                .id(4L).email("familiar@gmail.com").name("María García")
                .password("pass123").type(UserType.FAMILY_MEMBER)
                .birthDate(LocalDate.of(1975, 7, 22))
                .gender("F").phoneNumber(3154445566L)
                .gmailEmail("familiar@gmail.com").build();

        requestDTO = UserRequestDTO.builder()
                .email("familiar@gmail.com").password("pass123").name("María García")
                .birthDate(LocalDate.of(1975, 7, 22)).gender("F").phone(3154445566L)
                .gmailEmail("familiar@gmail.com").build();

        responseDTO = UserResponseDTO.builder()
                .email("familiar@gmail.com").name("María García")
                .type(UserType.FAMILY_MEMBER).build();
    }

    @Test @DisplayName("createUser: crea familiar correctamente")
    void createUser_success() {
        when(userRepository.save(any(User.class))).thenReturn(familyMember);
        when(userMapper.toDto(familyMember)).thenReturn(responseDTO);
        assertThat(familyMemberService.createUser(requestDTO).getName()).isEqualTo("María García");
    }

    @Test @DisplayName("deleteUser: elimina familiar existente")
    void deleteUser_success() {
        when(userRepository.existsById(4L)).thenReturn(true);
        assertThatCode(() -> familyMemberService.deleteUser(4L)).doesNotThrowAnyException();
    }

    @Test @DisplayName("deleteUser: lanza excepción si no existe")
    void deleteUser_notFound() {
        when(userRepository.existsById(99L)).thenReturn(false);
        assertThatThrownBy(() -> familyMemberService.deleteUser(99L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test @DisplayName("updateUser: actualiza familiar correctamente")
    void updateUser_success() {
        when(userRepository.findById(4L)).thenReturn(Optional.of(familyMember));
        when(userRepository.save(any())).thenReturn(familyMember);
        when(userMapper.toDto(familyMember)).thenReturn(responseDTO);
        assertThat(familyMemberService.updateUser(4L, requestDTO)).isNotNull();
    }

    @Test @DisplayName("updateUser: lanza excepción si no existe")
    void updateUser_notFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> familyMemberService.updateUser(99L, requestDTO))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test @DisplayName("getAllUsers: retorna lista de familiares")
    void getAllUsers_returnsList() {
        when(userRepository.findByType(UserType.FAMILY_MEMBER)).thenReturn(List.of(familyMember));
        when(userMapper.toDto(familyMember)).thenReturn(responseDTO);
        assertThat(familyMemberService.getAllUsers()).hasSize(1);
    }
}

// ─── GraduateService ──────────────────────────────────────────────────────────

@ExtendWith(MockitoExtension.class)
class GraduateServiceTest {

    @Mock UserRepository userRepository;
    @Mock UserMapper userMapper;

    GraduateService graduateService;

    User graduate;
    UserRequestDTO requestDTO;
    UserResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        graduateService = new GraduateService(userRepository, userMapper);

        graduate = User.builder()
                .id(5L).email("egresado@alumni.edu").name("Pedro Ramírez")
                .password("grad2020").type(UserType.GRADUATE)
                .birthDate(LocalDate.of(1998, 11, 5))
                .gender("M").phoneNumber(3007778899L)
                .academicProgram(AcademicProgram.CYBERSECURITY).graduationYear(2020)
                .build();

        requestDTO = UserRequestDTO.builder()
                .email("egresado@alumni.edu").password("grad2020").name("Pedro Ramírez")
                .birthDate(LocalDate.of(1998, 11, 5)).gender("M").phone(3007778899L)
                .academicProgram(AcademicProgram.CYBERSECURITY).graduationYear(2020)
                .build();

        responseDTO = UserResponseDTO.builder()
                .email("egresado@alumni.edu").name("Pedro Ramírez")
                .type(UserType.GRADUATE).build();
    }

    @Test @DisplayName("createUser: crea egresado correctamente")
    void createUser_success() {
        when(userRepository.save(any(User.class))).thenReturn(graduate);
        when(userMapper.toDto(graduate)).thenReturn(responseDTO);
        assertThat(graduateService.createUser(requestDTO).getName()).isEqualTo("Pedro Ramírez");
    }

    @Test @DisplayName("deleteUser: elimina egresado existente")
    void deleteUser_success() {
        when(userRepository.existsById(5L)).thenReturn(true);
        assertThatCode(() -> graduateService.deleteUser(5L)).doesNotThrowAnyException();
    }

    @Test @DisplayName("deleteUser: lanza excepción si no existe")
    void deleteUser_notFound() {
        when(userRepository.existsById(99L)).thenReturn(false);
        assertThatThrownBy(() -> graduateService.deleteUser(99L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test @DisplayName("updateUser: actualiza egresado correctamente")
    void updateUser_success() {
        when(userRepository.findById(5L)).thenReturn(Optional.of(graduate));
        when(userRepository.save(any())).thenReturn(graduate);
        when(userMapper.toDto(graduate)).thenReturn(responseDTO);
        assertThat(graduateService.updateUser(5L, requestDTO)).isNotNull();
    }

    @Test @DisplayName("updateUser: lanza excepción si no existe")
    void updateUser_notFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> graduateService.updateUser(99L, requestDTO))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test @DisplayName("getAllUsers: retorna lista de egresados")
    void getAllUsers_returnsList() {
        when(userRepository.findByType(UserType.GRADUATE)).thenReturn(List.of(graduate));
        when(userMapper.toDto(graduate)).thenReturn(responseDTO);
        assertThat(graduateService.getAllUsers()).hasSize(1);
    }

    @Test @DisplayName("getUserById: retorna DTO del egresado")
    void getUserById_found() {
        when(userRepository.findById(5L)).thenReturn(Optional.of(graduate));
        when(userMapper.toDto(Optional.of(graduate))).thenReturn(responseDTO);
        assertThat(graduateService.getUserById(5L)).isNotNull();
    }
}
