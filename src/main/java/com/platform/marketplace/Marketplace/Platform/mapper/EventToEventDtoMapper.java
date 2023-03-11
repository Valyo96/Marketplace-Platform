package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.service.image.ImageConvertor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@RequiredArgsConstructor
@Component
public class EventToEventDtoMapper implements Function<Event, EventDTO> {

    private final LocationToStringMapper locationToStringMapper;

    private final EventCategoryConverter categoryConverter;





    @Override
    public EventDTO apply(Event event) {
        return new EventDTO(event.getId(),
                event.getOrganisation().getOrganisationName(),
                categoryConverter.convertToString(event.getEventCategories()),
                event.getName(),
                event.getEntranceType(),
                event.getDescription(),
                event.getLinkToApplicationForm(),
               locationToStringMapper.apply(event.getLocations()),
                event.getAddress(),
                event.getStartsAt(),
                event.getEndsAt(),
                event.getKeyWords(),
                event.getImageDataUrl(),
                event.isEnabled(),
                event.getOrganisation().getId());
    }
}
