package com.project.Parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)

public class Exceptions__  extends RuntimeException {

    public Exceptions__(String message) {
        super(message);
    }
}

