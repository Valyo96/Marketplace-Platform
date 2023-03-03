package com.platform.marketplace.Marketplace.Platform.dto;

import com.platform.marketplace.Marketplace.Platform.utility.annotations.FutureDateTime;
import com.platform.marketplace.Marketplace.Platform.utility.consts.EntranceType;
import com.platform.marketplace.Marketplace.Platform.utility.scheduler.EventScheduler;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import static com.platform.marketplace.Marketplace.Platform.utility.consts.ConstantMessages.*;
import static com.platform.marketplace.Marketplace.Platform.utility.consts.Regex.*;

@Data
@NoArgsConstructor
public class EventDTO {
    private Long eventId;
    @NotBlank(message = FIELD_MUST_NOT_BE_BLANK)
    @Pattern(regexp = CYRILLIC_REGEX_PATTERN, message = ONLY_CYRILLIC_ALLOWED)
    private HashSet<EventCategoryDTO> eventCategories;
    @NotBlank(message = NOT_BLANK)
    @Length(min = 3, message = LENGTH_TOO_SMALL)
    @Pattern(regexp = CYRILLIC_AND_SYMBOLS_REGEX_PATTERN, message = ONLY_CYRILLIC_ALLOWED)
    private String name;

    private EntranceType entranceType;
    @Pattern(regexp = CYRILLIC_AND_SYMBOLS_REGEX_PATTERN, message = ONLY_CYRILLIC_ALLOWED)
    @NotBlank(message = FIELD_MUST_NOT_BE_BLANK)
    private String description;
    @Pattern(regexp = URL_REGEX_PATTERN, message = INVALID_URL_MESSAGE)
    private String linkToApplicationForm;
    @Size(min = 1, message = LOCATION_SIZE_NOT_NULL)
    private List<LocationDTO> locations;
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    @NotNull(message = DATE_NOT_NULL)
    @FutureDateTime
    private LocalDateTime startsAt;
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    @NotNull(message = DATE_NOT_NULL)
    @FutureDateTime
    private LocalDateTime endsAt;
    @Pattern(regexp = CYRILLIC_AND_COMA_PATTERN, message = CYRILLIC_AND_COMA_ALLOWED_MESSAGE)
    private String keywords;
    private String duration;
    private Long organisationId;

    private String counter;

    public EventDTO(Long eventId, HashSet<EventCategoryDTO> eventCategories, String name, EntranceType entranceType, String description, String linkToApplicationForm, List<LocationDTO> locations, LocalDateTime startsAt, LocalDateTime endsAt, String keywords, Duration duration, Long orgId) {
        this.eventId = eventId;
        this.eventCategories = eventCategories;
        this.name = name;
        this.entranceType = entranceType;
        this.description = description;
        this.linkToApplicationForm = linkToApplicationForm;
        this.locations = locations;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.keywords = keywords;
        this.duration = String.valueOf(duration);
        this.counter = getCounter();
        this.organisationId = orgId;
    }

    public String getCounter() {
        return counter;
    }
    @Scheduled(fixedRate = 1000)
    public void setCounter() {
        LocalDateTime now = LocalDateTime.now();

        if (now.isBefore(now)) {
            Duration duration = Duration.between(now, getStartsAt());
            this.counter = String.format("%02d:%02d:%02d:%02d",
                    duration.toDaysPart(),
                    duration.toHoursPart(),
                    duration.toMinutesPart(),
                    duration.toSecondsPart());
        } else {
            this.counter = "Събитието е започнало";
        }
    }
}
