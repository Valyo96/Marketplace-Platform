package com.platform.marketplace.Marketplace.Platform.utility.consts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public enum EntranceType {
    FREE("свободен"),
    PAID("платен");

    private String value;

    public String getValue() {
        return value;
    }


}
