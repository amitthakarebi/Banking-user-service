package com.banking.user_service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiResponse {

    private String message;
    private String status;
    private Object data;

    public ApiResponse(String message, String status){
        this.message = message;
        this.status = status;
    }

}
