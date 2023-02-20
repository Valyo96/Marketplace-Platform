package com.platform.marketplace.Marketplace.Platform.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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

    @ManyToMany
    @JoinTable(name = "organisation_locations")
    private List<Location> locations;

    public Organisation(String organisationName, User user,  List<Location> locations) {
        this.organisationName = organisationName;
        this.user = user;
        this.locations = locations;
    }
}
