package com.platform.marketplace.Marketplace.Platform.service.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.mapper.EventCategoryConverter;
import com.platform.marketplace.Marketplace.Platform.mapper.EventDtoToEventMapper;
import com.platform.marketplace.Marketplace.Platform.mapper.EventToEventDtoMapper;
import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.model.EventCategory;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.repository.EventRepository;
import com.platform.marketplace.Marketplace.Platform.service.location.LocationService;
import com.platform.marketplace.Marketplace.Platform.utility.consts.EntranceType;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException;

import java.time.LocalDateTime;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EventService.class})
@ExtendWith(SpringExtension.class)
class EventServiceTest {
    @MockBean
    private EventCategoryConverter eventCategoryConverter;

    @MockBean
    private EventCategoryService eventCategoryService;

    @MockBean
    private EventDtoToEventMapper eventDtoToEventMapper;

    @MockBean
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    @MockBean
    private EventToEventDtoMapper eventToEventDtoMapper;

    @MockBean
    private LocationService locationService;

    @MockBean
    private SpecificationEventFilter specificationEventFilter;

    /**
     * Method under test: {@link EventService#eventCounter(List)}
     */
    @Test
    void testEventCounter() {
        assertEquals(0, eventService.eventCounter(new ArrayList<>()));
    }

    /**
     * Method under test: {@link EventService#eventCounter(List)}
     */
    @Test
    void testEventCounter2() {
        ArrayList<EventDTO> eventDTOList = new ArrayList<>();
        eventDTOList.add(mock(EventDTO.class));
        assertEquals(1, eventService.eventCounter(eventDTOList));
    }

    /**
     * Method under test: {@link EventService#showAllEvents()}
     */
    @Test
    void testShowAllEvents() {
        when(eventRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(eventService.showAllEvents().isEmpty());
        verify(eventRepository).findAll();
    }

    /**
     * Method under test: {@link EventService#showAllEvents()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testShowAllEvents2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: An error occurred
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:57)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.showAllEvents(EventService.java:65)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findAll()).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        eventService.showAllEvents();
    }

    /**
     * Method under test: {@link EventService#getEventById(Long)}
     */
    @Test
    void testGetEventById() {
        Event event = new Event();
        when(eventRepository.findById((Long) any())).thenReturn(Optional.of(event));
        assertSame(event, eventService.getEventById(1L));
        verify(eventRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link EventService#getEventById(Long)}
     */
    @Test
    void testGetEventById2() {
        when(eventRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> eventService.getEventById(1L));
        verify(eventRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link EventService#getEventById(Long)}
     */
    @Test
    void testGetEventById3() {
        when(eventRepository.findById((Long) any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> eventService.getEventById(1L));
        verify(eventRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link EventService#getAllActiveEvents()}
     */
    @Test
    void testGetAllActiveEvents() {
        when(eventRepository.findAllActiveEvents()).thenReturn(new ArrayList<>());
        assertTrue(eventService.getAllActiveEvents().isEmpty());
        verify(eventRepository).findAllActiveEvents();
    }

    /**
     * Method under test: {@link EventService#getAllActiveEvents()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllActiveEvents2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: An error occurred
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:57)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.getAllActiveEvents(EventService.java:77)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findAllActiveEvents()).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        eventService.getAllActiveEvents();
    }

    /**
     * Method under test: {@link EventService#getAllInactiveEvents()}
     */
    @Test
    void testGetAllInactiveEvents() {
        when(eventRepository.findAllInactiveEvents()).thenReturn(new ArrayList<>());
        assertThrows(NotFoundException.class, () -> eventService.getAllInactiveEvents());
        verify(eventRepository, atLeast(1)).findAllInactiveEvents();
    }

    /**
     * Method under test: {@link EventService#getAllInactiveEvents()}
     */
    @Test
    void testGetAllInactiveEvents2() {
        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findAllInactiveEvents()).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenReturn(new EventDTO());
        assertEquals(1, eventService.getAllInactiveEvents().size());
        verify(eventRepository, atLeast(1)).findAllInactiveEvents();
        verify(eventToEventDtoMapper).apply((Event) any());
    }

    /**
     * Method under test: {@link EventService#getAllInactiveEvents()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllInactiveEvents3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: An error occurred
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:57)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.getAllInactiveEvents(EventService.java:84)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findAllInactiveEvents()).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        eventService.getAllInactiveEvents();
    }

    /**
     * Method under test: {@link EventService#getEventsByDescriptionKeyword(String)}
     */
    @Test
    void testGetEventsByDescriptionKeyword() {
        when(eventRepository.findEventsByDescriptionSearch((String) any())).thenReturn(new ArrayList<>());
        assertTrue(eventService.getEventsByDescriptionKeyword("Keyword").isEmpty());
        verify(eventRepository).findEventsByDescriptionSearch((String) any());
    }

    /**
     * Method under test: {@link EventService#getEventsByDescriptionKeyword(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetEventsByDescriptionKeyword2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: An error occurred
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:57)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.getEventsByDescriptionKeyword(EventService.java:88)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findEventsByDescriptionSearch((String) any())).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        eventService.getEventsByDescriptionKeyword("Keyword");
    }

    /**
     * Method under test: {@link EventService#getEventsByNameKeyword(String)}
     */
    @Test
    void testGetEventsByNameKeyword() {
        when(eventRepository.findEventsByName((String) any())).thenReturn(new ArrayList<>());
        assertTrue(eventService.getEventsByNameKeyword("Keyword").isEmpty());
        verify(eventRepository).findEventsByName((String) any());
    }

    /**
     * Method under test: {@link EventService#getEventsByNameKeyword(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetEventsByNameKeyword2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: An error occurred
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:57)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.getEventsByNameKeyword(EventService.java:92)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findEventsByName((String) any())).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        eventService.getEventsByNameKeyword("Keyword");
    }

    /**
     * Method under test: {@link EventService#getEventsByOneStringKeyword(String)}
     */
    @Test
    void testGetEventsByOneStringKeyword() {
        when(eventRepository.findEventsByOneStringKeyword((String) any())).thenReturn(new ArrayList<>());
        assertTrue(eventService.getEventsByOneStringKeyword("Keyword").isEmpty());
        verify(eventRepository).findEventsByOneStringKeyword((String) any());
    }

    /**
     * Method under test: {@link EventService#getEventsByOneStringKeyword(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetEventsByOneStringKeyword2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: An error occurred
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:57)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.getEventsByOneStringKeyword(EventService.java:96)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findEventsByOneStringKeyword((String) any())).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        eventService.getEventsByOneStringKeyword("Keyword");
    }

    /**
     * Method under test: {@link EventService#findEventsByOrgId(Long)}
     */
    @Test
    void testFindEventsByOrgId() {
        when(eventRepository.findEventsByOrganisationId((Long) any())).thenReturn(new ArrayList<>());
        assertTrue(eventService.findEventsByOrgId(1L).isEmpty());
        verify(eventRepository).findEventsByOrganisationId((Long) any());
    }

    /**
     * Method under test: {@link EventService#findEventsByOrgId(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindEventsByOrgId2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: An error occurred
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:57)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.findEventsByOrgId(EventService.java:100)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findEventsByOrganisationId((Long) any())).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        eventService.findEventsByOrgId(1L);
    }

    /**
     * Method under test: {@link EventService#findEventsByAddress(String)}
     */
    @Test
    void testFindEventsByAddress() {
        when(eventRepository.findEventsByAddress((String) any())).thenReturn(new ArrayList<>());
        assertTrue(eventService.findEventsByAddress("42 Main St").isEmpty());
        verify(eventRepository).findEventsByAddress((String) any());
    }

    /**
     * Method under test: {@link EventService#findEventsByAddress(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindEventsByAddress2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: An error occurred
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:57)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.findEventsByAddress(EventService.java:104)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findEventsByAddress((String) any())).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        eventService.findEventsByAddress("42 Main St");
    }

    /**
     * Method under test: {@link EventService#findEventsByLocation(String)}
     */
    @Test
    void testFindEventsByLocation() {
        when(eventRepository.findEventsByLocation((String) any())).thenReturn(new ArrayList<>());
        assertTrue(eventService.findEventsByLocation("Oxford").isEmpty());
        verify(eventRepository).findEventsByLocation((String) any());
    }

    /**
     * Method under test: {@link EventService#findEventsByLocation(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindEventsByLocation2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: An error occurred
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:57)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.findEventsByLocation(EventService.java:108)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findEventsByLocation((String) any())).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        eventService.findEventsByLocation("Oxford");
    }

    /**
     * Method under test: {@link EventService#findEventsByEntranceType(EntranceType)}
     */
    @Test
    void testFindEventsByEntranceType() {
        when(eventRepository.findEventsByEntranceType((EntranceType) any())).thenReturn(new ArrayList<>());
        assertTrue(eventService.findEventsByEntranceType(EntranceType.FREE).isEmpty());
        verify(eventRepository).findEventsByEntranceType((EntranceType) any());
    }

    /**
     * Method under test: {@link EventService#findEventsByEntranceType(EntranceType)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindEventsByEntranceType2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: An error occurred
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:57)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.findEventsByEntranceType(EventService.java:112)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findEventsByEntranceType((EntranceType) any())).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        eventService.findEventsByEntranceType(EntranceType.FREE);
    }

    /**
     * Method under test: {@link EventService#findEventsByEntranceType(EntranceType)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindEventsByEntranceType3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: An error occurred
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:57)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.findEventsByEntranceType(EventService.java:112)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findEventsByEntranceType((EntranceType) any())).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        eventService.findEventsByEntranceType(EntranceType.PAID);
    }

    /**
     * Method under test: {@link EventService#findEventsByKeyWords(String)}
     */
    @Test
    void testFindEventsByKeyWords() {
        when(eventRepository.findEventsByKeyWords((String) any())).thenReturn(new ArrayList<>());
        assertTrue(eventService.findEventsByKeyWords("Keyword").isEmpty());
        verify(eventRepository).findEventsByKeyWords((String) any());
    }

    /**
     * Method under test: {@link EventService#findEventsByKeyWords(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindEventsByKeyWords2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: An error occurred
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:57)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.findEventsByKeyWords(EventService.java:116)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findEventsByKeyWords((String) any())).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        eventService.findEventsByKeyWords("Keyword");
    }

    /**
     * Method under test: {@link EventService#filterEventsByStartDate(String)}
     */
    @Test
    void testFilterEventsByStartDate() {
        when(eventRepository.findEventsByStartDateAsc()).thenReturn(new ArrayList<>());
        assertTrue(eventService.filterEventsByStartDate("Filter").isEmpty());
        verify(eventRepository).findEventsByStartDateAsc();
    }

    /**
     * Method under test: {@link EventService#filterEventsByStartDate(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFilterEventsByStartDate2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: An error occurred
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:57)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.findEventsByStartDateAsc(EventService.java:120)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.filterEventsByStartDate(EventService.java:131)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findEventsByStartDateAsc()).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        eventService.filterEventsByStartDate("Filter");
    }

    /**
     * Method under test: {@link EventService#filterEventsByStartDate(String)}
     */
    @Test
    void testFilterEventsByStartDate3() {
        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findEventsByStartDateDesc()).thenReturn(new ArrayList<>());
        when(eventRepository.findEventsByStartDateAsc()).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        assertTrue(eventService.filterEventsByStartDate("desc").isEmpty());
        verify(eventRepository).findEventsByStartDateDesc();
    }

    /**
     * Method under test: {@link EventService#filterEventsByStartDate(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFilterEventsByStartDate4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: An error occurred
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:57)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.findEventsByStartDateDesc(EventService.java:124)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.filterEventsByStartDate(EventService.java:129)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());

        ArrayList<Event> eventList1 = new ArrayList<>();
        eventList1.add(new Event());
        when(eventRepository.findEventsByStartDateDesc()).thenReturn(eventList1);
        when(eventRepository.findEventsByStartDateAsc()).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        eventService.filterEventsByStartDate("desc");
    }

    /**
     * Method under test: {@link EventService#filterEventsByStartDate(String)}
     */
    @Test
    void testFilterEventsByStartDate5() {
        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findEventsByStartDateDesc()).thenThrow(new NotFoundException("An error occurred"));
        when(eventRepository.findEventsByStartDateAsc()).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> eventService.filterEventsByStartDate("desc"));
        verify(eventRepository).findEventsByStartDateDesc();
    }

    /**
     * Method under test: {@link EventService#filterEventsByEndDate(String)}
     */
    @Test
    void testFilterEventsByEndDate() {
        when(eventRepository.findEventsByEndDateAsc()).thenReturn(new ArrayList<>());
        assertTrue(eventService.filterEventsByEndDate("Filter").isEmpty());
        verify(eventRepository).findEventsByEndDateAsc();
    }

    /**
     * Method under test: {@link EventService#filterEventsByEndDate(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFilterEventsByEndDate2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: An error occurred
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:57)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.findEventsByEndDateAsc(EventService.java:141)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.filterEventsByEndDate(EventService.java:148)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findEventsByEndDateAsc()).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        eventService.filterEventsByEndDate("Filter");
    }

    /**
     * Method under test: {@link EventService#filterEventsByEndDate(String)}
     */
    @Test
    void testFilterEventsByEndDate3() {
        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findEventsByEndDateDesc()).thenReturn(new ArrayList<>());
        when(eventRepository.findEventsByEndDateAsc()).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        assertTrue(eventService.filterEventsByEndDate("desc").isEmpty());
        verify(eventRepository).findEventsByEndDateDesc();
    }

    /**
     * Method under test: {@link EventService#filterEventsByEndDate(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFilterEventsByEndDate4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: An error occurred
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:57)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.findEventsByEndDateDesc(EventService.java:136)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.filterEventsByEndDate(EventService.java:146)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());

        ArrayList<Event> eventList1 = new ArrayList<>();
        eventList1.add(new Event());
        when(eventRepository.findEventsByEndDateDesc()).thenReturn(eventList1);
        when(eventRepository.findEventsByEndDateAsc()).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        eventService.filterEventsByEndDate("desc");
    }

    /**
     * Method under test: {@link EventService#filterEventsByEndDate(String)}
     */
    @Test
    void testFilterEventsByEndDate5() {
        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findEventsByEndDateDesc()).thenThrow(new NotFoundException("An error occurred"));
        when(eventRepository.findEventsByEndDateAsc()).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> eventService.filterEventsByEndDate("desc"));
        verify(eventRepository).findEventsByEndDateDesc();
    }

    /**
     * Method under test: {@link EventService#filterByCreatedDate(String)}
     */
    @Test
    void testFilterByCreatedDate() {
        when(eventRepository.findOldestCreatedEvents()).thenReturn(new ArrayList<>());
        assertTrue(eventService.filterByCreatedDate("Filter").isEmpty());
        verify(eventRepository).findOldestCreatedEvents();
    }

    /**
     * Method under test: {@link EventService#filterByCreatedDate(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFilterByCreatedDate2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: An error occurred
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:57)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.findOldestEvents(EventService.java:157)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.filterByCreatedDate(EventService.java:164)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findOldestCreatedEvents()).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        eventService.filterByCreatedDate("Filter");
    }

    /**
     * Method under test: {@link EventService#filterByCreatedDate(String)}
     */
    @Test
    void testFilterByCreatedDate3() {
        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findNewestCreatedEvents()).thenReturn(new ArrayList<>());
        when(eventRepository.findOldestCreatedEvents()).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        assertTrue(eventService.filterByCreatedDate("desc").isEmpty());
        verify(eventRepository).findNewestCreatedEvents();
    }

    /**
     * Method under test: {@link EventService#filterByCreatedDate(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFilterByCreatedDate4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: An error occurred
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:57)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.findNewestEvents(EventService.java:153)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.filterByCreatedDate(EventService.java:162)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());

        ArrayList<Event> eventList1 = new ArrayList<>();
        eventList1.add(new Event());
        when(eventRepository.findNewestCreatedEvents()).thenReturn(eventList1);
        when(eventRepository.findOldestCreatedEvents()).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        eventService.filterByCreatedDate("desc");
    }

    /**
     * Method under test: {@link EventService#filterByCreatedDate(String)}
     */
    @Test
    void testFilterByCreatedDate5() {
        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findNewestCreatedEvents()).thenThrow(new NotFoundException("An error occurred"));
        when(eventRepository.findOldestCreatedEvents()).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> eventService.filterByCreatedDate("desc"));
        verify(eventRepository).findNewestCreatedEvents();
    }

    /**
     * Method under test: {@link EventService#findEventsByCategory(String)}
     */
    @Test
    void testFindEventsByCategory() {
        when(eventRepository.findEventsByCategories((String) any())).thenReturn(new ArrayList<>());
        assertTrue(eventService.findEventsByCategory("Category").isEmpty());
        verify(eventRepository).findEventsByCategories((String) any());
    }

    /**
     * Method under test: {@link EventService#findEventsByCategory(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindEventsByCategory2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: An error occurred
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:57)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.findEventsByCategory(EventService.java:169)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findEventsByCategories((String) any())).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        eventService.findEventsByCategory("Category");
    }

    /**
     * Method under test: {@link EventService#getEventDTOById(Long)}
     */
    @Test
    void testGetEventDTOById() {
        when(eventRepository.findById((Long) any())).thenReturn(Optional.of(new Event()));
        EventDTO eventDTO = new EventDTO();
        when(eventToEventDtoMapper.apply((Event) any())).thenReturn(eventDTO);
        assertSame(eventDTO, eventService.getEventDTOById(1L));
        verify(eventRepository).findById((Long) any());
        verify(eventToEventDtoMapper).apply((Event) any());
    }

    /**
     * Method under test: {@link EventService#getEventDTOById(Long)}
     */
    @Test
    void testGetEventDTOById2() {
        when(eventRepository.findById((Long) any())).thenReturn(Optional.of(new Event()));
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> eventService.getEventDTOById(1L));
        verify(eventRepository).findById((Long) any());
        verify(eventToEventDtoMapper).apply((Event) any());
    }

    /**
     * Method under test: {@link EventService#getEventDTOById(Long)}
     */
    @Test
    void testGetEventDTOById3() {
        when(eventRepository.findById((Long) any())).thenReturn(Optional.empty());
        when(eventToEventDtoMapper.apply((Event) any())).thenReturn(new EventDTO());
        assertThrows(NotFoundException.class, () -> eventService.getEventDTOById(1L));
        verify(eventRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link EventService#getEventDTOByName(String)}
     */
    @Test
    void testGetEventDTOByName() {
        when(eventRepository.findEventByName((String) any())).thenReturn(Optional.of(new Event()));
        EventDTO eventDTO = new EventDTO();
        when(eventToEventDtoMapper.apply((Event) any())).thenReturn(eventDTO);
        assertSame(eventDTO, eventService.getEventDTOByName("Name"));
        verify(eventRepository).findEventByName((String) any());
        verify(eventToEventDtoMapper).apply((Event) any());
    }

    /**
     * Method under test: {@link EventService#getEventDTOByName(String)}
     */
    @Test
    void testGetEventDTOByName2() {
        when(eventRepository.findEventByName((String) any())).thenReturn(Optional.of(new Event()));
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> eventService.getEventDTOByName("Name"));
        verify(eventRepository).findEventByName((String) any());
        verify(eventToEventDtoMapper).apply((Event) any());
    }

    /**
     * Method under test: {@link EventService#getEventDTOByName(String)}
     */
    @Test
    void testGetEventDTOByName3() {
        when(eventRepository.findEventByName((String) any())).thenReturn(Optional.empty());
        when(eventToEventDtoMapper.apply((Event) any())).thenReturn(new EventDTO());
        assertThrows(NotFoundException.class, () -> eventService.getEventDTOByName("Name"));
        verify(eventRepository).findEventByName((String) any());
    }

    /**
     * Method under test: {@link EventService#getEventByEventIdAndOrgId(Long, Long)}
     */
    @Test
    void testGetEventByEventIdAndOrgId() {
        Event event = new Event();
        when(eventRepository.getEventByEventIdAndOrgId((Long) any(), (Long) any())).thenReturn(Optional.of(event));
        assertSame(event, eventService.getEventByEventIdAndOrgId(1L, 1L));
        verify(eventRepository).getEventByEventIdAndOrgId((Long) any(), (Long) any());
    }

    /**
     * Method under test: {@link EventService#getEventByEventIdAndOrgId(Long, Long)}
     */
    @Test
    void testGetEventByEventIdAndOrgId2() {
        when(eventRepository.getEventByEventIdAndOrgId((Long) any(), (Long) any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> eventService.getEventByEventIdAndOrgId(1L, 1L));
        verify(eventRepository).getEventByEventIdAndOrgId((Long) any(), (Long) any());
    }

    /**
     * Method under test: {@link EventService#getEventByEventIdAndOrgId(Long, Long)}
     */
    @Test
    void testGetEventByEventIdAndOrgId3() {
        when(eventRepository.getEventByEventIdAndOrgId((Long) any(), (Long) any()))
                .thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> eventService.getEventByEventIdAndOrgId(1L, 1L));
        verify(eventRepository).getEventByEventIdAndOrgId((Long) any(), (Long) any());
    }

    /**
     * Method under test: {@link EventService#createEvent(EventDTO, Organisation)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateEvent() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Collection.toArray()" because "c" is null
        //       at java.util.ArrayList.<init>(ArrayList.java:181)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.createEvent(EventService.java:190)
        //   See https://diff.blue/R013 to resolve this issue.

        when(eventDtoToEventMapper.apply((EventDTO) any())).thenReturn(new Event());
        EventDTO eventDTO = new EventDTO();
        eventService.createEvent(eventDTO, new Organisation());
    }

    /**
     * Method under test: {@link EventService#createEvent(EventDTO, Organisation)}
     */
    @Test
    void testCreateEvent2() {
        when(eventRepository.save((Event) any())).thenReturn(new Event());
        doNothing().when(eventCategoryService).saveEventCategoriesFromList((List<EventCategory>) any());

        Event event = new Event();
        event.setEventCategories(new HashSet<>());
        when(eventDtoToEventMapper.apply((EventDTO) any())).thenReturn(event);
        EventDTO eventDTO = new EventDTO();
        eventService.createEvent(eventDTO, new Organisation());
        verify(eventRepository).save((Event) any());
        verify(eventCategoryService).saveEventCategoriesFromList((List<EventCategory>) any());
        verify(eventDtoToEventMapper).apply((EventDTO) any());
    }

    /**
     * Method under test: {@link EventService#createEvent(EventDTO, Organisation)}
     */
    @Test
    void testCreateEvent3() {
        when(eventRepository.save((Event) any())).thenReturn(new Event());
        doThrow(new NotFoundException("An error occurred")).when(eventCategoryService)
                .saveEventCategoriesFromList((List<EventCategory>) any());

        Event event = new Event();
        event.setEventCategories(new HashSet<>());
        when(eventDtoToEventMapper.apply((EventDTO) any())).thenReturn(event);
        EventDTO eventDTO = new EventDTO();
        assertThrows(NotFoundException.class, () -> eventService.createEvent(eventDTO, new Organisation()));
        verify(eventCategoryService).saveEventCategoriesFromList((List<EventCategory>) any());
        verify(eventDtoToEventMapper).apply((EventDTO) any());
    }

    /**
     * Method under test: {@link EventService#createEvent(EventDTO, Organisation)}
     */
    @Test
    void testCreateEvent4() {
        when(eventRepository.save((Event) any())).thenReturn(new Event());
        doNothing().when(eventCategoryService).saveEventCategoriesFromList((List<EventCategory>) any());
        Event event = mock(Event.class);
        when(event.getEventCategories()).thenReturn(new HashSet<>());
        doNothing().when(event).setEventCategories((Set<EventCategory>) any());
        doNothing().when(event).setOrganisation((Organisation) any());
        event.setEventCategories(new HashSet<>());
        when(eventDtoToEventMapper.apply((EventDTO) any())).thenReturn(event);
        EventDTO eventDTO = new EventDTO();
        eventService.createEvent(eventDTO, new Organisation());
        verify(eventRepository).save((Event) any());
        verify(eventCategoryService).saveEventCategoriesFromList((List<EventCategory>) any());
        verify(eventDtoToEventMapper).apply((EventDTO) any());
        verify(event).getEventCategories();
        verify(event).setEventCategories((Set<EventCategory>) any());
        verify(event).setOrganisation((Organisation) any());
    }

    /**
     * Method under test: {@link EventService#deleteEvent(Event)}
     */
    @Test
    void testDeleteEvent() {
        doNothing().when(eventRepository).delete((Event) any());
        Event event = new Event();
        eventService.deleteEvent(event);
        verify(eventRepository).delete((Event) any());
        assertFalse(event.isExpired());
        assertFalse(event.isEnabled());
    }

    /**
     * Method under test: {@link EventService#deleteEvent(Event)}
     */
    @Test
    void testDeleteEvent2() {
        doThrow(new NotFoundException("An error occurred")).when(eventRepository).delete((Event) any());
        assertThrows(NotFoundException.class, () -> eventService.deleteEvent(new Event()));
        verify(eventRepository).delete((Event) any());
    }

    /**
     * Method under test: {@link EventService#deleteEventById(Long)}
     */
    @Test
    void testDeleteEventById() {
        doNothing().when(eventRepository).deleteById((Long) any());
        eventService.deleteEventById(1L);
        verify(eventRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link EventService#deleteEventById(Long)}
     */
    @Test
    void testDeleteEventById2() {
        doThrow(new NotFoundException("An error occurred")).when(eventRepository).deleteById((Long) any());
        assertThrows(NotFoundException.class, () -> eventService.deleteEventById(1L));
        verify(eventRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link EventService#setIsEnabledEventField(Long, boolean)}
     */
    @Test
    void testSetIsEnabledEventField() {
        when(eventRepository.save((Event) any())).thenReturn(new Event());
        when(eventRepository.findById((Long) any())).thenReturn(Optional.of(new Event()));
        eventService.setIsEnabledEventField(1L, true);
        verify(eventRepository).save((Event) any());
        verify(eventRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link EventService#setIsEnabledEventField(Long, boolean)}
     */
    @Test
    void testSetIsEnabledEventField2() {
        when(eventRepository.save((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        when(eventRepository.findById((Long) any())).thenReturn(Optional.of(new Event()));
        assertThrows(NotFoundException.class, () -> eventService.setIsEnabledEventField(1L, true));
        verify(eventRepository).save((Event) any());
        verify(eventRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link EventService#setIsEnabledEventField(Long, boolean)}
     */
    @Test
    void testSetIsEnabledEventField3() {
        Event event = mock(Event.class);
        doNothing().when(event).setEnabled(anyBoolean());
        Optional<Event> ofResult = Optional.of(event);
        when(eventRepository.save((Event) any())).thenReturn(new Event());
        when(eventRepository.findById((Long) any())).thenReturn(ofResult);
        eventService.setIsEnabledEventField(1L, true);
        verify(eventRepository).save((Event) any());
        verify(eventRepository).findById((Long) any());
        verify(event).setEnabled(anyBoolean());
    }

    /**
     * Method under test: {@link EventService#setIsEnabledEventField(Long, boolean)}
     */
    @Test
    void testSetIsEnabledEventField4() {
        when(eventRepository.save((Event) any())).thenReturn(new Event());
        when(eventRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> eventService.setIsEnabledEventField(1L, true));
        verify(eventRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link EventService#setIsEnabledEventField(Long, boolean)}
     */
    @Test
    void testSetIsEnabledEventField5() {
        Event event = mock(Event.class);
        doThrow(new NotFoundException("An error occurred")).when(event).setEnabled(anyBoolean());
        Optional<Event> ofResult = Optional.of(event);
        when(eventRepository.save((Event) any())).thenReturn(new Event());
        when(eventRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(NotFoundException.class, () -> eventService.setIsEnabledEventField(1L, true));
        verify(eventRepository).findById((Long) any());
        verify(event).setEnabled(anyBoolean());
    }

    /**
     * Method under test: {@link EventService#updateEvent(Event, EventDTO)}
     */
    @Test
    void testUpdateEvent() {
        when(eventRepository.save((Event) any())).thenReturn(new Event());
        when(locationService.findLocationsByValues((List<String>) any())).thenReturn(new ArrayList<>());
        when(eventCategoryConverter.convertToEventCategories((String) any())).thenReturn(new HashSet<>());
        Event event = new Event();
        eventService.updateEvent(event, new EventDTO());
        verify(eventRepository).save((Event) any());
        verify(locationService).findLocationsByValues((List<String>) any());
        verify(eventCategoryConverter).convertToEventCategories((String) any());
        assertNull(event.getStartsAt());
        assertNull(event.getName());
        assertTrue(event.getLocations().isEmpty());
        assertNull(event.getLinkToApplicationForm());
        assertNull(event.getKeyWords());
        assertTrue(event.getEventCategories().isEmpty());
        assertNull(event.getEntranceType());
        assertNull(event.getEndsAt());
        assertNull(event.getDescription());
    }

    /**
     * Method under test: {@link EventService#updateEvent(Event, EventDTO)}
     */
    @Test
    void testUpdateEvent2() {
        when(eventRepository.save((Event) any())).thenReturn(new Event());
        when(locationService.findLocationsByValues((List<String>) any())).thenReturn(new ArrayList<>());
        when(eventCategoryConverter.convertToEventCategories((String) any()))
                .thenThrow(new NotFoundException("An error occurred"));
        Event event = new Event();
        assertThrows(NotFoundException.class, () -> eventService.updateEvent(event, new EventDTO()));
        verify(locationService).findLocationsByValues((List<String>) any());
        verify(eventCategoryConverter).convertToEventCategories((String) any());
    }

    /**
     * Method under test: {@link EventService#updateEvent(Event, EventDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateEvent3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.platform.marketplace.Marketplace.Platform.model.Event.setName(String)" because "event" is null
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.updateEvent(EventService.java:212)
        //   See https://diff.blue/R013 to resolve this issue.

        when(eventRepository.save((Event) any())).thenReturn(new Event());
        when(locationService.findLocationsByValues((List<String>) any())).thenReturn(new ArrayList<>());
        when(eventCategoryConverter.convertToEventCategories((String) any())).thenReturn(new HashSet<>());
        eventService.updateEvent(null, new EventDTO());
    }

    /**
     * Method under test: {@link EventService#updateEvent(Event, EventDTO)}
     */
    @Test
    void testUpdateEvent4() {
        when(eventRepository.save((Event) any())).thenReturn(new Event());
        when(locationService.findLocationsByValues((List<String>) any())).thenReturn(new ArrayList<>());
        when(eventCategoryConverter.convertToEventCategories((String) any())).thenReturn(new HashSet<>());
        Event event = mock(Event.class);
        doNothing().when(event).setDescription((String) any());
        doNothing().when(event).setEndsAt((LocalDateTime) any());
        doNothing().when(event).setEntranceType((EntranceType) any());
        doNothing().when(event).setEventCategories((Set<EventCategory>) any());
        doNothing().when(event).setKeyWords((String) any());
        doNothing().when(event).setLinkToApplicationForm((String) any());
        doNothing().when(event).setLocations((List<Location>) any());
        doNothing().when(event).setName((String) any());
        doNothing().when(event).setStartsAt((LocalDateTime) any());
        eventService.updateEvent(event, new EventDTO());
        verify(eventRepository).save((Event) any());
        verify(locationService).findLocationsByValues((List<String>) any());
        verify(eventCategoryConverter).convertToEventCategories((String) any());
        verify(event).setDescription((String) any());
        verify(event).setEndsAt((LocalDateTime) any());
        verify(event).setEntranceType((EntranceType) any());
        verify(event).setEventCategories((Set<EventCategory>) any());
        verify(event).setKeyWords((String) any());
        verify(event).setLinkToApplicationForm((String) any());
        verify(event).setLocations((List<Location>) any());
        verify(event).setName((String) any());
        verify(event).setStartsAt((LocalDateTime) any());
    }

    /**
     * Method under test: {@link EventService#updateEvent(Event, EventDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateEvent5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.platform.marketplace.Marketplace.Platform.dto.EventDTO.getLocations()" because "eventDTO" is null
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.updateEvent(EventService.java:211)
        //   See https://diff.blue/R013 to resolve this issue.

        when(eventRepository.save((Event) any())).thenReturn(new Event());
        when(locationService.findLocationsByValues((List<String>) any())).thenReturn(new ArrayList<>());
        when(eventCategoryConverter.convertToEventCategories((String) any())).thenReturn(new HashSet<>());
        Event event = mock(Event.class);
        doNothing().when(event).setDescription((String) any());
        doNothing().when(event).setEndsAt((LocalDateTime) any());
        doNothing().when(event).setEntranceType((EntranceType) any());
        doNothing().when(event).setEventCategories((Set<EventCategory>) any());
        doNothing().when(event).setKeyWords((String) any());
        doNothing().when(event).setLinkToApplicationForm((String) any());
        doNothing().when(event).setLocations((List<Location>) any());
        doNothing().when(event).setName((String) any());
        doNothing().when(event).setStartsAt((LocalDateTime) any());
        eventService.updateEvent(event, null);
    }

    /**
     * Method under test: {@link EventService#updateEvent(Event, EventDTO)}
     */
    @Test
    void testUpdateEvent6() {
        when(eventRepository.save((Event) any())).thenReturn(new Event());
        when(locationService.findLocationsByValues((List<String>) any())).thenReturn(new ArrayList<>());
        when(eventCategoryConverter.convertToEventCategories((String) any())).thenReturn(new HashSet<>());
        Event event = mock(Event.class);
        doNothing().when(event).setDescription((String) any());
        doNothing().when(event).setEndsAt((LocalDateTime) any());
        doNothing().when(event).setEntranceType((EntranceType) any());
        doNothing().when(event).setEventCategories((Set<EventCategory>) any());
        doNothing().when(event).setKeyWords((String) any());
        doNothing().when(event).setLinkToApplicationForm((String) any());
        doNothing().when(event).setLocations((List<Location>) any());
        doNothing().when(event).setName((String) any());
        doNothing().when(event).setStartsAt((LocalDateTime) any());
        EventDTO eventDTO = mock(EventDTO.class);
        when(eventDTO.getEntranceType()).thenReturn(EntranceType.FREE);
        when(eventDTO.getDescription()).thenReturn("The characteristics of someone or something");
        when(eventDTO.getEventCategories()).thenReturn("Event Categories");
        when(eventDTO.getKeywords()).thenReturn("Keywords");
        when(eventDTO.getLinkToApplicationForm()).thenReturn("Link To Application Form");
        when(eventDTO.getName()).thenReturn("Name");
        when(eventDTO.getEndsAt()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        when(eventDTO.getStartsAt()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        when(eventDTO.getLocations()).thenReturn(new ArrayList<>());
        eventService.updateEvent(event, eventDTO);
        verify(eventRepository).save((Event) any());
        verify(locationService).findLocationsByValues((List<String>) any());
        verify(eventCategoryConverter).convertToEventCategories((String) any());
        verify(event).setDescription((String) any());
        verify(event).setEndsAt((LocalDateTime) any());
        verify(event).setEntranceType((EntranceType) any());
        verify(event).setEventCategories((Set<EventCategory>) any());
        verify(event).setKeyWords((String) any());
        verify(event).setLinkToApplicationForm((String) any());
        verify(event).setLocations((List<Location>) any());
        verify(event).setName((String) any());
        verify(event).setStartsAt((LocalDateTime) any());
        verify(eventDTO).getEntranceType();
        verify(eventDTO).getDescription();
        verify(eventDTO).getEventCategories();
        verify(eventDTO).getKeywords();
        verify(eventDTO).getLinkToApplicationForm();
        verify(eventDTO).getName();
        verify(eventDTO).getEndsAt();
        verify(eventDTO).getStartsAt();
        verify(eventDTO).getLocations();
    }

    /**
     * Method under test: {@link EventService#returnSpecificFilteredEvents(String, String, String, String, String, String, String)}
     */
    @Test
    void testReturnSpecificFilteredEvents() {
        when(eventRepository.findAll((Specification<Event>) any())).thenReturn(new ArrayList<>());
        when(specificationEventFilter.filterEvents((String) any(), (String) any(), (String) any(), (String) any(),
                (String) any(), (String) any(), (String) any())).thenReturn(null);
        assertThrows(NotFoundException.class, () -> eventService.returnSpecificFilteredEvents("Name", "Organisation Name",
                "42 Main St", "Location", "Entrance", "Category", "Keyword"));
        verify(eventRepository).findAll((Specification<Event>) any());
        verify(specificationEventFilter).filterEvents((String) any(), (String) any(), (String) any(), (String) any(),
                (String) any(), (String) any(), (String) any());
    }

    /**
     * Method under test: {@link EventService#returnSpecificFilteredEvents(String, String, String, String, String, String, String)}
     */
    @Test
    void testReturnSpecificFilteredEvents2() {
        when(eventRepository.findAll((Specification<Event>) any())).thenReturn(new ArrayList<>());
        when(specificationEventFilter.filterEvents((String) any(), (String) any(), (String) any(), (String) any(),
                (String) any(), (String) any(), (String) any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> eventService.returnSpecificFilteredEvents("Name", "Organisation Name",
                "42 Main St", "Location", "Entrance", "Category", "Keyword"));
        verify(specificationEventFilter).filterEvents((String) any(), (String) any(), (String) any(), (String) any(),
                (String) any(), (String) any(), (String) any());
    }

    /**
     * Method under test: {@link EventService#returnSpecificFilteredEvents(String, String, String, String, String, String, String)}
     */
    @Test
    void testReturnSpecificFilteredEvents3() {
        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findAll((Specification<Event>) any())).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenReturn(new EventDTO());
        when(specificationEventFilter.filterEvents((String) any(), (String) any(), (String) any(), (String) any(),
                (String) any(), (String) any(), (String) any())).thenReturn(null);
        assertEquals(1,
                eventService
                        .returnSpecificFilteredEvents("Name", "Organisation Name", "42 Main St", "Location", "Entrance",
                                "Category", "Keyword")
                        .size());
        verify(eventRepository).findAll((Specification<Event>) any());
        verify(eventToEventDtoMapper).apply((Event) any());
        verify(specificationEventFilter).filterEvents((String) any(), (String) any(), (String) any(), (String) any(),
                (String) any(), (String) any(), (String) any());
    }

    /**
     * Method under test: {@link EventService#returnSpecificFilteredEvents(String, String, String, String, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testReturnSpecificFilteredEvents4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException: An error occurred
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.convertToDtoList(EventService.java:57)
        //       at com.platform.marketplace.Marketplace.Platform.service.event.EventService.returnSpecificFilteredEvents(EventService.java:231)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        when(eventRepository.findAll((Specification<Event>) any())).thenReturn(eventList);
        when(eventToEventDtoMapper.apply((Event) any())).thenThrow(new NotFoundException("An error occurred"));
        when(specificationEventFilter.filterEvents((String) any(), (String) any(), (String) any(), (String) any(),
                (String) any(), (String) any(), (String) any())).thenReturn(null);
        eventService.returnSpecificFilteredEvents("Name", "Organisation Name", "42 Main St", "Location", "Entrance",
                "Category", "Keyword");
    }
}

