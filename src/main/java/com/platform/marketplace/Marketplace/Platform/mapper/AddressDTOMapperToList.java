package com.platform.marketplace.Marketplace.Platform.mapper;

import com.platform.marketplace.Marketplace.Platform.dto.AddressDTO;
import com.platform.marketplace.Marketplace.Platform.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AddressDTOMapperToList implements Function<List<AddressDTO>,List<Address>> {

    private final AddressDTOMapper addressDTOMapper;
    @Override
    public List<Address> apply(List<AddressDTO> addressDTOS) {
        return addressDTOS.stream().map(addressDTOMapper).collect(Collectors.toList());
    }
}
