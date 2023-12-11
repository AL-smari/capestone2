package com.example.capstone2prakingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "username should not be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String username;
    @NotEmpty(message = "password should not be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String password;
    @NotEmpty(message = "email should not be empty")
    @Email(message = "should be valid email")
    @Column(columnDefinition = "varchar(255) not null unique")
    private String email;
    private boolean subscription;
    private LocalDateTime subscription_expired_date;
    @NotNull(message = "balance should not be null")
    @Column(columnDefinition = "int not null")
    private double balance;
    @DateTimeFormat( pattern= "yyyy-MM-dd")
    private Date bithdate;
}
