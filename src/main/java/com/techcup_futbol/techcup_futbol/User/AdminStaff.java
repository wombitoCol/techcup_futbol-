package com.techcup_futbol.techcup_futbol.User;

public class AdminStaff extends User {

    private String area;

    @Override
    public String getAffiliationType() { return "Admin Staff"; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
}