package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.model.EventCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class EventToEventDtoMapper implements Function<Event, EventDTO> {
    private final LocationToStringMapper locationToStringMapper;


    @Override
    public EventDTO apply(Event event) {

        return new EventDTO(event.getId(),
                event.getEventCategories().stream()
                        .map(EventCategory::getType)
                        .collect(Collectors.toCollection(HashSet::new)),
                event.getName(),
                event.getEntranceType(),
                event.getDescription(),
                event.getLinkToApplicationForm(),
                locationToStringMapper.apply(event.getLocations()),
                event.getAddress(),
                event.getStartsAt(),
                event.getEndsAt(),
                event.getDuration(),
                event.getOrganisation().getId());
    }
}
