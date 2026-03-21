package com.techcup_futbol.techcup_futbol.model.UserType;

import com.techcup_futbol.techcup_futbol.model.AcademicProgram;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student extends UserProfile {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AcademicProgram academicProgram;

    @Column(nullable = false)
    private String academicProgram;

    @Column(nullable = false)
    private int semester;

    @Override
    public String getAffiliationType() { return "Student"; }

    public String getAcademicProgram() { return academicProgram; }
    public void setAcademicProgram(String academicProgram) { this.academicProgram = academicProgram; }

    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }
}