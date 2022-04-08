package ru.geekbrains.march.market.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.geekbrains.march.market.dtos.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler
    public ResponseEntity<AppError> handleResourceNotFoundExpcetion(ResourceNotFoundException e){
        return new ResponseEntity<>(new AppError("RESOURCE_NOT_FOUND",e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
