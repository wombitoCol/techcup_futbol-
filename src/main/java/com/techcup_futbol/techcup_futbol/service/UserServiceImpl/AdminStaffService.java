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
public class AdminStaffService implements UserService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO createUser(UserRequestDTO request) {
        log.info("Intentando crear AdminStaff con email: {}", request.getEmail());

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
            .area(request.getArea())
            .build();

        User savedUser = userRepository.save(newUser);
        log.info("AdminStaff creado con ID: {} y correo: {}", savedUser.getId(), savedUser.getEmail());
        return userMapper.toDto(savedUser);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Intentando eliminar AdminStaff con ID: {}", id);
        if (!userRepository.existsById(id)) {
            log.error("AdminStaff con ID {} no encontrado", id);
            throw ResourceNotFoundException.create("AdminStaff", id);
        }
        userRepository.deleteById(id);
        log.info("AdminStaff con ID {} eliminado correctamente", id);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        log.info("Actualizando AdminStaff con ID: {}", id);
        User user = userRepository.findById(id)
            .orElseThrow(() -> {
                log.error("AdminStaff con ID {} no encontrado", id);
                return ResourceNotFoundException.create("AdminStaff", id);
            });

        user.setPassword(dto.getPassword());
        user.setArea(dto.getArea());
        user.setActive(dto.isActive());
        user.setPhoneNumber(dto.getPhone());
        user.setPhoto(dto.getPhoto());

        User updated = userRepository.save(user);
        log.info("AdminStaff con ID {} actualizado correctamente", updated.getId());
        return userMapper.toDto(updated);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        log.info("Obteniendo todos los AdminStaff");
        List<User> adminStaffs = userRepository.findByRoleName("ADMIN_STAFF");
        return adminStaffs.stream()
            .map(userMapper::toDto)
            .toList();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        log.info("Buscando AdminStaff con ID: {}", id);
        Optional<User> user = userRepository.findById(id);
        return userMapper.toDto(user);
    }
}