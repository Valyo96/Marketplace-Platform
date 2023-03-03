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
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoggedOrganisationService {

    private final UserService userService;

    private final OrganisationService organisationService;

    private final EventService eventService;

    private final Utility utility;


    public List<EventDTO> getEventsOfLoggedOrganisationById() {
        User user = utility.returnAuthenticatedUser();
        Organisation org = organisationService.findByEmail(user.getUsername());
        return eventService.findEventsByOrgId(org.getId());
    }

    public String updateLoggedOrganisationAccount(OrganisationUpdateDTO updatedOrganisation) {
        User loggedUser = utility.returnAuthenticatedUser();
       return organisationService.updateOrganisationAccount(updatedOrganisation, loggedUser);
    }

    public void changeLoggedOrganisationPassword(OrgPasswordChange pass) {
        User loggedUser = utility.authorizationCheck(pass.getOldPassword());
        if (utility.passwordConfirmation(pass.getNewPassword(), pass.getConfirmNewPassword())) {
            loggedUser.setPassword(utility.encodePassword(pass.getNewPassword()));
            userService.saveUser(loggedUser);
        }
    }



    public void disableCurrentLoggedAccount(String password) {
        User loggedAccount = utility.authorizationCheck(password);
        Organisation org = organisationService.findOrganisationByUserId(loggedAccount.getId());
            organisationService.updateOrganisationStatus(org , false);
    }
    public void createEventByLoggedOrganisation(EventDTO eventDTO) {
        User user = utility.returnAuthenticatedUser();
        Organisation org = organisationService.findOrganisationByUserId(user.getId());
        eventService.createEvent(eventDTO, org);
    }

    public void updateEventAvailabilityStatusOfEvent(Long eventId, boolean status ){
       User user = utility.returnAuthenticatedUser();
       Organisation org = organisationService.findOrganisationByUserId(user.getId());
       Event event = eventService.getEventByEventIdAndOrgId(org.getId(), eventId);
       eventService.setIsEnabledEventField(event , status);
    }

    public void deleteEventPermanent(Long id , String confirmationPassword){
        User loggedUser = utility.authorizationCheck(confirmationPassword);
        Organisation org = organisationService.findOrganisationByUserId(loggedUser.getId());
        Event event = eventService.getEventByEventIdAndOrgId(org.getId(), id);
        eventService.deleteEvent(event);
    }

    public void updateEventByOrgIdAndEventId(Long eventId , String confirmationPassword){
        User loggedUser = utility.authorizationCheck(confirmationPassword);
        Organisation org = organisationService.findOrganisationByUserId(loggedUser.getId());
        Event event = eventService.getEventByEventIdAndOrgId(org.getId(), eventId);
        EventDTO eventDTO = eventService.getEventDTOById(event.getId());
        eventService.updateEvent(event , eventDTO);
    }
}
