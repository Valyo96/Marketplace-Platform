package com.platform.marketplace.Marketplace.Platform.utility.scheduler;

import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.repository.EventRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EventScheduler {
    private final EventRepository eventRepository;




    @Scheduled(fixedRate = 60000) // Run every minute
    public void setExpiredEventsToExpired() {
        LocalDateTime now = LocalDateTime.now();
        List<Event> expiredEvents = eventRepository.findByEndsAtBefore(now);
        for(Event event : expiredEvents){
            event.setExpired(true);
        }
        eventRepository.saveAll(expiredEvents);
    }
//    @Scheduled(fixedRate = 1000)
//
//    public static String setCounterScheduler(LocalDateTime date) {
//        LocalDateTime now = LocalDateTime.now();
//
//        if (now.isBefore(date)) {
//            Duration duration = Duration.between(now, date);
//           return String.format("%02d:%02d:%02d:%02d",
//                    duration.toDaysPart(),
//                    duration.toHoursPart(),
//                    duration.toMinutesPart(),
//                    duration.toSecondsPart());
//        } else {
//            return "Събитието е започнало";
//        }
//    }

}
