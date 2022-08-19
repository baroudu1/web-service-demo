package com.rest.webservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("hello")
public class HelloController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/internationalized")
    public String hello() {
        return messageSource.getMessage("message", null, LocaleContextHolder.getLocale());
    }
}
