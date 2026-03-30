package com.techcup_futbol.techcup_futbol.model.User;

import jakarta.persistence.*;

@Entity
@Table(name = "admin_staff")
public class AdminStaff extends User {

    @Column(nullable = false)
    private String area;

    @Override
    public String getAffiliationType() { return "Admin Staff"; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
}