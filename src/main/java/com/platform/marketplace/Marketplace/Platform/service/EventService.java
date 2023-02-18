package com.platform.marketplace.Marketplace.Platform.service;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.dto.EventToEventDTO;
import com.platform.marketplace.Marketplace.Platform.mapper.EventDTOToEvent;
import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.repository.EventRepository;
import com.platform.marketplace.Marketplace.Platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.platform.marketplace.Marketplace.Platform.consts.ExceptionMessages.eventByNameNotFound;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    private final EventToEventDTO mapperTODto = new EventToEventDTO();

    private final EventDTOToEvent mapperToEntity = new EventDTOToEvent();

    private Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public EventDTO getEventByName(String name){
        Event event = eventRepository.findEventByName(name).orElseThrow(() -> new IllegalArgumentException(eventByNameNotFound));
        EventDTO eventDTO = mapperTODto.apply(event);
        return eventDTO;
    }

    public List<EventDTO> getEventsByDescriptionKeyword(String keyword){
        List<Event> events = eventRepository.findEventsByDescriptionKeyword(keyword);
        return events.stream()
                .map(mapperTODto)
                .collect(Collectors.toList());

    }

   public void createEvent(EventDTO eventDTO){
        Event event = mapperToEntity.apply(eventDTO);
        eventRepository.save(event);
   }

   public void deleteEventById(Long id , boolean confirmation){
        if(confirmation){
            eventRepository.deleteById(id);
        }
        //TODO : само логната дадена организация да може да трие собствените си събития.
   }


}
