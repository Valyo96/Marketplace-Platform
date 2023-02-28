package com.platform.marketplace.Marketplace.Platform.dto;

import com.platform.marketplace.Marketplace.Platform.utility.annotations.EmailExtended;
import com.platform.marketplace.Marketplace.Platform.utility.annotations.OrganisationName;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationUpdateDTO {

    @OrganisationName
    @NotBlank
    private String name;
    @EmailExtended
    private String email;

    private List<String> locations;
}
