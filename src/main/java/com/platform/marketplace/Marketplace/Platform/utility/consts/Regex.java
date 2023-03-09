package com.platform.marketplace.Marketplace.Platform.utility.consts;

public class Regex {

    public static final String EMAIL_REGEX_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    public static final String PASSWORD_REGEX_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{6,72}$";

    public static final String ORGANISATION_NAME_REGEX_PATTERN = "^[а-яА-Я][а-яА-Я0-9_\\-\\.]{2,50}$";

    public static final String URL_REGEX_PATTERN = "\\b(?:https?://|www\\.)\\S+\\b";

    public static final String CYRILLIC_AND_SYMBOLS_ADDRESS_DESCRIPTION_PATTERN = "^[а-яА-ЯёЁ\\s,.?'`_\\d-]+$";


    public static final String CYRILLIC_EVENT_NAME_PATTERN = "^[а-яА-Я\\s]+$";

    public static final String CYRILLIC_AND_COMA_PATTERN = "^[а-яА-ЯёЁ\\s,]+$";

    public static final String IMAGE_URL_PATTERN = "\\bhttps?:\\/\\/\\S+\\.([a-zA-Z]{2,6})(\\S+)?\\b";



}
