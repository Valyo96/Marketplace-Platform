package com.platform.marketplace.Marketplace.Platform.utility.seeder;

import com.platform.marketplace.Marketplace.Platform.model.EventCategory;
import com.platform.marketplace.Marketplace.Platform.repository.EventCategoryRepository;
import com.platform.marketplace.Marketplace.Platform.utility.consts.ListOfEventCategories;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class EventCategorySeeder implements CommandLineRunner {

    private final EventCategoryRepository eventCategoryRepository;

    private final ListOfEventCategories listOfEventCategories;

    @Override
    public void run(String... args) throws Exception {
        if(eventCategoryRepository.findAll().isEmpty()){
            for(String type : listOfEventCategories.getEventTypes()){
                EventCategory eventCategory = new EventCategory();
                eventCategory.setType(type);
                eventCategoryRepository.save(eventCategory);
            }
        }
    }
}
