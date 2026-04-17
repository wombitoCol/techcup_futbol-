package com.techcup_futbol.techcup_futbol.mappers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.techcup_futbol.techcup_futbol.dto.Request.UserRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.UserResponseDTO;
import com.techcup_futbol.techcup_futbol.model.User.User;
import com.techcup_futbol.techcup_futbol.model.User.UserType;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "roles", target = "roles", qualifiedByName = "mapRolesToStrings")
    @Mapping(source = "phoneNumber", target = "phone")
    UserResponseDTO toDto(User user);

    @Mapping(target = "roles", ignore = true)
    @Mapping(source = "phone", target = "phoneNumber")
    User toEntity(UserRequestDTO dto);

    // Para cuando viene Optional<User>
    default UserResponseDTO toDto(Optional<User> user) {
        return user.map(this::toDto).orElse(null);
    }

    @Named("mapRolesToStrings")
    default List<String> mapRolesToStrings(List<UserType> roles) {
        if (roles == null) return List.of();
        return roles.stream()
            .map(UserType::getName)
            .collect(Collectors.toList());
    }
}