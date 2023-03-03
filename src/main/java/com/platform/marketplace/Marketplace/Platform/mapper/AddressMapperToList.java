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
public class AddressMapperToList implements Function<List<Address>, List<AddressDTO>> {

    private final AddressMapper addressMapper;
    @Override
    public List<AddressDTO> apply(List<Address> address) {
        return address.stream().map(addressMapper).collect(Collectors.toList());
    }

}
