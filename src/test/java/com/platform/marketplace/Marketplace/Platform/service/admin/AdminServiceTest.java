package com.platform.marketplace.Marketplace.Platform.service.admin;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.service.event.EventService;
import com.platform.marketplace.Marketplace.Platform.service.organisation.OrganisationService;
import com.platform.marketplace.Marketplace.Platform.utility.Utility;
import com.platform.marketplace.Marketplace.Platform.utility.consts.Role;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AdminService.class})
@ExtendWith(SpringExtension.class)
class AdminServiceTest {
    @Autowired
    private AdminService adminService;

    @MockBean
    private EventService eventService;

    @MockBean
    private OrganisationService organisationService;

    @MockBean
    private Utility utility;

    /**
     * Method under test: {@link AdminService#getAllOrganisations()}
     */
    @Test
    void testGetAllOrganisations() {
        ArrayList<Organisation> organisationList = new ArrayList<>();
        when(organisationService.getAllOrganisations()).thenReturn(organisationList);
        List<Organisation> actualAllOrganisations = adminService.getAllOrganisations();
        assertSame(organisationList, actualAllOrganisations);
        assertTrue(actualAllOrganisations.isEmpty());
        verify(organisationService).getAllOrganisations();
    }

    /**
     * Method under test: {@link AdminService#listAllOrganisationsByRegistrationDateOrder(String)}
     */
    @Test
    void testListAllOrganisationsByRegistrationDateOrder() {
        ArrayList<Organisation> organisationList = new ArrayList<>();
        when(organisationService.getAllOrganisations()).thenReturn(organisationList);
        List<Organisation> actualListAllOrganisationsByRegistrationDateOrderResult = adminService
                .listAllOrganisationsByRegistrationDateOrder("Order");
        assertSame(organisationList, actualListAllOrganisationsByRegistrationDateOrderResult);
        assertTrue(actualListAllOrganisationsByRegistrationDateOrderResult.isEmpty());
        verify(organisationService).getAllOrganisations();
    }

    /**
     * Method under test: {@link AdminService#listAllOrganisationsByRegistrationDateOrder(String)}
     */
    @Test
    void testListAllOrganisationsByRegistrationDateOrder2() {
        ArrayList<Organisation> organisationList = new ArrayList<>();
        when(organisationService.findOrganisationsRegistrationDateByDescOrder()).thenReturn(organisationList);
        when(organisationService.getAllOrganisations()).thenReturn(new ArrayList<>());
        List<Organisation> actualListAllOrganisationsByRegistrationDateOrderResult = adminService
                .listAllOrganisationsByRegistrationDateOrder("desc");
        assertSame(organisationList, actualListAllOrganisationsByRegistrationDateOrderResult);
        assertTrue(actualListAllOrganisationsByRegistrationDateOrderResult.isEmpty());
        verify(organisationService).findOrganisationsRegistrationDateByDescOrder();
    }

    /**
     * Method under test: {@link AdminService#listAllOrganisationsByRegistrationDateOrder(String)}
     */
    @Test
    void testListAllOrganisationsByRegistrationDateOrder3() {
        ArrayList<Organisation> organisationList = new ArrayList<>();
        when(organisationService.findOrganisationsRegistrationDateByAscOrder()).thenReturn(organisationList);
        when(organisationService.findOrganisationsRegistrationDateByDescOrder()).thenReturn(new ArrayList<>());
        when(organisationService.getAllOrganisations()).thenReturn(new ArrayList<>());
        List<Organisation> actualListAllOrganisationsByRegistrationDateOrderResult = adminService
                .listAllOrganisationsByRegistrationDateOrder("asc");
        assertSame(organisationList, actualListAllOrganisationsByRegistrationDateOrderResult);
        assertTrue(actualListAllOrganisationsByRegistrationDateOrderResult.isEmpty());
        verify(organisationService).findOrganisationsRegistrationDateByAscOrder();
    }

    /**
     * Method under test: {@link AdminService#updateOrganisationStatus(Organisation, boolean)}
     */
    @Test
    void testUpdateOrganisationStatus() {
        doNothing().when(organisationService).updateOrganisationStatus((Organisation) any(), anyBoolean());
        adminService.updateOrganisationStatus(new Organisation(), true);
        verify(organisationService).updateOrganisationStatus((Organisation) any(), anyBoolean());
    }

    /**
     * Method under test: {@link AdminService#deleteOrganisationAccountById(Long, String)}
     */
    @Test
    void testDeleteOrganisationAccountById() {
        doNothing().when(organisationService).deleteOrganisationAccountById((Long) any());
        when(utility.authorizationCheck((String) any())).thenReturn(new User("janedoe", "iloveyou", Role.ADMIN, true));
        adminService.deleteOrganisationAccountById(1L, "iloveyou");
        verify(organisationService).deleteOrganisationAccountById((Long) any());
        verify(utility).authorizationCheck((String) any());
    }

    /**
     * Method under test: {@link AdminService#deleteAllAccounts(String)}
     */
    @Test
    void testDeleteAllAccounts() {
        doNothing().when(organisationService).deleteAllOrganisationsAndUsers();
        when(utility.authorizationCheck((String) any())).thenReturn(new User("janedoe", "iloveyou", Role.ADMIN, true));
        adminService.deleteAllAccounts("iloveyou");
        verify(organisationService).deleteAllOrganisationsAndUsers();
        verify(utility).authorizationCheck((String) any());
    }

    /**
     * Method under test: {@link AdminService#deleteEventByOrgId(Long)}
     */
    @Test
    void testDeleteEventByOrgId() {
        doNothing().when(eventService).deleteEventById((Long) any());
        adminService.deleteEventByOrgId(1L);
        verify(eventService).deleteEventById((Long) any());
    }
}

