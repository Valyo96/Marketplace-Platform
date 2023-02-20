package com.platform.marketplace.Marketplace.Platform.consts;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum Cities {
    BLAGOEVGRAD("Благоевград"),
    BURGAS("Бургас"),
    VARNA("Варна"),
    VELIKO_TYRNOVO("Велико Търново"),
    VIDIN("Видин"),
    VRATSA("Враца"),
    GABROVO("Габрово"),
    DOBRICH("Добрич"),
    KYRDJALI("Кърджали"),
    KYUSTENDIL("Кюстендил"),
    LOVECH("Ловеч"),
    MONTANA("Монтана"),
    PAZARDJIK("Пазарджик"),
    PERNIK("Перник"),
    PLEVEN("Плевен"),
    PLOVDIV("Пловдив"),
    RAZGRAD("Разград"),
    RUSE("Русе"),
    SILISTRA("Силистра"),
    SLIVEN("Сливен"),
    SMOLYAN("Смолян"),
    SOFIA("София"),
    STARA_ZAGORA("Стара Загора"),
    TARGOVISHTE("Търговище"),
    HASKOVO("Хасково"),
    SHUMEN("Шумен"),
    YAMBOL("Ямбол");

    private String cityRegion;

    public String getCityRegion() {
        return cityRegion;
    }

    public void setCityRegion(String cityRegion) {
        this.cityRegion = cityRegion;
    }
}
