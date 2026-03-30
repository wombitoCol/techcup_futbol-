package com.techcup_futbol.techcup_futbol.model.User;

public class Professor extends User {

    private AcademicProgram academicProgram;
    private String department;
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