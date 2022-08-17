package com.rest.webservices.exceptionHandler;

import lombok.*;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class ExceptionBody {
    private Date timestamp;
    private String message;
    private String details;
}
