package com.banking.user_service.exceptions;

import com.banking.user_service.response.ApiExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameAlreadyExist.class)
    public ResponseEntity<ApiExceptionResponse> handleUsernameAlreadyExistException(UsernameAlreadyExist ex){
        ApiExceptionResponse apiExceptionResponse
                = new ApiExceptionResponse("Error occurred during user creation.",
                "Username already exist in the system, please try different username.");
        return ResponseEntity.badRequest().body(apiExceptionResponse);
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiExceptionResponse> handleGeneralException(GeneralException ex){
        ApiExceptionResponse apiExceptionResponse
                = new ApiExceptionResponse("Exception occurred.",
                ex.getMessage());
        return ResponseEntity.badRequest().body(apiExceptionResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put("Exception", errorMessage);
            errors.put("Field",fieldName);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
