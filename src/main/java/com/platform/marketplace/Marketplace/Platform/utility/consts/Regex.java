package com.platform.marketplace.Marketplace.Platform.utility.consts;

public class Regex {

    public static final String EMAIL_REGEX_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    public static final String PASSWORD_REGEX_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{6,72}$";

    public static final String ORGANISATION_NAME_REGEX_PATTERN = "^[a-zA-Z][a-zA-Z0-9_\\-\\.]{2,50}$";

    public static final String URL_REGEX_PATTERN = "^((https?://)?(www\\.)?([a-zA-Z0-9]+(-[a-zA-Z0-9]+)*\\.)+[a-zA-Z]{2,})$";

    public static final String CYRILLIC_AND_SYMBOLS_REGEX_PATTERN = "^[а-яА-Я,.+\\-_!?\\s@#$%^&*()]+$";

    public static final String CYRILLIC_REGEX_PATTERN = "^[а-яА-Я]+$";

    public static final String CYRILLIC_AND_COMA_PATTERN = "^[а-яА-ЯёЁ\\s,]+$";

    public static final String IMAGE_URL_PATTERN = "^(https?:\\/\\/)?(www\\.)?[\\w-]+\\.[\\w.-]+(\\/[\\w-]+)*\\/?.(jpg|jpeg|png|gif)$\n";


}
