package com.techcup_futbol.techcup_futbol.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.techcup_futbol.techcup_futbol.dto.Request.UserRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.UserResponseDTO;
import com.techcup_futbol.techcup_futbol.exception.ResourceNotFoundException;
import com.techcup_futbol.techcup_futbol.mappers.UserMapper;
import com.techcup_futbol.techcup_futbol.model.User.User;
import com.techcup_futbol.techcup_futbol.model.User.UserType;
import com.techcup_futbol.techcup_futbol.repository.UserRepository;
import com.techcup_futbol.techcup_futbol.repository.UserTypeRepository;
import com.techcup_futbol.techcup_futbol.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService implements UserService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO createUser(UserRequestDTO request) {
        log.info("Intentando crear estudiante con email: {}", request.getEmail());

        // Traduce cada String de roles a su objeto UserType desde la BD
        List<UserType> roles = request.getRoles().stream()
            .map(roleName -> userTypeRepository.findByName(roleName)
                .orElseThrow(() -> {
                    log.error("Rol {} no encontrado en la BD", roleName);
                    return new RuntimeException("Rol " + roleName + " no encontrado");
                }))
            .toList();

        User newUser = User.builder()
            .email(request.getEmail())
            .password(request.getPassword())
            .name(request.getName())
            .roles(roles)
            .birthDate(request.getBirthDate())
            .gender(request.getGender())
            .phoneNumber(request.getPhone())
            .photo(request.getPhoto())
            .semester(request.getSemester())
            .academicProgram(request.getAcademicProgram())
            .build();

        User savedUser = userRepository.save(newUser);
        log.info("Estudiante creado con ID: {} y correo: {}", savedUser.getId(), savedUser.getEmail());

        return userMapper.toDto(savedUser);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Intentando eliminar estudiante con ID: {}", id);
        if (!userRepository.existsById(id)) {
            log.error("Estudiante con ID {} no encontrado", id);
            throw ResourceNotFoundException.create("ID", id);
        }
        userRepository.deleteById(id);
        log.info("Estudiante con ID {} eliminado correctamente", id);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        log.info("Actualizando estudiante con ID: {}", id);
        User user = userRepository.findById(id)
            .orElseThrow(() -> {
                log.error("Estudiante con ID {} no encontrado", id);
                return ResourceNotFoundException.create("ID", id);
            });

        user.setPassword(dto.getPassword());
        user.setSemester(dto.getSemester());
        user.setActive(dto.isActive());
        user.setPhoneNumber(dto.getPhone());
        user.setPhoto(dto.getPhoto());

        User updated = userRepository.save(user);
        log.info("Estudiante con ID {} actualizado correctamente", id);
        return userMapper.toDto(updated);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        log.info("Obteniendo todos los estudiantes");
        List<User> students = userRepository.findByRoleName("STUDENT");
        return students.stream()
            .map(userMapper::toDto)
            .toList();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        log.info("Buscando estudiante con ID: {}", id);
        Optional<User> user = userRepository.findById(id);
        return userMapper.toDto(user);
    }
}