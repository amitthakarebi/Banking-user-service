package com.banking.user_service.services;

import com.banking.user_service.dto.PasswordChangeDTO;
import com.banking.user_service.dto.UserDTO;
import com.banking.user_service.entities.User;
import com.banking.user_service.exceptions.*;
import com.banking.user_service.repository.UserRepository;
import com.banking.user_service.response.ApiResponse;
import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return new ApiResponse("Successfully created the user.", "Success");
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
        if (user.getFirstName() != null && !user.getFirstName().isEmpty()) {
            existingUser.setFirstName(user.getFirstName());
        }

        // Middle name
        if (user.getMiddleName() != null && !user.getMiddleName().isEmpty()) {
            existingUser.setMiddleName(user.getMiddleName());
        }

        // Last name
        if (user.getLastName() != null && !user.getLastName().isEmpty()) {
            existingUser.setLastName(user.getLastName());
        }

        // Date of birth
        if (user.getDateOfBirth() != null) {
            existingUser.setDateOfBirth(user.getDateOfBirth());
        }

        // Email
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            existingUser.setEmail(user.getEmail());
        }

        // Mobile
        if (user.getMobile() != null && user.getMobile().isEmpty()) {
            existingUser.setMobile(user.getMobile());
        }

        // Address
        if (user.getAddress() != null && user.getAddress().isEmpty()) {
            existingUser.setAddress(user.getAddress());
        }

        existingUser.setUpdatedAt(localDateTime);

        userRepository.save(existingUser);
        return new ApiResponse("Successfully updated the user.", "Success");

    }

    public ApiResponse deleteUser(User user) {
        if(user.getUsername()==null || user.getPassword()==null ||
        user.getUsername().isEmpty() || user.getPassword().isEmpty()){
            throw new UsernameAndPasswordNotNullException("Error occurred while deleting user.");
        }
        Optional<User> dbUser = userRepository.getUserByUsername(user.getUsername());
        if (dbUser.isEmpty()) {
            throw new UsernameNotFoundException("Error occurred while deleting user.");
        }else if(!dbUser.get().getPassword().equals(user.getPassword())){
            throw new PasswordDoesNotMatchException("Error occurred while deleting user.");
        }else{
            userRepository.delete(dbUser.get());
            return new ApiResponse("Successfully deleted the user.", "Success");
        }
    }

    public ApiResponse updatePassword(PasswordChangeDTO user) {

        if(user.getUsername()==null || user.getCurrentPassword()==null || user.getNewPassword()==null ||
                user.getUsername().isEmpty() || user.getCurrentPassword().isEmpty() || user.getNewPassword().isEmpty()){
            throw new UsernameAndPasswordNotNullException("Error occurred while deleting user.");
        }
        Optional<User> dbUser = userRepository.getUserByUsername(user.getUsername());
        if (dbUser.isEmpty()) {
            throw new UsernameNotFoundException("Error occurred while deleting user.");
        }else if(!dbUser.get().getPassword().equals(user.getCurrentPassword())){
            throw new PasswordDoesNotMatchException("Error occurred while deleting user.");
        }else{
            User tempUser = dbUser.get();
            tempUser.setPassword(user.getNewPassword());
            userRepository.save(tempUser);
            return new ApiResponse("Successfully updated the user.", "Success");
        }

    }

    public User getUserByUsername(String username) {

        Optional<User> dbUser = userRepository.getUserByUsername(username);
        if (dbUser.isEmpty()) {
            throw new UsernameNotFoundException("Error occurred while deleting user.");
        }else{
            return dbUser.get();
        }


    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = users.stream()
                .map(UserDTO::new)
                .toList();
        return userDTOS;
    }
}
