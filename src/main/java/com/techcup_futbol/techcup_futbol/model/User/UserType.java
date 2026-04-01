package com.techcup_futbol.techcup_futbol.model.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserType {
    
    PROFESSOR,
    STUDENT,
    GRADUATE,
    ADMIN_STAFF,
    FAMILY_MEMBER 
}
