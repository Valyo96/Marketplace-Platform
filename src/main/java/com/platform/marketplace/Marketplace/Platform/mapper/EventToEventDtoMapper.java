package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@RequiredArgsConstructor
@Component
public class EventToEventDtoMapper implements Function<Event, EventDTO> {
    private final EventCategoryMapperToList eventCategoryMapperToList;
    private final LocationToStringMapper locationToStringMapper;



    @Override
    public EventDTO apply(Event event) {


        return new EventDTO(event.getId(),
              eventCategoryMapperToList.apply(event.getEventCategories()),
                event.getName(),
                event.getEntranceType(),
                event.getDescription(),
                event.getLinkToApplicationForm(),
               locationToStringMapper.apply(event.getLocations()),
                event.getAddress(),
                event.getStartsAt(),
                event.getEndsAt(),
                event.getKeyWords(),
                event.getDuration(),
                event.getOrganisation().getId());
    }
}
