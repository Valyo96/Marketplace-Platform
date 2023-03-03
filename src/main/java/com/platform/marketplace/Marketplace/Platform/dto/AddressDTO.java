package com.platform.marketplace.Marketplace.Platform.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private Long id;
    private String address;

    public AddressDTO(Long id, String address) {
        this.id = id;
        this.address = address;
    }
}
