package com.platform.marketplace.Marketplace.Platform.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "organisations")
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String organisationName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate dateOfFoundation;
    @ManyToMany
    @JoinTable(name = "location")
    private List<Location> locations;

    public Organisation(String organisationName, User user, LocalDate dateOfFoundation, List<Location> locations) {
        this.organisationName = organisationName;
        this.user = user;
        this.dateOfFoundation = dateOfFoundation;
        this.locations = locations;
    }
}
