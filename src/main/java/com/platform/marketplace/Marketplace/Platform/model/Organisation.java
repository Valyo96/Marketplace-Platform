package com.platform.marketplace.Marketplace.Platform.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "organisations")
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate dateOfFoundation;
    @ManyToMany
    @JoinTable(name = "location_id")
    private List<Location> locations;

}
