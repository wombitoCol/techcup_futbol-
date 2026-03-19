package com.techcup_futbol.techcup_futbol.entity;

import com.techcup_futbol.techcup_futbol.model.AcademicProgram;
import com.techcup_futbol.techcup_futbol.model.ContractType;
import jakarta.persistence.*;

@Entity
@Table(name = "professors")
public class Professor extends UserProfile {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AcademicProgram academicProgram;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
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