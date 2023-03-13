package com.platform.marketplace.Marketplace.Platform.utility;

import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.service.user.UserService;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotAuthorizeException;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.WrongPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UtilityTest {
    private Utility utility;

    @Mock
    private UserService userService;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        utility = new Utility(userService, passwordEncoder);
    }
    @Test
    public void testCheckIfEmailExists() {
        when(userService.getUserByEmail("test@example.com")).thenReturn(new User());
        assertTrue(utility.checkIfEmailExists("test@example.com"));
        assertFalse(utility.checkIfEmailExists("example@test.com"));
        verify(userService, times(2)).getUserByEmail(anyString());
        verify(userService, times(1)).getUserByEmail("test@example.com");
        verify(userService, times(1)).getUserByEmail("example@test.com");
    }
    @Test
    public void testPasswordConfirmation() {
        assertTrue(utility.passwordConfirmation("password", "password"));
        assertThrows(BadCredentialsException.class, () -> {
            utility.passwordConfirmation("password", "pass");
        });
    }
    @Test
    public void testVerifyPassword() {
        when(passwordEncoder.matches("password", "hashedPassword")).thenReturn(true);
        utility.verifyPassword("password", "hashedPassword");
        when(passwordEncoder.matches("password", "hashedPassword")).thenReturn(false);
        assertThrows(WrongPasswordException.class, () -> {
            utility.verifyPassword("password", "hashedPassword");
        });
    }
    @Test
    public void testReturnAuthenticatedUser() {
        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(auth);
        when(auth.isAuthenticated()).thenReturn(true);
        when(auth.getName()).thenReturn("test@example.com");
        when(userService.getUserByEmail("test@example.com")).thenReturn(new User());
        assertEquals(new User(), utility.returnAuthenticatedUser());
        when(auth.isAuthenticated()).thenReturn(false);
        assertThrows(NotAuthorizeException.class, () -> {
            utility.returnAuthenticatedUser();
        });
    }
    @Test
    public void testAuthorizationCheck() {
        User user = new User();
        user.setPassword("hashedPassword");
        when(passwordEncoder.matches("password", "hashedPassword")).thenReturn(true);
        when(userService.getUserByEmail(anyString())).thenReturn(user);

        // create mock authentication object
        Authentication auth = Mockito.mock(Authentication.class);
        Mockito.when(auth.getName()).thenReturn("test@example.com");
        Mockito.when(auth.isAuthenticated()).thenReturn(true);

        // set the mock authentication to the security context holder
        SecurityContextHolder.getContext().setAuthentication(auth);

        assertEquals(user, utility.authorizationCheck("password"));

        when(passwordEncoder.matches("password", "hashedPassword")).thenReturn(false);
        assertThrows(WrongPasswordException.class, () -> {
            utility.authorizationCheck("password");
        });
    }


    @Test
    public void testEncodePassword() {
        String plainPassword = "test123";
        String hashedPassword = "hashedPassword";

        // Mock the behavior of the passwordEncoder dependency
        when(passwordEncoder.encode(plainPassword)).thenReturn(hashedPassword);

        // Call the method being tested
        String result = utility.encodePassword(plainPassword);

        // Verify that the passwordEncoder.encode method is called exactly once with the expected argument
        verify(passwordEncoder, times(1)).encode(plainPassword);

        // Verify that the result of the method is the expected hashed password
        assertEquals(hashedPassword, result);
    }


}
