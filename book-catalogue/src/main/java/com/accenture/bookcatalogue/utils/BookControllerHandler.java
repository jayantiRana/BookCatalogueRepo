package com.accenture.bookcatalogue.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class BookControllerHandler {

    @ExceptionHandler(BookNotFoundException.class) // exception handled
    public ResponseEntity<ErrorResponse> handleBookNotFoundExceptions( Exception e) {
        HttpStatus status = HttpStatus.NOT_FOUND; // 404
        return new ResponseEntity<>(new ErrorResponse(status,"No Book Found.."),status);
    }

    @ExceptionHandler(IncorrectDataException.class) // exception handled
    public ResponseEntity<ErrorResponse> handleIncorrectDataExceptions( Exception e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 404
        return new ResponseEntity<>(new ErrorResponse(status,"Incorrect Data"),status);
    }


    @ExceptionHandler(NullPointerException.class) // exception handled
    public ResponseEntity<ErrorResponse> handleNullPointerExceptions( Exception e) {
        HttpStatus status = HttpStatus.NOT_FOUND; // 404
        return new ResponseEntity<>(new ErrorResponse(status,e.getMessage()),status);
    }
    // fallback method
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleExceptions(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500

        // converting the stack trace to String
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();

        return new ResponseEntity<>(new ErrorResponse(status,e.getMessage(),stackTrace),status);
    }
}
