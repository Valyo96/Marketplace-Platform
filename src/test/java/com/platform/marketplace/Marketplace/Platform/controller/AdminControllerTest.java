package com.platform.marketplace.Marketplace.Platform.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.mapper.EventCategoryConverter;
import com.platform.marketplace.Marketplace.Platform.mapper.EventDtoToEventMapper;
import com.platform.marketplace.Marketplace.Platform.mapper.EventToEventDtoMapper;
import com.platform.marketplace.Marketplace.Platform.mapper.LocationToStringMapper;
import com.platform.marketplace.Marketplace.Platform.mapper.OrganisationRegDtoToOrganisationMapper;
import com.platform.marketplace.Marketplace.Platform.mapper.OrganisationRegDtoUserMapper;
import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.model.EventCategory;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.repository.EventCategoryRepository;
import com.platform.marketplace.Marketplace.Platform.repository.EventRepository;
import com.platform.marketplace.Marketplace.Platform.repository.LocationRepository;
import com.platform.marketplace.Marketplace.Platform.repository.OrganisationRepository;
import com.platform.marketplace.Marketplace.Platform.repository.UserRepository;
import com.platform.marketplace.Marketplace.Platform.service.admin.AdminService;
import com.platform.marketplace.Marketplace.Platform.service.event.EventCategoryService;
import com.platform.marketplace.Marketplace.Platform.service.event.EventService;
import com.platform.marketplace.Marketplace.Platform.service.event.SpecificationEventFilter;
import com.platform.marketplace.Marketplace.Platform.service.image.ImageConvertor;
import com.platform.marketplace.Marketplace.Platform.service.location.LocationService;
import com.platform.marketplace.Marketplace.Platform.service.organisation.OrganisationService;
import com.platform.marketplace.Marketplace.Platform.service.user.UserService;
import com.platform.marketplace.Marketplace.Platform.utility.Utility;
import com.platform.marketplace.Marketplace.Platform.utility.consts.EntranceType;
import com.platform.marketplace.Marketplace.Platform.utility.consts.Role;
import jakarta.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

class AdminControllerTest {
    /**
     * Method under test: {@link AdminController#getAllOrgs(Model, String, HttpSession)}
     */
    @Test
    void testGetAllOrgs() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        when(organisationRepository.findAll()).thenReturn(new ArrayList<>());
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository = mock(EventRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        UserService userService2 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService2, new BCryptPasswordEncoder());

        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper, new EventCategoryConverter());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository1, new LocationToStringMapper());

        OrganisationService organisationService1 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService, utility1,
                new EventService(eventRepository1, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService4 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService4, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository3 = mock(LocationRepository.class);
        OrganisationService organisationService2 = new OrganisationService(organisationRepository1, userService3,
                eventRepository2, utility2, new OrganisationRegDtoToOrganisationMapper(userConverter1,
                new LocationService(locationRepository3, new LocationToStringMapper())));

        EventRepository eventRepository3 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository4, new LocationToStringMapper());

        OrganisationRepository organisationRepository2 = mock(OrganisationRepository.class);
        UserService userService5 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository4 = mock(EventRepository.class);
        Utility utility3 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService3 = new OrganisationService(organisationRepository2, userService5,
                eventRepository4, utility3,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService3,
                converter2, new ImageConvertor());

        LocationRepository locationRepository5 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository5, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        AdminController adminController = new AdminController(adminService, organisationService2,
                new EventService(eventRepository3, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()));
        ConcurrentModel model = new ConcurrentModel();
        assertEquals("admin", adminController.getAllOrgs(model, "An error occurred", new MockHttpSession()));
        verify(organisationRepository).findAll();
    }

    /**
     * Method under test: {@link AdminController#getAllOrgs(Model, String, HttpSession)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllOrgs2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.platform.marketplace.Marketplace.Platform.service.organisation.OrganisationService.getAllOrganisations()" because "this.organisationService" is null
        //       at com.platform.marketplace.Marketplace.Platform.service.admin.AdminService.getAllOrganisations(AdminService.java:26)
        //       at com.platform.marketplace.Marketplace.Platform.controller.AdminController.getAllOrgs(AdminController.java:35)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService, new BCryptPasswordEncoder());

        EventRepository eventRepository = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository, new LocationToStringMapper());

        OrganisationService organisationService = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService, converter,
                new ImageConvertor());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository1, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminService adminService = new AdminService(null, utility,
                new EventService(eventRepository, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository1 = mock(EventRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService2, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository2 = mock(LocationRepository.class);
        OrganisationService organisationService1 = new OrganisationService(organisationRepository, userService1,
                eventRepository1, utility1, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository2, new LocationToStringMapper())));

        EventRepository eventRepository2 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository3 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository3, new LocationToStringMapper());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository3 = mock(EventRepository.class);
        Utility utility2 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService2 = new OrganisationService(organisationRepository1, userService3,
                eventRepository3, utility2,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService2,
                converter2, new ImageConvertor());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository4, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        AdminController adminController = new AdminController(adminService, organisationService1,
                new EventService(eventRepository2, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()));
        ConcurrentModel model = new ConcurrentModel();
        adminController.getAllOrgs(model, "An error occurred", new MockHttpSession());
    }

    /**
     * Method under test: {@link AdminController#getAllOrgs(Model, String, HttpSession)}
     */
    @Test
    void testGetAllOrgs3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        OrganisationService organisationService = mock(OrganisationService.class);
        when(organisationService.getAllOrganisations()).thenReturn(new ArrayList<>());
        UserService userService = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService, new BCryptPasswordEncoder());

        EventRepository eventRepository = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository, new LocationToStringMapper());

        OrganisationService organisationService1 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository1, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService, utility,
                new EventService(eventRepository, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository1 = mock(EventRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService2, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository2 = mock(LocationRepository.class);
        OrganisationService organisationService2 = new OrganisationService(organisationRepository, userService1,
                eventRepository1, utility1, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository2, new LocationToStringMapper())));

        EventRepository eventRepository2 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository3 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository3, new LocationToStringMapper());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository3 = mock(EventRepository.class);
        Utility utility2 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService3 = new OrganisationService(organisationRepository1, userService3,
                eventRepository3, utility2,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService3,
                converter2, new ImageConvertor());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository4, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        AdminController adminController = new AdminController(adminService, organisationService2,
                new EventService(eventRepository2, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()));
        ConcurrentModel model = new ConcurrentModel();
        assertEquals("admin", adminController.getAllOrgs(model, "An error occurred", new MockHttpSession()));
        verify(organisationService).getAllOrganisations();
    }

    /**
     * Method under test: {@link AdminController#getAllOrgs(Model, String, HttpSession)}
     */
    @Test
    void testGetAllOrgs4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        AdminService adminService = mock(AdminService.class);
        when(adminService.getAllOrganisations()).thenReturn(new ArrayList<>());
        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository = mock(EventRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository1, new LocationToStringMapper());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        Utility utility1 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService1 = new OrganisationService(organisationRepository1, userService2,
                eventRepository2, utility1,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminController adminController = new AdminController(adminService, organisationService,
                new EventService(eventRepository1, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));
        ConcurrentModel model = new ConcurrentModel();
        assertEquals("admin", adminController.getAllOrgs(model, "An error occurred", new MockHttpSession()));
        verify(adminService).getAllOrganisations();
    }

    /**
     * Method under test: {@link AdminController#getAllOrgs(Model, String, HttpSession)}
     */
    @Test
    void testGetAllOrgs5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        AdminService adminService = mock(AdminService.class);
        when(adminService.getAllOrganisations()).thenReturn(new ArrayList<>());
        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository = mock(EventRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository1, new LocationToStringMapper());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        Utility utility1 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService1 = new OrganisationService(organisationRepository1, userService2,
                eventRepository2, utility1,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminController adminController = new AdminController(adminService, organisationService,
                new EventService(eventRepository1, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));
        ConcurrentModel model = new ConcurrentModel();
        HttpSession httpSession = mock(HttpSession.class);
        when(httpSession.getAttribute((String) any())).thenReturn(new ArrayList<>());
        assertEquals("admin", adminController.getAllOrgs(model, "An error occurred", httpSession));
        verify(adminService).getAllOrganisations();
        verify(httpSession).getAttribute((String) any());
    }

    /**
     * Method under test: {@link AdminController#getOrgsByDate(String, HttpSession)}
     */
    @Test
    void testGetOrgsByDate() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        when(organisationRepository.findAll()).thenReturn(new ArrayList<>());
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository = mock(EventRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        UserService userService2 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService2, new BCryptPasswordEncoder());

        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository1, new LocationToStringMapper());

        OrganisationService organisationService1 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService, utility1,
                new EventService(eventRepository1, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService4 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService4, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository3 = mock(LocationRepository.class);
        OrganisationService organisationService2 = new OrganisationService(organisationRepository1, userService3,
                eventRepository2, utility2, new OrganisationRegDtoToOrganisationMapper(userConverter1,
                new LocationService(locationRepository3, new LocationToStringMapper())));

        EventRepository eventRepository3 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository4, new LocationToStringMapper());

        OrganisationRepository organisationRepository2 = mock(OrganisationRepository.class);
        UserService userService5 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository4 = mock(EventRepository.class);
        Utility utility3 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService3 = new OrganisationService(organisationRepository2, userService5,
                eventRepository4, utility3,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService3,
                converter2, new ImageConvertor());

        LocationRepository locationRepository5 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository5, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        AdminController adminController = new AdminController(adminService, organisationService2,
                new EventService(eventRepository3, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()));
        assertEquals("redirect:/admin/organisations", adminController.getOrgsByDate("Order", new MockHttpSession()));
        verify(organisationRepository).findAll();
    }

    /**
     * Method under test: {@link AdminController#getOrgsByDate(String, HttpSession)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetOrgsByDate2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.platform.marketplace.Marketplace.Platform.service.organisation.OrganisationService.getAllOrganisations()" because "this.organisationService" is null
        //       at com.platform.marketplace.Marketplace.Platform.service.admin.AdminService.getAllOrganisations(AdminService.java:26)
        //       at com.platform.marketplace.Marketplace.Platform.service.admin.AdminService.listAllOrganisationsByRegistrationDateOrder(AdminService.java:35)
        //       at com.platform.marketplace.Marketplace.Platform.controller.AdminController.getOrgsByDate(AdminController.java:45)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService, new BCryptPasswordEncoder());

        EventRepository eventRepository = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository, new LocationToStringMapper());

        OrganisationService organisationService = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService, converter,
                new ImageConvertor());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository1, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminService adminService = new AdminService(null, utility,
                new EventService(eventRepository, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository1 = mock(EventRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService2, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository2 = mock(LocationRepository.class);
        OrganisationService organisationService1 = new OrganisationService(organisationRepository, userService1,
                eventRepository1, utility1, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository2, new LocationToStringMapper())));

        EventRepository eventRepository2 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository3 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository3, new LocationToStringMapper());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository3 = mock(EventRepository.class);
        Utility utility2 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService2 = new OrganisationService(organisationRepository1, userService3,
                eventRepository3, utility2,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService2,
                converter2, new ImageConvertor());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository4, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        AdminController adminController = new AdminController(adminService, organisationService1,
                new EventService(eventRepository2, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()));
        adminController.getOrgsByDate("Order", new MockHttpSession());
    }

    /**
     * Method under test: {@link AdminController#getOrgsByDate(String, HttpSession)}
     */
    @Test
    void testGetOrgsByDate3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        OrganisationService organisationService = mock(OrganisationService.class);
        when(organisationService.getAllOrganisations()).thenReturn(new ArrayList<>());
        UserService userService = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService, new BCryptPasswordEncoder());

        EventRepository eventRepository = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository, new LocationToStringMapper());

        OrganisationService organisationService1 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository1, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService, utility,
                new EventService(eventRepository, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository1 = mock(EventRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService2, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository2 = mock(LocationRepository.class);
        OrganisationService organisationService2 = new OrganisationService(organisationRepository, userService1,
                eventRepository1, utility1, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository2, new LocationToStringMapper())));

        EventRepository eventRepository2 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository3 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository3, new LocationToStringMapper());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository3 = mock(EventRepository.class);
        Utility utility2 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService3 = new OrganisationService(organisationRepository1, userService3,
                eventRepository3, utility2,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService3,
                converter2, new ImageConvertor());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository4, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        AdminController adminController = new AdminController(adminService, organisationService2,
                new EventService(eventRepository2, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()));
        assertEquals("redirect:/admin/organisations", adminController.getOrgsByDate("Order", new MockHttpSession()));
        verify(organisationService).getAllOrganisations();
    }

    /**
     * Method under test: {@link AdminController#getOrgsByDate(String, HttpSession)}
     */
    @Test
    void testGetOrgsByDate4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        AdminService adminService = mock(AdminService.class);
        when(adminService.listAllOrganisationsByRegistrationDateOrder((String) any())).thenReturn(new ArrayList<>());
        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository = mock(EventRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository1, new LocationToStringMapper());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        Utility utility1 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService1 = new OrganisationService(organisationRepository1, userService2,
                eventRepository2, utility1,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminController adminController = new AdminController(adminService, organisationService,
                new EventService(eventRepository1, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));
        assertEquals("redirect:/admin/organisations", adminController.getOrgsByDate("Order", new MockHttpSession()));
        verify(adminService).listAllOrganisationsByRegistrationDateOrder((String) any());
    }

    /**
     * Method under test: {@link AdminController#getOrgsByDate(String, HttpSession)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetOrgsByDate5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "jakarta.servlet.http.HttpSession.setAttribute(String, Object)" because "session" is null
        //       at com.platform.marketplace.Marketplace.Platform.controller.AdminController.getOrgsByDate(AdminController.java:45)
        //   See https://diff.blue/R013 to resolve this issue.

        AdminService adminService = mock(AdminService.class);
        when(adminService.listAllOrganisationsByRegistrationDateOrder((String) any())).thenReturn(new ArrayList<>());
        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository = mock(EventRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository1, new LocationToStringMapper());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        Utility utility1 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService1 = new OrganisationService(organisationRepository1, userService2,
                eventRepository2, utility1,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        (new AdminController(adminService, organisationService, new EventService(eventRepository1, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor())))
                .getOrgsByDate("Order", null);
    }

    /**
     * Method under test: {@link AdminController#getOrgsByDate(String, HttpSession)}
     */
    @Test
    void testGetOrgsByDate6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        when(organisationRepository.findOrganisationsByRegistrationDateDesc()).thenReturn(new ArrayList<>());
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository = mock(EventRepository.class);
        Utility utility = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService1, new BCryptPasswordEncoder());

        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(null, null, converter, new ImageConvertor());

        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService, utility1,
                new EventService(eventRepository1, eventCategoryService, mapperTODto, mapperToEntity, locationService,
                        converter1, specificationEventFilter, new ImageConvertor()));

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService3, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository1 = mock(LocationRepository.class);
        OrganisationService organisationService1 = new OrganisationService(organisationRepository1, userService2,
                eventRepository2, utility2, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository1, new LocationToStringMapper())));

        EventRepository eventRepository3 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        OrganisationService organisationService2 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService1, organisationService2,
                converter2, new ImageConvertor());

        LocationRepository locationRepository3 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository3, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        AdminController adminController = new AdminController(adminService, organisationService1,
                new EventService(eventRepository3, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService2,
                        converter3, specificationEventFilter1, new ImageConvertor()));
        assertEquals("redirect:/admin/organisations",
                adminController.getOrgsByDate((String) "desc", new MockHttpSession()));
        verify(organisationRepository).findOrganisationsByRegistrationDateDesc();
    }

    /**
     * Method under test: {@link AdminController#getOrgsByDate(String, HttpSession)}
     */
    @Test
    void testGetOrgsByDate7() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        OrganisationService organisationService = mock(OrganisationService.class);
        when(organisationService.findOrganisationsRegistrationDateByDescOrder()).thenReturn(new ArrayList<>());
        UserService userService = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService, new BCryptPasswordEncoder());

        EventRepository eventRepository = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(null, null, converter, new ImageConvertor());

        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService, utility,
                new EventService(eventRepository, eventCategoryService, mapperTODto, mapperToEntity, locationService,
                        converter1, specificationEventFilter, new ImageConvertor()));

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository1 = mock(EventRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService2, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository1 = mock(LocationRepository.class);
        OrganisationService organisationService1 = new OrganisationService(organisationRepository, userService1,
                eventRepository1, utility1, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository1, new LocationToStringMapper())));

        EventRepository eventRepository2 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        OrganisationService organisationService2 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService1, organisationService2,
                converter2, new ImageConvertor());

        LocationRepository locationRepository3 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository3, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        AdminController adminController = new AdminController(adminService, organisationService1,
                new EventService(eventRepository2, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService2,
                        converter3, specificationEventFilter1, new ImageConvertor()));
        assertEquals("redirect:/admin/organisations",
                adminController.getOrgsByDate((String) "desc", new MockHttpSession()));
        verify(organisationService).findOrganisationsRegistrationDateByDescOrder();
    }

    /**
     * Method under test: {@link AdminController#updateOrgStatus(Long, boolean)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateOrgStatus() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.platform.marketplace.Marketplace.Platform.model.User.isEnabled()" because the return value of "com.platform.marketplace.Marketplace.Platform.model.Organisation.getUser()" is null
        //       at com.platform.marketplace.Marketplace.Platform.service.organisation.OrganisationService.updateOrganisationStatus(OrganisationService.java:100)
        //       at com.platform.marketplace.Marketplace.Platform.service.admin.AdminService.updateOrganisationStatus(AdminService.java:42)
        //       at com.platform.marketplace.Marketplace.Platform.controller.AdminController.updateOrgStatus(AdminController.java:59)
        //   See https://diff.blue/R013 to resolve this issue.

        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findEventsByOrganisationId((Long) any())).thenReturn(new ArrayList<>());
        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        UserService userService2 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService2, new BCryptPasswordEncoder());

        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository1, new LocationToStringMapper());

        OrganisationService organisationService1 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService, utility1,
                new EventService(eventRepository1, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        when(organisationRepository1.findById((Long) any())).thenReturn(Optional.of(new Organisation()));
        UserService userService3 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService4 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService4, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository3 = mock(LocationRepository.class);
        OrganisationService organisationService2 = new OrganisationService(organisationRepository1, userService3,
                eventRepository2, utility2, new OrganisationRegDtoToOrganisationMapper(userConverter1,
                new LocationService(locationRepository3, new LocationToStringMapper())));

        EventRepository eventRepository3 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository4, new LocationToStringMapper());

        OrganisationRepository organisationRepository2 = mock(OrganisationRepository.class);
        UserService userService5 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository4 = mock(EventRepository.class);
        Utility utility3 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService3 = new OrganisationService(organisationRepository2, userService5,
                eventRepository4, utility3,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService3,
                converter2, new ImageConvertor());

        LocationRepository locationRepository5 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository5, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        (new AdminController(adminService, organisationService2,
                new EventService(eventRepository3, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()))).updateOrgStatus(1L, true);
    }

    /**
     * Method under test: {@link AdminController#updateOrgStatus(Long, boolean)}
     */
    @Test
    void testUpdateOrgStatus2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        OrganisationService organisationService = mock(OrganisationService.class);
        doNothing().when(organisationService).updateOrganisationStatus((Organisation) any(), anyBoolean());
        UserService userService = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService, new BCryptPasswordEncoder());

        EventRepository eventRepository = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository, new LocationToStringMapper());

        OrganisationService organisationService1 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository1, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService, utility,
                new EventService(eventRepository, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        when(organisationRepository.findById((Long) any())).thenReturn(Optional.of(new Organisation()));
        UserService userService1 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository1 = mock(EventRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService2, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository2 = mock(LocationRepository.class);
        OrganisationService organisationService2 = new OrganisationService(organisationRepository, userService1,
                eventRepository1, utility1, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository2, new LocationToStringMapper())));

        EventRepository eventRepository2 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository3 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository3, new LocationToStringMapper());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository3 = mock(EventRepository.class);
        Utility utility2 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService3 = new OrganisationService(organisationRepository1, userService3,
                eventRepository3, utility2,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService3,
                converter2, new ImageConvertor());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository4, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        assertTrue(
                (new AdminController(adminService, organisationService2,
                        new EventService(eventRepository2, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                                converter3, specificationEventFilter1, new ImageConvertor()))).updateOrgStatus(1L, true)
                        .isReference());
        verify(organisationService).updateOrganisationStatus((Organisation) any(), anyBoolean());
        verify(organisationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AdminController#updateOrgStatus(Long, boolean)}
     */
    @Test
    void testUpdateOrgStatus3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        AdminService adminService = mock(AdminService.class);
        doNothing().when(adminService).updateOrganisationStatus((Organisation) any(), anyBoolean());
        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        when(organisationRepository.findById((Long) any())).thenReturn(Optional.of(new Organisation()));
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository = mock(EventRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository1, new LocationToStringMapper());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        Utility utility1 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService1 = new OrganisationService(organisationRepository1, userService2,
                eventRepository2, utility1,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        assertTrue(
                (new AdminController(adminService, organisationService,
                        new EventService(eventRepository1, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                                converter1, specificationEventFilter, new ImageConvertor()))).updateOrgStatus(1L, true)
                        .isReference());
        verify(adminService).updateOrganisationStatus((Organisation) any(), anyBoolean());
        verify(organisationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AdminController#updateOrgStatus(Long, boolean)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateOrgStatus4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: Няма намерена организация
        //       at com.platform.marketplace.Marketplace.Platform.service.organisation.OrganisationService.lambda$findOrganisationById$2(OrganisationService.java:63)
        //       at java.util.Optional.orElseThrow(Optional.java:403)
        //       at com.platform.marketplace.Marketplace.Platform.service.organisation.OrganisationService.findOrganisationById(OrganisationService.java:63)
        //       at com.platform.marketplace.Marketplace.Platform.controller.AdminController.updateOrgStatus(AdminController.java:59)
        //   See https://diff.blue/R013 to resolve this issue.

        AdminService adminService = mock(AdminService.class);
        doNothing().when(adminService).updateOrganisationStatus((Organisation) any(), anyBoolean());
        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        when(organisationRepository.findById((Long) any())).thenReturn(Optional.empty());
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository = mock(EventRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository1, new LocationToStringMapper());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        Utility utility1 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService1 = new OrganisationService(organisationRepository1, userService2,
                eventRepository2, utility1,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        (new AdminController(adminService, organisationService, new EventService(eventRepository1, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor())))
                .updateOrgStatus(1L, true);
    }

    /**
     * Method under test: {@link AdminController#updateOrgStatus(Long, boolean)}
     */
    @Test
    void testUpdateOrgStatus5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        AdminService adminService = mock(AdminService.class);
        doNothing().when(adminService).updateOrganisationStatus((Organisation) any(), anyBoolean());
        OrganisationService organisationService = mock(OrganisationService.class);
        when(organisationService.findOrganisationById((Long) any())).thenReturn(new Organisation());
        EventRepository eventRepository = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository, new LocationToStringMapper());

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository1 = mock(EventRepository.class);
        Utility utility = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService1 = new OrganisationService(organisationRepository, userService,
                eventRepository1, utility,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository1, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        assertTrue(
                (new AdminController(adminService, organisationService,
                        new EventService(eventRepository, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                                converter1, specificationEventFilter, new ImageConvertor()))).updateOrgStatus(1L, true)
                        .isReference());
        verify(adminService).updateOrganisationStatus((Organisation) any(), anyBoolean());
        verify(organisationService).findOrganisationById((Long) any());
    }

    /**
     * Method under test: {@link AdminController#deleteOrgStatus(Long, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteOrgStatus() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.Authentication.isAuthenticated()" because "auth" is null
        //       at com.platform.marketplace.Marketplace.Platform.utility.Utility.returnAuthenticatedUser(Utility.java:53)
        //       at com.platform.marketplace.Marketplace.Platform.utility.Utility.authorizationCheck(Utility.java:60)
        //       at com.platform.marketplace.Marketplace.Platform.service.admin.AdminService.deleteOrganisationAccountById(AdminService.java:46)
        //       at com.platform.marketplace.Marketplace.Platform.controller.AdminController.deleteOrgStatus(AdminController.java:65)
        //   See https://diff.blue/R013 to resolve this issue.

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository = mock(EventRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        UserService userService2 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService2, new BCryptPasswordEncoder());

        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper, new EventCategoryConverter());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository1, new LocationToStringMapper());

        OrganisationService organisationService1 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService, utility1,
                new EventService(eventRepository1, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService4 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService4, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository3 = mock(LocationRepository.class);
        OrganisationService organisationService2 = new OrganisationService(organisationRepository1, userService3,
                eventRepository2, utility2, new OrganisationRegDtoToOrganisationMapper(userConverter1,
                new LocationService(locationRepository3, new LocationToStringMapper())));

        EventRepository eventRepository3 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository4, new LocationToStringMapper());

        OrganisationRepository organisationRepository2 = mock(OrganisationRepository.class);
        UserService userService5 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository4 = mock(EventRepository.class);
        Utility utility3 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService3 = new OrganisationService(organisationRepository2, userService5,
                eventRepository4, utility3,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService3,
                converter2, new ImageConvertor());

        LocationRepository locationRepository5 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository5, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        (new AdminController(adminService, organisationService2, new EventService(eventRepository3, eventCategoryService1,
                mapperTODto1, mapperToEntity1, locationService3, converter3, specificationEventFilter1, new ImageConvertor())))
                .deleteOrgStatus(1L, "iloveyou");
    }

    /**
     * Method under test: {@link AdminController#deleteOrgStatus(Long, String)}
     */
    @Test
    void testDeleteOrgStatus2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        doNothing().when(organisationRepository).delete((Organisation) any());
        when(organisationRepository.findById((Long) any())).thenReturn(Optional.of(new Organisation()));
        UserRepository userRepository = mock(UserRepository.class);
        doNothing().when(userRepository).delete((User) any());
        UserService userService = new UserService(userRepository);
        EventRepository eventRepository = mock(EventRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        Utility utility1 = mock(Utility.class);
        when(utility1.authorizationCheck((String) any())).thenReturn(new User("janedoe", "iloveyou", Role.ADMIN, true));
        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository1, new LocationToStringMapper());

        OrganisationService organisationService1 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService, utility1,
                new EventService(eventRepository1, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService3, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository3 = mock(LocationRepository.class);
        OrganisationService organisationService2 = new OrganisationService(organisationRepository1, userService2,
                eventRepository2, utility2, new OrganisationRegDtoToOrganisationMapper(userConverter1,
                new LocationService(locationRepository3, new LocationToStringMapper())));

        EventRepository eventRepository3 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository4, new LocationToStringMapper());

        OrganisationRepository organisationRepository2 = mock(OrganisationRepository.class);
        UserService userService4 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository4 = mock(EventRepository.class);
        Utility utility3 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService3 = new OrganisationService(organisationRepository2, userService4,
                eventRepository4, utility3,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService3,
                converter2, new ImageConvertor());

        LocationRepository locationRepository5 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository5, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        assertTrue((new AdminController(adminService, organisationService2,
                new EventService(eventRepository3, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()))).deleteOrgStatus(1L, "iloveyou")
                .isReference());
        verify(organisationRepository).findById((Long) any());
        verify(organisationRepository).delete((Organisation) any());
        verify(userRepository).delete((User) any());
        verify(utility1).authorizationCheck((String) any());
    }

    /**
     * Method under test: {@link AdminController#deleteOrgStatus(Long, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteOrgStatus3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: Няма намерена организация
        //       at com.platform.marketplace.Marketplace.Platform.service.organisation.OrganisationService.lambda$findOrganisationById$2(OrganisationService.java:63)
        //       at java.util.Optional.orElseThrow(Optional.java:403)
        //       at com.platform.marketplace.Marketplace.Platform.service.organisation.OrganisationService.findOrganisationById(OrganisationService.java:63)
        //       at com.platform.marketplace.Marketplace.Platform.service.organisation.OrganisationService.deleteOrganisationAccountById(OrganisationService.java:120)
        //       at com.platform.marketplace.Marketplace.Platform.service.admin.AdminService.deleteOrganisationAccountById(AdminService.java:47)
        //       at com.platform.marketplace.Marketplace.Platform.controller.AdminController.deleteOrgStatus(AdminController.java:65)
        //   See https://diff.blue/R013 to resolve this issue.

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        doNothing().when(organisationRepository).delete((Organisation) any());
        when(organisationRepository.findById((Long) any())).thenReturn(Optional.empty());
        UserRepository userRepository = mock(UserRepository.class);
        doNothing().when(userRepository).delete((User) any());
        UserService userService = new UserService(userRepository);
        EventRepository eventRepository = mock(EventRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        Utility utility1 = mock(Utility.class);
        when(utility1.authorizationCheck((String) any())).thenReturn(new User("janedoe", "iloveyou", Role.ADMIN, true));
        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository1, new LocationToStringMapper());

        OrganisationService organisationService1 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService, utility1,
                new EventService(eventRepository1, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService3, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository3 = mock(LocationRepository.class);
        OrganisationService organisationService2 = new OrganisationService(organisationRepository1, userService2,
                eventRepository2, utility2, new OrganisationRegDtoToOrganisationMapper(userConverter1,
                new LocationService(locationRepository3, new LocationToStringMapper())));

        EventRepository eventRepository3 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository4, new LocationToStringMapper());

        OrganisationRepository organisationRepository2 = mock(OrganisationRepository.class);
        UserService userService4 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository4 = mock(EventRepository.class);
        Utility utility3 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService3 = new OrganisationService(organisationRepository2, userService4,
                eventRepository4, utility3,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService3,
                converter2, new ImageConvertor());

        LocationRepository locationRepository5 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository5, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        (new AdminController(adminService, organisationService2,
                new EventService(eventRepository3, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()))).deleteOrgStatus(1L, "iloveyou");
    }

    /**
     * Method under test: {@link AdminController#deleteOrgStatus(Long, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteOrgStatus4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.platform.marketplace.Marketplace.Platform.service.user.UserService.deleteUser(com.platform.marketplace.Marketplace.Platform.model.User)" because "this.userService" is null
        //       at com.platform.marketplace.Marketplace.Platform.service.organisation.OrganisationService.deleteOrganisationAccountById(OrganisationService.java:122)
        //       at com.platform.marketplace.Marketplace.Platform.service.admin.AdminService.deleteOrganisationAccountById(AdminService.java:47)
        //       at com.platform.marketplace.Marketplace.Platform.controller.AdminController.deleteOrgStatus(AdminController.java:65)
        //   See https://diff.blue/R013 to resolve this issue.

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        doNothing().when(organisationRepository).delete((Organisation) any());
        when(organisationRepository.findById((Long) any())).thenReturn(Optional.of(new Organisation()));
        EventRepository eventRepository = mock(EventRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, null, eventRepository,
                utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        Utility utility1 = mock(Utility.class);
        when(utility1.authorizationCheck((String) any())).thenReturn(new User("janedoe", "iloveyou", Role.ADMIN, true));
        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository1, new LocationToStringMapper());

        OrganisationService organisationService1 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService, utility1,
                new EventService(eventRepository1, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService2, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository3 = mock(LocationRepository.class);
        OrganisationService organisationService2 = new OrganisationService(organisationRepository1, userService1,
                eventRepository2, utility2, new OrganisationRegDtoToOrganisationMapper(userConverter1,
                new LocationService(locationRepository3, new LocationToStringMapper())));

        EventRepository eventRepository3 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository4, new LocationToStringMapper());

        OrganisationRepository organisationRepository2 = mock(OrganisationRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository4 = mock(EventRepository.class);
        Utility utility3 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService3 = new OrganisationService(organisationRepository2, userService3,
                eventRepository4, utility3,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService3,
                converter2, new ImageConvertor());

        LocationRepository locationRepository5 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository5, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        (new AdminController(adminService, organisationService2,
                new EventService(eventRepository3, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()))).deleteOrgStatus(1L, "iloveyou");
    }

    /**
     * Method under test: {@link AdminController#deleteOrgStatus(Long, String)}
     */
    @Test
    void testDeleteOrgStatus5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        doNothing().when(organisationRepository).delete((Organisation) any());
        when(organisationRepository.findById((Long) any())).thenReturn(Optional.of(new Organisation()));
        UserService userService = mock(UserService.class);
        doNothing().when(userService).deleteUser((User) any());
        EventRepository eventRepository = mock(EventRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        Utility utility1 = mock(Utility.class);
        when(utility1.authorizationCheck((String) any())).thenReturn(new User("janedoe", "iloveyou", Role.ADMIN, true));
        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository1, new LocationToStringMapper());

        OrganisationService organisationService1 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService, utility1,
                new EventService(eventRepository1, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService3, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository3 = mock(LocationRepository.class);
        OrganisationService organisationService2 = new OrganisationService(organisationRepository1, userService2,
                eventRepository2, utility2, new OrganisationRegDtoToOrganisationMapper(userConverter1,
                new LocationService(locationRepository3, new LocationToStringMapper())));

        EventRepository eventRepository3 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository4, new LocationToStringMapper());

        OrganisationRepository organisationRepository2 = mock(OrganisationRepository.class);
        UserService userService4 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository4 = mock(EventRepository.class);
        Utility utility3 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService3 = new OrganisationService(organisationRepository2, userService4,
                eventRepository4, utility3,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService3,
                converter2, new ImageConvertor());

        LocationRepository locationRepository5 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository5, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        assertTrue((new AdminController(adminService, organisationService2,
                new EventService(eventRepository3, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()))).deleteOrgStatus(1L, "iloveyou")
                .isReference());
        verify(organisationRepository).findById((Long) any());
        verify(organisationRepository).delete((Organisation) any());
        verify(userService).deleteUser((User) any());
        verify(utility1).authorizationCheck((String) any());
    }

    /**
     * Method under test: {@link AdminController#deleteOrgStatus(Long, String)}
     */
    @Test
    void testDeleteOrgStatus6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        OrganisationService organisationService = mock(OrganisationService.class);
        doNothing().when(organisationService).deleteOrganisationAccountById((Long) any());
        Utility utility = mock(Utility.class);
        when(utility.authorizationCheck((String) any())).thenReturn(new User("janedoe", "iloveyou", Role.ADMIN, true));
        EventRepository eventRepository = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository, new LocationToStringMapper());

        OrganisationService organisationService1 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository1, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService, utility,
                new EventService(eventRepository, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository1 = mock(EventRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository2 = mock(LocationRepository.class);
        OrganisationService organisationService2 = new OrganisationService(organisationRepository, userService,
                eventRepository1, utility1, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository2, new LocationToStringMapper())));

        EventRepository eventRepository2 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository3 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository3, new LocationToStringMapper());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository3 = mock(EventRepository.class);
        Utility utility2 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService3 = new OrganisationService(organisationRepository1, userService2,
                eventRepository3, utility2,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService3,
                converter2, new ImageConvertor());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository4, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        assertTrue((new AdminController(adminService, organisationService2,
                new EventService(eventRepository2, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()))).deleteOrgStatus(1L, "iloveyou")
                .isReference());
        verify(organisationService).deleteOrganisationAccountById((Long) any());
        verify(utility).authorizationCheck((String) any());
    }

    /**
     * Method under test: {@link AdminController#deleteOrgStatus(Long, String)}
     */
    @Test
    void testDeleteOrgStatus7() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        AdminService adminService = mock(AdminService.class);
        doNothing().when(adminService).deleteOrganisationAccountById((Long) any(), (String) any());
        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository = mock(EventRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository1, new LocationToStringMapper());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        Utility utility1 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService1 = new OrganisationService(organisationRepository1, userService2,
                eventRepository2, utility1,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        assertTrue((new AdminController(adminService, organisationService,
                new EventService(eventRepository1, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()))).deleteOrgStatus(1L, "iloveyou")
                .isReference());
        verify(adminService).deleteOrganisationAccountById((Long) any(), (String) any());
    }

    /**
     * Method under test: {@link AdminController#deleteAllOrgs(String, HttpSession)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteAllOrgs() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.Authentication.isAuthenticated()" because "auth" is null
        //       at com.platform.marketplace.Marketplace.Platform.utility.Utility.returnAuthenticatedUser(Utility.java:53)
        //       at com.platform.marketplace.Marketplace.Platform.utility.Utility.authorizationCheck(Utility.java:60)
        //       at com.platform.marketplace.Marketplace.Platform.service.admin.AdminService.deleteAllAccounts(AdminService.java:51)
        //       at com.platform.marketplace.Marketplace.Platform.controller.AdminController.deleteAllOrgs(AdminController.java:72)
        //   See https://diff.blue/R013 to resolve this issue.

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository = mock(EventRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        UserService userService2 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService2, new BCryptPasswordEncoder());

        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper, new EventCategoryConverter());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository1, new LocationToStringMapper());

        OrganisationService organisationService1 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService, utility1,
                new EventService(eventRepository1, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService4 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService4, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository3 = mock(LocationRepository.class);
        OrganisationService organisationService2 = new OrganisationService(organisationRepository1, userService3,
                eventRepository2, utility2, new OrganisationRegDtoToOrganisationMapper(userConverter1,
                new LocationService(locationRepository3, new LocationToStringMapper())));

        EventRepository eventRepository3 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository4, new LocationToStringMapper());

        OrganisationRepository organisationRepository2 = mock(OrganisationRepository.class);
        UserService userService5 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository4 = mock(EventRepository.class);
        Utility utility3 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService3 = new OrganisationService(organisationRepository2, userService5,
                eventRepository4, utility3,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService3,
                converter2, new ImageConvertor());

        LocationRepository locationRepository5 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository5, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        AdminController adminController = new AdminController(adminService, organisationService2,
                new EventService(eventRepository3, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()));
        adminController.deleteAllOrgs("iloveyou", new MockHttpSession());
    }

    /**
     * Method under test: {@link AdminController#deleteAllOrgs(String, HttpSession)}
     */
    @Test
    void testDeleteAllOrgs2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        when(organisationRepository.findAll()).thenReturn(new ArrayList<>());
        doNothing().when(organisationRepository).deleteAll();
        EventRepository eventRepository = mock(EventRepository.class);
        doNothing().when(eventRepository).deleteAll();
        UserService userService = new UserService(mock(UserRepository.class));
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        Utility utility1 = mock(Utility.class);
        when(utility1.authorizationCheck((String) any())).thenReturn(new User("janedoe", "iloveyou", Role.ADMIN, true));
        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository1, new LocationToStringMapper());

        OrganisationService organisationService1 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService, utility1,
                new EventService(eventRepository1, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService3, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository3 = mock(LocationRepository.class);
        OrganisationService organisationService2 = new OrganisationService(organisationRepository1, userService2,
                eventRepository2, utility2, new OrganisationRegDtoToOrganisationMapper(userConverter1,
                new LocationService(locationRepository3, new LocationToStringMapper())));

        EventRepository eventRepository3 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository4, new LocationToStringMapper());

        OrganisationRepository organisationRepository2 = mock(OrganisationRepository.class);
        UserService userService4 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository4 = mock(EventRepository.class);
        Utility utility3 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService3 = new OrganisationService(organisationRepository2, userService4,
                eventRepository4, utility3,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService3,
                converter2, new ImageConvertor());

        LocationRepository locationRepository5 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository5, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        AdminController adminController = new AdminController(adminService, organisationService2,
                new EventService(eventRepository3, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()));
        assertTrue(adminController.deleteAllOrgs("iloveyou", new MockHttpSession()).isReference());
        verify(organisationRepository).findAll();
        verify(organisationRepository).deleteAll();
        verify(eventRepository).deleteAll();
        verify(utility1).authorizationCheck((String) any());
    }

    /**
     * Method under test: {@link AdminController#deleteAllOrgs(String, HttpSession)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteAllOrgs3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.platform.marketplace.Marketplace.Platform.model.User.getId()" because the return value of "com.platform.marketplace.Marketplace.Platform.model.Organisation.getUser()" is null
        //       at com.platform.marketplace.Marketplace.Platform.service.organisation.OrganisationService.lambda$deleteAllOrganisationsAndUsers$6(OrganisationService.java:131)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.ReferencePipeline$Head.forEach(ReferencePipeline.java:762)
        //       at com.platform.marketplace.Marketplace.Platform.service.organisation.OrganisationService.deleteAllOrganisationsAndUsers(OrganisationService.java:130)
        //       at com.platform.marketplace.Marketplace.Platform.service.admin.AdminService.deleteAllAccounts(AdminService.java:52)
        //       at com.platform.marketplace.Marketplace.Platform.controller.AdminController.deleteAllOrgs(AdminController.java:72)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Organisation> organisationList = new ArrayList<>();
        organisationList.add(new Organisation());
        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        when(organisationRepository.findAll()).thenReturn(organisationList);
        doNothing().when(organisationRepository).deleteAll();
        EventRepository eventRepository = mock(EventRepository.class);
        doNothing().when(eventRepository).deleteAll();
        UserService userService = new UserService(mock(UserRepository.class));
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        Utility utility1 = mock(Utility.class);
        when(utility1.authorizationCheck((String) any())).thenReturn(new User("janedoe", "iloveyou", Role.ADMIN, true));
        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository1, new LocationToStringMapper());

        OrganisationService organisationService1 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService, utility1,
                new EventService(eventRepository1, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService3, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository3 = mock(LocationRepository.class);
        OrganisationService organisationService2 = new OrganisationService(organisationRepository1, userService2,
                eventRepository2, utility2, new OrganisationRegDtoToOrganisationMapper(userConverter1,
                new LocationService(locationRepository3, new LocationToStringMapper())));

        EventRepository eventRepository3 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository4, new LocationToStringMapper());

        OrganisationRepository organisationRepository2 = mock(OrganisationRepository.class);
        UserService userService4 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository4 = mock(EventRepository.class);
        Utility utility3 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService3 = new OrganisationService(organisationRepository2, userService4,
                eventRepository4, utility3,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService3,
                converter2, new ImageConvertor());

        LocationRepository locationRepository5 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository5, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        AdminController adminController = new AdminController(adminService, organisationService2,
                new EventService(eventRepository3, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()));
        adminController.deleteAllOrgs("iloveyou", new MockHttpSession());
    }

    /**
     * Method under test: {@link AdminController#deleteAllOrgs(String, HttpSession)}
     */
    @Test
    void testDeleteAllOrgs4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        OrganisationService organisationService = mock(OrganisationService.class);
        doNothing().when(organisationService).deleteAllOrganisationsAndUsers();
        Utility utility = mock(Utility.class);
        when(utility.authorizationCheck((String) any())).thenReturn(new User("janedoe", "iloveyou", Role.ADMIN, true));
        EventRepository eventRepository = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository, new LocationToStringMapper());

        OrganisationService organisationService1 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository1, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService, utility,
                new EventService(eventRepository, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository1 = mock(EventRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository2 = mock(LocationRepository.class);
        OrganisationService organisationService2 = new OrganisationService(organisationRepository, userService,
                eventRepository1, utility1, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository2, new LocationToStringMapper())));

        EventRepository eventRepository2 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository3 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository3, new LocationToStringMapper());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository3 = mock(EventRepository.class);
        Utility utility2 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService3 = new OrganisationService(organisationRepository1, userService2,
                eventRepository3, utility2,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService3,
                converter2, new ImageConvertor());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository4, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        AdminController adminController = new AdminController(adminService, organisationService2,
                new EventService(eventRepository2, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()));
        assertTrue(adminController.deleteAllOrgs("iloveyou", new MockHttpSession()).isReference());
        verify(organisationService).deleteAllOrganisationsAndUsers();
        verify(utility).authorizationCheck((String) any());
    }

    /**
     * Method under test: {@link AdminController#deleteAllOrgs(String, HttpSession)}
     */
    @Test
    void testDeleteAllOrgs5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        AdminService adminService = mock(AdminService.class);
        doNothing().when(adminService).deleteAllAccounts((String) any());
        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository = mock(EventRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository1, new LocationToStringMapper());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        Utility utility1 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService1 = new OrganisationService(organisationRepository1, userService2,
                eventRepository2, utility1,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminController adminController = new AdminController(adminService, organisationService,
                new EventService(eventRepository1, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));
        assertTrue(adminController.deleteAllOrgs("iloveyou", new MockHttpSession()).isReference());
        verify(adminService).deleteAllAccounts((String) any());
    }

    /**
     * Method under test: {@link AdminController#getEventsByOrganisationId(Long, Model)}
     */
    @Test
    void testGetEventsByOrganisationId() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findEventsByOrganisationId((Long) any())).thenReturn(new ArrayList<>());
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository, new LocationToStringMapper());

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository1 = mock(EventRepository.class);
        Utility utility = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository1, utility,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService, converter,
                new ImageConvertor());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository1, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        EventService eventService = new EventService(eventRepository, eventCategoryService, mapperTODto, mapperToEntity,
                locationService1, converter1, specificationEventFilter, new ImageConvertor());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService2, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository2 = mock(LocationRepository.class);
        OrganisationService organisationService1 = new OrganisationService(organisationRepository1, userService1,
                eventRepository2, utility1, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository2, new LocationToStringMapper())));

        UserService userService3 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService3, new BCryptPasswordEncoder());

        EventRepository eventRepository3 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository3 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository3, new LocationToStringMapper());

        OrganisationService organisationService2 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService2,
                converter2, new ImageConvertor());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository4, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService1, utility2,
                new EventService(eventRepository3, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()));

        OrganisationRepository organisationRepository2 = mock(OrganisationRepository.class);
        UserService userService4 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository4 = mock(EventRepository.class);
        UserService userService5 = new UserService(mock(UserRepository.class));
        Utility utility3 = new Utility(userService5, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository5 = mock(LocationRepository.class);
        AdminController adminController = new AdminController(adminService,
                new OrganisationService(organisationRepository2, userService4, eventRepository4, utility3,
                        new OrganisationRegDtoToOrganisationMapper(userConverter1,
                                new LocationService(locationRepository5, new LocationToStringMapper()))),
                eventService);
        assertEquals("adminEventManagement", adminController.getEventsByOrganisationId(1L, new ConcurrentModel()));
        verify(eventRepository).findEventsByOrganisationId((Long) any());
    }

    /**
     * Method under test: {@link AdminController#getEventsByOrganisationId(Long, Model)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetEventsByOrganisationId2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.platform.marketplace.Marketplace.Platform.model.Organisation.getOrganisationName()" because the return value of "com.platform.marketplace.Marketplace.Platform.model.Event.getOrganisation()" is null
        //       at com.platform.marketplace.Marketplace.Platform.mapper.EventToEventDtoMapper.apply(EventToEventDtoMapper.java:26)
        //       at com.platform.marketplace.Marketplace.Platform.mapper.EventToEventDtoMapper.apply(EventToEventDtoMapper.java:11)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:61)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.findEventsByOrgId(EventService.java:104)
        //       at com.platform.marketplace.Marketplace.Platform.controller.AdminController.getEventsByOrganisationId(AdminController.java:79)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findEventsByOrganisationId((Long) any())).thenReturn(eventList);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository, new LocationToStringMapper());

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository1 = mock(EventRepository.class);
        Utility utility = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository1, utility,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService, converter,
                new ImageConvertor());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository1, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        EventService eventService = new EventService(eventRepository, eventCategoryService, mapperTODto, mapperToEntity,
                locationService1, converter1, specificationEventFilter, new ImageConvertor());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService2, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository2 = mock(LocationRepository.class);
        OrganisationService organisationService1 = new OrganisationService(organisationRepository1, userService1,
                eventRepository2, utility1, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository2, new LocationToStringMapper())));

        UserService userService3 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService3, new BCryptPasswordEncoder());

        EventRepository eventRepository3 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository3 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository3, new LocationToStringMapper());

        OrganisationService organisationService2 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService2,
                converter2, new ImageConvertor());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository4, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService1, utility2,
                new EventService(eventRepository3, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()));

        OrganisationRepository organisationRepository2 = mock(OrganisationRepository.class);
        UserService userService4 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository4 = mock(EventRepository.class);
        UserService userService5 = new UserService(mock(UserRepository.class));
        Utility utility3 = new Utility(userService5, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository5 = mock(LocationRepository.class);
        AdminController adminController = new AdminController(adminService,
                new OrganisationService(organisationRepository2, userService4, eventRepository4, utility3,
                        new OrganisationRegDtoToOrganisationMapper(userConverter1,
                                new LocationService(locationRepository5, new LocationToStringMapper()))),
                eventService);
        adminController.getEventsByOrganisationId(1L, new ConcurrentModel());
    }

    /**
     * Method under test: {@link AdminController#getEventsByOrganisationId(Long, Model)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetEventsByOrganisationId3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.util.Objects.requireNonNull(Objects.java:208)
        //       at java.util.stream.ReferencePipeline.map(ReferencePipeline.java:189)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:61)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.findEventsByOrgId(EventService.java:104)
        //       at com.platform.marketplace.Marketplace.Platform.controller.AdminController.getEventsByOrganisationId(AdminController.java:79)
        //   See https://diff.blue/R013 to resolve this issue.

        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findEventsByOrganisationId((Long) any())).thenReturn(new ArrayList<>());
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository, new LocationToStringMapper());

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository1 = mock(EventRepository.class);
        Utility utility = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository1, utility,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService, converter,
                new ImageConvertor());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository1, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        EventService eventService = new EventService(eventRepository, eventCategoryService, null, mapperToEntity,
                locationService1, converter1, specificationEventFilter, new ImageConvertor());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService2, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository2 = mock(LocationRepository.class);
        OrganisationService organisationService1 = new OrganisationService(organisationRepository1, userService1,
                eventRepository2, utility1, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository2, new LocationToStringMapper())));

        UserService userService3 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService3, new BCryptPasswordEncoder());

        EventRepository eventRepository3 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository3 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository3, new LocationToStringMapper());

        OrganisationService organisationService2 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService2,
                converter2, new ImageConvertor());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository4, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService1, utility2,
                new EventService(eventRepository3, eventCategoryService1, mapperTODto, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()));

        OrganisationRepository organisationRepository2 = mock(OrganisationRepository.class);
        UserService userService4 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository4 = mock(EventRepository.class);
        UserService userService5 = new UserService(mock(UserRepository.class));
        Utility utility3 = new Utility(userService5, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository5 = mock(LocationRepository.class);
        AdminController adminController = new AdminController(adminService,
                new OrganisationService(organisationRepository2, userService4, eventRepository4, utility3,
                        new OrganisationRegDtoToOrganisationMapper(userConverter1,
                                new LocationService(locationRepository5, new LocationToStringMapper()))),
                eventService);
        adminController.getEventsByOrganisationId(1L, new ConcurrentModel());
    }

    /**
     * Method under test: {@link AdminController#getEventsByOrganisationId(Long, Model)}
     */
    @Test
    void testGetEventsByOrganisationId4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventService eventService = mock(EventService.class);
        when(eventService.findEventsByOrgId((Long) any())).thenReturn(new ArrayList<>());
        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository = mock(EventRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        UserService userService2 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService2, new BCryptPasswordEncoder());

        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository1, new LocationToStringMapper());

        OrganisationService organisationService1 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService, utility1,
                new EventService(eventRepository1, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService4 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService4, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository3 = mock(LocationRepository.class);
        AdminController adminController = new AdminController(adminService,
                new OrganisationService(organisationRepository1, userService3, eventRepository2, utility2,
                        new OrganisationRegDtoToOrganisationMapper(userConverter1,
                                new LocationService(locationRepository3, new LocationToStringMapper()))),
                eventService);
        assertEquals("adminEventManagement", adminController.getEventsByOrganisationId(1L, new ConcurrentModel()));
        verify(eventService).findEventsByOrgId((Long) any());
    }

    /**
     * Method under test: {@link AdminController#deleteEvent(Long, String)}
     */
    @Test
    void testDeleteEvent() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventRepository eventRepository = mock(EventRepository.class);
        doNothing().when(eventRepository).deleteById((Long) any());
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository, new LocationToStringMapper());

        OrganisationService organisationService = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService, converter,
                new ImageConvertor());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository1, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        EventService eventService = new EventService(eventRepository, eventCategoryService, mapperTODto, mapperToEntity,
                locationService1, converter1, specificationEventFilter, new ImageConvertor());

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository1 = mock(EventRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository2 = mock(LocationRepository.class);
        OrganisationService organisationService1 = new OrganisationService(organisationRepository, userService,
                eventRepository1, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository2, new LocationToStringMapper())));

        UserService userService2 = new UserService(mock(UserRepository.class));
        AdminService adminService = new AdminService(organisationService1,
                new Utility(userService2, new BCryptPasswordEncoder()), eventService);

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService4 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService4, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository3 = mock(LocationRepository.class);
        OrganisationService organisationService2 = new OrganisationService(organisationRepository1, userService3,
                eventRepository2, utility1, new OrganisationRegDtoToOrganisationMapper(userConverter1,
                new LocationService(locationRepository3, new LocationToStringMapper())));

        EventRepository eventRepository3 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository4, new LocationToStringMapper());

        OrganisationRepository organisationRepository2 = mock(OrganisationRepository.class);
        UserService userService5 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository4 = mock(EventRepository.class);
        Utility utility2 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService3 = new OrganisationService(organisationRepository2, userService5,
                eventRepository4, utility2,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService3,
                converter2, new ImageConvertor());

        LocationRepository locationRepository5 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository5, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        assertTrue((new AdminController(adminService, organisationService2,
                new EventService(eventRepository3, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor())))
                .deleteEvent(1L, "https://example.org/example")
                .isReference());
        verify(eventRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link AdminController#deleteEvent(Long, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteEvent2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.platform.marketplace.Marketplace.Platform.service.event.EventService.deleteEventById(java.lang.Long)" because "this.eventService" is null
        //       at com.platform.marketplace.Marketplace.Platform.service.admin.AdminService.deleteEventByOrgId(AdminService.java:56)
        //       at com.platform.marketplace.Marketplace.Platform.controller.AdminController.deleteEvent(AdminController.java:86)
        //   See https://diff.blue/R013 to resolve this issue.

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository = mock(EventRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        UserService userService2 = new UserService(mock(UserRepository.class));
        AdminService adminService = new AdminService(organisationService,
                new Utility(userService2, new BCryptPasswordEncoder()), null);

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository1 = mock(EventRepository.class);
        UserService userService4 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService4, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository1 = mock(LocationRepository.class);
        OrganisationService organisationService1 = new OrganisationService(organisationRepository1, userService3,
                eventRepository1, utility1, new OrganisationRegDtoToOrganisationMapper(userConverter1,
                new LocationService(locationRepository1, new LocationToStringMapper())));

        EventRepository eventRepository2 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository2, new LocationToStringMapper());

        OrganisationRepository organisationRepository2 = mock(OrganisationRepository.class);
        UserService userService5 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository3 = mock(EventRepository.class);
        Utility utility2 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService2 = new OrganisationService(organisationRepository2, userService5,
                eventRepository3, utility2,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService2, converter,
                new ImageConvertor());

        LocationRepository locationRepository3 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository3, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        (new AdminController(adminService, organisationService1,
                new EventService(eventRepository2, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()))).deleteEvent(1L,
                "https://example.org/example");
    }

    /**
     * Method under test: {@link AdminController#deleteEvent(Long, String)}
     */
    @Test
    void testDeleteEvent3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventService eventService = mock(EventService.class);
        doNothing().when(eventService).deleteEventById((Long) any());
        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository = mock(EventRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        UserService userService2 = new UserService(mock(UserRepository.class));
        AdminService adminService = new AdminService(organisationService,
                new Utility(userService2, new BCryptPasswordEncoder()), eventService);

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository1 = mock(EventRepository.class);
        UserService userService4 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService4, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository1 = mock(LocationRepository.class);
        OrganisationService organisationService1 = new OrganisationService(organisationRepository1, userService3,
                eventRepository1, utility1, new OrganisationRegDtoToOrganisationMapper(userConverter1,
                new LocationService(locationRepository1, new LocationToStringMapper())));

        EventRepository eventRepository2 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository2, new LocationToStringMapper());

        OrganisationRepository organisationRepository2 = mock(OrganisationRepository.class);
        UserService userService5 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository3 = mock(EventRepository.class);
        Utility utility2 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService2 = new OrganisationService(organisationRepository2, userService5,
                eventRepository3, utility2,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService2, converter,
                new ImageConvertor());

        LocationRepository locationRepository3 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository3, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        assertTrue((new AdminController(adminService, organisationService1,
                new EventService(eventRepository2, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor())))
                .deleteEvent(1L, "https://example.org/example")
                .isReference());
        verify(eventService).deleteEventById((Long) any());
    }

    /**
     * Method under test: {@link AdminController#deleteEvent(Long, String)}
     */
    @Test
    void testDeleteEvent4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        AdminService adminService = mock(AdminService.class);
        doNothing().when(adminService).deleteEventByOrgId((Long) any());
        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository = mock(EventRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository1, new LocationToStringMapper());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        Utility utility1 = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService1 = new OrganisationService(organisationRepository1, userService2,
                eventRepository2, utility1,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        assertTrue((new AdminController(adminService, organisationService,
                new EventService(eventRepository1, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor())))
                .deleteEvent(1L, "https://example.org/example")
                .isReference());
        verify(adminService).deleteEventByOrgId((Long) any());
    }

    /**
     * Method under test: {@link AdminController#updateEventStatus(Long, boolean, String)}
     */
    @Test
    void testUpdateEventStatus() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.save((Event) any())).thenReturn(new Event());
        when(eventRepository.findById((Long) any())).thenReturn(Optional.of(new Event()));
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository, new LocationToStringMapper());

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository1 = mock(EventRepository.class);
        Utility utility = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository1, utility,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService, converter,
                new ImageConvertor());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository1, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        EventService eventService = new EventService(eventRepository, eventCategoryService, mapperTODto, mapperToEntity,
                locationService1, converter1, specificationEventFilter, new ImageConvertor());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService2, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository2 = mock(LocationRepository.class);
        OrganisationService organisationService1 = new OrganisationService(organisationRepository1, userService1,
                eventRepository2, utility1, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository2, new LocationToStringMapper())));

        UserService userService3 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService3, new BCryptPasswordEncoder());

        EventRepository eventRepository3 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository3 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository3, new LocationToStringMapper());

        OrganisationService organisationService2 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService2,
                converter2, new ImageConvertor());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository4, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService1, utility2,
                new EventService(eventRepository3, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()));

        OrganisationRepository organisationRepository2 = mock(OrganisationRepository.class);
        UserService userService4 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository4 = mock(EventRepository.class);
        UserService userService5 = new UserService(mock(UserRepository.class));
        Utility utility3 = new Utility(userService5, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository5 = mock(LocationRepository.class);
        assertTrue((new AdminController(adminService,
                new OrganisationService(organisationRepository2, userService4, eventRepository4, utility3,
                        new OrganisationRegDtoToOrganisationMapper(userConverter1,
                                new LocationService(locationRepository5, new LocationToStringMapper()))),
                eventService)).updateEventStatus(1L, true, "https://example.org/example").isReference());
        verify(eventRepository).save((Event) any());
        verify(eventRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AdminController#updateEventStatus(Long, boolean, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateEventStatus2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: Търсеното от вас събитие не съществува
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.lambda$getEventById$0(EventService.java:73)
        //       at java.util.Optional.orElseThrow(Optional.java:403)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.getEventById(EventService.java:73)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.setIsEnabledEventField(EventService.java:209)
        //       at com.platform.marketplace.Marketplace.Platform.controller.AdminController.updateEventStatus(AdminController.java:92)
        //   See https://diff.blue/R013 to resolve this issue.

        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.save((Event) any())).thenReturn(new Event());
        when(eventRepository.findById((Long) any())).thenReturn(Optional.empty());
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository, new LocationToStringMapper());

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository1 = mock(EventRepository.class);
        Utility utility = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository1, utility,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService, converter,
                new ImageConvertor());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository1, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        EventService eventService = new EventService(eventRepository, eventCategoryService, mapperTODto, mapperToEntity,
                locationService1, converter1, specificationEventFilter, new ImageConvertor());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService2, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository2 = mock(LocationRepository.class);
        OrganisationService organisationService1 = new OrganisationService(organisationRepository1, userService1,
                eventRepository2, utility1, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository2, new LocationToStringMapper())));

        UserService userService3 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService3, new BCryptPasswordEncoder());

        EventRepository eventRepository3 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository3 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository3, new LocationToStringMapper());

        OrganisationService organisationService2 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService2,
                converter2, new ImageConvertor());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository4, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService1, utility2,
                new EventService(eventRepository3, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()));

        OrganisationRepository organisationRepository2 = mock(OrganisationRepository.class);
        UserService userService4 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository4 = mock(EventRepository.class);
        UserService userService5 = new UserService(mock(UserRepository.class));
        Utility utility3 = new Utility(userService5, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository5 = mock(LocationRepository.class);
        (new AdminController(adminService,
                new OrganisationService(organisationRepository2, userService4, eventRepository4, utility3,
                        new OrganisationRegDtoToOrganisationMapper(userConverter1,
                                new LocationService(locationRepository5, new LocationToStringMapper()))),
                eventService)).updateEventStatus(1L, true, "https://example.org/example");
    }

    /**
     * Method under test: {@link AdminController#updateEventStatus(Long, boolean, String)}
     */
    @Test
    void testUpdateEventStatus3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventService eventService = mock(EventService.class);
        doNothing().when(eventService).setIsEnabledEventField((Long) any(), anyBoolean());
        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository = mock(EventRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility = new Utility(userService1, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository = mock(LocationRepository.class);
        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository, new LocationToStringMapper())));

        UserService userService2 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService2, new BCryptPasswordEncoder());

        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository1, new LocationToStringMapper());

        OrganisationService organisationService1 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService1, converter,
                new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService, utility1,
                new EventService(eventRepository1, eventCategoryService, mapperTODto, mapperToEntity, locationService1,
                        converter1, specificationEventFilter, new ImageConvertor()));

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService4 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService4, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository3 = mock(LocationRepository.class);
        assertTrue((new AdminController(adminService,
                new OrganisationService(organisationRepository1, userService3, eventRepository2, utility2,
                        new OrganisationRegDtoToOrganisationMapper(userConverter1,
                                new LocationService(locationRepository3, new LocationToStringMapper()))),
                eventService)).updateEventStatus(1L, true, "https://example.org/example").isReference());
        verify(eventService).setIsEnabledEventField((Long) any(), anyBoolean());
    }

    /**
     * Method under test: {@link AdminController#getEventDetails(Long, Model)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetEventDetails() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.platform.marketplace.Marketplace.Platform.model.Organisation.getOrganisationName()" because the return value of "com.platform.marketplace.Marketplace.Platform.model.Event.getOrganisation()" is null
        //       at com.platform.marketplace.Marketplace.Platform.mapper.EventToEventDtoMapper.apply(EventToEventDtoMapper.java:26)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.getEventDTOById(EventService.java:179)
        //       at com.platform.marketplace.Marketplace.Platform.controller.AdminController.getEventDetails(AdminController.java:98)
        //   See https://diff.blue/R013 to resolve this issue.

        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findById((Long) any())).thenReturn(Optional.of(new Event()));
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository, new LocationToStringMapper());

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository1 = mock(EventRepository.class);
        Utility utility = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository1, utility,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService, converter,
                new ImageConvertor());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository1, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        EventService eventService = new EventService(eventRepository, eventCategoryService, mapperTODto, mapperToEntity,
                locationService1, converter1, specificationEventFilter, new ImageConvertor());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService2, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository2 = mock(LocationRepository.class);
        OrganisationService organisationService1 = new OrganisationService(organisationRepository1, userService1,
                eventRepository2, utility1, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository2, new LocationToStringMapper())));

        UserService userService3 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService3, new BCryptPasswordEncoder());

        EventRepository eventRepository3 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository3 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository3, new LocationToStringMapper());

        OrganisationService organisationService2 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService2,
                converter2, new ImageConvertor());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository4, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService1, utility2,
                new EventService(eventRepository3, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()));

        OrganisationRepository organisationRepository2 = mock(OrganisationRepository.class);
        UserService userService4 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository4 = mock(EventRepository.class);
        UserService userService5 = new UserService(mock(UserRepository.class));
        Utility utility3 = new Utility(userService5, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository5 = mock(LocationRepository.class);
        AdminController adminController = new AdminController(adminService,
                new OrganisationService(organisationRepository2, userService4, eventRepository4, utility3,
                        new OrganisationRegDtoToOrganisationMapper(userConverter1,
                                new LocationService(locationRepository5, new LocationToStringMapper()))),
                eventService);
        adminController.getEventDetails(1L, new ConcurrentModel());
    }

    /**
     * Method under test: {@link AdminController#getEventDetails(Long, Model)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetEventDetails2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Collection.stream()" because "that" is null
        //       at com.platform.marketplace.Marketplace.Platform.mapper.EventCategoryConverter.convertToString(EventCategoryConverter.java:33)
        //       at com.platform.marketplace.Marketplace.Platform.mapper.EventToEventDtoMapper.apply(EventToEventDtoMapper.java:27)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.getEventDTOById(EventService.java:179)
        //       at com.platform.marketplace.Marketplace.Platform.controller.AdminController.getEventDetails(AdminController.java:98)
        //   See https://diff.blue/R013 to resolve this issue.

        Event event = new Event();
        event.setOrganisation(new Organisation());
        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findById((Long) any())).thenReturn(Optional.of(event));
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository, new LocationToStringMapper());

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository1 = mock(EventRepository.class);
        Utility utility = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository1, utility,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService, converter,
                new ImageConvertor());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository1, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        EventService eventService = new EventService(eventRepository, eventCategoryService, mapperTODto, mapperToEntity,
                locationService1, converter1, specificationEventFilter, new ImageConvertor());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService2, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository2 = mock(LocationRepository.class);
        OrganisationService organisationService1 = new OrganisationService(organisationRepository1, userService1,
                eventRepository2, utility1, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository2, new LocationToStringMapper())));

        UserService userService3 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService3, new BCryptPasswordEncoder());

        EventRepository eventRepository3 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository3 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository3, new LocationToStringMapper());

        OrganisationService organisationService2 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService2,
                converter2, new ImageConvertor());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository4, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService1, utility2,
                new EventService(eventRepository3, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()));

        OrganisationRepository organisationRepository2 = mock(OrganisationRepository.class);
        UserService userService4 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository4 = mock(EventRepository.class);
        UserService userService5 = new UserService(mock(UserRepository.class));
        Utility utility3 = new Utility(userService5, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository5 = mock(LocationRepository.class);
        AdminController adminController = new AdminController(adminService,
                new OrganisationService(organisationRepository2, userService4, eventRepository4, utility3,
                        new OrganisationRegDtoToOrganisationMapper(userConverter1,
                                new LocationService(locationRepository5, new LocationToStringMapper()))),
                eventService);
        adminController.getEventDetails(1L, new ConcurrentModel());
    }

    /**
     * Method under test: {@link AdminController#getEventDetails(Long, Model)}
     */
    @Test
    void testGetEventDetails3() throws UnsupportedEncodingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        HashSet<EventCategory> eventCategories = new HashSet<>();
        ArrayList<Location> locations = new ArrayList<>();
        LocalDateTime startsAt = LocalDateTime.of(1, 1, 1, 1, 1);
        LocalDateTime endsAt = LocalDateTime.of(1, 1, 1, 1, 1);
        byte[] image = "AXAXAXAX".getBytes("UTF-8");

        Event event = new Event(eventCategories, "Name", EntranceType.FREE, "The characteristics of someone or something",
                "Link To Application Form", locations, "42 Main St", startsAt, endsAt, "Key Words", image,
                "https://example.org/example", new Organisation());
        ArrayList<Location> locationList = new ArrayList<>();
        event.setOrganisation(new Organisation("event", locationList));
        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findById((Long) any())).thenReturn(Optional.of(event));
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository, new LocationToStringMapper());

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository1 = mock(EventRepository.class);
        Utility utility = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository1, utility,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService, converter,
                new ImageConvertor());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository1, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        EventService eventService = new EventService(eventRepository, eventCategoryService, mapperTODto, mapperToEntity,
                locationService1, converter1, specificationEventFilter, new ImageConvertor());

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService1 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService2, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository2 = mock(LocationRepository.class);
        OrganisationService organisationService1 = new OrganisationService(organisationRepository1, userService1,
                eventRepository2, utility1, new OrganisationRegDtoToOrganisationMapper(userConverter,
                new LocationService(locationRepository2, new LocationToStringMapper())));

        UserService userService3 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService3, new BCryptPasswordEncoder());

        EventRepository eventRepository3 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        LocationRepository locationRepository3 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository3, new LocationToStringMapper());

        OrganisationService organisationService2 = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(locationService2, organisationService2,
                converter2, new ImageConvertor());

        LocationRepository locationRepository4 = mock(LocationRepository.class);
        LocationService locationService3 = new LocationService(locationRepository4, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService1, utility2,
                new EventService(eventRepository3, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService3,
                        converter3, specificationEventFilter1, new ImageConvertor()));

        OrganisationRepository organisationRepository2 = mock(OrganisationRepository.class);
        UserService userService4 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository4 = mock(EventRepository.class);
        UserService userService5 = new UserService(mock(UserRepository.class));
        Utility utility3 = new Utility(userService5, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter1 = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository5 = mock(LocationRepository.class);
        AdminController adminController = new AdminController(adminService,
                new OrganisationService(organisationRepository2, userService4, eventRepository4, utility3,
                        new OrganisationRegDtoToOrganisationMapper(userConverter1,
                                new LocationService(locationRepository5, new LocationToStringMapper()))),
                eventService);
        ConcurrentModel concurrentModel = new ConcurrentModel();
        assertEquals("eventDetails", adminController.getEventDetails(1L, concurrentModel));
        verify(eventRepository).findById((Long) any());
        assertEquals("42 Main St", ((EventDTO) concurrentModel.get("event")).getAddress());
        assertTrue(((EventDTO) concurrentModel.get("event")).isEnabled());
        assertEquals("event", ((EventDTO) concurrentModel.get("event")).getOrganisationName());
        assertEquals("01:01", ((EventDTO) concurrentModel.get("event")).getStartsAt().toLocalTime().toString());
        assertNull(((EventDTO) concurrentModel.get("event")).getOrganisationId());
        assertEquals("Name", ((EventDTO) concurrentModel.get("event")).getName());
        assertEquals(locationList, ((EventDTO) concurrentModel.get("event")).getLocations());
        assertEquals("Link To Application Form", ((EventDTO) concurrentModel.get("event")).getLinkToApplicationForm());
        assertEquals("Key Words", ((EventDTO) concurrentModel.get("event")).getKeywords());
        assertEquals("https://example.org/example", ((EventDTO) concurrentModel.get("event")).getImageDataUrl());
        assertNull(((EventDTO) concurrentModel.get("event")).getEventId());
        assertEquals("", ((EventDTO) concurrentModel.get("event")).getEventCategories());
        assertEquals(EntranceType.FREE, ((EventDTO) concurrentModel.get("event")).getEntranceType());
        assertEquals("00 часа и 00 минути", ((EventDTO) concurrentModel.get("event")).getDuration());
        assertEquals("01:01", ((EventDTO) concurrentModel.get("event")).getEndsAt().toLocalTime().toString());
        assertEquals("The characteristics of someone or something",
                ((EventDTO) concurrentModel.get("event")).getDescription());
        assertEquals("Събитието е започнало", ((EventDTO) concurrentModel.get("event")).getCounter());
    }

    /**
     * Method under test: {@link AdminController#getEventDetails(Long, Model)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetEventDetails4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: Търсеното от вас събитие не съществува
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.lambda$getEventById$0(EventService.java:73)
        //       at java.util.Optional.orElseThrow(Optional.java:403)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.getEventById(EventService.java:73)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.getEventDTOById(EventService.java:179)
        //       at com.platform.marketplace.Marketplace.Platform.controller.AdminController.getEventDetails(AdminController.java:98)
        //   See https://diff.blue/R013 to resolve this issue.

        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findById((Long) any())).thenReturn(Optional.empty());
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository, new LocationToStringMapper());

        OrganisationService organisationService = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService, converter,
                new ImageConvertor());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository1, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        EventService eventService = new EventService(eventRepository, eventCategoryService, mapperTODto, mapperToEntity,
                locationService1, converter1, specificationEventFilter, new ImageConvertor());

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository1 = mock(EventRepository.class);
        Utility utility = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService1 = new OrganisationService(organisationRepository, userService,
                eventRepository1, utility,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService1, new BCryptPasswordEncoder());

        EventRepository eventRepository2 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper1 = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto1 = new EventToEventDtoMapper(locationToStringMapper1,
                new EventCategoryConverter());

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(null, null, converter2, new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService1, utility1,
                new EventService(eventRepository2, eventCategoryService1, mapperTODto1, mapperToEntity1, locationService2,
                        converter3, specificationEventFilter1, new ImageConvertor()));

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository3 = mock(EventRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService3, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository3 = mock(LocationRepository.class);
        AdminController adminController = new AdminController(adminService,
                new OrganisationService(organisationRepository1, userService2, eventRepository3, utility2,
                        new OrganisationRegDtoToOrganisationMapper(userConverter,
                                new LocationService(locationRepository3, new LocationToStringMapper()))),
                eventService);
        adminController.getEventDetails(1L, new ConcurrentModel());
    }

    /**
     * Method under test: {@link AdminController#getEventDetails(Long, Model)}
     */
    @Test
    void testGetEventDetails5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findById((Long) any())).thenReturn(Optional.of(new Event()));
        EventToEventDtoMapper eventToEventDtoMapper = mock(EventToEventDtoMapper.class);
        when(eventToEventDtoMapper.apply((Event) any())).thenReturn(new EventDTO());
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository, new LocationToStringMapper());

        OrganisationService organisationService = new OrganisationService(mock(OrganisationRepository.class), null,
                mock(EventRepository.class), null, null);

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(locationService, organisationService, converter,
                new ImageConvertor());

        LocationRepository locationRepository1 = mock(LocationRepository.class);
        LocationService locationService1 = new LocationService(locationRepository1, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        EventService eventService = new EventService(eventRepository, eventCategoryService, eventToEventDtoMapper,
                mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor());

        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository1 = mock(EventRepository.class);
        Utility utility = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService1 = new OrganisationService(organisationRepository, userService,
                eventRepository1, utility,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService1, new BCryptPasswordEncoder());

        EventRepository eventRepository2 = mock(EventRepository.class);
        EventCategoryService eventCategoryService1 = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        EventCategoryConverter converter2 = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity1 = new EventDtoToEventMapper(null, null, converter2, new ImageConvertor());

        LocationRepository locationRepository2 = mock(LocationRepository.class);
        LocationService locationService2 = new LocationService(locationRepository2, new LocationToStringMapper());

        EventCategoryConverter converter3 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter1 = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService1, utility1,
                new EventService(eventRepository2, eventCategoryService1, mapperTODto, mapperToEntity1, locationService2,
                        converter3, specificationEventFilter1, new ImageConvertor()));

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository3 = mock(EventRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService3, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository3 = mock(LocationRepository.class);
        AdminController adminController = new AdminController(adminService,
                new OrganisationService(organisationRepository1, userService2, eventRepository3, utility2,
                        new OrganisationRegDtoToOrganisationMapper(userConverter,
                                new LocationService(locationRepository3, new LocationToStringMapper()))),
                eventService);
        assertEquals("eventDetails", adminController.getEventDetails(1L, new ConcurrentModel()));
        verify(eventRepository).findById((Long) any());
        verify(eventToEventDtoMapper).apply((Event) any());
    }

    /**
     * Method under test: {@link AdminController#getEventDetails(Long, Model)}
     */
    @Test
    void testGetEventDetails6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventService eventService = mock(EventService.class);
        when(eventService.getEventDTOById((Long) any())).thenReturn(new EventDTO());
        OrganisationRepository organisationRepository = mock(OrganisationRepository.class);
        UserService userService = new UserService(mock(UserRepository.class));
        EventRepository eventRepository = mock(EventRepository.class);
        Utility utility = new Utility(null, new BCryptPasswordEncoder());

        OrganisationService organisationService = new OrganisationService(organisationRepository, userService,
                eventRepository, utility,
                new OrganisationRegDtoToOrganisationMapper(new OrganisationRegDtoUserMapper(), null));

        UserService userService1 = new UserService(mock(UserRepository.class));
        Utility utility1 = new Utility(userService1, new BCryptPasswordEncoder());

        EventRepository eventRepository1 = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper,
                new EventCategoryConverter());

        EventCategoryConverter converter = new EventCategoryConverter();
        EventDtoToEventMapper mapperToEntity = new EventDtoToEventMapper(null, null, converter, new ImageConvertor());

        LocationRepository locationRepository = mock(LocationRepository.class);
        LocationService locationService = new LocationService(locationRepository, new LocationToStringMapper());

        EventCategoryConverter converter1 = new EventCategoryConverter();
        SpecificationEventFilter specificationEventFilter = new SpecificationEventFilter();
        AdminService adminService = new AdminService(organisationService, utility1,
                new EventService(eventRepository1, eventCategoryService, mapperTODto, mapperToEntity, locationService,
                        converter1, specificationEventFilter, new ImageConvertor()));

        OrganisationRepository organisationRepository1 = mock(OrganisationRepository.class);
        UserService userService2 = new UserService(mock(UserRepository.class));
        EventRepository eventRepository2 = mock(EventRepository.class);
        UserService userService3 = new UserService(mock(UserRepository.class));
        Utility utility2 = new Utility(userService3, new BCryptPasswordEncoder());

        OrganisationRegDtoUserMapper userConverter = new OrganisationRegDtoUserMapper();
        LocationRepository locationRepository1 = mock(LocationRepository.class);
        AdminController adminController = new AdminController(adminService,
                new OrganisationService(organisationRepository1, userService2, eventRepository2, utility2,
                        new OrganisationRegDtoToOrganisationMapper(userConverter,
                                new LocationService(locationRepository1, new LocationToStringMapper()))),
                eventService);
        assertEquals("eventDetails", adminController.getEventDetails(1L, new ConcurrentModel()));
        verify(eventService).getEventDTOById((Long) any());
    }
}

