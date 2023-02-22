package com.platform.marketplace.Marketplace.Platform.dto;

import com.platform.marketplace.Marketplace.Platform.annotations.EmailExtended;
import com.platform.marketplace.Marketplace.Platform.annotations.OrganisationName;
import com.platform.marketplace.Marketplace.Platform.annotations.Password;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationRegDTO {
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
