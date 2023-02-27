package com.platform.marketplace.Marketplace.Platform.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
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
    @CreationTimestamp
    private LocalDateTime registeredAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(name = "organisation_locations")
    private List<Location> locations;

    public Organisation(String organisationName, User user,  List<Location> locations) {
        this.organisationName = organisationName;
        this.user = user;
        this.locations = locations;
    }

    public Organisation(String organisationName, List<Location> locations) {
        this.organisationName = organisationName;
        this.locations = locations;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Id=").append(id);
        sb.append("Име на организация - ").append(organisationName).append('\'');
        sb.append("регистрирана на ").append(registeredAt);
        sb.append("обновена на ").append(updatedAt);
        return sb.toString();
    }
}
