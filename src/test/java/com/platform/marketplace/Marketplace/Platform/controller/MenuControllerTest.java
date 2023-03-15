package com.platform.marketplace.Marketplace.Platform.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
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
import com.platform.marketplace.Marketplace.Platform.repository.EventCategoryRepository;
import com.platform.marketplace.Marketplace.Platform.repository.EventRepository;
import com.platform.marketplace.Marketplace.Platform.repository.LocationRepository;
import com.platform.marketplace.Marketplace.Platform.repository.OrganisationRepository;
import com.platform.marketplace.Marketplace.Platform.repository.UserRepository;
import com.platform.marketplace.Marketplace.Platform.service.event.EventCategoryService;
import com.platform.marketplace.Marketplace.Platform.service.event.EventService;
import com.platform.marketplace.Marketplace.Platform.service.event.SpecificationEventFilter;
import com.platform.marketplace.Marketplace.Platform.service.image.ImageConvertor;
import com.platform.marketplace.Marketplace.Platform.service.location.LocationService;
import com.platform.marketplace.Marketplace.Platform.service.organisation.OrganisationService;
import com.platform.marketplace.Marketplace.Platform.service.user.UserService;
import com.platform.marketplace.Marketplace.Platform.utility.Utility;
import com.platform.marketplace.Marketplace.Platform.utility.consts.EntranceType;
import jakarta.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

class MenuControllerTest {
    /**
     * Method under test: {@link MenuController#hm()}
     */
    @Test
    void testHm() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventRepository eventRepository = mock(EventRepository.class);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper, new EventCategoryConverter());

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
        assertEquals("login", (new MenuController(new EventService(eventRepository, eventCategoryService, mapperTODto,
                mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()))).hm());
    }

    /**
     * Method under test: {@link MenuController#main(Model, HttpSession, String)}
     */
    @Test
    void testMain() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findEventsByStartDateDesc()).thenReturn(new ArrayList<>());
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        ConcurrentModel model = new ConcurrentModel();
        assertEquals("menu", menuController.main(model, new MockHttpSession(), "An error occurred"));
        verify(eventRepository).findEventsByStartDateDesc();
    }

    /**
     * Method under test: {@link MenuController#main(Model, HttpSession, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testMain2() {
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
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.findEventsByStartDateDesc(EventService.java:129)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.filterEventsByStartDate(EventService.java:134)
        //       at com.platform.marketplace.Marketplace.Platform.controller.MenuController.main(MenuController.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findEventsByStartDateDesc()).thenReturn(eventList);
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        ConcurrentModel model = new ConcurrentModel();
        menuController.main(model, new MockHttpSession(), "An error occurred");
    }

    /**
     * Method under test: {@link MenuController#main(Model, HttpSession, String)}
     */
    @Test
    void testMain3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventService eventService = mock(EventService.class);
        when(eventService.filterEventsByStartDate((String) any())).thenReturn(new ArrayList<>());
        MenuController menuController = new MenuController(eventService);
        ConcurrentModel model = new ConcurrentModel();
        assertEquals("menu", menuController.main(model, new MockHttpSession(), "An error occurred"));
        verify(eventService).filterEventsByStartDate((String) any());
    }

    /**
     * Method under test: {@link MenuController#main(Model, HttpSession, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testMain4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "jakarta.servlet.http.HttpSession.getAttribute(String)" because "session" is null
        //       at com.platform.marketplace.Marketplace.Platform.controller.MenuController.main(MenuController.java:32)
        //   See https://diff.blue/R013 to resolve this issue.

        EventService eventService = mock(EventService.class);
        when(eventService.filterEventsByStartDate((String) any())).thenReturn(new ArrayList<>());
        MenuController menuController = new MenuController(eventService);
        menuController.main(new ConcurrentModel(), null, "An error occurred");
    }

    /**
     * Method under test: {@link MenuController#showAllActiveEvents(Model)}
     */
    @Test
    void testShowAllActiveEvents() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findAllActiveEvents()).thenReturn(new ArrayList<>());
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        assertEquals("menu", menuController.showAllActiveEvents(new ConcurrentModel()));
        verify(eventRepository).findAllActiveEvents();
    }

    /**
     * Method under test: {@link MenuController#showAllActiveEvents(Model)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testShowAllActiveEvents2() {
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
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.getAllActiveEvents(EventService.java:81)
        //       at com.platform.marketplace.Marketplace.Platform.controller.MenuController.showAllActiveEvents(MenuController.java:46)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findAllActiveEvents()).thenReturn(eventList);
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        menuController.showAllActiveEvents(new ConcurrentModel());
    }

    /**
     * Method under test: {@link MenuController#showAllActiveEvents(Model)}
     */
    @Test
    void testShowAllActiveEvents3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventService eventService = mock(EventService.class);
        when(eventService.getAllActiveEvents()).thenReturn(new ArrayList<>());
        MenuController menuController = new MenuController(eventService);
        assertEquals("menu", menuController.showAllActiveEvents(new ConcurrentModel()));
        verify(eventService).getAllActiveEvents();
    }

    /**
     * Method under test: {@link MenuController#showAllActiveEvents(Model)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testShowAllActiveEvents4() {
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
        //       at com.platform.marketplace.Marketplace.Platform.mapper.EventToEventDtoMapper.apply(EventToEventDtoMapper.java:11)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:61)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.getAllActiveEvents(EventService.java:81)
        //       at com.platform.marketplace.Marketplace.Platform.controller.MenuController.showAllActiveEvents(MenuController.java:46)
        //   See https://diff.blue/R013 to resolve this issue.

        Event event = new Event();
        event.setOrganisation(new Organisation());

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(event);
        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findAllActiveEvents()).thenReturn(eventList);
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        menuController.showAllActiveEvents(new ConcurrentModel());
    }

    /**
     * Method under test: {@link MenuController#showAllActiveEvents(Model)}
     */
    @Test
    void testShowAllActiveEvents5() throws UnsupportedEncodingException {
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
        event.setOrganisation(new Organisation("events", new ArrayList<>()));

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(event);
        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findAllActiveEvents()).thenReturn(eventList);
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        assertEquals("menu", menuController.showAllActiveEvents(new ConcurrentModel()));
        verify(eventRepository).findAllActiveEvents();
    }

    /**
     * Method under test: {@link MenuController#showAllActiveEvents(Model)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testShowAllActiveEvents6() throws UnsupportedEncodingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.platform.marketplace.Marketplace.Platform.mapper.LocationToStringMapper.apply(java.util.List)" because "this.locationToStringMapper" is null
        //       at com.platform.marketplace.Marketplace.Platform.mapper.EventToEventDtoMapper.apply(EventToEventDtoMapper.java:32)
        //       at com.platform.marketplace.Marketplace.Platform.mapper.EventToEventDtoMapper.apply(EventToEventDtoMapper.java:11)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:61)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.getAllActiveEvents(EventService.java:81)
        //       at com.platform.marketplace.Marketplace.Platform.controller.MenuController.showAllActiveEvents(MenuController.java:46)
        //   See https://diff.blue/R013 to resolve this issue.

        HashSet<EventCategory> eventCategories = new HashSet<>();
        ArrayList<Location> locations = new ArrayList<>();
        LocalDateTime startsAt = LocalDateTime.of(1, 1, 1, 1, 1);
        LocalDateTime endsAt = LocalDateTime.of(1, 1, 1, 1, 1);
        byte[] image = "AXAXAXAX".getBytes("UTF-8");

        Event event = new Event(eventCategories, "Name", EntranceType.FREE, "The characteristics of someone or something",
                "Link To Application Form", locations, "42 Main St", startsAt, endsAt, "Key Words", image,
                "https://example.org/example", new Organisation());
        event.setOrganisation(new Organisation("events", new ArrayList<>()));

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(event);
        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findAllActiveEvents()).thenReturn(eventList);
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(null, new EventCategoryConverter());

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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        menuController.showAllActiveEvents(new ConcurrentModel());
    }

    /**
     * Method under test: {@link MenuController#showAllActiveEvents(Model)}
     */
    @Test
    void testShowAllActiveEvents7() throws UnsupportedEncodingException {
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
        event.setOrganisation(new Organisation("events", new ArrayList<>()));

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(event);
        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findAllActiveEvents()).thenReturn(eventList);
        EventCategoryConverter eventCategoryConverter = mock(EventCategoryConverter.class);
        when(eventCategoryConverter.convertToString((Set<EventCategory>) any())).thenReturn("Convert To String");
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(new LocationToStringMapper(),
                eventCategoryConverter);

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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        assertEquals("menu", menuController.showAllActiveEvents(new ConcurrentModel()));
        verify(eventRepository).findAllActiveEvents();
        verify(eventCategoryConverter).convertToString((Set<EventCategory>) any());
    }

    /**
     * Method under test: {@link MenuController#getInactiveEvents(HttpSession)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetInactiveEvents() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: Няма намерени събития
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.getAllInactiveEvents(EventService.java:86)
        //       at com.platform.marketplace.Marketplace.Platform.controller.MenuController.getInactiveEvents(MenuController.java:53)
        //   See https://diff.blue/R013 to resolve this issue.

        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findAllInactiveEvents()).thenReturn(new ArrayList<>());
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper, new EventCategoryConverter());

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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        menuController.getInactiveEvents(new MockHttpSession());
    }

    /**
     * Method under test: {@link MenuController#getInactiveEvents(HttpSession)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetInactiveEvents2() {
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
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.getAllInactiveEvents(EventService.java:88)
        //       at com.platform.marketplace.Marketplace.Platform.controller.MenuController.getInactiveEvents(MenuController.java:53)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findAllInactiveEvents()).thenReturn(eventList);
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        menuController.getInactiveEvents(new MockHttpSession());
    }

    /**
     * Method under test: {@link MenuController#getInactiveEvents(HttpSession)}
     */
    @Test
    void testGetInactiveEvents3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventService eventService = mock(EventService.class);
        when(eventService.getAllInactiveEvents()).thenReturn(new ArrayList<>());
        MenuController menuController = new MenuController(eventService);
        assertTrue(menuController.getInactiveEvents(new MockHttpSession()).isReference());
        verify(eventService).getAllInactiveEvents();
    }

    /**
     * Method under test: {@link MenuController#getInactiveEvents(HttpSession)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetInactiveEvents4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "jakarta.servlet.http.HttpSession.setAttribute(String, Object)" because "session" is null
        //       at com.platform.marketplace.Marketplace.Platform.controller.MenuController.getInactiveEvents(MenuController.java:53)
        //   See https://diff.blue/R013 to resolve this issue.

        EventService eventService = mock(EventService.class);
        when(eventService.getAllInactiveEvents()).thenReturn(new ArrayList<>());
        (new MenuController(eventService)).getInactiveEvents(null);
    }

    /**
     * Method under test: {@link MenuController#filterEventsByStartDate(String, HttpSession)}
     */
    @Test
    void testFilterEventsByStartDate() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findEventsByStartDateAsc()).thenReturn(new ArrayList<>());
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper, new EventCategoryConverter());

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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        assertTrue(menuController.filterEventsByStartDate("Filter", new MockHttpSession()).isReference());
        verify(eventRepository).findEventsByStartDateAsc();
    }

    /**
     * Method under test: {@link MenuController#filterEventsByStartDate(String, HttpSession)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFilterEventsByStartDate2() {
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
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.findEventsByStartDateAsc(EventService.java:125)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.filterEventsByStartDate(EventService.java:136)
        //       at com.platform.marketplace.Marketplace.Platform.controller.MenuController.filterEventsByStartDate(MenuController.java:59)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findEventsByStartDateAsc()).thenReturn(eventList);
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        menuController.filterEventsByStartDate("Filter", new MockHttpSession());
    }

    /**
     * Method under test: {@link MenuController#filterEventsByStartDate(String, HttpSession)}
     */
    @Test
    void testFilterEventsByStartDate3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventService eventService = mock(EventService.class);
        when(eventService.filterEventsByStartDate((String) any())).thenReturn(new ArrayList<>());
        MenuController menuController = new MenuController(eventService);
        assertTrue(menuController.filterEventsByStartDate("Filter", new MockHttpSession()).isReference());
        verify(eventService).filterEventsByStartDate((String) any());
    }

    /**
     * Method under test: {@link MenuController#filterEventsByStartDate(String, HttpSession)}
     */
    @Test
    void testFilterEventsByStartDate4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findEventsByStartDateDesc()).thenReturn(new ArrayList<>());
        when(eventRepository.findEventsByStartDateAsc()).thenReturn(new ArrayList<>());
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        assertTrue(menuController.filterEventsByStartDate("desc", new MockHttpSession()).isReference());
        verify(eventRepository).findEventsByStartDateDesc();
    }

    /**
     * Method under test: {@link MenuController#filterEventsByStartDate(String, HttpSession)}
     */
    @Test
    void testFilterEventsByStartDate5() throws UnsupportedEncodingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ArrayList<Event> eventList = new ArrayList<>();
        HashSet<EventCategory> eventCategories = new HashSet<>();
        ArrayList<Location> locations = new ArrayList<>();
        LocalDateTime startsAt = LocalDateTime.of(1, 1, 1, 1, 1);
        LocalDateTime endsAt = LocalDateTime.of(1, 1, 1, 1, 1);
        byte[] image = "AXAXAXAX".getBytes("UTF-8");
        eventList.add(new Event(eventCategories, "Name", EntranceType.FREE, "The characteristics of someone or something",
                "Link To Application Form", locations, "42 Main St", startsAt, endsAt, "Key Words", image,
                "https://example.org/example", new Organisation()));
        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findEventsByStartDateDesc()).thenReturn(eventList);
        when(eventRepository.findEventsByStartDateAsc()).thenReturn(new ArrayList<>());
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        assertTrue(menuController.filterEventsByStartDate("desc", new MockHttpSession()).isReference());
        verify(eventRepository).findEventsByStartDateDesc();
    }

    /**
     * Method under test: {@link MenuController#filterEventsByEndDate(String, HttpSession)}
     */
    @Test
    void testFilterEventsByEndDate() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findEventsByEndDateAsc()).thenReturn(new ArrayList<>());
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper, new EventCategoryConverter());

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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        assertTrue(menuController.filterEventsByEndDate("Filter", new MockHttpSession()).isReference());
        verify(eventRepository).findEventsByEndDateAsc();
    }

    /**
     * Method under test: {@link MenuController#filterEventsByEndDate(String, HttpSession)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFilterEventsByEndDate2() {
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
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.findEventsByEndDateAsc(EventService.java:146)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.filterEventsByEndDate(EventService.java:153)
        //       at com.platform.marketplace.Marketplace.Platform.controller.MenuController.filterEventsByEndDate(MenuController.java:65)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findEventsByEndDateAsc()).thenReturn(eventList);
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        menuController.filterEventsByEndDate("Filter", new MockHttpSession());
    }

    /**
     * Method under test: {@link MenuController#filterEventsByEndDate(String, HttpSession)}
     */
    @Test
    void testFilterEventsByEndDate3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventService eventService = mock(EventService.class);
        when(eventService.filterEventsByEndDate((String) any())).thenReturn(new ArrayList<>());
        MenuController menuController = new MenuController(eventService);
        assertTrue(menuController.filterEventsByEndDate("Filter", new MockHttpSession()).isReference());
        verify(eventService).filterEventsByEndDate((String) any());
    }

    /**
     * Method under test: {@link MenuController#filterEventsByEndDate(String, HttpSession)}
     */
    @Test
    void testFilterEventsByEndDate4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findEventsByEndDateDesc()).thenReturn(new ArrayList<>());
        when(eventRepository.findEventsByEndDateAsc()).thenReturn(new ArrayList<>());
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        assertTrue(menuController.filterEventsByEndDate("desc", new MockHttpSession()).isReference());
        verify(eventRepository).findEventsByEndDateDesc();
    }

    /**
     * Method under test: {@link MenuController#filterEventsByEndDate(String, HttpSession)}
     */
    @Test
    void testFilterEventsByEndDate5() throws UnsupportedEncodingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ArrayList<Event> eventList = new ArrayList<>();
        HashSet<EventCategory> eventCategories = new HashSet<>();
        ArrayList<Location> locations = new ArrayList<>();
        LocalDateTime startsAt = LocalDateTime.of(1, 1, 1, 1, 1);
        LocalDateTime endsAt = LocalDateTime.of(1, 1, 1, 1, 1);
        byte[] image = "AXAXAXAX".getBytes("UTF-8");
        eventList.add(new Event(eventCategories, "Name", EntranceType.FREE, "The characteristics of someone or something",
                "Link To Application Form", locations, "42 Main St", startsAt, endsAt, "Key Words", image,
                "https://example.org/example", new Organisation()));
        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findEventsByEndDateDesc()).thenReturn(eventList);
        when(eventRepository.findEventsByEndDateAsc()).thenReturn(new ArrayList<>());
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        assertTrue(menuController.filterEventsByEndDate("desc", new MockHttpSession()).isReference());
        verify(eventRepository).findEventsByEndDateDesc();
    }

    /**
     * Method under test: {@link MenuController#filterEventsByCreateDate(String, HttpSession)}
     */
    @Test
    void testFilterEventsByCreateDate() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findOldestCreatedEvents()).thenReturn(new ArrayList<>());
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        LocationToStringMapper locationToStringMapper = new LocationToStringMapper();
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(locationToStringMapper, new EventCategoryConverter());

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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        assertTrue(menuController.filterEventsByCreateDate("Filter", new MockHttpSession()).isReference());
        verify(eventRepository).findOldestCreatedEvents();
    }

    /**
     * Method under test: {@link MenuController#filterEventsByCreateDate(String, HttpSession)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFilterEventsByCreateDate2() {
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
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.findOldestEvents(EventService.java:162)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.filterByCreatedDate(EventService.java:169)
        //       at com.platform.marketplace.Marketplace.Platform.controller.MenuController.filterEventsByCreateDate(MenuController.java:71)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findOldestCreatedEvents()).thenReturn(eventList);
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        menuController.filterEventsByCreateDate("Filter", new MockHttpSession());
    }

    /**
     * Method under test: {@link MenuController#filterEventsByCreateDate(String, HttpSession)}
     */
    @Test
    void testFilterEventsByCreateDate3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventService eventService = mock(EventService.class);
        when(eventService.filterByCreatedDate((String) any())).thenReturn(new ArrayList<>());
        MenuController menuController = new MenuController(eventService);
        assertTrue(menuController.filterEventsByCreateDate("Filter", new MockHttpSession()).isReference());
        verify(eventService).filterByCreatedDate((String) any());
    }

    /**
     * Method under test: {@link MenuController#filterEventsByCreateDate(String, HttpSession)}
     */
    @Test
    void testFilterEventsByCreateDate4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findNewestCreatedEvents()).thenReturn(new ArrayList<>());
        when(eventRepository.findOldestCreatedEvents()).thenReturn(new ArrayList<>());
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        assertTrue(menuController.filterEventsByCreateDate("desc", new MockHttpSession()).isReference());
        verify(eventRepository).findNewestCreatedEvents();
    }

    /**
     * Method under test: {@link MenuController#filterEventsByCreateDate(String, HttpSession)}
     */
    @Test
    void testFilterEventsByCreateDate5() throws UnsupportedEncodingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ArrayList<Event> eventList = new ArrayList<>();
        HashSet<EventCategory> eventCategories = new HashSet<>();
        ArrayList<Location> locations = new ArrayList<>();
        LocalDateTime startsAt = LocalDateTime.of(1, 1, 1, 1, 1);
        LocalDateTime endsAt = LocalDateTime.of(1, 1, 1, 1, 1);
        byte[] image = "AXAXAXAX".getBytes("UTF-8");
        eventList.add(new Event(eventCategories, "Name", EntranceType.FREE, "The characteristics of someone or something",
                "Link To Application Form", locations, "42 Main St", startsAt, endsAt, "Key Words", image,
                "https://example.org/example", new Organisation()));
        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findNewestCreatedEvents()).thenReturn(eventList);
        when(eventRepository.findOldestCreatedEvents()).thenReturn(new ArrayList<>());
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        assertTrue(menuController.filterEventsByCreateDate("desc", new MockHttpSession()).isReference());
        verify(eventRepository).findNewestCreatedEvents();
    }

    /**
     * Method under test: {@link MenuController#getEvents(String, String, String, String, String, String, String, HttpSession)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetEvents() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: Няма намерени събития с посочените от вас изисквания
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.returnSpecificFilteredEvents(EventService.java:249)
        //       at com.platform.marketplace.Marketplace.Platform.controller.MenuController.getEvents(MenuController.java:86)
        //   See https://diff.blue/R013 to resolve this issue.

        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findAll((Specification<Event>) any())).thenReturn(new ArrayList<>());
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        menuController.getEvents("Name", "Organisation Name", "42 Main St", "Location", "Entrance", "Category", "Keyword",
                new MockHttpSession());
    }

    /**
     * Method under test: {@link MenuController#getEvents(String, String, String, String, String, String, String, HttpSession)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetEvents2() {
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
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.returnSpecificFilteredEvents(EventService.java:238)
        //       at com.platform.marketplace.Marketplace.Platform.controller.MenuController.getEvents(MenuController.java:86)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findAll((Specification<Event>) any())).thenReturn(eventList);
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        menuController.getEvents("Name", "Organisation Name", "42 Main St", "Location", "Entrance", "Category", "Keyword",
                new MockHttpSession());
    }

    /**
     * Method under test: {@link MenuController#getEvents(String, String, String, String, String, String, String, HttpSession)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetEvents3() {
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
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.returnSpecificFilteredEvents(EventService.java:238)
        //       at com.platform.marketplace.Marketplace.Platform.controller.MenuController.getEvents(MenuController.java:86)
        //   See https://diff.blue/R013 to resolve this issue.

        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findAll((Specification<Event>) any())).thenReturn(new ArrayList<>());
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService, null,
                mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        menuController.getEvents("Name", "Organisation Name", "42 Main St", "Location", "Entrance", "Category", "Keyword",
                new MockHttpSession());
    }

    /**
     * Method under test: {@link MenuController#getEvents(String, String, String, String, String, String, String, HttpSession)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetEvents4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.platform.marketplace.Marketplace.Platform.service.event.SpecificationEventFilter.filterEvents(String, String, String, String, String, String, String)" because "this.specificationEventFilter" is null
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.returnSpecificFilteredEvents(EventService.java:240)
        //       at com.platform.marketplace.Marketplace.Platform.controller.MenuController.getEvents(MenuController.java:86)
        //   See https://diff.blue/R013 to resolve this issue.

        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findAll((Specification<Event>) any())).thenReturn(new ArrayList<>());
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, null, new ImageConvertor()));
        menuController.getEvents("Name", "Organisation Name", "42 Main St", "Location", "Entrance", "Category", "Keyword",
                new MockHttpSession());
    }

    /**
     * Method under test: {@link MenuController#getEvents(String, String, String, String, String, String, String, HttpSession)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetEvents5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: Няма намерени събития с посочените от вас изисквания
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.returnSpecificFilteredEvents(EventService.java:249)
        //       at com.platform.marketplace.Marketplace.Platform.controller.MenuController.getEvents(MenuController.java:86)
        //   See https://diff.blue/R013 to resolve this issue.

        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findAll((Specification<Event>) any())).thenReturn(new ArrayList<>());
        SpecificationEventFilter specificationEventFilter = mock(SpecificationEventFilter.class);
        when(specificationEventFilter.filterEvents((String) any(), (String) any(), (String) any(), (String) any(),
                (String) any(), (String) any(), (String) any())).thenReturn(null);
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        menuController.getEvents("Name", "Organisation Name", "42 Main St", "Location", "Entrance", "Category", "Keyword",
                new MockHttpSession());
    }

    /**
     * Method under test: {@link MenuController#getEvents(String, String, String, String, String, String, String, HttpSession)}
     */
    @Test
    void testGetEvents6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventService eventService = mock(EventService.class);
        when(eventService.returnSpecificFilteredEvents((String) any(), (String) any(), (String) any(), (String) any(),
                (String) any(), (String) any(), (String) any())).thenReturn(new ArrayList<>());
        MenuController menuController = new MenuController(eventService);
        assertTrue(
                menuController
                        .getEvents("Name", "Organisation Name", "42 Main St", "Location", "Entrance", "Category", "Keyword",
                                new MockHttpSession())
                        .isReference());
        verify(eventService).returnSpecificFilteredEvents((String) any(), (String) any(), (String) any(), (String) any(),
                (String) any(), (String) any(), (String) any());
    }

    /**
     * Method under test: {@link MenuController#showEventDetails(Long, Model)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testShowEventDetails() {
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
        //       at com.platform.marketplace.Marketplace.Platform.controller.MenuController.showEventDetails(MenuController.java:92)
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        menuController.showEventDetails(1L, new ConcurrentModel());
    }

    /**
     * Method under test: {@link MenuController#showEventDetails(Long, Model)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testShowEventDetails2() {
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
        //       at com.platform.marketplace.Marketplace.Platform.controller.MenuController.showEventDetails(MenuController.java:92)
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        menuController.showEventDetails(1L, new ConcurrentModel());
    }

    /**
     * Method under test: {@link MenuController#showEventDetails(Long, Model)}
     */
    @Test
    void testShowEventDetails3() throws UnsupportedEncodingException {
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        ConcurrentModel concurrentModel = new ConcurrentModel();
        assertEquals("eventDetails", menuController.showEventDetails(1L, concurrentModel));
        verify(eventRepository, atLeast(1)).findById((Long) any());
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
     * Method under test: {@link MenuController#showEventDetails(Long, Model)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testShowEventDetails4() {
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
        //       at com.platform.marketplace.Marketplace.Platform.controller.MenuController.showEventDetails(MenuController.java:92)
        //   See https://diff.blue/R013 to resolve this issue.

        EventRepository eventRepository = mock(EventRepository.class);
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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        menuController.showEventDetails(1L, new ConcurrentModel());
    }

    /**
     * Method under test: {@link MenuController#showEventDetails(Long, Model)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testShowEventDetails5() throws UnsupportedEncodingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.platform.marketplace.Marketplace.Platform.mapper.LocationToStringMapper.apply(java.util.List)" because "this.locationToStringMapper" is null
        //       at com.platform.marketplace.Marketplace.Platform.mapper.EventToEventDtoMapper.apply(EventToEventDtoMapper.java:32)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.getEventDTOById(EventService.java:179)
        //       at com.platform.marketplace.Marketplace.Platform.controller.MenuController.showEventDetails(MenuController.java:92)
        //   See https://diff.blue/R013 to resolve this issue.

        HashSet<EventCategory> eventCategories = new HashSet<>();
        ArrayList<Location> locations = new ArrayList<>();
        LocalDateTime startsAt = LocalDateTime.of(1, 1, 1, 1, 1);
        LocalDateTime endsAt = LocalDateTime.of(1, 1, 1, 1, 1);
        byte[] image = "AXAXAXAX".getBytes("UTF-8");

        Event event = new Event(eventCategories, "Name", EntranceType.FREE, "The characteristics of someone or something",
                "Link To Application Form", locations, "42 Main St", startsAt, endsAt, "Key Words", image,
                "https://example.org/example", new Organisation());
        event.setOrganisation(new Organisation("event", new ArrayList<>()));
        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findById((Long) any())).thenReturn(Optional.of(event));
        EventCategoryService eventCategoryService = new EventCategoryService(mock(EventCategoryRepository.class));
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(null, new EventCategoryConverter());

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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        menuController.showEventDetails(1L, new ConcurrentModel());
    }

    /**
     * Method under test: {@link MenuController#showEventDetails(Long, Model)}
     */
    @Test
    void testShowEventDetails6() throws UnsupportedEncodingException {
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
        EventCategoryConverter eventCategoryConverter = mock(EventCategoryConverter.class);
        when(eventCategoryConverter.convertToString((Set<EventCategory>) any())).thenReturn("Convert To String");
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(new LocationToStringMapper(),
                eventCategoryConverter);

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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        ConcurrentModel concurrentModel = new ConcurrentModel();
        assertEquals("eventDetails", menuController.showEventDetails(1L, concurrentModel));
        verify(eventRepository, atLeast(1)).findById((Long) any());
        verify(eventCategoryConverter, atLeast(1)).convertToString((Set<EventCategory>) any());
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
        assertEquals("Convert To String", ((EventDTO) concurrentModel.get("event")).getEventCategories());
        assertEquals(EntranceType.FREE, ((EventDTO) concurrentModel.get("event")).getEntranceType());
        assertEquals("00 часа и 00 минути", ((EventDTO) concurrentModel.get("event")).getDuration());
        assertEquals("01:01", ((EventDTO) concurrentModel.get("event")).getEndsAt().toLocalTime().toString());
        assertEquals("The characteristics of someone or something",
                ((EventDTO) concurrentModel.get("event")).getDescription());
        assertEquals("Събитието е започнало", ((EventDTO) concurrentModel.get("event")).getCounter());
    }

    /**
     * Method under test: {@link MenuController#showEventDetails(Long, Model)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testShowEventDetails7() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: temporal
        //       at java.util.Objects.requireNonNull(Objects.java:233)
        //       at java.time.LocalDate.from(LocalDate.java:394)
        //       at java.time.LocalDateTime.from(LocalDateTime.java:458)
        //       at java.time.LocalDateTime.until(LocalDateTime.java:1677)
        //       at java.time.Duration.between(Duration.java:490)
        //       at com.platform.marketplace.Marketplace.Platform.dto.EventDTO.setDuration(EventDTO.java:123)
        //       at com.platform.marketplace.Marketplace.Platform.dto.EventDTO.<init>(EventDTO.java:92)
        //       at com.platform.marketplace.Marketplace.Platform.mapper.EventToEventDtoMapper.apply(EventToEventDtoMapper.java:39)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.getEventDTOById(EventService.java:179)
        //       at com.platform.marketplace.Marketplace.Platform.controller.MenuController.showEventDetails(MenuController.java:92)
        //   See https://diff.blue/R013 to resolve this issue.

        Event event = mock(Event.class);
        when(event.isEnabled()).thenReturn(true);
        when(event.getEntranceType()).thenReturn(EntranceType.FREE);
        when(event.getAddress()).thenReturn("42 Main St");
        when(event.getDescription()).thenReturn("The characteristics of someone or something");
        when(event.getImageDataUrl()).thenReturn("https://example.org/example");
        when(event.getKeyWords()).thenReturn("Key Words");
        when(event.getLinkToApplicationForm()).thenReturn("Link To Application Form");
        when(event.getName()).thenReturn("Name");
        when(event.getEndsAt()).thenReturn(null);
        when(event.getStartsAt()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        when(event.getLocations()).thenReturn(new ArrayList<>());
        when(event.getEventCategories()).thenReturn(new HashSet<>());
        when(event.getOrganisation()).thenReturn(new Organisation());
        when(event.getId()).thenReturn(1L);
        doNothing().when(event).setOrganisation((Organisation) any());
        event.setOrganisation(new Organisation("event", new ArrayList<>()));
        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findById((Long) any())).thenReturn(Optional.of(event));
        EventCategoryConverter eventCategoryConverter = mock(EventCategoryConverter.class);
        when(eventCategoryConverter.convertToString((Set<EventCategory>) any())).thenReturn("Convert To String");
        EventToEventDtoMapper mapperTODto = new EventToEventDtoMapper(new LocationToStringMapper(),
                eventCategoryConverter);

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
        MenuController menuController = new MenuController(new EventService(eventRepository, eventCategoryService,
                mapperTODto, mapperToEntity, locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        menuController.showEventDetails(1L, new ConcurrentModel());
    }

    /**
     * Method under test: {@link MenuController#showEventDetails(Long, Model)}
     */
    @Test
    void testShowEventDetails8() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        Event event = mock(Event.class);
        when(event.isEnabled()).thenReturn(true);
        when(event.getEntranceType()).thenReturn(EntranceType.FREE);
        when(event.getAddress()).thenReturn("42 Main St");
        when(event.getDescription()).thenReturn("The characteristics of someone or something");
        when(event.getImageDataUrl()).thenReturn("https://example.org/example");
        when(event.getKeyWords()).thenReturn("Key Words");
        when(event.getLinkToApplicationForm()).thenReturn("Link To Application Form");
        when(event.getName()).thenReturn("Name");
        when(event.getEndsAt()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        when(event.getStartsAt()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        when(event.getLocations()).thenReturn(new ArrayList<>());
        when(event.getEventCategories()).thenReturn(new HashSet<>());
        when(event.getOrganisation()).thenReturn(new Organisation());
        when(event.getId()).thenReturn(1L);
        doNothing().when(event).setOrganisation((Organisation) any());
        event.setOrganisation(new Organisation("event", new ArrayList<>()));
        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findById((Long) any())).thenReturn(Optional.of(event));
        EventToEventDtoMapper eventToEventDtoMapper = mock(EventToEventDtoMapper.class);
        when(eventToEventDtoMapper.apply((Event) any())).thenReturn(new EventDTO());
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
        MenuController menuController = new MenuController(
                new EventService(eventRepository, eventCategoryService, eventToEventDtoMapper, mapperToEntity,
                        locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        assertEquals("eventDetails", menuController.showEventDetails(1L, new ConcurrentModel()));
        verify(eventRepository, atLeast(1)).findById((Long) any());
        verify(event).setOrganisation((Organisation) any());
        verify(eventToEventDtoMapper, atLeast(1)).apply((Event) any());
    }

    /**
     * Method under test: {@link MenuController#showEventDetails(Long, Model)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testShowEventDetails9() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.platform.marketplace.Marketplace.Platform.dto.EventDTO.getEventId()" because the return value of "com.platform.marketplace.Marketplace.Platform.service.event.EventService.getEventDTOById(java.lang.Long)" is null
        //       at com.platform.marketplace.Marketplace.Platform.controller.MenuController.showEventDetails(MenuController.java:93)
        //   See https://diff.blue/R013 to resolve this issue.

        EventRepository eventRepository = mock(EventRepository.class);
        when(eventRepository.findById((Long) any())).thenReturn(Optional.of(new Event()));
        EventToEventDtoMapper eventToEventDtoMapper = mock(EventToEventDtoMapper.class);
        when(eventToEventDtoMapper.apply((Event) any())).thenReturn(null);
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
        MenuController menuController = new MenuController(
                new EventService(eventRepository, eventCategoryService, eventToEventDtoMapper, mapperToEntity,
                        locationService1, converter1, specificationEventFilter, new ImageConvertor()));
        menuController.showEventDetails(1L, new ConcurrentModel());
    }

    /**
     * Method under test: {@link MenuController#showEventDetails(Long, Model)}
     */
    @Test
    void testShowEventDetails10() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        EventService eventService = mock(EventService.class);
        when(eventService.getEventDTOById((Long) any())).thenReturn(new EventDTO());
        MenuController menuController = new MenuController(eventService);
        assertEquals("eventDetails", menuController.showEventDetails(1L, new ConcurrentModel()));
        verify(eventService, atLeast(1)).getEventDTOById((Long) any());
    }
}

