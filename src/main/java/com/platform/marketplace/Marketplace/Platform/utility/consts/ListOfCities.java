package com.platform.marketplace.Marketplace.Platform.utility.consts;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
@Data
@NoArgsConstructor
@Component
public class ListOfCities {
   private HashSet<String> cities =new HashSet<>( Arrays.asList("Благоевград",
            "Бургас",
            "Варна",
            "Велико Търново",
            "Видин",
            "Враца",
            "Габрово",
            "Добрич",
            "Кърджали",
            "Кюстендил",
            "Ловеч",
            "Монтана",
            "Пазарджик",
            "Перник",
            "Плевен",
            "Пловдив",
            "Разград",
            "Русе",
            "Силистра",
            "Смолян",
            "Сливен",
            "София",
            "Стара Загора",
            "Търговище",
            "Хасково",
            "Шумен",
            "Ямбол"));

   public ListOfCities(String city) {
       this.cities.add(city);
   }
}
