package com.platform.marketplace.Marketplace.Platform.model;

import com.platform.marketplace.Marketplace.Platform.consts.Cities;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}
