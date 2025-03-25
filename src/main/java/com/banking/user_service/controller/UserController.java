package com.banking.user_service.controller;


import com.banking.user_service.entities.User;
import com.banking.user_service.response.ApiResponse;
import com.banking.user_service.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody User user){
        ApiResponse apiResponse = userService.createUser(user);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/update-user")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody User user){
        ApiResponse apiResponse = userService.updateUser(user);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<ApiResponse> deleteUser(@RequestBody User user){
        ApiResponse apiResponse = userService.deleteUser(user);
        return ResponseEntity.ok(apiResponse);
    }

}
