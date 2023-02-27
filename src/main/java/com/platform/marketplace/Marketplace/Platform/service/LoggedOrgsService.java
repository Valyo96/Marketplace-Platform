package com.platform.marketplace.Marketplace.Platform.service;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.dto.OrgPasswordChange;
import com.platform.marketplace.Marketplace.Platform.dto.OrganisationUpdateDTO;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotAuthorizeException;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.utility.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.platform.marketplace.Marketplace.Platform.utility.consts.ConstantMessages.*;

@Service
@RequiredArgsConstructor
public class LoggedOrgsService {

    private final UserService userService;

    private final OrganisationService organisationService;

    private final EventService eventService;

    private final Utility utility;


    public List<EventDTO> getEventsOfLoggedOrganisationById() {
        User user = utility.returnAuthenticatedUser();
        Organisation org = organisationService.findByEmail(user.getUsername());
        return eventService.findEventsByOrgId(org.getId());
    }

    public void updateLoggedOrganisationAccount(OrganisationUpdateDTO updatedOrganisation) {
        User loggedUser = utility.returnAuthenticatedUser();
//        Organisation org = organisationService.findOrganisationByUserId(loggedUser.getId());
        organisationService.updateOrganisationAccount(updatedOrganisation, loggedUser);

    }

    public void changeLoggedOrganisationPassword(OrgPasswordChange pass) {
        User loggedUser = utility.authorizationCheck(pass.getOldPassword());
        if (utility.passwordConfirmation(pass.getNewPassword(), pass.getConfirmNewPassword())) {
            loggedUser.setPassword(utility.encodePassword(pass.getNewPassword()));
            userService.saveUser(loggedUser);
        }
    }

    public void createEventByLoggedOrganisation(EventDTO eventDTO) {
        User user = utility.returnAuthenticatedUser();
        Organisation org = organisationService.findOrganisationByUserId(user.getId());
        eventService.createEvent(eventDTO, org);
    }

    public void deleteCurrentLoggedAccount(String password) {
        User loggedAccount = utility.authorizationCheck(password);
        Organisation org = organisationService.findOrganisationByUserId(loggedAccount.getId());

            organisationService.deleteOrganisationAccount(org);
            userService.deleteUserByOrganizationId(org.getId());

    }


//    public void updateEventById(EventDTO eventDTO){
//        Event event = eventRepository.findById(eventDTO.getId()).orElseThrow(()-> new NotFoundException(eventByNameNotFound));
//        event.se
//    }
}
