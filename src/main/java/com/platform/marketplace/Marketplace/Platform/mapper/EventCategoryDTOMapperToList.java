package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.dto.EventCategoryDTO;
import com.platform.marketplace.Marketplace.Platform.model.EventCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EventCategoryDTOMapperToList implements Function<HashSet<EventCategoryDTO> , HashSet<EventCategory>> {

    private final EventCategoryDTOMapper eventCategoryDTOMapper;



    @Override
    public HashSet<EventCategory> apply(HashSet<EventCategoryDTO> eventCategoryDTOS) {
        return (HashSet<EventCategory>) eventCategoryDTOS.stream().map(eventCategoryDTOMapper).toList();
    }
}
