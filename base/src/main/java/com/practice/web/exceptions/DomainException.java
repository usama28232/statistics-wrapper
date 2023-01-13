package com.practice.web.exceptions;

import com.practice.web.config.AppConfig;
import org.springframework.context.i18n.LocaleContextHolder;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class DomainException extends RuntimeException {

    private String message;

    /**
     * Get Message from messages<<locale>>.properties
     *
     * @param message code defined in properties file
     * @param args    to replace in user defined message
     */
    public DomainException(String message, Object... args) {
        Locale loc = LocaleContextHolder.getLocale();
        this.message = MessageFormat.format(
                ResourceBundle.getBundle(
                        AppConfig.RESOURCE_NAME,
                        loc
                ).getString(message),
                args
        );
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}

