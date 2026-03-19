package com.techcup_futbol.techcup_futbol.entity;

import com.techcup_futbol.techcup_futbol.model.AcademicProgram;
import jakarta.persistence.*;

@Entity
@Table(name = "graduates")
public class Graduate extends UserProfile {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AcademicProgram academicProgram;

    @Column(nullable = false)
    private int graduationYear;

    @Override
    public String getAffiliationType() { return "Graduate"; }

    public AcademicProgram getAcademicProgram() { return academicProgram; }
    public void setAcademicProgram(AcademicProgram academicProgram) { this.academicProgram = academicProgram; }

    public int getGraduationYear() { return graduationYear; }
    public void setGraduationYear(int graduationYear) { this.graduationYear = graduationYear; }
}