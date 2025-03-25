package com.banking.user_service.entities;

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
@Table(name = "User")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull(message = "First name cannot be empty.")
    @Column(nullable = false,length = 25)
    String firstName;

    @NotNull(message = "Middle name cannot be empty.")
    @Column(nullable = false,length = 25)
    String middleName;

    @NotNull(message = "Last name cannot be empty.")
    @Column(nullable = false,length = 25)
    String lastName;

    @NotNull(message = "Date of birth cannot be empty.")
    @Column(nullable = false)
    LocalDate dateOfBirth;

    @NotNull(message = "Email cannot be empty.")
    @Column(nullable = false,length = 50)
    String email;

    @NotNull(message = "Mobile no cannot be empty.")
    @Column(nullable = false,length = 10)
    String mobile;

    @Lob
    @NotNull(message = "Address cannot be empty.")
    @Column(nullable = false,length = 300)
    String address;

    @NotNull(message = "Username cannot be empty.")
    @Column(nullable = false, length = 50,unique = true)
    String username;

    @NotNull(message = "Password cannot be empty.")
    @Column(nullable = false, length = 30)
    String password;

    boolean verified;
    boolean active;
    int failedLoginAttempts;
    boolean isLocked;
    LocalDateTime lastLogin;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
