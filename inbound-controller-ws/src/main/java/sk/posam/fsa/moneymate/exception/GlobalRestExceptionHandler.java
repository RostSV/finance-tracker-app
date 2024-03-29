package sk.posam.fsa.moneymate.exception;


import com.nimbusds.jose.shaded.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;


@ControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class, NullPointerException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex) {
        return ResponseEntity.badRequest().body(new Gson().toJson(
                new Response(ex.getMessage(), HttpStatus.BAD_REQUEST.toString(), new Timestamp(System.currentTimeMillis()))));
    }
}


