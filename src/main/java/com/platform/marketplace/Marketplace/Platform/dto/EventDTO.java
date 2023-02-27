package com.platform.marketplace.Marketplace.Platform.dto;

import com.platform.marketplace.Marketplace.Platform.utility.consts.EntranceType;
import com.platform.marketplace.Marketplace.Platform.utility.consts.EventTypes;
import com.platform.marketplace.Marketplace.Platform.model.Location;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import static com.platform.marketplace.Marketplace.Platform.utility.consts.ConstantMessages.*;
import static com.platform.marketplace.Marketplace.Platform.utility.consts.Regex.CYRILLIC_REGEX_PATTERN;
import static com.platform.marketplace.Marketplace.Platform.utility.consts.Regex.URL_REGEX_PATTERN;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO  {
    private Long id;

    private HashSet<String> eventTypes;
    @NotBlank(message = NOT_BLANK)
    @Length(min = 3 ,message = LENGTH_TOO_SMALL)
    @Pattern(regexp = CYRILLIC_REGEX_PATTERN)
    private String name;

    private EntranceType entranceType;
    @Pattern(regexp = CYRILLIC_REGEX_PATTERN)
    private String description;
    @Pattern(regexp = URL_REGEX_PATTERN , message = INVALID_URL_MESSAGE)
    private String linkToApplicationForm;

    private List<String> locations;

    private LocalDateTime startsAt;

    private LocalDateTime endsAt;

    private Long organisationId;

    public EventDTO(HashSet<String> eventTypes, String name, EntranceType entranceType, String description, String linkToApplicationForm, List<String> locations, LocalDateTime startsAt, LocalDateTime endsAt) {
        this.eventTypes = eventTypes;
        this.name = name;
        this.entranceType = entranceType;
        this.description = description;
        this.linkToApplicationForm = linkToApplicationForm;
        this.locations = locations;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
    }
}
