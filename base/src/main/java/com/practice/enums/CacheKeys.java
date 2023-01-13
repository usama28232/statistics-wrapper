package com.practice.enums;

public enum CacheKeys {

    MENU("MenuCache"),
    MENU_DETAIL("MenuDetailCache"),
    AREA("AreaCache"),
    STATISTICS("StatsCache");

    public final String value;

    CacheKeys(String value) {
        this.value = value;
    }
}
