package com.techcup_futbol.techcup_futbol.model.UserType;

import com.techcup_futbol.techcup_futbol.model.User.AcademicProgram;
import com.techcup_futbol.techcup_futbol.model.User.ContractType;

import jakarta.persistence.*;

@Entity
@Table(name = "professors")
public class Professor extends UserProfile {

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private AcademicProgram academicProgram;

    @Basic(optional = false)
    private String department;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    @Override
    public String getAffiliationType() { return "Professor"; }

    public AcademicProgram getAcademicProgram() { return academicProgram; }
    public void setAcademicProgram(AcademicProgram academicProgram) { this.academicProgram = academicProgram; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public ContractType getContractType() { return contractType; }
    public void setContractType(ContractType contractType) { this.contractType = contractType; }
}