package com.platform.marketplace.Marketplace.Platform.service.organisation;

import com.platform.marketplace.Marketplace.Platform.dto.OrganisationDTO;
import com.platform.marketplace.Marketplace.Platform.dto.OrganisationUpdateDTO;
import com.platform.marketplace.Marketplace.Platform.mapper.OrganisationRegDtoToOrganisationMapper;
import com.platform.marketplace.Marketplace.Platform.mapper.OrganisationRegDtoUserMapper;
import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.repository.EventRepository;
import com.platform.marketplace.Marketplace.Platform.repository.OrganisationRepository;
import com.platform.marketplace.Marketplace.Platform.service.user.UserService;
import com.platform.marketplace.Marketplace.Platform.utility.Utility;
import com.platform.marketplace.Marketplace.Platform.utility.consts.Role;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.AlreadyExistException;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {OrganisationService.class})
@ExtendWith(SpringExtension.class)
class OrganisationServiceTest {
    @MockBean
    private EventRepository eventRepository;

    @MockBean
    private OrganisationRegDtoToOrganisationMapper organisationRegDtoToOrganisationMapper;

    @MockBean
    private OrganisationRepository organisationRepository;

    @Autowired
    private OrganisationService organisationService;

    @MockBean
    private UserService userService;

    @MockBean
    private Utility utility;
    @MockBean
    private BCryptPasswordEncoder passwordEncoder;
    @MockBean
    private OrganisationRegDtoUserMapper organisationRegDtoUserMapper;

    /**
     * Method under test: {@link OrganisationService#register(OrganisationDTO)}
     */
    @Test
    void testRegistration() {
        OrganisationDTO organisationDTO = new OrganisationDTO("Организация", "test@abv.bg", "Parola1234!", "Parola1234!", null);
        User user = new User(organisationDTO.getEmail(), organisationDTO.getPassword(), Role.ADMIN, true);
        Organisation organisation = new Organisation(organisationDTO.getName(), user, null);
        when(organisationRegDtoToOrganisationMapper.apply((OrganisationDTO) any())).thenReturn(organisation);
//        when(organisationRegDtoUserMapper.apply(organisationDTO)).thenReturn(user);
        organisationService.register(organisationDTO);

    }

    /**
     * Method under test: {@link OrganisationService#register(OrganisationDTO)}
     */
    @Test
    void testRegistration2() {
        when(organisationRepository.save((Organisation) any())).thenReturn(new Organisation());
        doNothing().when(userService).saveUser((User) any());
        when(utility.passwordConfirmation((String) any(), (String) any())).thenReturn(true);
        when(utility.checkIfEmailExists((String) any())).thenReturn(true);
        when(utility.encodePassword((String) any())).thenReturn("secret");

        Organisation organisation = new Organisation();
        organisation.setUser(new User("janedoe", "iloveyou", Role.ADMIN, true));
        when(organisationRegDtoToOrganisationMapper.apply((OrganisationDTO) any())).thenReturn(organisation);
        organisationService.register(new OrganisationDTO());
        verify(organisationRepository).save((Organisation) any());
        verify(userService).saveUser((User) any());
        verify(utility).checkIfEmailExists((String) any());
        verify(utility).passwordConfirmation((String) any(), (String) any());
        verify(utility).encodePassword((String) any());
        verify(organisationRegDtoToOrganisationMapper).apply((OrganisationDTO) any());
    }

    /**
     * Method under test: {@link OrganisationService#register(OrganisationDTO)}
     */
    @Test
    void testRegistration4() {
        when(organisationRepository.save((Organisation) any())).thenReturn(new Organisation());
        doNothing().when(userService).saveUser((User) any());
        when(utility.passwordConfirmation((String) any(), (String) any())).thenReturn(false);
        when(utility.checkIfEmailExists((String) any())).thenReturn(true);
        when(utility.encodePassword((String) any())).thenReturn("secret");

        Organisation organisation = new Organisation();
        organisation.setUser(new User("janedoe", "iloveyou", Role.ORGANISATION, true));
        when(organisationRegDtoToOrganisationMapper.apply((OrganisationDTO) any())).thenReturn(organisation);
        assertThrows(AlreadyExistException.class, () -> organisationService.register(new OrganisationDTO()));

        verify(utility).checkIfEmailExists((String) any());

    }

    /**
     * Method under test: {@link OrganisationService#register(OrganisationDTO)}
     */
    @Test
    void testRegistration5() {
        when(organisationRepository.save((Organisation) any())).thenReturn(new Organisation());
        doNothing().when(userService).saveUser((User) any());
        when(utility.passwordConfirmation((String) any(), (String) any())).thenReturn(true);
        when(utility.checkIfEmailExists((String) any())).thenReturn(false);
        when(utility.encodePassword((String) any())).thenReturn("secret");

        Organisation organisation = new Organisation();
        organisation.setUser(new User("janedoe", "iloveyou", Role.ADMIN, true));
        when(organisationRegDtoToOrganisationMapper.apply((OrganisationDTO) any())).thenReturn(organisation);
        organisationService.register(new OrganisationDTO());
        verify(organisationRepository).save((Organisation) any());
        verify(userService).saveUser((User) any());
        verify(utility).checkIfEmailExists((String) any());
        verify(utility).encodePassword((String) any());
        verify(organisationRegDtoToOrganisationMapper).apply((OrganisationDTO) any());
    }


    /**
     * Method under test: {@link OrganisationService#updateOrganisationAccount(OrganisationUpdateDTO, User)}
     */
    @Test
    void testUpdateOrganisationAccount2() {
        when(organisationRepository.save((Organisation) any())).thenReturn(new Organisation());
        when(organisationRepository.findOrganisationByUserId((Long) any())).thenReturn(Optional.of(new Organisation()));
        doNothing().when(userService).saveUser((User) any());
        when(utility.checkIfEmailExists((String) any())).thenThrow(new AlreadyExistException("An error occurred"));
        OrganisationUpdateDTO updatedOrganisation = new OrganisationUpdateDTO();
        assertThrows(AlreadyExistException.class, () -> organisationService.updateOrganisationAccount(updatedOrganisation,
                new User("janedoe", "iloveyou", Role.ORGANISATION, true)));
        verify(utility).checkIfEmailExists((String) any());
    }

    /**
     * Method under test: {@link OrganisationService#updateOrganisationAccount(OrganisationUpdateDTO, User)}
     */
    @Test
    void testUpdateOrganisationAccount3() {
        when(organisationRepository.save((Organisation) any())).thenReturn(new Organisation());
        when(organisationRepository.findOrganisationByUserId((Long) any())).thenReturn(Optional.of(new Organisation()));
        doNothing().when(userService).saveUser((User) any());
        when(utility.checkIfEmailExists((String) any())).thenReturn(true);

        OrganisationUpdateDTO organisationUpdateDTO = new OrganisationUpdateDTO();
        organisationUpdateDTO.setEmail("jane.doe@example.org");
        assertThrows(AlreadyExistException.class, () -> organisationService
                .updateOrganisationAccount(organisationUpdateDTO, new User("janedoe", "iloveyou", Role.ORGANISATION, true)));
        verify(utility).checkIfEmailExists((String) any());
    }


    /**
     * Method under test: {@link OrganisationService#updateOrganisationAccount(OrganisationUpdateDTO, User)}
     */
    @Test
    void testUpdateOrganisationAccount6() {
        when(organisationRepository.save((Organisation) any())).thenReturn(new Organisation());
        when(organisationRepository.findOrganisationByUserId((Long) any())).thenReturn(Optional.of(new Organisation()));
        doNothing().when(userService).saveUser((User) any());
        when(utility.checkIfEmailExists((String) any())).thenReturn(false);

        OrganisationUpdateDTO organisationUpdateDTO = new OrganisationUpdateDTO();
        organisationUpdateDTO.setEmail("jane.doe@example.org");
        User user = new User("janedoe", "iloveyou", Role.ORGANISATION, true);

        organisationService.updateOrganisationAccount(organisationUpdateDTO, user);
        verify(organisationRepository).save((Organisation) any());
        verify(organisationRepository).findOrganisationByUserId((Long) any());
        verify(userService).saveUser((User) any());
        verify(utility).checkIfEmailExists((String) any());
        assertEquals("jane.doe@example.org", user.getUsername());
    }

    /**
     * Method under test: {@link OrganisationService#updateOrganisationAccount(OrganisationUpdateDTO, User)}
     */
    @Test
    void testUpdateOrganisationAccount7() {
        when(organisationRepository.save((Organisation) any())).thenReturn(new Organisation());
        when(organisationRepository.findOrganisationByUserId((Long) any())).thenReturn(Optional.of(new Organisation()));
        doNothing().when(userService).saveUser((User) any());
        when(utility.checkIfEmailExists((String) any())).thenReturn(true);

        OrganisationUpdateDTO organisationUpdateDTO = new OrganisationUpdateDTO();
        organisationUpdateDTO.setEmail("janedoe");
        User user = new User("janedoe", "iloveyou", Role.ORGANISATION, true);

        organisationService.updateOrganisationAccount(organisationUpdateDTO, user);
        verify(organisationRepository).save((Organisation) any());
        verify(organisationRepository).findOrganisationByUserId((Long) any());
        verify(userService).saveUser((User) any());
        verify(utility).checkIfEmailExists((String) any());
        assertEquals("janedoe", user.getUsername());
    }


    /**
     * Method under test: {@link OrganisationService#updateOrganisationStatus(Organisation, boolean)}
     */
    @Test
    void testUpdateOrganisationStatus2() {
        doNothing().when(userService).saveUser((User) any());
        when(eventRepository.saveAll((Iterable<Event>) any())).thenReturn(new ArrayList<>());
        when(eventRepository.findEventsByOrganisationId((Long) any())).thenReturn(new ArrayList<>());

        Organisation organisation = new Organisation();
        organisation.setUser(new User("janedoe", "iloveyou", Role.ADMIN, true));
        organisationService.updateOrganisationStatus(organisation, true);
        verify(userService).saveUser((User) any());
        verify(eventRepository).findEventsByOrganisationId((Long) any());
        verify(eventRepository).saveAll((Iterable<Event>) any());
        assertTrue(organisation.getUser().isEnabled());
    }

    /**
     * Method under test: {@link OrganisationService#updateOrganisationStatus(Organisation, boolean)}
     */
    @Test
    void testUpdateOrganisationStatus3() {
        doNothing().when(userService).saveUser((User) any());
        when(eventRepository.saveAll((Iterable<Event>) any())).thenThrow(new AlreadyExistException("An error occurred"));
        when(eventRepository.findEventsByOrganisationId((Long) any())).thenReturn(new ArrayList<>());

        Organisation organisation = new Organisation();
        organisation.setUser(new User("janedoe", "iloveyou", Role.ADMIN, true));
        assertThrows(AlreadyExistException.class, () -> organisationService.updateOrganisationStatus(organisation, true));
        verify(eventRepository).findEventsByOrganisationId((Long) any());
        verify(eventRepository).saveAll((Iterable<Event>) any());
    }

    /**
     * Method under test: {@link OrganisationService#updateOrganisationStatus(Organisation, boolean)}
     */
    @Test
    void testUpdateOrganisationStatus4() {
        doNothing().when(userService).saveUser((User) any());
        when(eventRepository.saveAll((Iterable<Event>) any())).thenReturn(new ArrayList<>());
        when(eventRepository.findEventsByOrganisationId((Long) any())).thenReturn(new ArrayList<>());

        Organisation organisation = new Organisation();
        organisation.setUser(new User("janedoe", "iloveyou", Role.ADMIN, false));
        organisationService.updateOrganisationStatus(organisation, true);
        verify(userService).saveUser((User) any());
        verify(eventRepository).findEventsByOrganisationId((Long) any());
        verify(eventRepository).saveAll((Iterable<Event>) any());
        assertTrue(organisation.getUser().isEnabled());
    }

    /**
     * Method under test: {@link OrganisationService#updateOrganisationStatus(Organisation, boolean)}
     */
    @Test
    void testUpdateOrganisationStatus5() {
        doNothing().when(userService).saveUser((User) any());
        when(eventRepository.saveAll((Iterable<Event>) any())).thenReturn(new ArrayList<>());
        when(eventRepository.findEventsByOrganisationId((Long) any())).thenReturn(new ArrayList<>());

        Organisation organisation = new Organisation();
        organisation.setUser(new User("janedoe", "iloveyou", Role.ADMIN, true));
        organisationService.updateOrganisationStatus(organisation, false);
        verify(userService).saveUser((User) any());
        verify(eventRepository).findEventsByOrganisationId((Long) any());
        verify(eventRepository).saveAll((Iterable<Event>) any());
        assertFalse(organisation.getUser().isEnabled());
    }

    /**
     * Method under test: {@link OrganisationService#updateOrganisationStatus(Organisation, boolean)}
     */
    @Test
    void testUpdateOrganisationStatus6() {
        doNothing().when(userService).saveUser((User) any());

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.saveAll((Iterable<Event>) any())).thenReturn(new ArrayList<>());
        when(eventRepository.findEventsByOrganisationId((Long) any())).thenReturn(eventList);

        Organisation organisation = new Organisation();
        organisation.setUser(new User("janedoe", "iloveyou", Role.ADMIN, false));
        organisationService.updateOrganisationStatus(organisation, true);
        verify(userService).saveUser((User) any());
        verify(eventRepository).findEventsByOrganisationId((Long) any());
        verify(eventRepository).saveAll((Iterable<Event>) any());
        assertTrue(organisation.getUser().isEnabled());
    }

    /**
     * Method under test: {@link OrganisationService#updateOrganisationStatus(Organisation, boolean)}
     */
    @Test
    void testUpdateOrganisationStatus7() {
        doNothing().when(userService).saveUser((User) any());

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        eventList.add(new Event());
        when(eventRepository.saveAll((Iterable<Event>) any())).thenReturn(new ArrayList<>());
        when(eventRepository.findEventsByOrganisationId((Long) any())).thenReturn(eventList);

        Organisation organisation = new Organisation();
        organisation.setUser(new User("janedoe", "iloveyou", Role.ADMIN, false));
        organisationService.updateOrganisationStatus(organisation, true);
        verify(userService).saveUser((User) any());
        verify(eventRepository).findEventsByOrganisationId((Long) any());
        verify(eventRepository).saveAll((Iterable<Event>) any());
        assertTrue(organisation.getUser().isEnabled());
    }

    /**
     * Method under test: {@link OrganisationService#updateOrganisationStatus(Organisation, boolean)}
     */
    @Test
    void testUpdateOrganisationStatus8() {
        doNothing().when(userService).saveUser((User) any());

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.saveAll((Iterable<Event>) any())).thenReturn(new ArrayList<>());
        when(eventRepository.findEventsByOrganisationId((Long) any())).thenReturn(eventList);

        Organisation organisation = new Organisation();
        organisation.setUser(new User("janedoe", "iloveyou", Role.ADMIN, true));
        organisationService.updateOrganisationStatus(organisation, false);
        verify(userService).saveUser((User) any());
        verify(eventRepository).findEventsByOrganisationId((Long) any());
        verify(eventRepository).saveAll((Iterable<Event>) any());
        assertFalse(organisation.getUser().isEnabled());
    }

    /**
     * Method under test: {@link OrganisationService#updateOrganisationStatus(Organisation, boolean)}
     */
    @Test
    void testUpdateOrganisationStatus9() {
        doNothing().when(userService).saveUser((User) any());

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        eventList.add(new Event());
        when(eventRepository.saveAll((Iterable<Event>) any())).thenReturn(new ArrayList<>());
        when(eventRepository.findEventsByOrganisationId((Long) any())).thenReturn(eventList);

        Organisation organisation = new Organisation();
        organisation.setUser(new User("janedoe", "iloveyou", Role.ADMIN, true));
        organisationService.updateOrganisationStatus(organisation, false);
        verify(userService).saveUser((User) any());
        verify(eventRepository).findEventsByOrganisationId((Long) any());
        verify(eventRepository).saveAll((Iterable<Event>) any());
        assertFalse(organisation.getUser().isEnabled());
    }

    /**
     * Method under test: {@link OrganisationService#deleteOrganisationAccountsThatAreInactiveMoreThanSixMonths(LocalDateTime)}
     */
    @Test
    void testDeleteOrganisationAccountsThatAreInactiveMoreThanSixMonths() {
        when(organisationRepository.findByIsEnabledFalseAndDisabledPeriodEquals((LocalDateTime) any()))
                .thenReturn(new ArrayList<>());
        doNothing().when(organisationRepository).deleteAll((Iterable<Organisation>) any());
        organisationService.deleteOrganisationAccountsThatAreInactiveMoreThanSixMonths(LocalDateTime.of(1, 1, 1, 1, 1));
        verify(organisationRepository).findByIsEnabledFalseAndDisabledPeriodEquals((LocalDateTime) any());
        verify(organisationRepository).deleteAll((Iterable<Organisation>) any());
    }

    /**
     * Method under test: {@link OrganisationService#deleteOrganisationAccountsThatAreInactiveMoreThanSixMonths(LocalDateTime)}
     */
    @Test
    void testDeleteOrganisationAccountsThatAreInactiveMoreThanSixMonths2() {
        ArrayList<Organisation> organisationList = new ArrayList<>();
        organisationList.add(new Organisation());
        when(organisationRepository.findByIsEnabledFalseAndDisabledPeriodEquals((LocalDateTime) any()))
                .thenReturn(organisationList);
        doNothing().when(organisationRepository).deleteAll((Iterable<Organisation>) any());
        doNothing().when(userService).deleteUserById((Long) any());
        organisationService.deleteOrganisationAccountsThatAreInactiveMoreThanSixMonths(LocalDateTime.of(1, 1, 1, 1, 1));
        verify(organisationRepository).findByIsEnabledFalseAndDisabledPeriodEquals((LocalDateTime) any());
        verify(organisationRepository).deleteAll((Iterable<Organisation>) any());
        verify(userService).deleteUserById((Long) any());
    }

    /**
     * Method under test: {@link OrganisationService#deleteOrganisationAccountsThatAreInactiveMoreThanSixMonths(LocalDateTime)}
     */
    @Test
    void testDeleteOrganisationAccountsThatAreInactiveMoreThanSixMonths3() {
        ArrayList<Organisation> organisationList = new ArrayList<>();
        organisationList.add(new Organisation());
        organisationList.add(new Organisation());
        when(organisationRepository.findByIsEnabledFalseAndDisabledPeriodEquals((LocalDateTime) any()))
                .thenReturn(organisationList);
        doNothing().when(organisationRepository).deleteAll((Iterable<Organisation>) any());
        doNothing().when(userService).deleteUserById((Long) any());
        organisationService.deleteOrganisationAccountsThatAreInactiveMoreThanSixMonths(LocalDateTime.of(1, 1, 1, 1, 1));
        verify(organisationRepository).findByIsEnabledFalseAndDisabledPeriodEquals((LocalDateTime) any());
        verify(organisationRepository).deleteAll((Iterable<Organisation>) any());
        verify(userService, atLeast(1)).deleteUserById((Long) any());
    }

    /**
     * Method under test: {@link OrganisationService#deleteOrganisationAccountsThatAreInactiveMoreThanSixMonths(LocalDateTime)}
     */
    @Test
    void testDeleteOrganisationAccountsThatAreInactiveMoreThanSixMonths5() {
        ArrayList<Organisation> organisationList = new ArrayList<>();
        organisationList.add(new Organisation());
        when(organisationRepository.findByIsEnabledFalseAndDisabledPeriodEquals((LocalDateTime) any()))
                .thenReturn(organisationList);
        doNothing().when(organisationRepository).deleteAll((Iterable<Organisation>) any());
        doThrow(new AlreadyExistException("An error occurred")).when(userService).deleteUserById((Long) any());
        assertThrows(AlreadyExistException.class, () -> organisationService
                .deleteOrganisationAccountsThatAreInactiveMoreThanSixMonths(LocalDateTime.of(1, 1, 1, 1, 1)));
        verify(organisationRepository).findByIsEnabledFalseAndDisabledPeriodEquals((LocalDateTime) any());
        verify(organisationRepository).deleteAll((Iterable<Organisation>) any());
        verify(userService).deleteUserById((Long) any());
    }

    /**
     * Method under test: {@link OrganisationService#deleteOrganisationAccountById(Long)}
     */
    @Test
    void testDeleteOrganisationAccountById() {
        doNothing().when(organisationRepository).delete((Organisation) any());
        when(organisationRepository.findById((Long) any())).thenReturn(Optional.of(new Organisation()));
        doNothing().when(userService).deleteUser((User) any());
        organisationService.deleteOrganisationAccountById(123L);
        verify(organisationRepository).findById((Long) any());
        verify(organisationRepository).delete((Organisation) any());
        verify(userService).deleteUser((User) any());
    }

    /**
     * Method under test: {@link OrganisationService#deleteOrganisationAccountById(Long)}
     */
    @Test
    void testDeleteOrganisationAccountById2() {
        doNothing().when(organisationRepository).delete((Organisation) any());
        when(organisationRepository.findById((Long) any())).thenReturn(Optional.of(new Organisation()));
        doThrow(new AlreadyExistException("An error occurred")).when(userService).deleteUser((User) any());
        assertThrows(AlreadyExistException.class, () -> organisationService.deleteOrganisationAccountById(123L));
        verify(organisationRepository).findById((Long) any());
        verify(organisationRepository).delete((Organisation) any());
        verify(userService).deleteUser((User) any());
    }

    /**
     * Method under test: {@link OrganisationService#deleteOrganisationAccountById(Long)}
     */
    @Test
    void testDeleteOrganisationAccountById3() {
        Organisation organisation = mock(Organisation.class);
        when(organisation.getUser()).thenThrow(new AlreadyExistException("An error occurred"));
        Optional<Organisation> ofResult = Optional.of(organisation);
        doNothing().when(organisationRepository).delete((Organisation) any());
        when(organisationRepository.findById((Long) any())).thenReturn(ofResult);
        doNothing().when(userService).deleteUser((User) any());
        assertThrows(AlreadyExistException.class, () -> organisationService.deleteOrganisationAccountById(123L));
        verify(organisationRepository).findById((Long) any());
        verify(organisationRepository).delete((Organisation) any());
        verify(organisation).getUser();
    }

    /**
     * Method under test: {@link OrganisationService#deleteOrganisationAccountById(Long)}
     */
    @Test
    void testDeleteOrganisationAccountById4() {
        doNothing().when(organisationRepository).delete((Organisation) any());
        when(organisationRepository.findById((Long) any())).thenReturn(Optional.empty());
        new AlreadyExistException("An error occurred");
        doNothing().when(userService).deleteUser((User) any());
        assertThrows(NotFoundException.class, () -> organisationService.deleteOrganisationAccountById(123L));
        verify(organisationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link OrganisationService#deleteAllOrganisationsAndUsers()}
     */
    @Test
    void testDeleteAllOrganisationsAndUsers() {
        when(organisationRepository.findAll()).thenReturn(new ArrayList<>());
        doNothing().when(organisationRepository).deleteAll();
        doNothing().when(eventRepository).deleteAll();
        organisationService.deleteAllOrganisationsAndUsers();
        verify(organisationRepository).findAll();
        verify(organisationRepository).deleteAll();
        verify(eventRepository).deleteAll();
    }

    /**
     * Method under test: {@link OrganisationService#deleteAllOrganisationsAndUsers()}
     */
    @Test
    void testDeleteAllOrganisationsAndUsers2() {
        when(organisationRepository.findAll()).thenReturn(new ArrayList<>());
        doNothing().when(organisationRepository).deleteAll();
        doThrow(new AlreadyExistException("An error occurred")).when(eventRepository).deleteAll();
        assertThrows(AlreadyExistException.class, () -> organisationService.deleteAllOrganisationsAndUsers());
        verify(organisationRepository).findAll();
        verify(eventRepository).deleteAll();
    }



    /**
     * Method under test: {@link OrganisationService#deleteAllOrganisationsAndUsers()}
     */
    @Test
    void testDeleteAllOrganisationsAndUsers4() {
        Organisation organisation = new Organisation();
        organisation.setUser(new User("janedoe", "iloveyou", Role.ADMIN, true));

        ArrayList<Organisation> organisationList = new ArrayList<>();
        organisationList.add(organisation);
        when(organisationRepository.findAll()).thenReturn(organisationList);
        doNothing().when(organisationRepository).deleteAll();
        doNothing().when(userService).deleteUserById((Long) any());
        doNothing().when(eventRepository).deleteAll();
        organisationService.deleteAllOrganisationsAndUsers();
        verify(organisationRepository).findAll();
        verify(organisationRepository).deleteAll();
        verify(userService).deleteUserById((Long) any());
        verify(eventRepository).deleteAll();
    }


    /**
     * Method under test: {@link OrganisationService#deleteAllOrganisationsAndUsers()}
     */
    @Test
    void testDeleteAllOrganisationsAndUsers6() {
        Organisation organisation = new Organisation();
        organisation.setUser(new User("janedoe", "iloveyou", Role.ADMIN, true));

        ArrayList<Organisation> organisationList = new ArrayList<>();
        User user = new User("janedoe", "iloveyou", Role.ADMIN, true);

        organisationList.add(new Organisation("Organisation Name", user, new ArrayList<>()));
        organisationList.add(organisation);
        when(organisationRepository.findAll()).thenReturn(organisationList);
        doNothing().when(organisationRepository).deleteAll();
        doNothing().when(userService).deleteUserById((Long) any());
        doNothing().when(eventRepository).deleteAll();
        organisationService.deleteAllOrganisationsAndUsers();
        verify(organisationRepository).findAll();
        verify(organisationRepository).deleteAll();
        verify(userService, atLeast(1)).deleteUserById((Long) any());
        verify(eventRepository).deleteAll();
    }

}

