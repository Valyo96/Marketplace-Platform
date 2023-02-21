package com.platform.marketplace.Marketplace.Platform.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Utility {
    @Autowired
    private static BCryptPasswordEncoder passwordEncoder;
    public static boolean passwordConfirmation(String password , String confirmPassword){
        return password.equals(confirmPassword);
    }

    public static boolean verifyPassword(String givenPassword , String hashedPassword){
        return passwordEncoder.matches(givenPassword, hashedPassword);
    }

}
