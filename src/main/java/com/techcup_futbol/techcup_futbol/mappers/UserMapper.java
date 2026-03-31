package com.techcup_futbol.techcup_futbol.mappers;

import org.springframework.stereotype.Component;
import com.techcup_futbol.techcup_futbol.model.PlayerDecorator.Player; 
import com.techcup_futbol.techcup_futbol.dto.Request.UserRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.UserResponseDTO;
import com.techcup_futbol.techcup_futbol.model.User.User;
import com.techcup_futbol.techcup_futbol.model.User.AdminStaff;

@Component
public class UserMapper {

    // Entidad, responseDTO (Para mostrar datos sin la contraseña)
    public UserResponseDTO toDto(User user) {
        if (user == null) {
            return null;
        }

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());

        return dto;
    }

    // RequestDTO, entidad (Para guardar en la BD)
    public User toEntity(UserRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        User user;
        String role = (dto.getRole() != null && !dto.getRole().trim().isEmpty()) 
                      ? dto.getRole().toLowerCase() 
                      : "player";
        switch (role) {
            case "admin":
            case "adminstaff":
                user = new AdminStaff();
                break;
        
            case "player":
                user = new Player();
                break;

            default:
                throw new IllegalArgumentException("Error al mapear: Rol de usuario no válido (" + role + ")");
        }

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(role); 

        return user;
    }
}