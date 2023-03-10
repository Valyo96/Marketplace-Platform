package com.platform.marketplace.Marketplace.Platform.dto;

import com.platform.marketplace.Marketplace.Platform.model.Location;
import com.platform.marketplace.Marketplace.Platform.utility.consts.EntranceType;
import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.platform.marketplace.Marketplace.Platform.utility.consts.ConstantMessages.*;
import static com.platform.marketplace.Marketplace.Platform.utility.consts.Regex.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvancedSearchDTO {
    @Pattern(regexp = CYRILLIC_EVENT_NAME_PATTERN , message = ONLY_CYRILLIC_ALLOWED)
    @Nullable
    private String eventName;
    @Nullable
    @Pattern(regexp = ORGANISATION_NAME_REGEX_PATTERN , message = ADVANCED_SEARCH_NAME_INPUT_ERROR_MESSAGE)
    private String organisationName;
    @Nullable
    @Pattern(regexp = CYRILLIC_AND_SYMBOLS_ADDRESS_DESCRIPTION_PATTERN, message =ONLY_CYRILLIC_ALLOWED)
    private String address;
    @Nullable
    private List<Location> locations;
    @Nullable
    @Pattern(regexp = CYRILLIC_AND_COMA_PATTERN, message = CYRILLIC_AND_COMA_ALLOWED_MESSAGE)
    private String eventCategory;
    @Nullable
    @Pattern(regexp = CYRILLIC_AND_COMA_PATTERN, message = CYRILLIC_AND_COMA_ALLOWED_MESSAGE)
    private String keywords;
    @Nullable
    private EntranceType entranceType;
}
