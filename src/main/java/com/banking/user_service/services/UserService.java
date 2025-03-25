package com.banking.user_service.services;

import com.banking.user_service.entities.User;
import com.banking.user_service.exceptions.GeneralException;
import com.banking.user_service.exceptions.UsernameAlreadyExist;
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

        try {
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
        }catch(UsernameAlreadyExist ex){
            throw new UsernameAlreadyExist();
        }catch(NonUniqueResultException ex){
            throw new GeneralException("Username is already present with multiple entries.");
        }
    }

}
