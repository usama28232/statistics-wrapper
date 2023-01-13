package com.practice.web.utils;

import com.practice.constants.AppConstants;
import com.practice.web.exceptions.DomainException;

public class Utils {

    private static final String UNDEFINED_KEY_EX = "app.utils.key.null.error";

    /**
     * Create new key from collection separated by a delimiter
     *
     * @param keys string collection
     * @return concatenated Key with "_"
     */
    public static String keyMaker(String... keys) {
        if (keys == null || keys.length == 0) {
            throw new DomainException(UNDEFINED_KEY_EX);
        }
        String out = AppConstants.EMPTY;
        for (String s : keys) {
            out = out.concat(s).concat(AppConstants.DELIM);
        }
        return out.substring(0, out.length() - 1);
    }

}
