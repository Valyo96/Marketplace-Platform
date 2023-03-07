package com.platform.marketplace.Marketplace.Platform.dto;

import com.platform.marketplace.Marketplace.Platform.utility.annotations.FutureDateTime;
import com.platform.marketplace.Marketplace.Platform.utility.consts.EntranceType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static com.platform.marketplace.Marketplace.Platform.utility.consts.ConstantMessages.*;
import static com.platform.marketplace.Marketplace.Platform.utility.consts.Regex.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    private Long eventId;

    @NotBlank(message = FIELD_MUST_NOT_BE_BLANK)
    @Pattern(regexp = CYRILLIC_AND_COMA_PATTERN, message = ONLY_CYRILLIC_ALLOWED)
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
    @Pattern(regexp = URL_REGEX_PATTERN, message = INVALID_URL_MESSAGE)
    @Nullable
    private String linkToApplicationForm;
    @Size(min = 1, message = LOCATION_SIZE_NOT_NULL)
    private List<String> locations;
    @Nullable
    @Pattern(regexp = CYRILLIC_AND_SYMBOLS_ADDRESS_DESCRIPTION_PATTERN, message =ONLY_CYRILLIC_ALLOWED)
    private String address;
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    @NotNull(message = DATE_NOT_NULL)
    @FutureDateTime
    private LocalDateTime startsAt;
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    @NotNull(message = DATE_NOT_NULL)
    @FutureDateTime
    private LocalDateTime endsAt;
    @Pattern(regexp = CYRILLIC_AND_COMA_PATTERN, message = CYRILLIC_AND_COMA_ALLOWED_MESSAGE)
    @Nullable
    private String keywords;
    private String duration;
   @Pattern(regexp =  IMAGE_URL_PATTERN , message = INVALID_URL_MESSAGE)
    private String imageUrl;
    private Long organisationId;

    private String counter;

    public EventDTO(Long eventId, String eventCategories, String name, EntranceType entranceType, String description, String linkToApplicationForm, List<String> locations, String address,LocalDateTime startsAt, LocalDateTime endsAt, String keywords, Duration duration,String imageUrl ,Long orgId) {
        this.eventId = eventId;
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
        this.imageUrl = imageUrl;
        this.counter = setCounter();
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
        Duration duration = Duration.between(startsAt , endsAt);
        return String.format("%02d:%02d:%02d:%02d",
        duration.toDaysPart(),
                duration.toHoursPart(),
                duration.toMinutesPart(),
                duration.toSecondsPart());
    }

    @Override
    public String toString() {
        return
                "Име на събитието:" + name + '\'' +
                "Вход: " + entranceType +
                "Описание: " + description + '\'' +
                "Линк към сайт: " + linkToApplicationForm + '\'' +
                "Локации:" + locations +
                "Ще се състои на:" + startsAt +
                "Ще свърши на:" + endsAt +
                "Ключови думи: " + keywords + '\'' +
                "Времетраене: " + duration + '\'' +
                "Организирано от: " + organisationId +
                "Брояч до започване: " + counter;
    }
}
