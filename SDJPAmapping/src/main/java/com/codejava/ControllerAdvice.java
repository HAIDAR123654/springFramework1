package com.codejava;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class })
        public ResponseEntity<ErrorMessage> methodArgumentNotValid(Exception ex){
        String errorMessage;
        if(ex instanceof MethodArgumentNotValidException manve){
            errorMessage = manve.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(","));
        }
        else{
            ConstraintViolationException cve = (ConstraintViolationException) ex;
            errorMessage = cve.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        }
        ErrorMessage er = new ErrorMessage();
        er.setErrorCode(HttpStatus.BAD_REQUEST.value());
        er.setErrorMessage(errorMessage);


        return new ResponseEntity<ErrorMessage>(er, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(SeatNotLeftException.class)
    public ResponseEntity<ErrorMessage> seatNotLeft(SeatNotLeftException ex){
        ErrorMessage e = new ErrorMessage();
        e.setErrorCode(HttpStatus.BAD_REQUEST.value());
        e.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(WrongEventException.class)
    public ResponseEntity<ErrorMessage> seatNotLeft(WrongEventException ex){
        ErrorMessage e = new ErrorMessage();
        e.setErrorCode(HttpStatus.BAD_REQUEST.value());
        e.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EventStallBookingException.class)
    public ResponseEntity<ErrorMessage> seatNotLeft(EventStallBookingException ex){
        ErrorMessage e = new ErrorMessage();
        e.setErrorCode(HttpStatus.BAD_REQUEST.value());
        e.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({DateException.class, UserAlreadyExist.class})
    public ResponseEntity<ErrorMessage> dateException(Exception ex){
        ErrorMessage e = new ErrorMessage();
        if(ex instanceof DateException || ex instanceof UserAlreadyExist) {
            e.setErrorCode(HttpStatus.BAD_REQUEST.value());
            e.setErrorMessage(ex.getMessage());
        }
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }

}
