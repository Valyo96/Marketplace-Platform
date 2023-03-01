package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import com.platform.marketplace.Marketplace.Platform.service.LocationService;
import com.platform.marketplace.Marketplace.Platform.service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class EventDTOToEvent implements Function<EventDTO, Event> {
    @Autowired
    private LocationService locationService;

    @Autowired
    private OrganisationService organisationService;

    @Override
    public Event apply(EventDTO eventDTO) {
        List<Location> cities = locationService.findLocationByValues(eventDTO.getLocations());
        return new Event(eventDTO.getEventTypes(), eventDTO.getName(), eventDTO.getEntranceType(), eventDTO.getDescription(), eventDTO.getLinkToApplicationForm(), cities, eventDTO.getAddress(), eventDTO.getStartsAt(),
                eventDTO.getEndsAt(), organisationService.findOrganisationById(eventDTO.getOrganisationId()));
    }
}
