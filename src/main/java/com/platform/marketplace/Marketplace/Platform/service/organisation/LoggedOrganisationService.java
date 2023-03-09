package com.platform.marketplace.Marketplace.Platform.service.organisation;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.dto.OrgPasswordChange;
import com.platform.marketplace.Marketplace.Platform.dto.OrganisationUpdateDTO;
import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.service.event.EventService;
import com.platform.marketplace.Marketplace.Platform.service.user.UserService;
import com.platform.marketplace.Marketplace.Platform.utility.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoggedOrganisationService {

    private final UserService userService;

    private final OrganisationService organisationService;

    private final EventService eventService;

    private final Utility validate;


    public List<EventDTO> getEventsOfLoggedOrganisationById() {
        User user = validate.returnAuthenticatedUser();
        Organisation org = organisationService.findByEmail(user.getUsername());
        return eventService.findEventsByOrgId(org.getId());
    }

    public void updateLoggedOrganisationAccount(OrganisationUpdateDTO updatedOrganisation) {
        User loggedUser = validate.returnAuthenticatedUser();
        organisationService.updateOrganisationAccount(updatedOrganisation, loggedUser);
    }

    public void changeLoggedOrganisationPassword(OrgPasswordChange pass) {
        User loggedUser = validate.authorizationCheck(pass.getOldPassword());
        if (validate.passwordConfirmation(pass.getNewPassword(), pass.getConfirmNewPassword())) {
            loggedUser.setPassword(validate.encodePassword(pass.getNewPassword()));
            userService.saveUser(loggedUser);
        }
    }



    public void disableCurrentLoggedAccount(String password) {
        User loggedAccount = validate.authorizationCheck(password);
        Organisation org = organisationService.findOrganisationByUserId(loggedAccount.getId());
            organisationService.updateOrganisationStatus(org , false);
    }
    public void createEventByLoggedOrganisation(EventDTO eventDTO) {
        User user = validate.returnAuthenticatedUser();
        Organisation org = organisationService.findOrganisationByUserId(user.getId());
        eventDTO.setOrganisationId(org.getId());
        eventService.createEvent(eventDTO, org);
    }

//    public void updateEventAvailabilityStatusOfEvent(Long eventId, boolean status ){
//       User user = utility.returnAuthenticatedUser();
//       Organisation org = organisationService.findOrganisationByUserId(user.getId());
//       Event event = eventService.getEventByEventIdAndOrgId(org.getId(), eventId);
//       eventService.setIsEnabledEventField(event , status);
//    }

    public void deleteEventPermanent(Long id , String confirmationPassword){
        User loggedUser = validate.authorizationCheck(confirmationPassword);
        Organisation org = organisationService.findOrganisationByUserId(loggedUser.getId());
        Event event = eventService.getEventByEventIdAndOrgId(org.getId(), id);
        eventService.deleteEvent(event);
    }

    public void updateEventByOrgIdAndEventId(Long eventId , EventDTO eventDTO){
        User loggedUser = validate.returnAuthenticatedUser();
        Organisation org = organisationService.findOrganisationByUserId(loggedUser.getId());
        Event event = eventService.getEventByEventIdAndOrgId(org.getId(), eventId);
        eventService.updateEvent(event , eventDTO);
    }
}
