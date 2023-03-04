package com.platform.marketplace.Marketplace.Platform.dto;

import com.platform.marketplace.Marketplace.Platform.utility.annotations.EmailExtended;
import com.platform.marketplace.Marketplace.Platform.utility.annotations.OrganisationName;
import com.platform.marketplace.Marketplace.Platform.utility.annotations.Password;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

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

    @Nullable
    private List<String> locations;

}
