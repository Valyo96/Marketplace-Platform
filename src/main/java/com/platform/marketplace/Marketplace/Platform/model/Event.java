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
import java.util.List;
import java.util.Set;

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
    private Set<EventCategory> eventCategories;
    @Column(columnDefinition = "TEXT")
    private String linkToApplicationForm;
    @ManyToMany
    @JoinColumn(name = "organisations_location")
    private List<Location> locations;

    private  String address;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @DateTimeFormat(pattern = DATE_TIME_FORMAT ,iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startsAt;
    @DateTimeFormat(pattern = DATE_TIME_FORMAT , iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endsAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT, locale = "bg_BG")
    private Duration duration;


    private String keyWords;

    @Lob
    private byte[] image;
    @Column(columnDefinition = "TEXT")
    private String imageDataUrl;
    private boolean isEnabled;
    private boolean isExpired;





    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

    public Event(Set<EventCategory> eventCategories , String name, EntranceType entranceType , String description, String linkToApplicationForm, List<Location> locations, String address, LocalDateTime startsAt, LocalDateTime endsAt, String keyWords, byte[] image,String imageDataUrl ,Organisation organisation) {
        this.eventCategories = eventCategories;
        this.name = name;
        this.entranceType = entranceType;
        this.description = description;
        this.linkToApplicationForm = linkToApplicationForm;
        this.locations = locations;
        this.address = address;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.duration = Duration.between(startsAt, endsAt);
        this.keyWords = keyWords;
        this.isEnabled = true;
        this.isExpired = false;
        this.image = image;
        this.imageDataUrl = imageDataUrl;
        this.organisation = organisation;
    }

}
