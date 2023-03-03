package com.platform.marketplace.Marketplace.Platform.dto;

import lombok.Data;

import java.util.List;
@Data
public class LocationDTO {
    private Long id;
    private String city;
    private List<AddressDTO> addresses;

    public LocationDTO(Long id, String city, List<AddressDTO> addresses) {
        this.id = id;
        this.city = city;
        this.addresses = addresses;
    }
}
