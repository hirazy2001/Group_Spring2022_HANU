package com.example.group_hanu_spring2022.constants;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Constants {
    public static BigDecimal INTEREST_RATE = new BigDecimal(0.02);

    public static String ROLE_USER = "USER";
    public static String ROLE_ADMIN = "ADMIN";

    public static String ACCOUNT_LOAN = "ACCOUNT_LOAN";
    public static String ACCOUNT_SAVE = "ACCOUNT_SAVE";

    public static String ACCOUNT_STATUS_ACTIVE = "ACTIVE";
    public static String ACCOUNT_STATUS_ = "ACCOUNT_STATUS_CANCELED";

    public static String PERMISSION_DENIED = "Permission Denied!";

    public final static int LENGTH_USERNAME = 30;
    public final static int LENGTH_FIRSTNAME = 30;
    public final static int LENGTH_MIDDLE_NAME = 30;
    public final static int LENGTH_LAST_NAME = 30;
    public static int LENGTH_FULL_NAME = 60;
    public static int LENGTH_PHONE_NUMBER = 11;


    public static String DEFAULT_ICON = "https://img.nimo.tv/t/1599514158915/202105041620141250244_1599514158915_avatar.png/w120_l0/img.webp";
    public static LocalDateTime DEFAULT_TIME = LocalDateTime.now();
}
