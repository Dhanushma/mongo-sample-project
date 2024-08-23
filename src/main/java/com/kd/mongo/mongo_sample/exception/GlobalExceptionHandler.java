package com.kd.mongo.mongo_sample.exception;

import com.kd.mongo.mongo_sample.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error ->{
                    String field = ((FieldError) error).getField();
                    String messsage = error.getDefaultMessage();
                    errors.put(field, messsage);
                }
                );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<String> handlePlayerNotFoundException(PlayerNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTeamNotFoundException(TeamNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder().errorMessage(ex.getMessage()).status(HttpStatus.NOT_FOUND)
                .apiPath(request.getDescription(false)).timeStamp(LocalDateTime.now()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
