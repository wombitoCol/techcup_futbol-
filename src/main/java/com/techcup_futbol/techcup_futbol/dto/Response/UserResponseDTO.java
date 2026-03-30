package com.techcup_futbol.techcup_futbol.dto.Response;


import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {

    private Long id;
    private String email;
    private String role;
    private boolean active;
    private String name;
    private LocalDate birthDate;
    private String gender;
    private Long phoneNumber;


}