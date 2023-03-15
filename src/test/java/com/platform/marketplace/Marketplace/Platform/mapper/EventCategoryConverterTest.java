package com.platform.marketplace.Marketplace.Platform.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.platform.marketplace.Marketplace.Platform.model.EventCategory;
import com.platform.marketplace.Marketplace.Platform.service.event.EventCategoryService;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EventCategoryConverter.class})
@ExtendWith(SpringExtension.class)
class EventCategoryConverterTest {
    @Autowired
    private EventCategoryConverter eventCategoryConverter;

    @MockBean
    private EventCategoryService eventCategoryService;

    /**
     * Method under test: {@link EventCategoryConverter#convertToEventCategories(String)}
     */
    @Test
    void testConvertToEventCategories() {
        when(eventCategoryService.findCategoryByValue((String) any())).thenReturn(new EventCategory("Type"));
        assertEquals(1, eventCategoryConverter.convertToEventCategories("Event Categories String").size());
        verify(eventCategoryService, atLeast(1)).findCategoryByValue((String) any());
    }

    /**
     * Method under test: {@link EventCategoryConverter#convertToEventCategories(String)}
     */
    @Test
    void testConvertToEventCategories2() {
        when(eventCategoryService.findCategoryByValue((String) any())).thenReturn(null);
        assertEquals(3, eventCategoryConverter.convertToEventCategories("Event Categories String").size());
        verify(eventCategoryService, atLeast(1)).findCategoryByValue((String) any());
    }


    /**
     * Method under test: {@link EventCategoryConverter#convertToEventCategories(String)}
     */
    @Test
    void testConvertToEventCategories4() {
        when(eventCategoryService.findCategoryByValue((String) any())).thenReturn(new EventCategory("Type"));
        assertEquals(1, eventCategoryConverter.convertToEventCategories("").size());
        verify(eventCategoryService).findCategoryByValue((String) any());
    }

    /**
     * Method under test: {@link EventCategoryConverter#convertToString(Set)}
     */
    @Test
    void testConvertToString() {
        assertEquals("", eventCategoryConverter.convertToString(new HashSet<>()));
    }

    /**
     * Method under test: {@link EventCategoryConverter#convertToString(Set)}
     */
    @Test
    void testConvertToString3() {
        EventCategory eventCategory = mock(EventCategory.class);
        when(eventCategory.getType()).thenReturn("Type");

        HashSet<EventCategory> eventCategorySet = new HashSet<>();
        eventCategorySet.add(eventCategory);
        assertEquals("Type", eventCategoryConverter.convertToString(eventCategorySet));
        verify(eventCategory).getType();
    }
}

