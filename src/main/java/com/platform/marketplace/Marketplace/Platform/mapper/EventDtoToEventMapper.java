package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.model.EventCategory;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import com.platform.marketplace.Marketplace.Platform.service.event.EventCategoryService;
import com.platform.marketplace.Marketplace.Platform.service.location.LocationService;
import com.platform.marketplace.Marketplace.Platform.service.organisation.OrganisationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor

public class EventDtoToEventMapper implements Function<EventDTO, Event> {

    private final LocationService locationService;


    private final OrganisationService organisationService;

    private final EventCategoryService eventCategoryService;

    @Override
    public Event apply(EventDTO eventDTO) {
        List<Location> cities = locationService.findLocationByValues(eventDTO.getLocations());
        List<EventCategory> categories = eventCategoryService.findEventCategoriesByValues((List<String>) eventDTO.getEventTypes());
        return new Event(categories, eventDTO.getName(), eventDTO.getEntranceType(), eventDTO.getDescription(), eventDTO.getLinkToApplicationForm(), cities, eventDTO.getAddress(), eventDTO.getStartsAt(),
                eventDTO.getEndsAt(), organisationService.findOrganisationById(eventDTO.getOrganisationId()));
    }
}
