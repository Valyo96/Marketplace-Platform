package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.dto.EventCategoryDTO;
import com.platform.marketplace.Marketplace.Platform.model.EventCategory;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class EventCategoryMapper implements Function<EventCategory , EventCategoryDTO> {
    @Override
    public EventCategoryDTO apply(EventCategory eventCategory) {
        return new EventCategoryDTO(eventCategory.getType());
    }
}
