package com.platform.marketplace.Marketplace.Platform.controller;

import com.platform.marketplace.Marketplace.Platform.dto.OrgPasswordChange;
import com.platform.marketplace.Marketplace.Platform.dto.OrganisationUpdateDTO;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.service.LoggedOrgsService;
import com.platform.marketplace.Marketplace.Platform.service.OrganisationService;
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

    private final LoggedOrgsService loggedOrgsService;


    @GetMapping("/settings")
    public String orgSettings(Model model){

        model.addAttribute("org" , new OrganisationUpdateDTO());
        return "organisationSettings";
    }



    @PostMapping("update")
    public ModelAndView updateOrg(@Valid OrganisationUpdateDTO org ){
        try{
            loggedOrgsService.updateLoggedOrganisationAccount(org);
        }catch (Exception e) {
            return new ModelAndView("redirect:/organisation/settings")
                    .addObject("errorMessage" , e.getMessage());
        }

        return new ModelAndView("redirect:/organisation/settings");
    }


    @PostMapping("change-password")
    public ModelAndView changePassword(@Valid OrgPasswordChange orgPas , BindingResult bindingResult, HttpSession session){
        if(bindingResult.hasErrors()){
            session.setAttribute("invalidPassword" ,bindingResult.getFieldError("newPassword").getDefaultMessage());
            return new ModelAndView("redirect:/organisation/change-password");
        }
        try {
            loggedOrgsService.changeLoggedOrganisationPassword(orgPas);
        }catch (Exception e) {
            session.setAttribute("errorMessage" ,e.getMessage());
            return new ModelAndView("redirect:/organisation/change-password");
        }
        return new ModelAndView("organisationSettings");
    }


//    @PostMapping("")
    @PostMapping("/delete")
    public ModelAndView deleteOrganisation(@RequestParam("password") String password) {
            try {
                loggedOrgsService.deleteCurrentLoggedAccount(password);
            } catch (Exception e) {
                return new ModelAndView("redirect:/organisations")
                        .addObject("errorMessage" , e.getMessage());
            }
            return new ModelAndView("redirect:/organisations");
    }
}
