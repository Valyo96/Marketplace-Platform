package com.platform.marketplace.Marketplace.Platform.utility;

import com.platform.marketplace.Marketplace.Platform.exceptions.NotAuthorizeException;
import com.platform.marketplace.Marketplace.Platform.exceptions.WrongPasswordException;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import static com.platform.marketplace.Marketplace.Platform.consts.ConstantMessages.notAuthorizeExceptionMessage;
import static com.platform.marketplace.Marketplace.Platform.consts.ConstantMessages.wrongPassword;

@Component
@RequiredArgsConstructor
public class Utility {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    public boolean passwordConfirmation(String password , String confirmPassword){
        return password.equals(confirmPassword);
    }

    public boolean verifyPassword(String givenPassword , String hashedPassword){
        return passwordEncoder.matches(givenPassword, hashedPassword);
    }

    public void authorizationCheck(String password) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.isAuthenticated() || auth == null) {
            throw new NotAuthorizeException(notAuthorizeExceptionMessage);
        }
        User admin = userService.getUserByEmail(auth.getName());

        if (!verifyPassword(password, admin.getPassword())) {
            throw new WrongPasswordException(wrongPassword);
        }
    }

}
