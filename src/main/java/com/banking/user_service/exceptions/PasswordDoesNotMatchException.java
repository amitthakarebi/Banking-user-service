package com.banking.user_service.exceptions;

public class PasswordDoesNotMatchException extends RuntimeException{
    public PasswordDoesNotMatchException(String msg){
        super(msg);
    }
}
