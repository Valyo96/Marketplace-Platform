package com.platform.marketplace.Marketplace.Platform.utility.consts;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum EventTypes {
    FESTIVAL("фестивал"),
    SPORT("спорт"),
    ART("култура"),
    BUSINESS("бизнес"),
    OTHER("други");

    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
