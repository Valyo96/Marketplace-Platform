package com.platform.marketplace.Marketplace.Platform.utility;

import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.service.user.UserService;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.InvalidDateTimeFormatException;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotAuthorizeException;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.WrongPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.platform.marketplace.Marketplace.Platform.utility.consts.ConstantMessages.*;

@Service
@RequiredArgsConstructor

public class Utility {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    private UserService getUserService() {
        return userService;
    }

    private BCryptPasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public boolean checkIfEmailExists(String email) {
        if (userService.getUserByEmail(email) != null) {
            return true;
        } else {
            return false;
        }
    }


    public boolean passwordConfirmation(String password, String confirmPassword) {
        if (password.equals(confirmPassword)) {
            return true;
        }
        throw new BadCredentialsException(PASSWORDS_NOT_MATCHING_MESSAGE);
    }

    public void verifyPassword(String givenPassword, String hashedPassword) {
        if (passwordEncoder.matches(givenPassword, hashedPassword)) {
            return;
        }
        throw new WrongPasswordException(WRONG_PASSWORD);
    }

    public User returnAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            return userService.getUserByEmail(auth.getName());
        }
        throw new NotAuthorizeException(NOT_AUTHORIZE_EXCEPTION_MESSAGE);
    }

    public User authorizationCheck(String password) {
        User user = returnAuthenticatedUser();
        verifyPassword(password, user.getPassword());
        return user;
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
