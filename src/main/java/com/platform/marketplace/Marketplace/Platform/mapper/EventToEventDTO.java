package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.model.Event;

import java.util.function.Function;

public class EventToEventDTO implements Function<Event, EventDTO> {
    @Override
    public EventDTO apply(Event event) {
        return new EventDTO(event.getId(),
                event.getEventTypes(),
                event.getName(),
                event.getEntranceType(),
                event.getDescription(),
                event.getLinkToApplicationForm(),
                event.getLocations(),
                event.getStartsAt(),
                event.getEndsAt(),
                event.getOrganisation().getId());
    }
}
