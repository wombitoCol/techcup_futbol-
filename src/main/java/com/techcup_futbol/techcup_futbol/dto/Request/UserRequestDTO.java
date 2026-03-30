package com.techcup_futbol.techcup_futbol.dto.Request;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDTO {
    
    private String email;
    private String password;

}