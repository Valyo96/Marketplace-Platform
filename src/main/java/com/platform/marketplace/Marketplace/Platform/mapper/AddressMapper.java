package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.dto.AddressDTO;
import com.platform.marketplace.Marketplace.Platform.model.Address;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class AddressMapper implements Function<Address , AddressDTO> {
    @Override
    public AddressDTO apply(Address address) {
        return new AddressDTO(address.getId(),
                address.getAddress());
    }
}
