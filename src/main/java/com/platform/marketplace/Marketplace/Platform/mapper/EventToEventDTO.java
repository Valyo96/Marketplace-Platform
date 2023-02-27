package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@RequiredArgsConstructor
@Component
public class EventToEventDTO implements Function<Event, EventDTO> {
    private final LocationMapperToString locationMapperToString;


    @Override
    public EventDTO apply(Event event) {

        return new EventDTO(event.getId(),
                event.getEventTypes(),
                event.getName(),
                event.getEntranceType(),
                event.getDescription(),
                event.getLinkToApplicationForm(),
                locationMapperToString.apply(event.getLocations()),
                event.getStartsAt(),
                event.getEndsAt(),
                event.getOrganisation().getId());
    }
}
