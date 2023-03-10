package com.platform.marketplace.Marketplace.Platform.service.admin;

import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.service.event.EventService;
import com.platform.marketplace.Marketplace.Platform.service.organisation.OrganisationService;
import com.platform.marketplace.Marketplace.Platform.utility.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AdminService {

    private final OrganisationService organisationService;

    private final Utility utility;

    private final EventService eventService;


    public List<Organisation> getAllOrganisations() {
        return organisationService.getAllOrganisations();
    }

    public List<Organisation> listAllOrganisationsByRegistrationDateOrder(String order) {
        if (order.equals("desc")) {
            return organisationService.findOrganisationsRegistrationDateByDescOrder();
        } else if (order.equals("asc")) {
            return organisationService.findOrganisationsRegistrationDateByAscOrder();
        } else {
            return getAllOrganisations();
        }
    }

    public void updateOrganisationStatus(Organisation org, boolean status) {
        organisationService.updateOrganisationStatus(org, status);
    }

    public void deleteOrganisationAccountById(Long id, String password) {
        utility.authorizationCheck(password);
        organisationService.deleteOrganisationAccountById(id);
    }

    public void deleteAllAccounts(String password) {
        utility.authorizationCheck(password);
        organisationService.deleteAllOrganisationsAndUsers();
    }

    public void deleteEventByOrgId(Long id ){
        eventService.deleteEventById(id);
    }


}
