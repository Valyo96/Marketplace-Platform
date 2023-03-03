//package com.platform.marketplace.Marketplace.Platform.security;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class LoggedInUserFilter extends OncePerRequestFilter {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    public LoggedInUserFilter(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication != null && authentication.isAuthenticated() && request.getRequestURI().endsWith("/register")) {
//            // User is already logged in, redirect to a different page
//            response.sendRedirect("/already-registered");
//            return;
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
