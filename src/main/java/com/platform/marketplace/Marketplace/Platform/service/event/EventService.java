package com.platform.marketplace.Marketplace.Platform.service.event;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException;
import com.platform.marketplace.Marketplace.Platform.mapper.EventToEventDtoMapper;
import com.platform.marketplace.Marketplace.Platform.mapper.EventDtoToEventMapper;
import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.repository.EventRepository;
import com.platform.marketplace.Marketplace.Platform.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.platform.marketplace.Marketplace.Platform.utility.consts.ConstantMessages.EVENT_BY_NAME_NOT_FOUND;

@Service
@RequiredArgsConstructor

public class EventService {

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    private final EventToEventDtoMapper mapperTODto ;

    private final EventDtoToEventMapper mapperToEntity;

    private Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public List<EventDTO>showAllEvents(){
        return getAllEvents().stream().map(mapperTODto).collect(Collectors.toList());
    }

    public EventDTO getEventByName(String name){
        Event event = eventRepository.findEventByName(name).orElseThrow(() -> new NotFoundException(EVENT_BY_NAME_NOT_FOUND));
        EventDTO eventDTO = mapperTODto.apply(event);
        return eventDTO;
    }

    public List<EventDTO> getEventsByDescriptionKeyword(String keyword){
        List<Event> events = eventRepository.findEventsByDescriptionKeyword(keyword);
        return events.stream()
                .map(mapperTODto)
                .toList();

    }



   public void createEvent(EventDTO eventDTO, Organisation org){
        Event event = mapperToEntity.apply(eventDTO);
        event.setOrganisation(org);
        eventRepository.save(event);
   }

   public void deleteEventById(Long id , boolean confirmation){
        if(confirmation){
            eventRepository.deleteById(id);
        }
        //TODO : само логната дадена организация да може да трие собствените си събития.
   }

   public List<EventDTO> findEventsByOrgId(Long id){
        return eventRepository.findEventsByOrganisationId(id).stream().map(mapperTODto).collect(Collectors.toList());
   }


}
