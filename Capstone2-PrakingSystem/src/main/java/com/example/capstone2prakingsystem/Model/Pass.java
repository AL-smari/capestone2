package com.example.capstone2prakingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "user id should not be null")
    @Column(columnDefinition = "int not null")
    private Integer parkingid;
    @NotNull(message = "user id should not be null")
    @Column(columnDefinition = "int not null")
    private Integer userid;
    @NotEmpty(message = "state should not empty")
    @Pattern(regexp = "active|expired")
    @Column(columnDefinition = "varchar(255) not null check(state='active' or state='expired')")
    private String state;

    private LocalDateTime expired_date;
}
