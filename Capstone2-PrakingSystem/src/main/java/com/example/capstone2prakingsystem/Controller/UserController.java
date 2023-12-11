package com.example.capstone2prakingsystem.Controller;

import com.example.capstone2prakingsystem.Model.User;
import com.example.capstone2prakingsystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getUser(){

        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid@RequestBody User user , Errors errors){

        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("user added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id,@Valid@RequestBody User user , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        userService.updateUser(id, user);
        return ResponseEntity.status(HttpStatus.OK).body("user updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){

        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("user deleted");
    }

    @GetMapping("/login/{username}/{password}")
    public ResponseEntity login(@PathVariable String username , @PathVariable String password){

        userService.login(username, password);
        return ResponseEntity.status(HttpStatus.OK).body("login done");
    }

    @PutMapping("/addBalance/{id}/{amount}")
    public ResponseEntity addBalance(@PathVariable Integer id,@PathVariable double amount){

        userService.addBalance(id,amount);

        return ResponseEntity.status(HttpStatus.OK).body("balance updated");
    }

    @PostMapping("/rent/{id}/{parking_id}/{hours}")
    public ResponseEntity rentParking(@PathVariable Integer id , @PathVariable Integer parking_id,@PathVariable Integer hours){

        userService.rentParking(id, parking_id, hours);
        return ResponseEntity.status(HttpStatus.OK).body("Parking rented");
    }

    @PutMapping("/subscribe/{id}")
    public ResponseEntity subscribe(@PathVariable Integer id){

        userService.subscribe(id);
        return ResponseEntity.status(HttpStatus.OK).body("subscribe completed");
    }

    @PutMapping("/forget-password/{id}/{birthDate}/{password}")
    public ResponseEntity forgetPassword(@PathVariable Integer id , @PathVariable String birthDate, @PathVariable String password){

        userService.forgetPassword(id, birthDate, password);
        return ResponseEntity.status(HttpStatus.OK).body("password updated");
    }

    @GetMapping("/getSubscribed")
    public ResponseEntity getAllSubscribed(){

        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllSubscribed());
    }
}
