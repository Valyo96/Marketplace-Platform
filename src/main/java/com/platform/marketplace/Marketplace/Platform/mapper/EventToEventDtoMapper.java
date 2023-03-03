package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.model.Address;
import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.model.EventCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class EventToEventDtoMapper implements Function<Event, EventDTO> {
    private final LocationMapperToList locationMapperToList;

    private final EventCategoryMapperToList eventCategoryMapperToList;



    @Override
    public EventDTO apply(Event event) {

        return new EventDTO(event.getId(),
              eventCategoryMapperToList.apply(event.getEventCategories()),
                event.getName(),
                event.getEntranceType(),
                event.getDescription(),
                event.getLinkToApplicationForm(),
                locationMapperToList.apply(event.getLocations()),
                event.getStartsAt(),
                event.getEndsAt(),
                event.getKeyWords(),
                event.getDuration(),
                event.getOrganisation().getId());
    }
}
