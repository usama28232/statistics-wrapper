package com.practice.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Configuration
public class AppConfig {

    public static final String RESOURCE_NAME = "messages";

    @Value("${app.message.cache.duration:0}")
    int cacheSeconds;

    @Value("${spring.messages.encoding:UTF-8}")
    String messageEncoding;

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver slr = new AcceptHeaderLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    @Bean("MessagesSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(RESOURCE_NAME);
        if (this.cacheSeconds > 0) {
            messageSource.setCacheSeconds(this.cacheSeconds);
        }
        messageSource.setDefaultEncoding(this.messageEncoding);
        return messageSource;
    }

}
