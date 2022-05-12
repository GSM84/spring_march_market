package ru.geekbrains.march.market.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.geekbrains.march.market.api.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler
    public ResponseEntity<AppError> handleResourceNotFoundExpcetion(ResourceNotFoundException e){
        return new ResponseEntity<>(new AppError("RESOURCE_NOT_FOUND", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handlePasswordConfirmationException(PasswordConfirmationException e){
        return new ResponseEntity<>(new AppError("PASSWORD_CONFIRMATION_EXCEPTION", e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handleUserAlreadyExists(UserAlreadyExistsException e){
        return new ResponseEntity<>(new AppError("USER_ALREADY_EXISTS", e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }
}
