/* 
package com.techcup_futbol.techcup_futbol.model.User;

import com.techcup_futbol.techcup_futbol.entity.*;
import com.techcup_futbol.techcup_futbol.model.UserType.AdminStaff;
import com.techcup_futbol.techcup_futbol.model.UserType.FamilyMember;
import com.techcup_futbol.techcup_futbol.model.UserType.Graduate;
import com.techcup_futbol.techcup_futbol.model.UserType.Professor;
import com.techcup_futbol.techcup_futbol.model.UserType.Student;
import com.techcup_futbol.techcup_futbol.model.UserType.UserProfile;

public class UserFactory {

    public static UserProfile createUser(String type) {
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
*/