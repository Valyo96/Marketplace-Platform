package com.platform.marketplace.Marketplace.Platform.dto;

import com.platform.marketplace.Marketplace.Platform.utility.annotations.FutureEndDateTime;
import com.platform.marketplace.Marketplace.Platform.utility.annotations.FutureStartDateTime;
import com.platform.marketplace.Marketplace.Platform.utility.annotations.NotEmptyFile;
import com.platform.marketplace.Marketplace.Platform.utility.consts.EntranceType;
import jakarta.annotation.Nullable;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static com.platform.marketplace.Marketplace.Platform.utility.consts.ConstantMessages.*;
import static com.platform.marketplace.Marketplace.Platform.utility.consts.Regex.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FutureEndDateTime
public class EventDTO {
    private Long eventId;

    private String organisationName;

    @NotBlank(message = FIELD_MUST_NOT_BE_BLANK)
    @Pattern(regexp = CYRILLIC_AND_COMA_PATTERN, message = CYRILLIC_AND_COMA_ALLOWED_MESSAGE)
    private String eventCategories;
    @NotBlank(message = NOT_BLANK)
    @Length(min = 3, message = LENGTH_TOO_SMALL)
    @Pattern(regexp = CYRILLIC_EVENT_NAME_PATTERN, message = ONLY_CYRILLIC_ALLOWED)
    private String name;

    private EntranceType entranceType;
    @Pattern(regexp = CYRILLIC_AND_SYMBOLS_ADDRESS_DESCRIPTION_PATTERN, message = ONLY_CYRILLIC_ALLOWED)
    @NotBlank(message = FIELD_MUST_NOT_BE_BLANK)
    @Size(min =10 , max = 250 , message = DESCRIPTION_LENGTH_OUT_OF_BOUNDS)
    private String description;
//    @Pattern(regexp = URL_REGEX_PATTERN, message = INVALID_URL_MESSAGE)
    @Nullable
    @URL(message = INVALID_URL_MESSAGE)
    private String linkToApplicationForm;
    @Size(min = 1, message = LOCATION_SIZE_NOT_NULL)
    private List<String> locations;
    @Nullable
    @Size(min = 0)
    @Pattern(regexp = CYRILLIC_AND_SYMBOLS_ADDRESS_DESCRIPTION_PATTERN, message =ONLY_CYRILLIC_ALLOWED)
    private String address;
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    @NotNull(message = DATE_NOT_NULL)
    @FutureStartDateTime
    private LocalDateTime startsAt;
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    @NotNull(message = DATE_NOT_NULL)
    @FutureStartDateTime
    private LocalDateTime endsAt;
    @Pattern(regexp = CYRILLIC_AND_COMA_PATTERN, message = CYRILLIC_AND_COMA_ALLOWED_MESSAGE)
    @Nullable
    private String keywords;
    private String duration;
    @Transient
    @NotEmptyFile
    private MultipartFile imagePath;
    private String imageDataUrl;
    private Long organisationId;
    private boolean isEnabled;
    private String counter;

    public EventDTO(Long eventId, String organisationName , String eventCategories, String name, EntranceType entranceType, String description, String linkToApplicationForm, List<String> locations, String address, LocalDateTime startsAt, LocalDateTime endsAt, String keywords,String imageDataUrl ,boolean isEnabled, Long orgId) {
        this.eventId = eventId;
        this.organisationName = organisationName;
        this.eventCategories = eventCategories;
        this.name = name;
        this.entranceType = entranceType;
        this.description = description;
        this.linkToApplicationForm = linkToApplicationForm;
        this.locations = locations;
        this.address = address;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.keywords = keywords;
        this.duration = setDuration();
        this.counter = setCounter();
        this.imageDataUrl = imageDataUrl;
        this.isEnabled = isEnabled;
        this.organisationId = orgId;
    }


    public String getCounter() {
        return counter;
    }
    @Scheduled(fixedRate = 1000)
    public String setCounter() {
        LocalDateTime now = LocalDateTime.now();

        if (now.isBefore(getStartsAt())) {
            Duration duration = Duration.between(now, this.startsAt);
         return  String.format("%02d ДЕНА " +
                         "%02d ЧАСА " +
                         "%02d МИНУТИ " +
                         "%02d СЕКУНДИ ",
                    duration.toDaysPart(),
                    duration.toHoursPart(),
                    duration.toMinutesPart(),
                    duration.toSecondsPart());
        } else {
           return "Събитието е започнало";
        }
    }

    public String setDuration(){
        Duration duration = Duration.between(startsAt, endsAt);
        if (duration.toDays() > 0) {
            return String.format("%d дни",
                    duration.toDays());
        } else {
            return String.format("%02d часа и %02d минути",
                    duration.toHours(),
                    duration.toMinutesPart());
        }
    }


}
