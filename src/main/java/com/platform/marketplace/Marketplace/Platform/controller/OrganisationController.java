package com.platform.marketplace.Marketplace.Platform.controller;

import com.platform.marketplace.Marketplace.Platform.dto.OrgPasswordChange;
import com.platform.marketplace.Marketplace.Platform.dto.OrganisationUpdateDTO;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.service.location.LocationService;
import com.platform.marketplace.Marketplace.Platform.service.organisation.LoggedOrganisationService;
import com.platform.marketplace.Marketplace.Platform.service.organisation.OrganisationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/organisation")
public class OrganisationController {

    private final OrganisationService organisationService;

    private final LoggedOrganisationService loggedOrganisationService;

    private final LocationService locationService;


    @GetMapping("/settings")
    public String orgSettings(Model model , HttpSession session){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Organisation org = organisationService.findByEmail(authentication.getName());
        model.addAttribute("errorMessage" , session.getAttribute("errorMessage"));
        session.removeAttribute("invalidPassword");
        model.addAttribute("organisation" , org);
        model.addAttribute("org" , new OrganisationUpdateDTO());
        model.addAttribute("locations" , locationService.getAllLocations());
        return "organisationSettings";
    }



    @PostMapping("update")
    public ModelAndView updateOrg(@Valid OrganisationUpdateDTO org , HttpSession session ){
        String update ="";
        try{
           update=  loggedOrganisationService.updateLoggedOrganisationAccount(org);
           session.invalidate();
        }catch (Exception e) {
            return new ModelAndView("redirect:/organisation/settings")
                    .addObject("errorMessage" , e.getMessage());
        }

        return new ModelAndView("redirect:/login").addObject("update" , update);
    }


    @PostMapping("change-password")
    public ModelAndView changePassword(@Valid OrgPasswordChange orgPas , BindingResult bindingResult, HttpSession session){
        if(bindingResult.hasErrors()){
            session.setAttribute("invalidPassword" ,bindingResult.getFieldError("newPassword").getDefaultMessage());
            return new ModelAndView("redirect:/organisation/settings");
        }
        try {
            loggedOrganisationService.changeLoggedOrganisationPassword(orgPas);
            session.invalidate();
        }catch (Exception e) {
            session.setAttribute("errorMessage" ,e.getMessage());
            return new ModelAndView("redirect:/organisation/settings");
        }
        return new ModelAndView("redirect:/login");
    }



    @PostMapping("disable")
    public ModelAndView disabledOrganisationAccount(@RequestParam("password") String password) {
            try {
                loggedOrganisationService.disableCurrentLoggedAccount(password);
            } catch (Exception e) {
                return new ModelAndView("redirect:/organisation/settings")
                        .addObject("errorMessage" , e.getMessage());
            }
            return new ModelAndView("redirect:/login");
    }
}
