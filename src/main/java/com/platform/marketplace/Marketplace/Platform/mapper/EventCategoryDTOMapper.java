package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.dto.EventCategoryDTO;
import com.platform.marketplace.Marketplace.Platform.model.EventCategory;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class EventCategoryDTOMapper implements Function<EventCategoryDTO , EventCategory> {
    @Override
    public EventCategory apply(EventCategoryDTO eventCategoryDTO) {
        return new EventCategory(eventCategoryDTO.getCategory());
    }
}
