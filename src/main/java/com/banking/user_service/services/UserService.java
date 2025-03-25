package com.banking.user_service.services;

import com.banking.user_service.entities.User;
import com.banking.user_service.exceptions.GeneralException;
import com.banking.user_service.exceptions.PasswordDoesNotMatchException;
import com.banking.user_service.exceptions.UsernameAlreadyExist;
import com.banking.user_service.exceptions.UsernameNotFoundException;
import com.banking.user_service.repository.UserRepository;
import com.banking.user_service.response.ApiResponse;
import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ApiResponse createUser(User user) {
        Optional<User> dbUser = userRepository.getUserByUsername(user.getUsername());
        if (dbUser.isPresent()) {
            throw new UsernameAlreadyExist();
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        user.setActive(true);
        user.setCreatedAt(localDateTime);
        user.setUpdatedAt(localDateTime);
        user.setLastLogin(localDateTime);
        userRepository.save(user);
        return new ApiResponse("Successfully created the user.", "Success", user.getUsername());
    }

    public ApiResponse updateUser(User user) {
        Optional<User> dbUser = userRepository.getUserByUsername(user.getUsername());
        User existingUser = null;
        if (dbUser.isEmpty()) {
            throw new UsernameNotFoundException("Error occurred in the update user.");
        }else{
            existingUser = dbUser.get();
            if(!existingUser.getPassword().equals(user.getPassword())){
                throw new PasswordDoesNotMatchException("Error occurred in the update user.");
            }
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        // First name
        if (user.getFirstName() != null) {
            existingUser.setFirstName(user.getFirstName());
        }

        // Middle name
        if (user.getMiddleName() != null) {
            existingUser.setMiddleName(user.getMiddleName());
        }

        // Last name
        if (user.getLastName() != null) {
            existingUser.setLastName(user.getLastName());
        }

        // Date of birth
        if (user.getDateOfBirth() != null) {
            existingUser.setDateOfBirth(user.getDateOfBirth());
        }

        // Email
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }

        // Mobile
        if (user.getMobile() != null) {
            existingUser.setMobile(user.getMobile());
        }

        // Address
        if (user.getAddress() != null) {
            existingUser.setAddress(user.getAddress());
        }

        existingUser.setUpdatedAt(localDateTime);

        userRepository.save(existingUser);
        return new ApiResponse("Successfully updated the user.", "Success", user.getUsername());

    }
}
