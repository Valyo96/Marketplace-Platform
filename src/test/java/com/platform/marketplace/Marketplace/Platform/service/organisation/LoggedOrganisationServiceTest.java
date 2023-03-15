package com.platform.marketplace.Marketplace.Platform.service.organisation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.dto.OrgPasswordChange;
import com.platform.marketplace.Marketplace.Platform.dto.OrganisationUpdateDTO;
import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.service.event.EventService;
import com.platform.marketplace.Marketplace.Platform.service.user.UserService;
import com.platform.marketplace.Marketplace.Platform.utility.Utility;
import com.platform.marketplace.Marketplace.Platform.utility.consts.Role;

import java.util.ArrayList;
import java.util.List;

import com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LoggedOrganisationService.class})
@ExtendWith(SpringExtension.class)
class LoggedOrganisationServiceTest {
    @MockBean
    private EventService eventService;

    @Autowired
    private LoggedOrganisationService loggedOrganisationService;

    @MockBean
    private OrganisationService organisationService;

    @MockBean
    private UserService userService;

    @MockBean
    private Utility utility;

    /**
     * Method under test: {@link LoggedOrganisationService#getEventsOfLoggedOrganisationById()}
     */
    @Test
    void testGetEventsOfLoggedOrganisationById() {
        when(organisationService.findByEmail((String) any())).thenReturn(new Organisation());
        ArrayList<EventDTO> eventDTOList = new ArrayList<>();
        when(eventService.findEventsByOrgId((Long) any())).thenReturn(eventDTOList);
        when(utility.returnAuthenticatedUser()).thenReturn(new User("janedoe", "iloveyou", Role.ADMIN, true));
        List<EventDTO> actualEventsOfLoggedOrganisationById = loggedOrganisationService
                .getEventsOfLoggedOrganisationById();
        assertSame(eventDTOList, actualEventsOfLoggedOrganisationById);
        assertTrue(actualEventsOfLoggedOrganisationById.isEmpty());
        verify(organisationService).findByEmail((String) any());
        verify(eventService).findEventsByOrgId((Long) any());
        verify(utility).returnAuthenticatedUser();
    }


    /**
     * Method under test: {@link LoggedOrganisationService#updateLoggedOrganisationAccount(OrganisationUpdateDTO)}
     */
    @Test
    void testUpdateLoggedOrganisationAccount() {
        doNothing().when(organisationService).updateOrganisationAccount((OrganisationUpdateDTO) any(), (User) any());
        when(utility.returnAuthenticatedUser()).thenReturn(new User("janedoe", "iloveyou", Role.ADMIN, true));
        loggedOrganisationService.updateLoggedOrganisationAccount(new OrganisationUpdateDTO());
        verify(organisationService).updateOrganisationAccount((OrganisationUpdateDTO) any(), (User) any());
        verify(utility).returnAuthenticatedUser();
    }

    /**
     * Method under test: {@link LoggedOrganisationService#changeLoggedOrganisationPassword(OrgPasswordChange)}
     */
    @Test
    void testChangeLoggedOrganisationPassword() {
        doNothing().when(userService).saveUser((User) any());
        when(utility.encodePassword((String) any())).thenReturn("secret");
        when(utility.passwordConfirmation((String) any(), (String) any())).thenReturn(true);
        when(utility.authorizationCheck((String) any())).thenReturn(new User("janedoe", "iloveyou", Role.ADMIN, true));
        loggedOrganisationService
                .changeLoggedOrganisationPassword(new OrgPasswordChange("iloveyou", "iloveyou", "iloveyou"));
        verify(userService).saveUser((User) any());
        verify(utility).passwordConfirmation((String) any(), (String) any());
        verify(utility).authorizationCheck((String) any());
        verify(utility).encodePassword((String) any());
    }

    /**
     * Method under test: {@link LoggedOrganisationService#changeLoggedOrganisationPassword(OrgPasswordChange)}
     */
    @Test
    void testChangeLoggedOrganisationPassword2() {
        doNothing().when(userService).saveUser((User) any());
        when(utility.encodePassword((String) any())).thenReturn("secret");
        when(utility.passwordConfirmation((String) any(), (String) any())).thenReturn(false);
        when(utility.authorizationCheck((String) any())).thenReturn(new User("janedoe", "iloveyou", Role.ADMIN, true));
        loggedOrganisationService
                .changeLoggedOrganisationPassword(new OrgPasswordChange("iloveyou", "iloveyou", "iloveyou"));
        verify(utility).passwordConfirmation((String) any(), (String) any());
        verify(utility).authorizationCheck((String) any());
    }


    /**
     * Method under test: {@link LoggedOrganisationService#disableCurrentLoggedAccount(String)}
     */
    @Test
    void testDisableCurrentLoggedAccount() {
        when(organisationService.findOrganisationByUserId((Long) any())).thenReturn(new Organisation());
        doNothing().when(organisationService).updateOrganisationStatus((Organisation) any(), anyBoolean());
        when(utility.authorizationCheck((String) any())).thenReturn(new User("janedoe", "iloveyou", Role.ADMIN, true));
        loggedOrganisationService.disableCurrentLoggedAccount("iloveyou");
        verify(organisationService).findOrganisationByUserId((Long) any());
        verify(organisationService).updateOrganisationStatus((Organisation) any(), anyBoolean());
        verify(utility).authorizationCheck((String) any());
    }

    /**
     * Method under test: {@link LoggedOrganisationService#createEventByLoggedOrganisation(EventDTO)}
     */
    @Test
    void testCreateEventByLoggedOrganisation() {
        when(organisationService.findOrganisationByUserId((Long) any())).thenReturn(new Organisation());
        doNothing().when(eventService).createEvent((EventDTO) any(), (Organisation) any());
        when(utility.returnAuthenticatedUser()).thenReturn(new User("janedoe", "iloveyou", Role.ADMIN, true));
        EventDTO eventDTO = new EventDTO();
        loggedOrganisationService.createEventByLoggedOrganisation(eventDTO);
        verify(organisationService).findOrganisationByUserId((Long) any());
        verify(eventService).createEvent((EventDTO) any(), (Organisation) any());
        verify(utility).returnAuthenticatedUser();
        assertNull(eventDTO.getOrganisationId());
    }






    /**
     * Method under test: {@link LoggedOrganisationService#deleteEventPermanent(Long, String)}
     */
    @Test
    void testDeleteEventPermanent() {
        when(organisationService.findOrganisationByUserId((Long) any())).thenReturn(new Organisation());
        when(eventService.getEventByEventIdAndOrgId((Long) any(), (Long) any())).thenReturn(new Event());
        doNothing().when(eventService).deleteEvent((Event) any());
        when(utility.authorizationCheck((String) any())).thenReturn(new User("janedoe", "iloveyou", Role.ADMIN, true));
        loggedOrganisationService.deleteEventPermanent(1L, "iloveyou");
        verify(organisationService).findOrganisationByUserId((Long) any());
        verify(eventService).getEventByEventIdAndOrgId((Long) any(), (Long) any());
        verify(eventService).deleteEvent((Event) any());
        verify(utility).authorizationCheck((String) any());
    }




    /**
     * Method under test: {@link LoggedOrganisationService#updateEventByOrgIdAndEventId(Long, EventDTO)}
     */
    @Test
    void testUpdateEventByOrgIdAndEventId() {
        when(organisationService.findOrganisationByUserId((Long) any())).thenReturn(new Organisation());
        when(eventService.getEventByEventIdAndOrgId((Long) any(), (Long) any())).thenReturn(new Event());
        doNothing().when(eventService).updateEvent((Event) any(), (EventDTO) any());
        when(utility.returnAuthenticatedUser()).thenReturn(new User("janedoe", "iloveyou", Role.ADMIN, true));
        loggedOrganisationService.updateEventByOrgIdAndEventId(1L, new EventDTO());
        verify(organisationService).findOrganisationByUserId((Long) any());
        verify(eventService).getEventByEventIdAndOrgId((Long) any(), (Long) any());
        verify(eventService).updateEvent((Event) any(), (EventDTO) any());
        verify(utility).returnAuthenticatedUser();
    }



}

