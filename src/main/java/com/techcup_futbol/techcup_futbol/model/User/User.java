package com.techcup_futbol.techcup_futbol.model.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import org.springframework.stereotype.Indexed;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false, unique = true)
    protected String email;

    @Column(nullable = false)
    protected String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected UserType type;

    @Column(nullable = false)
    protected boolean isActive;

    @Column(nullable = false)
    protected String name;
    @Column(nullable = false)
    protected LocalDate birthDate;
    
    @Column(nullable = false)
    protected String gender;

    @Column(nullable = false)
    protected Long phoneNumber;

    @Column(nullable = true)
    protected String photo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private AcademicProgram academicProgram;

    @Column(nullable = true)
    private int semester;

    @Column(nullable = true)
    private String department;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private ContractType contractType;

    @Column(nullable = true)
    private int graduationYear;

    @Column(nullable = true)
    private String gmailEmail;

    @ManyToOne
    @JoinColumn(name = "related_user_id")
    private User relatedUser;

    @Column(nullable = true)
    private String area;


    public UserType getAffiliationType(){return type;};

}