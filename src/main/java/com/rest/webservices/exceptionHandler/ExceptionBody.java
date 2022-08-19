package com.rest.webservices.exceptionHandler;

import lombok.*;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@ToString
public class ExceptionBody {
    private Date timestamp = new Date();
    private String message;
    private String details;

    public ExceptionBody(String message, String details) {
        this.message = message;
        this.details = details;
    }
}
