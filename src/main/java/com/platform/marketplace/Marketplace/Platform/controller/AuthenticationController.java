package com.platform.marketplace.Marketplace.Platform.controller;

import com.platform.marketplace.Marketplace.Platform.dto.OrganisationDTO;
import com.platform.marketplace.Marketplace.Platform.service.location.LocationService;
import com.platform.marketplace.Marketplace.Platform.service.organisation.OrganisationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor

public class AuthenticationController {

    private final OrganisationService organisationService;

    private final LocationService locationService;

    @GetMapping("/login")
    public String orgLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String orgRegister(Model model, @ModelAttribute("errorMessage") String errorMessage,
                              @ModelAttribute("nameError") String nameError,
                              @ModelAttribute("emailError") String emailError,
                              @ModelAttribute("passwordError") String passwordError) {
        model.addAttribute("org", new OrganisationDTO());
        model.addAttribute("cities", locationService.getAllLocations());
        if (errorMessage != null && !errorMessage.isEmpty()) {
            model.addAttribute("errorMessage", errorMessage);
        }
        if (nameError != null && !nameError.isEmpty()) {
            model.addAttribute("nameError", nameError);
        }
        if (emailError != null && !emailError.isEmpty()) {
            model.addAttribute("emailError", emailError);
        }
        if (passwordError != null && !passwordError.isEmpty()) {
            model.addAttribute("passwordError", passwordError);
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated()){
            model.addAttribute("errorMessage" , "Излезте от текущият акаунт, ако искате да се регистрирате наново");
            return ""; //TODO трябва да направя филтър.. 
        } else {
            return "registration";
        }

    }

    @PostMapping("submit")
    public ModelAndView orgRegister(@Valid OrganisationDTO org,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {
        String name = "";
        String email = "";
        String password = "";

        if (bindingResult.hasErrors()) {
            if (bindingResult.hasFieldErrors("name")) {
                name = bindingResult.getFieldError("name").getDefaultMessage();
            }
            if (bindingResult.hasFieldErrors("email")) {
                email = bindingResult.getFieldError("email").getDefaultMessage();
            }
            if (bindingResult.hasFieldErrors("password")) {
                password = bindingResult.getFieldError("password").getDefaultMessage();
            }
            redirectAttributes.addFlashAttribute("org", org);
            return new ModelAndView("redirect:/register")
                    .addObject("nameError", name)
                    .addObject("emailError", email)
                    .addObject("passwordError", password);
        }
        try {
            organisationService.registration(org);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("org", org);
            return new ModelAndView("redirect:/register")
                    .addObject("errorMessage", e.getMessage());

        }
        return new ModelAndView("menu");
    }

    @PostMapping("/logout")
    public String logout() {
        return "menu";
    }
}