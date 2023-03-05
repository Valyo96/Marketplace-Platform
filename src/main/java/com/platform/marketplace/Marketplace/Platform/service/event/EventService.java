package com.platform.marketplace.Marketplace.Platform.service.event;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.mapper.*;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import com.platform.marketplace.Marketplace.Platform.service.location.LocationService;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException;
import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.platform.marketplace.Marketplace.Platform.utility.consts.ConstantMessages.*;

@Service
@RequiredArgsConstructor

public class EventService {

    private final EventRepository eventRepository;


    private final EventToEventDtoMapper mapperTODto ;

    private final EventDtoToEventMapper mapperToEntity;

    private final LocationService locationService;

    private final EventCategoryConverter converter;


    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public List<EventDTO>showAllEvents(){
        return getAllEvents().stream().map(mapperTODto).collect(Collectors.toList());
    }

    public Event getEventById(Long id){
        return eventRepository.findById(id).orElseThrow(()-> new  NotFoundException(EVENT_BY_ID_NOT_FOUND));
    }

    public EventDTO getEventByName(String name){
        Event event = eventRepository.findEventByName(name).orElseThrow(() -> new NotFoundException(EVENT_BY_NAME_NOT_FOUND));
        return mapperTODto.apply(event);
    }

    public EventDTO getEventDTOById(Long id){
        Event event = getEventById(id);
        return mapperTODto.apply(event);

    }

    public List<EventDTO> getEventsByDescriptionKeyword(String keyword){
        List<Event> events = eventRepository.findEventsByDescriptionSearch(keyword);
        return events.stream()
                .map(mapperTODto)
                .toList();

    }

    public List<EventDTO> getEventsByOneStringKeyword(String keyword){
        return eventRepository.findEventsByOneStringKeyword(keyword).stream().map(mapperTODto).toList();
    }

    public Event getEventByEventIdAndOrgId(Long orgId, Long eventId){
        return eventRepository.getEventByEventIdAndOrgId(orgId , eventId).orElseThrow(()-> new NotFoundException(EVENT_NOT_FOUND_BY_ORG_ID_MESSAGE));
    }


   public void createEvent(EventDTO eventDTO, Organisation org){
        Event event = mapperToEntity.apply(eventDTO);
        event.setOrganisation(org);
        eventRepository.save(event);
   }

   public void deleteEvent(Event event){
        eventRepository.delete(event);
   }

   public void setIsEnabledEventField(Event event,boolean status){
        event.setEnabled(status);
        eventRepository.save(event);
   }

   public List<EventDTO> findEventsByOrgId(Long id){
        return eventRepository.findEventsByOrganisationId(id).stream().map(mapperTODto).collect(Collectors.toList());
   }

   public void updateEvent(Event event , EventDTO eventDTO){
        List<Location> locations = locationService.findLocationsByValues(eventDTO.getLocations());
        event.setName(eventDTO.getName());
        event.setEntranceType(eventDTO.getEntranceType());
        event.setDescription(eventDTO.getDescription());
        event.setEventCategories(converter.convertToEventCategories(eventDTO.getEventCategories()));
        event.setLinkToApplicationForm(eventDTO.getLinkToApplicationForm());
        event.setLocations(locations);
        event.setStartsAt(eventDTO.getStartsAt());
        event.setEndsAt(eventDTO.getEndsAt());
        event.setKeyWords(eventDTO.getKeywords());
        eventRepository.save(event);
   }


}
