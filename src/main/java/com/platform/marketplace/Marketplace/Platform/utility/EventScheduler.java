package com.platform.marketplace.Marketplace.Platform.utility;

import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.model.ExpiredEvents;
import com.platform.marketplace.Marketplace.Platform.repository.EventRepository;
import com.platform.marketplace.Marketplace.Platform.repository.ExpiredEventsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EventScheduler {
    private final EventRepository eventRepository;

    private final ExpiredEventsRepository expiredEventsRepository;



    @Scheduled(fixedRate = 60000) // Run every minute
    public void deleteExpiredEvents() {
        LocalDateTime now = LocalDateTime.now();
        List<Event> expiredEvents = eventRepository.findByEndsAtBefore(now);
        expiredEvents.stream().map(ExpiredEvents::new).forEach(expiredEventsRepository::save);
        eventRepository.deleteAll(expiredEvents);
    }


}
