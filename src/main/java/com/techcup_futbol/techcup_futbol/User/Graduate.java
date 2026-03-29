package com.techcup_futbol.techcup_futbol.User;

public class Graduate extends User {

    private AcademicProgram academicProgram;
    private int graduationYear;

    @Override
    public String getAffiliationType() { return "Graduate"; }

    public AcademicProgram getAcademicProgram() { return academicProgram; }
    public void setAcademicProgram(AcademicProgram academicProgram) { this.academicProgram = academicProgram; }

    public int getGraduationYear() { return graduationYear; }
    public void setGraduationYear(int graduationYear) { this.graduationYear = graduationYear; }
}