package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.dto.AddressDTO;
import com.platform.marketplace.Marketplace.Platform.model.Address;
import org.springframework.stereotype.Component;

import java.util.function.Function;


import java.util.function.Function;
@Component

public class AddressDTOMapper implements Function<AddressDTO , Address> {
    @Override
    public Address apply(AddressDTO addressDTO) {
        return new Address(
                addressDTO.getAddress());
    }
}
