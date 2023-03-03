package com.platform.marketplace.Marketplace.Platform.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    @OneToMany
    @JoinColumn(name = "location_id")
    private List<Address> addresses;

    public Location(String city, List<Address> addresses) {
        this.city = city;
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return city;

    }
}
