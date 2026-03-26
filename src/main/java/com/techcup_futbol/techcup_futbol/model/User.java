package com.techcup_futbol.techcup_futbol.model;

public class User {
    private Long id;
    private String email;
    private String password;
    private String role;
    private boolean active;
    private String name;
    private String registrationNumber;
    private String gender;
    private String phone;
    private String photo;

    public User() {
        this.role = "player";
        this.active = true;
    }

    public User(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = "player";
        this.active = true;
    }

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

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }
}