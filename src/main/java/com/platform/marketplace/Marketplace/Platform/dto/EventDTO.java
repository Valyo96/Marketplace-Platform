package com.platform.marketplace.Marketplace.Platform.dto;

import com.platform.marketplace.Marketplace.Platform.consts.EntranceType;
import com.platform.marketplace.Marketplace.Platform.consts.EventTypes;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO  {
    private Long id;

    private List<EventTypes> eventTypes;

    private String name;

    private EntranceType entranceType;

    private String description;

    private String linkToApplicationForm;

    private List<Location> locations;

    private LocalDateTime startsAt;

    private LocalDateTime endsAt;

    private Long organisationId;
}
