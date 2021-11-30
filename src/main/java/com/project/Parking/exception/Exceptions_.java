package com.project.Parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.CONFLICT)
public class Exceptions_ extends RuntimeException {

    public Exceptions_(String message) {
        super(message);
    }
}


