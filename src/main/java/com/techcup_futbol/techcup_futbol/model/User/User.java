package com.techcup_futbol.techcup_futbol.model.User;

import java.time.LocalDate;

public abstract class User {

    protected Long id;
    protected String email;
    protected String password;
    protected String role;
    protected boolean active;
    protected String name;
    protected LocalDate birthDate;
    protected String gender;
    protected Long phoneNumber;
    protected String photo;

    public User() {
        this.role = "player";
        this.active = true;
    }

    public abstract String getAffiliationType();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Long getPhone() { return phoneNumber; }
    public void setPhone(Long phone) { this.phoneNumber = phone; }

    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }
}