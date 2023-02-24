package org.iesvdm.examen.controller;

import org.iesvdm.examen.exception.CarritoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CarritoNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(CarritoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String carritoNotFoundHandler(CarritoNotFoundException carritoNotFoundException) {
        return carritoNotFoundException.getMessage();
    }

}
