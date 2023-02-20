package com.platform.marketplace.Marketplace.Platform.model;

import com.platform.marketplace.Marketplace.Platform.consts.Cities;
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

    @Enumerated(EnumType.STRING)
    private Cities city;

    public Location(Cities city) {
        this.city = city;
    }
}
