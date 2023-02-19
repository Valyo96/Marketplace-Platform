package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.consts.EventTypes;
import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Function;

public class EventDTOToEvent implements Function<EventDTO, Event> {
    @Autowired
    OrganisationRepository organisationRepository;

    @Override
    public Event apply(EventDTO eventDTO) {
        Organisation org = organisationRepository.findById(eventDTO.getOrganisationId()).orElseThrow(() -> new IllegalArgumentException(""));
        return new Event(eventDTO.getEventTypes() ,eventDTO.getName(),eventDTO.getEntranceType() ,eventDTO.getDescription(), eventDTO.getLinkToApplicationForm(), eventDTO.getLocations(), eventDTO.getStartsAt(),
                eventDTO.getEndsAt(), org);
    }
}
