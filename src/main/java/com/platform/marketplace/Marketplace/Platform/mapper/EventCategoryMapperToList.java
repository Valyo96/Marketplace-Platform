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
public class EventCategoryMapperToList implements Function<HashSet<EventCategory>,HashSet<EventCategoryDTO>> {

    private final EventCategoryMapper eventCategoryMapper;


    @Override
    public HashSet<EventCategoryDTO> apply(HashSet<EventCategory> eventCategories) {
        return (HashSet<EventCategoryDTO>) eventCategories.stream().map(eventCategoryMapper).collect(Collectors.toList());
    }
}
