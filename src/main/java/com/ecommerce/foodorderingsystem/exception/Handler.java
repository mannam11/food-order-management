package com.ecommerce.foodorderingsystem.exception;

import com.ecommerce.foodorderingsystem.exception.exceptions.CityNotFoundException;
import com.ecommerce.foodorderingsystem.exception.exceptions.MenuTypeNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleCityNameEmptyException(BadRequestException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<String> handleCityNotFoundException(CityNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MenuTypeNotFoundException.class)
    public ResponseEntity<String> handleMenuTypeNotFoundException(MenuTypeNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
