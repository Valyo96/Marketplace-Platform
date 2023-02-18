package com.platform.marketplace.Marketplace.Platform.model;

import com.platform.marketplace.Marketplace.Platform.consts.EventTypes;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private List<EventTypes> eventTypes;

    private String name;

    private String description;

    private String linkToApplicationForm;
    @ManyToMany
    @JoinColumn(name = "organisations_location")
    private List<Location> locations;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime startsAt;

    private LocalDateTime endsAt;

    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

    public Event(Long id,List<EventTypes> eventTypes ,String name, String description, String linkToApplicationForm, List<Location> locations, LocalDateTime startsAt, LocalDateTime endsAt, Organisation organisation) {
        this.id = id;
        this.eventTypes = eventTypes;
        this.name = name;
        this.description = description;
        this.linkToApplicationForm = linkToApplicationForm;
        this.locations = locations;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.organisation = organisation;
    }
}
