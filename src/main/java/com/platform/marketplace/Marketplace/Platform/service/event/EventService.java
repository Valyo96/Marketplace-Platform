package com.platform.marketplace.Marketplace.Platform.service.event;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.mapper.*;
import com.platform.marketplace.Marketplace.Platform.model.EventCategory;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import com.platform.marketplace.Marketplace.Platform.service.image.ImageConvertor;
import com.platform.marketplace.Marketplace.Platform.service.location.LocationService;
import com.platform.marketplace.Marketplace.Platform.utility.Utility;
import com.platform.marketplace.Marketplace.Platform.utility.consts.EntranceType;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException;
import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.repository.EventRepository;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.platform.marketplace.Marketplace.Platform.utility.consts.ConstantMessages.*;

@Service
@RequiredArgsConstructor

public class EventService {

    private final EventRepository eventRepository;

    private final EventCategoryService eventCategoryService;


    private final EventToEventDtoMapper mapperTODto;

    private final EventDtoToEventMapper mapperToEntity;

    private final LocationService locationService;

    private final EventCategoryConverter converter;

    private final SpecificationEventFilter specificationEventFilter;

    private final ImageConvertor imageConvertor;


    public int eventCounter(List<EventDTO> events) {
        return events.size();
    }

    private List<EventDTO> convertToDtoList(List<Event> events) {
        return events.stream().map(mapperTODto).collect(Collectors.toList());
    }

    private List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<EventDTO> showAllEvents() {
        return convertToDtoList(getAllEvents());
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new NotFoundException(EVENT_BY_ID_NOT_FOUND));
    }

    private Event getEventByName(String name) {
        return eventRepository.findEventByName(name).orElseThrow(() -> new NotFoundException(EVENT_NOT_FOUND));
    }

    public List<EventDTO> getAllActiveEvents() {
        return convertToDtoList(eventRepository.findAllActiveEvents());
    }

    public List<EventDTO> getAllInactiveEvents() {
        if (eventRepository.findAllInactiveEvents() == null || eventRepository.findAllInactiveEvents().size() == 0) {
            throw new NotFoundException("Няма намерени събития");
        }
        return convertToDtoList(eventRepository.findAllInactiveEvents());
    }

    public List<EventDTO> getEventsByDescriptionKeyword(String keyword) {
        return convertToDtoList(eventRepository.findEventsByDescriptionSearch(keyword));
    }

    public List<EventDTO> getEventsByNameKeyword(String keyword) {
        return convertToDtoList(eventRepository.findEventsByName(keyword));
    }

    public List<EventDTO> getEventsByOneStringKeyword(String keyword) {
        return convertToDtoList(eventRepository.findEventsByOneStringKeyword(keyword));
    }

    public List<EventDTO> findEventsByOrgId(Long id) {
        return convertToDtoList(eventRepository.findEventsByOrganisationId(id));
    }


    public List<EventDTO> findEventsByAddress(String address) {
        return convertToDtoList(eventRepository.findEventsByAddress(address));
    }

    public List<EventDTO> findEventsByLocation(String city) {
        return convertToDtoList(eventRepository.findEventsByLocation(city));
    }

    public List<EventDTO> findEventsByEntranceType(EntranceType entrance) {
        return convertToDtoList(eventRepository.findEventsByEntranceType(entrance));
    }

    public List<EventDTO> findEventsByKeyWords(String keyword) {
        return convertToDtoList(eventRepository.findEventsByKeyWords(keyword));
    }

    private List<EventDTO> findEventsByStartDateAsc() {
        return convertToDtoList(eventRepository.findEventsByStartDateAsc());
    }

    private List<EventDTO> findEventsByStartDateDesc() {
        return convertToDtoList(eventRepository.findEventsByStartDateDesc());
    }

    public List<EventDTO> filterEventsByStartDate(String filter) {
        if (filter.equals("desc")) {
            return findEventsByStartDateDesc();
        } else {
            return findEventsByStartDateAsc();
        }
    }

    private List<EventDTO> findEventsByEndDateDesc() {
        return convertToDtoList(eventRepository.findEventsByEndDateDesc());
    }


    private List<EventDTO> findEventsByEndDateAsc() {
        return convertToDtoList(eventRepository.findEventsByEndDateAsc());
    }

    public List<EventDTO> filterEventsByEndDate(String filter) {
        if (filter.equals("desc")) {
            return findEventsByEndDateDesc();
        } else {
            return findEventsByEndDateAsc();
        }
    }

    private List<EventDTO> findNewestEvents() {
        return convertToDtoList(eventRepository.findNewestCreatedEvents());
    }

    private List<EventDTO> findOldestEvents() {
        return convertToDtoList(eventRepository.findOldestCreatedEvents());
    }

    public List<EventDTO> filterByCreatedDate(String filter) {
        if (filter.equals("desc")) {
            return findNewestEvents();
        } else {
            return findOldestEvents();
        }
    }

    public List<EventDTO> findEventsByCategory(String category) {
        return convertToDtoList(eventRepository.findEventsByCategories(category));
    }


    public EventDTO getEventDTOById(Long id) {
        return mapperTODto.apply(getEventById(id));
    }


    public EventDTO getEventDTOByName(String name) {
        return mapperTODto.apply(getEventByName(name));
    }

    public Event getEventByEventIdAndOrgId(Long orgId, Long eventId) {
        return eventRepository.getEventByEventIdAndOrgId(orgId, eventId).orElseThrow(() -> new NotFoundException(EVENT_NOT_FOUND_BY_ORG_ID_MESSAGE));
    }


    public void createEvent(EventDTO eventDTO, Organisation org) {
        Event event = mapperToEntity.apply(eventDTO);
        event.setOrganisation(org);
        List<EventCategory> categories = new ArrayList<>(event.getEventCategories());
        eventCategoryService.saveEventCategoriesFromList(categories);
        eventRepository.save(event);
    }

    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }

    public void deleteEventById(Long id) {
        eventRepository.deleteById(id);
    }

    public void setIsEnabledEventField(Long id, boolean status) {
        Event event = getEventById(id);
        event.setEnabled(status);
        eventRepository.save(event);
    }


    public void updateEvent(Event event, EventDTO eventDTO) {
        List<Location> locations = locationService.findLocationsByValues(eventDTO.getLocations());
        event.setName(eventDTO.getName());
        event.setEntranceType(eventDTO.getEntranceType());
        event.setDescription(eventDTO.getDescription());
        event.setEventCategories(converter.convertToEventCategories(eventDTO.getEventCategories()));
        event.setLinkToApplicationForm(eventDTO.getLinkToApplicationForm());
        event.setLocations(locations);
        event.setStartsAt(eventDTO.getStartsAt());
        event.setEndsAt(eventDTO.getEndsAt());
        event.setImage(imageConvertor.convertMultipartToByteArray(eventDTO));
        event.setImageDataUrl(imageConvertor.convertByteToString(event.getImage()));
        event.setKeyWords(eventDTO.getKeywords());
        eventRepository.save(event);
    }

    public List<EventDTO> returnSpecificFilteredEvents(String name,
                                                       String organisationName,
                                                       String address,
                                                       String location,
                                                       String entrance,
                                                       String category,
                                                       String keyword) {
        List<EventDTO> events = convertToDtoList(eventRepository.
                findAll(specificationEventFilter.filterEvents
                        (name,
                                        organisationName,
                                        address,
                                        location,
                                        entrance,
                                        category,
                                        keyword))
        );
        if (events.isEmpty() || events.size() == 0 || events == null) {
            throw new NotFoundException(EVENT_NOT_FOUND);
        }
        return events;
    }


//    private Specification<Event> withDateRange(String startDateTime, String endDateTime) {
//        return (root, query, builder) -> {
//            if (startDateTime == null || endDateTime == null) {
//                return null;
//            }
//            Expression<Date> startsAtDate = builder.function("DATE", Date.class, root.get("startsAt"));
//            Expression<Date> endsAtDate = builder.function("DATE", Date.class, root.get("endsAt"));
//            Predicate startDatePredicate = builder.greaterThanOrEqualTo(startsAtDate, Date.valueOf(startDateTime.toLocalDate()));
//            Predicate endDatePredicate = builder.lessThanOrEqualTo(endsAtDate, Date.valueOf(endDateTime.toLocalDate()));
//            return builder.and(startDatePredicate, endDatePredicate);
//        };
//    }
}
