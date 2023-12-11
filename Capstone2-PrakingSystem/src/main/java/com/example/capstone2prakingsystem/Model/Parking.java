package com.example.capstone2prakingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "location should not be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String location;
    @NotNull(message = "price should not be empty")
    @Column(columnDefinition = "int not null")
    private double price;
    @NotNull(message = "available should not be null")
    private boolean available;
    @NotNull(message = "position should not be null")
    @Pattern(regexp = "in|out", message = "position must be in or out")
    @Column(columnDefinition = "varchar(3) not null check(position='in' or position='out')")
    private String position;
    @NotNull(message = "category should not be null")
    @Pattern(regexp = "specialNeeds|public", message = "category must be specialNeeds or public")
    @Column(columnDefinition = "varchar(12) not null check(category='specialNeeds' or category='public')")
    private String category;
}
