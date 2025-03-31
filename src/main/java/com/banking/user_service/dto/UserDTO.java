package com.banking.user_service.dto;

import com.banking.user_service.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {


    Long id;
    String firstName;
    String middleName;
    String lastName;
    LocalDate dateOfBirth;
    String email;
    String mobile;
    String address;
    String username;

    public UserDTO(User user){
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.middleName = user.getMiddleName();
        this.lastName = user.getLastName();
        this.dateOfBirth = user.getDateOfBirth();
        this.email = user.getEmail();
        this.mobile = user.getMobile();
        this.address = user.getAddress();
        this.username = user.getUsername();

    }

}
