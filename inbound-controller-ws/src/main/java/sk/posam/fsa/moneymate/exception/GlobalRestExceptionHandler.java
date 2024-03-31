package sk.posam.fsa.moneymate.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sk.posam.fsa.moneymate.domain.exceptions.InstanceAlreadyExistsException;
import sk.posam.fsa.moneymate.domain.exceptions.InstanceNotFoundException;
import sk.posam.fsa.moneymate.domain.exceptions.InvalidInstanceArgsException;

import java.sql.Timestamp;


@RestControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {


    //handler of invalid input and return http status 400(BAD_REQUEST)
    @ExceptionHandler(value = {IllegalArgumentException.class, NullPointerException.class, InvalidInstanceArgsException.class})
    protected ResponseEntity<ErrorResponse> handleConflictInvalidInput(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    //handler of already existing instance and return http status 409(CONFLICT)
    @ExceptionHandler(value = {InstanceAlreadyExistsException.class})
    protected ResponseEntity<ErrorResponse> handleConflictAlreadyExists(InstanceAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    //handler of not found instance and return http status 404(NOT_FOUND)
    @ExceptionHandler(InstanceNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleConflictResourceNotFound(InstanceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}


