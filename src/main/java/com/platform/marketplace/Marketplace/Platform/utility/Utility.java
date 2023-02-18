package com.platform.marketplace.Marketplace.Platform.utility;

public class Utility {

    public static boolean passwordConfirmation(String password , String confirmPassword){
        return password.equals(confirmPassword);
    }
}
