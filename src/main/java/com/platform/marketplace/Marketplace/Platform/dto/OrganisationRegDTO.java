package com.platform.marketplace.Marketplace.Platform.dto;

import com.platform.marketplace.Marketplace.Platform.model.Location;
import lombok.Data;

import java.util.List;
@Data
public class OrganisationRegDTO {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;


    private List<String> locations;

}
