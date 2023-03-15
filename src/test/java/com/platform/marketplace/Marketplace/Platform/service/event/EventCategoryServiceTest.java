package com.platform.marketplace.Marketplace.Platform.service.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.platform.marketplace.Marketplace.Platform.dto.EventCategoryDTO;
import com.platform.marketplace.Marketplace.Platform.model.EventCategory;
import com.platform.marketplace.Marketplace.Platform.repository.EventCategoryRepository;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EventCategoryService.class})
@ExtendWith(SpringExtension.class)
class EventCategoryServiceTest {
    @MockBean
    private EventCategoryRepository eventCategoryRepository;

    @Autowired
    private EventCategoryService eventCategoryService;

    /**
     * Method under test: {@link EventCategoryService#findEventCategoriesByValues(HashSet)}
     */
    @Test
    void testFindEventCategoriesByValues() {
        HashSet<EventCategory> eventCategorySet = new HashSet<>();
        when(eventCategoryRepository.findEventCategoriesByValue((HashSet<EventCategoryDTO>) any()))
                .thenReturn(eventCategorySet);
        HashSet<EventCategory> actualFindEventCategoriesByValuesResult = eventCategoryService
                .findEventCategoriesByValues(new HashSet<>());
        assertSame(eventCategorySet, actualFindEventCategoriesByValuesResult);
        assertTrue(actualFindEventCategoriesByValuesResult.isEmpty());
        verify(eventCategoryRepository).findEventCategoriesByValue((HashSet<EventCategoryDTO>) any());
    }

    /**
     * Method under test: {@link EventCategoryService#findCategoryByValue(String)}
     */
    @Test
    void testFindCategoryByValue() {
        EventCategory eventCategory = new EventCategory("Type");
        when(eventCategoryRepository.findCategoryByValue((String) any())).thenReturn(eventCategory);
        assertSame(eventCategory, eventCategoryService.findCategoryByValue("42"));
        verify(eventCategoryRepository).findCategoryByValue((String) any());
    }

    /**
     * Method under test: {@link EventCategoryService#saveEventCategory(EventCategory)}
     */
    @Test
    void testSaveEventCategory() {
        when(eventCategoryRepository.save((EventCategory) any())).thenReturn(new EventCategory("Type"));
        EventCategory eventCategory = new EventCategory("Type");
        eventCategoryService.saveEventCategory(eventCategory);
        verify(eventCategoryRepository).save((EventCategory) any());
        assertEquals("Type", eventCategory.getType());
    }

    /**
     * Method under test: {@link EventCategoryService#saveEventCategories(List)}
     */
    @Test
    void testSaveEventCategories() {
        when(eventCategoryRepository.saveAll((Iterable<EventCategory>) any())).thenReturn(new ArrayList<>());
        eventCategoryService.saveEventCategories(new ArrayList<>());
        verify(eventCategoryRepository).saveAll((Iterable<EventCategory>) any());
    }

    /**
     * Method under test: {@link EventCategoryService#saveEventCategoriesFromList(List)}
     */
    @Test
    void testSaveEventCategoriesFromList() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     EventCategoryService.eventCategoryRepository

        eventCategoryService.saveEventCategoriesFromList(new ArrayList<>());
    }

    /**
     * Method under test: {@link EventCategoryService#saveEventCategoriesFromList(List)}
     */
    @Test
    void testSaveEventCategoriesFromList2() {
        when(eventCategoryRepository.findCategoryByValue((String) any())).thenReturn(new EventCategory("Type"));
        when(eventCategoryRepository.save((EventCategory) any())).thenReturn(new EventCategory("Type"));

        ArrayList<EventCategory> eventCategoryList = new ArrayList<>();
        eventCategoryList.add(new EventCategory("Type"));
        eventCategoryService.saveEventCategoriesFromList(eventCategoryList);
        verify(eventCategoryRepository).findCategoryByValue((String) any());
    }

    /**
     * Method under test: {@link EventCategoryService#saveEventCategoriesFromList(List)}
     */
    @Test
    void testSaveEventCategoriesFromList3() {
        when(eventCategoryRepository.findCategoryByValue((String) any())).thenReturn(null);
        when(eventCategoryRepository.save((EventCategory) any())).thenReturn(new EventCategory("Type"));

        ArrayList<EventCategory> eventCategoryList = new ArrayList<>();
        eventCategoryList.add(new EventCategory("Type"));
        eventCategoryService.saveEventCategoriesFromList(eventCategoryList);
        verify(eventCategoryRepository).findCategoryByValue((String) any());
        verify(eventCategoryRepository).save((EventCategory) any());
    }

    /**
     * Method under test: {@link EventCategoryService#saveEventCategoriesFromList(List)}
     */
    @Test
    void testSaveEventCategoriesFromList4() {
        when(eventCategoryRepository.findCategoryByValue((String) any())).thenReturn(new EventCategory("Type"));
        when(eventCategoryRepository.save((EventCategory) any())).thenReturn(new EventCategory("Type"));

        ArrayList<EventCategory> eventCategoryList = new ArrayList<>();
        eventCategoryList.add(new EventCategory("Type"));
        eventCategoryList.add(new EventCategory("Type"));
        eventCategoryService.saveEventCategoriesFromList(eventCategoryList);
        verify(eventCategoryRepository, atLeast(1)).findCategoryByValue((String) any());
    }

}

