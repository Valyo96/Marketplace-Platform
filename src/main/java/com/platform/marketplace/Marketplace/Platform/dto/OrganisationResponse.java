package com.platform.marketplace.Marketplace.Platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationResponse {
    private Long id;
    private String organisationName;
    private List<String> locations;



    @Override
    public String toString() {
        return
                 organisationName + "\n"+ locations ;
    }
}
