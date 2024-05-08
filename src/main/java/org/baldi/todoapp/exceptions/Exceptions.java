package org.baldi.todoapp.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper=false)
public class Exceptions extends RuntimeException{

    private String message;
    private HttpStatus httpStatus;

    public Exceptions(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
