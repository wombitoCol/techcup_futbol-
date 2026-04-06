package com.techcup_futbol.techcup_futbol.dto.Request;

import java.time.LocalDate;

import com.techcup_futbol.techcup_futbol.model.User.AcademicProgram;
import com.techcup_futbol.techcup_futbol.model.User.ContractType;
import com.techcup_futbol.techcup_futbol.model.User.User;
import com.techcup_futbol.techcup_futbol.model.User.UserType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    public static final UserType STUDENT = null;
    private String email;
    private String password;
    private String name;
    private LocalDate birthDate;
    private String gender;
    private Long phone;
    private String photo;
    private UserType type;
    private boolean isActive;
    private AcademicProgram academicProgram;
    private Integer semester;
    private String department;
    private ContractType contractType;
    private Integer graduationYear;
    private String gmailEmail;
    private String relationship;
    private String area;
    private User relatedUser;


}