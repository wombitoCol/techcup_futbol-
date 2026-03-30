package com.techcup_futbol.techcup_futbol.model.User;

import java.time.LocalDate;

public class DefaultUserBuilder implements UserBuilder {

    private String email;
    private String password;
    private String name;
    private String role;
    private LocalDate birthDate;
    private String gender;
    private Long phone;
    private String photo;

    @Override public DefaultUserBuilder email(String email) { this.email = email; return this; }
    @Override public DefaultUserBuilder password(String password) { this.password = password; return this; }
    @Override public DefaultUserBuilder name(String name) { this.name = name; return this; }
    @Override public DefaultUserBuilder role(String role) { this.role = role; return this; }
    @Override public DefaultUserBuilder birthDate(LocalDate birthDate) { this.birthDate = birthDate; return this; }
    @Override public DefaultUserBuilder gender(String gender) { this.gender = gender; return this; }
    @Override public DefaultUserBuilder phone(Long phone) { this.phone = phone; return this; }
    @Override public DefaultUserBuilder photo(String photo) { this.photo = photo; return this; }

    @Override
    public Student build() {
        Student student = new Student();
        student.setEmail(email);
        student.setPassword(password);
        student.setName(name);
        student.setRole(role);
        student.setBirthDate(birthDate);
        student.setGender(gender);
        student.setPhone(phone);
        student.setPhoto(photo);
        return student;
    }
}