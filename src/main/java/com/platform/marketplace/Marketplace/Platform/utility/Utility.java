package com.platform.marketplace.Marketplace.Platform.utility;

import com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotAuthorizeException;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.WrongPasswordException;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import static com.platform.marketplace.Marketplace.Platform.utility.consts.ConstantMessages.NOT_AUTHORIZE_EXCEPTION_MESSAGE;
import static com.platform.marketplace.Marketplace.Platform.utility.consts.ConstantMessages.WRONG_PASSWORD;

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
            throw new NotAuthorizeException(NOT_AUTHORIZE_EXCEPTION_MESSAGE);
        }
        User user = userService.getUserByEmail(auth.getName());

        if (!verifyPassword(password, user.getPassword())) {
            throw new WrongPasswordException(WRONG_PASSWORD);
        }
    }

    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

}
