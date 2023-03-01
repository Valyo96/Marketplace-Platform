package com.platform.marketplace.Marketplace.Platform.utility.seeder;

import com.platform.marketplace.Marketplace.Platform.model.EventCategory;
import com.platform.marketplace.Marketplace.Platform.repository.EventCategoryRepository;
import com.platform.marketplace.Marketplace.Platform.utility.consts.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventCategorySeeder implements CommandLineRunner {

    private final EventCategoryRepository eventCategoryRepository;

    private final EventType eventType;

    @Override
    public void run(String... args) throws Exception {
        if(eventCategoryRepository.findAll().isEmpty()){
            for(String type : eventType.getEventTypes()){
                EventCategory eventCategory = new EventCategory();
                eventCategory.setType(type);
                eventCategoryRepository.save(eventCategory);
            }
        }
    }
}
