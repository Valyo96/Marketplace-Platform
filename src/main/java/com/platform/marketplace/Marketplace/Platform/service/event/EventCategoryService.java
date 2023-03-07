package com.platform.marketplace.Marketplace.Platform.service.event;

import com.platform.marketplace.Marketplace.Platform.dto.EventCategoryDTO;
import com.platform.marketplace.Marketplace.Platform.model.EventCategory;
import com.platform.marketplace.Marketplace.Platform.repository.EventCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventCategoryService {

    private final EventCategoryRepository eventCategoryRepository;

    public HashSet<EventCategory> findEventCategoriesByValues(HashSet<EventCategoryDTO> values){
        return eventCategoryRepository.findEventCategoriesByValue(values);
    }

    public EventCategory findCategoryByValue(String value){
        return eventCategoryRepository.findCategoryByValue(value);
    }

    public void saveEventCategory(EventCategory eventCategory){
        eventCategoryRepository.save(eventCategory);
    }

    public void saveEventCategories(List<EventCategory>categories){
        eventCategoryRepository.saveAll(categories);
    }

    public void saveEventCategoriesFromList(List<EventCategory> eventCategories){
        eventCategories.forEach(category -> {
            if (findCategoryByValue(category.getType()) == null) {
                saveEventCategory(category);
            }
        });
    }
}
