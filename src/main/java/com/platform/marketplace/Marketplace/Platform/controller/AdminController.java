package com.platform.marketplace.Marketplace.Platform.controller;

import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.service.AdminService;
import com.platform.marketplace.Marketplace.Platform.service.OrganisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    private final OrganisationService organisationService;

    @GetMapping("/organisations")
    public String getAllOrgs( Model model){
        model.addAttribute("orgs" , adminService.getAllOrganisations());
        return "admin";
    }

    @GetMapping("/edit-organisation-status/{id}")
    public String edintOrgStatus(@PathVariable("id") Long id ,Model model){
        Organisation org = organisationService.findOrganisationById(id);
        model.addAttribute("org" , org);
        return "admin";
    }

    @PostMapping("update")
    public ModelAndView updateOrgStatus(Organisation org , @Param("status") boolean status){
        adminService.updateOrganisationStatus(org ,status);
        return new ModelAndView("redirect:/admin");
    }
}
