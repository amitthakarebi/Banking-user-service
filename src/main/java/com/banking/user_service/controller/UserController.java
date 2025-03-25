package com.banking.user_service.controller;


import com.banking.user_service.entities.User;
import com.banking.user_service.response.ApiResponse;
import com.banking.user_service.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody User user){
        ApiResponse apiResponse = userService.createUser(user);
        return ResponseEntity.ok(apiResponse);
    }

}
