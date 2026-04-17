package com.techcup_futbol.techcup_futbol.model.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

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
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private  List<UserType> roles;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private Long phoneNumber;

    @Column(nullable = true)
    private String photo;

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
}