package ru.geekbrains.march.market.auth.exceptions;

public class PasswordConfirmationException extends RuntimeException {
    public PasswordConfirmationException(String message){
        super(message);
    }
}
