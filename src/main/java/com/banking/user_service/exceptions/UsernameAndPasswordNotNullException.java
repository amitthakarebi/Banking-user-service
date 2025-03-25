package com.banking.user_service.exceptions;

public class UsernameAndPasswordNotNullException extends RuntimeException{
    public UsernameAndPasswordNotNullException(String msg){
        super(msg);
    }
}
