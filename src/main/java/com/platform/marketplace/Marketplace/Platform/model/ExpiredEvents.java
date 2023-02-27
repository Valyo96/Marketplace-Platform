package com.platform.marketplace.Marketplace.Platform.model;

import com.platform.marketplace.Marketplace.Platform.utility.consts.EntranceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import static com.platform.marketplace.Marketplace.Platform.utility.consts.ConstantMessages.DATE_TIME_FORMAT;

@Entity
@Table(name = "expired_events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpiredEvents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private HashSet<String> eventTypes;

    private String name;

    @Enumerated(EnumType.STRING)
    private EntranceType entranceType;
    @Column(columnDefinition = "TEXT")
    private String description;

    private String linkToApplicationForm;
    @ManyToMany
    @JoinColumn(name = "organisations_location")
    private List<Location> locations;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    private LocalDateTime startsAt;
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    private LocalDateTime endsAt;

    private int duration;

    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

    public ExpiredEvents(Event event) {
        this.eventTypes = event.getEventTypes();
        this.name = event.getName();
        this.entranceType = event.getEntranceType();
        this.description = event.getDescription();
        this.linkToApplicationForm = event.getLinkToApplicationForm();
        this.locations = event.getLocations();
        this.createdAt = event.getCreatedAt();
        this.updatedAt = event.getUpdatedAt();
        this.startsAt = event.getStartsAt();
        this.endsAt = event.getEndsAt();
        this.organisation = event.getOrganisation();
    }
}
