package com.techcup_futbol.techcup_futbol.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admin_staff")
public class AdminStaff extends UserProfile {

    @Column(nullable = false)
    private String area;

    @Override
    public String getAffiliationType() { return "Admin Staff"; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
}