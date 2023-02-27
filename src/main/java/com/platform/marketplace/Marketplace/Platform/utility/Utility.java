package com.platform.marketplace.Marketplace.Platform.utility;

import com.platform.marketplace.Marketplace.Platform.utility.annotations.OrganisationName;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.AlreadyExistException;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotAuthorizeException;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.WrongPasswordException;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.platform.marketplace.Marketplace.Platform.utility.consts.ConstantMessages.*;
import static com.platform.marketplace.Marketplace.Platform.utility.consts.Regex.organisationNameRegexPattern;

@Component
@RequiredArgsConstructor
public class Utility {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;


    public boolean checkIfEmailExists(String email){
        if(userService.getUserByEmail(email) == null){
            return true;
        } else {
            throw new AlreadyExistException(EMAIL_ALREADY_TAKEN);
        }
    }


    public boolean passwordConfirmation(String password , String confirmPassword){
        if(password.equals(confirmPassword)){
            return true;
        }
        throw new BadCredentialsException(PASSWORDS_NOT_MATCHING_MESSAGE);
    }

    public boolean verifyPassword(String givenPassword , String hashedPassword){
        if(passwordEncoder.matches(givenPassword, hashedPassword)){
            return true;
        }
        throw new WrongPasswordException(WRONG_PASSWORD);
    }

    public User authorizationCheck(String password) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.isAuthenticated() || auth == null) {
            throw new NotAuthorizeException(NOT_AUTHORIZE_EXCEPTION_MESSAGE);
        }
        User user = userService.getUserByEmail(auth.getName());
        verifyPassword(password, user.getPassword());
        return user;
    }

    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

}
