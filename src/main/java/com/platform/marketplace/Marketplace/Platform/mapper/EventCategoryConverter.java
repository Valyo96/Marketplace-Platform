package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.model.EventCategory;
import com.platform.marketplace.Marketplace.Platform.repository.EventCategoryRepository;
import com.platform.marketplace.Marketplace.Platform.service.event.EventCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventCategoryConverter {
    @Autowired
    private EventCategoryService eventCategoryService;
    public  Set<EventCategory> convertToEventCategories(String eventCategoriesString) {
        Set<String> categoryNames = new HashSet<>(Arrays.asList(eventCategoriesString.split(",\\s*")));
        Set<EventCategory> categories = new HashSet<>();
        for (String categoryName : categoryNames) {
            if (!categoryName.isEmpty()) {
                EventCategory eventCategory = new EventCategory(categoryName.trim().toLowerCase());
                categories.add(eventCategory);
                if(eventCategoryService.findCategoryByValue(eventCategory.getType())!=null){
                    eventCategoryService.saveEventCategory(eventCategory);
                }
            }
        }
        return categories;
    }

    public String convertToString(Set<EventCategory> eventCategories){
        return eventCategories.stream().map(EventCategory :: getType).collect(Collectors.joining(", "));
    }
}
