package com.platform.marketplace.Marketplace.Platform.utility.consts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventType {
    private HashSet<String> eventTypes =new HashSet<>(Arrays.asList("фестивал",
            "спорт",
            "култура",
            "бизнес",
            "здравоопазване",
            "театър",
            "други"));

    public EventType(String eventType) {
        this.eventTypes.add(eventType);
    }
}
