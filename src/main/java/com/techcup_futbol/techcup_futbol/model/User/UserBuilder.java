package com.techcup_futbol.techcup_futbol.model.User;

import java.time.LocalDate;

public interface UserBuilder {

    UserBuilder email(String email);
    UserBuilder password(String password);
    UserBuilder name(String name);
    UserBuilder role(String role);
    UserBuilder birthDate(LocalDate birthDate);
    UserBuilder gender(String gender);
    UserBuilder phone(Long phone);
    UserBuilder photo(String photo);

    Student build();
}