package com.techcup_futbol.techcup_futbol.User;

import com.techcup_futbol.techcup_futbol.User.AcademicProgram;

public class Student extends User {

    private AcademicProgram academicProgram;
    private int semester;

    @Override
    public String getAffiliationType() { return "Student"; }

    public AcademicProgram getAcademicProgram() { return academicProgram; }
    public void setAcademicProgram(AcademicProgram academicProgram) { this.academicProgram = academicProgram; }

    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }
}