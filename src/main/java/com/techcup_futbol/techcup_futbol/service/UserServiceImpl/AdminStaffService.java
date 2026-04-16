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
import com.techcup_futbol.techcup_futbol.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j //libreria para logs
@RequiredArgsConstructor
@Service
public class AdminStaffService implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO createUser(UserRequestDTO request) {

        log.info("Creando estudiante con email: {}", request.getEmail());
        User newUser = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .name(request.getName())
                .type(UserType.ADMIN_STAFF)
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
        if (!userRepository.existsById(id)) {
            throw ResourceNotFoundException.create("AdminStaff", id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.create("AdminStaff", id));

        user.setPassword(dto.getPassword());
        user.setArea(dto.getArea());
        user.setActive(dto.isActive());
        user.setPhoneNumber(dto.getPhone());
        user.setPhoto(dto.getPhoto());


        User updated = userRepository.save(user);
        log.info("AdminStaff actualizado con ID: {} correo antiguo: {}y correo: {}", updated.getId(),dto.getEmail(), updated.getEmail());
        return userMapper.toDto(updated);
    }
 
    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> adminStaffs = userRepository.findByType(UserType.ADMIN_STAFF);
        return adminStaffs.stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return userMapper.toDto(user);
    }
}