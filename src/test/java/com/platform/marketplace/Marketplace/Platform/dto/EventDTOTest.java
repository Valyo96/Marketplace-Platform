package com.platform.marketplace.Marketplace.Platform.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.platform.marketplace.Marketplace.Platform.utility.consts.EntranceType;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

class EventDTOTest {

    /**
     * Method under test: {@link EventDTO#setCounter()}
     */
    @Test
    void testSetCounter2() {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setStartsAt(LocalDateTime.of(1, 1, 1, 1, 1));
        assertEquals("Събитието е започнало", eventDTO.setCounter());
    }

    /**
     * Method under test: {@link EventDTO#setCounter()}
     */
    @Test
    void testSetCounter3() throws IOException {
        DataInputStream dataInputStream = mock(DataInputStream.class);
        when(dataInputStream.transferTo((OutputStream) any())).thenReturn(1L);
        doNothing().when(dataInputStream).close();
        MockMultipartFile imagePath = new MockMultipartFile("Name", dataInputStream);

        ArrayList<String> locations = new ArrayList<>();
        LocalDateTime startsAt = LocalDateTime.of(1, 1, 1, 1, 1);
        assertEquals("Събитието е започнало",
                (new EventDTO(1L, "Organisation Name", "Event Categories", "Name", EntranceType.FREE,
                        "The characteristics of someone or something", "Link To Application Form", locations, "42 Main St",
                        startsAt, LocalDateTime.of(1, 1, 1, 1, 1), "Keywords", "Duration", imagePath,
                        "https://example.org/example", 1L, true, "3")).setCounter());
        verify(dataInputStream).transferTo((OutputStream) any());
        verify(dataInputStream).close();
    }


    /**
     * Method under test: {@link EventDTO#setDuration()}
     */
    @Test
    void testSetDuration2() {
        ArrayList<String> locations = new ArrayList<>();
        LocalDateTime startsAt = LocalDateTime.of(1, 1, 1, 1, 1);
        assertEquals("00 часа и 00 минути",
                (new EventDTO(1L, "Organisation Name", "Event Categories", "Name", EntranceType.FREE,
                        "The characteristics of someone or something", "Link To Application Form", locations, "42 Main St",
                        startsAt, LocalDateTime.of(1, 1, 1, 1, 1), "Keywords", "https://example.org/example", true, 1L))
                        .setDuration());
    }

    /**
     * Method under test: {@link EventDTO#setDuration()}
     */
    @Test
    void testSetDuration3() {
        ArrayList<String> locations = new ArrayList<>();
        LocalDateTime startsAt = LocalDateTime.of(0, 1, 1, 1, 1);
        assertEquals("366 дни",
                (new EventDTO(1L, "Organisation Name", "Event Categories", "Name", EntranceType.FREE,
                        "The characteristics of someone or something", "Link To Application Form", locations, "42 Main St",
                        startsAt, LocalDateTime.of(1, 1, 1, 1, 1), "Keywords", "https://example.org/example", true, 1L))
                        .setDuration());
    }
}

