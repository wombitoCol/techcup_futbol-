/* 
package com.techcup_futbol.techcup_futbol.model.UserType;

import com.techcup_futbol.techcup_futbol.model.User.AcademicProgram;

import jakarta.persistence.*;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "graduates")
public class Graduate extends UserProfile {

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private AcademicProgram academicProgram;

    @Basic(optional = false)
    private int graduationYear;

    @Override
    public String getAffiliationType() { return "Graduate"; }

    public AcademicProgram getAcademicProgram() { return academicProgram; }
    public void setAcademicProgram(AcademicProgram academicProgram) { this.academicProgram = academicProgram; }

    public int getGraduationYear() { return graduationYear; }
    public void setGraduationYear(int graduationYear) { this.graduationYear = graduationYear; }
}
*/