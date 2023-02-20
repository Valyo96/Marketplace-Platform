package com.platform.marketplace.Marketplace.Platform.model;

import com.platform.marketplace.Marketplace.Platform.consts.EntranceType;
import com.platform.marketplace.Marketplace.Platform.consts.EventTypes;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

import static com.platform.marketplace.Marketplace.Platform.consts.ConstantMessages.dateTimeFormat;

@Entity
@Table(name = "events")
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private List<EventTypes> eventTypes;

    private String name;

    @Enumerated(EnumType.STRING)
    private EntranceType entranceType;
    @Column(columnDefinition = "TEXT")
    private String description;

    private String linkToApplicationForm;
    @ManyToMany
    @JoinColumn(name = "organisations_location")
    private List<Location> locations;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @DateTimeFormat(pattern = dateTimeFormat)
    private LocalDateTime startsAt;
    @DateTimeFormat(pattern = dateTimeFormat)
    private LocalDateTime endsAt;

    private int duration;

    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

    public Event(List<EventTypes> eventTypes ,String name,EntranceType entranceType ,String description, String linkToApplicationForm, List<Location> locations, LocalDateTime startsAt, LocalDateTime endsAt, Organisation organisation) {
        this.eventTypes = eventTypes;
        this.name = name;
        this.entranceType = entranceType;
        this.description = description;
        this.linkToApplicationForm = linkToApplicationForm;
        this.locations = locations;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.organisation = organisation;
    }
}
