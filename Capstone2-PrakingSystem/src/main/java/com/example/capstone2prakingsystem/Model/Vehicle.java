package com.example.capstone2prakingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "user id should not be null")
    @Column(columnDefinition = "int not null")
    private Integer userid;
    @NotEmpty(message = "vehicle type should not be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String vehicleType;
    @NotEmpty(message = "plate should not be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String plate;
}
