package com.techcup_futbol.techcup_futbol.mappers;

import org.springframework.stereotype.Component;
import com.techcup_futbol.techcup_futbol.model.PlayerDecorator.Player; 
import com.techcup_futbol.techcup_futbol.dto.Request.UserRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.UserResponseDTO;
import com.techcup_futbol.techcup_futbol.model.User.User;

@Component
public interface UserMapper {


    public UserResponseDTO toDto(User user);

    public User toEntity(UserRequestDTO dto);
}