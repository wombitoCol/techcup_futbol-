package com.techcup_futbol.techcup_futbol.service.UserServiceImpl;

import java.util.List;

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

@RequiredArgsConstructor
@Service
public class GraduateService implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO createUser(UserRequestDTO request) {

        User newUser = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .name(request.getName())
                .type(UserType.GRADUATE)
                .birthDate(request.getBirthDate())
                .gender(request.getGender())
                .phoneNumber(request.getPhone())
                .photo(request.getPhoto())
                .academicProgram(request.getAcademicProgram())
                .graduationYear(request.getGraduationYear())
                .build();

        User savedUser = userRepository.save(newUser);
        return userMapper.toDto(savedUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw ResourceNotFoundException.create("ID", id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.create("ID", id));

        user.setPassword(dto.getPassword());
        user.setGraduationYear(dto.getGraduationYear());
        user.setAcademicProgram(dto.getAcademicProgram());
        user.setActive(dto.isActive());
        user.setPhoneNumber(dto.getPhone());
        user.setPhoto(dto.getPhoto());

        User updated = userRepository.save(user);
        return userMapper.toDto(updated);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> graduates = userRepository.findByType(UserType.GRADUATE);
        return graduates.stream()
                .map(userMapper::toDto)
                .toList();
    }
}