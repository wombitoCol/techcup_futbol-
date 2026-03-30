package com.techcup_futbol.techcup_futbol.model.User;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student extends User {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AcademicProgram academicProgram;

    @Column(nullable = false)
    private int semester;

    @Override
    public String getAffiliationType() { return "Student"; }

    public AcademicProgram getAcademicProgram() { return academicProgram; }
    public void setAcademicProgram(AcademicProgram academicProgram) { this.academicProgram = academicProgram; }

    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }
}