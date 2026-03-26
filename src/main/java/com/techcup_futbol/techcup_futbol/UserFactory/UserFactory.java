package com.techcup_futbol.techcup_futbol.UserFactory;

import com.techcup_futbol.techcup_futbol.User.*;

public class UserFactory {

    public static User createUser(String type) {
        switch (type.toUpperCase()) {
            case "STUDENT":
                return new Student();
            case "GRADUATE":
                return new Graduate();
            case "PROFESSOR":
                return new Professor();
            case "ADMIN_STAFF":
                return new AdminStaff();
            case "FAMILY_MEMBER":
                return new FamilyMember();
            default:
                throw new IllegalArgumentException("Unknown user type: " + type);
        }
    }
}