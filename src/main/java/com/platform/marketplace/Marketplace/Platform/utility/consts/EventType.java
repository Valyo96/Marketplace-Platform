package com.platform.marketplace.Marketplace.Platform.utility.consts;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
@Component
public class EventType {
    public HashSet<String> eventTypes =new HashSet<>(Arrays.asList("фестивал",
            "спорт",
            "култура",
            "бизнес",
            "здравоопазване",
            "театър",
            "други"));
}
