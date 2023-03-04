package com.platform.marketplace.Marketplace.Platform.dto;

import lombok.Data;

@Data
public class EventCategoryDTO {
    private String category;

    public EventCategoryDTO(String category) {
        this.category = category;
    }
}
