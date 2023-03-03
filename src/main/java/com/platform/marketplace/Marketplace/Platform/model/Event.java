package com.platform.marketplace.Marketplace.Platform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.platform.marketplace.Marketplace.Platform.utility.consts.EntranceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import static com.platform.marketplace.Marketplace.Platform.utility.consts.ConstantMessages.DATE_TIME_FORMAT;

@Entity
@Table(name = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private EntranceType entranceType;
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany
    @JoinTable(name = "eventId_eventCategoryId")
    private HashSet<EventCategory> eventCategories;
    private String linkToApplicationForm;
    @ManyToMany
    @JoinColumn(name = "organisations_location")
    private List<Location> locations;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    private LocalDateTime startsAt;
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    private LocalDateTime endsAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT, locale = "bg_BG")
    private Duration duration;

    private String keyWords;

    private boolean isEnabled;
    private boolean isExpired;





    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

    public Event(HashSet<EventCategory> eventCategories ,String name,EntranceType entranceType ,String description, String linkToApplicationForm, List<Location> locations,LocalDateTime startsAt, LocalDateTime endsAt,String keyWords, Organisation organisation) {
        this.eventCategories = eventCategories;
        this.name = name;
        this.entranceType = entranceType;
        this.description = description;
        this.linkToApplicationForm = linkToApplicationForm;
        this.locations = locations;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.duration = Duration.between(startsAt, endsAt);
        this.keyWords = keyWords;
        this.isEnabled = true;
        this.isExpired = false;
        this.organisation = organisation;
    }
}
