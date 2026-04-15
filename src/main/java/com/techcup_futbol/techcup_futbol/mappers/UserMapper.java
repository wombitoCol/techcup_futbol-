package com.techcup_futbol.techcup_futbol.mappers;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import com.techcup_futbol.techcup_futbol.dto.Request.UserRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.UserResponseDTO;
import com.techcup_futbol.techcup_futbol.model.User.User;


@Mapper(componentModel = "spring")
public interface UserMapper {


    public UserResponseDTO toDto(Optional<User> user);

    public User toEntity(UserRequestDTO dto);

    public UserResponseDTO toDto(User user);

}