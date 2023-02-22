package com.platform.marketplace.Marketplace.Platform.dto;

import com.platform.marketplace.Marketplace.Platform.annotations.EmailExtended;
import com.platform.marketplace.Marketplace.Platform.annotations.OrganisationName;
import com.platform.marketplace.Marketplace.Platform.annotations.Password;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationDTO {
    @OrganisationName
    @NotBlank
    private String name;
    @EmailExtended
    private String email;
    @Password
    private String password;
    private String confirmPassword;


    private List<String> locations;

}
