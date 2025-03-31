package com.banking.user_service.controller;


import com.banking.user_service.dto.PasswordChangeDTO;
import com.banking.user_service.dto.UserDTO;
import com.banking.user_service.entities.User;
import com.banking.user_service.response.ApiResponse;
import com.banking.user_service.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/update-password")
    public ResponseEntity<ApiResponse> updatePassword(@RequestBody PasswordChangeDTO user){
        ApiResponse apiResponse = userService.updatePassword(user);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username){
        User finalUser = userService.getUserByUsername(username);
        return ResponseEntity.ok(new UserDTO(finalUser));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/forget-password/{email}")
    public ResponseEntity<ApiResponse> forgetPassword(String email){
        return ResponseEntity.ok(new ApiResponse("Email sent successfully.","Success"));
    }

}
