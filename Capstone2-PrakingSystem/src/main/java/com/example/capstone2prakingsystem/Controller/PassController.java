package com.example.capstone2prakingsystem.Controller;

import com.example.capstone2prakingsystem.Model.Pass;
import com.example.capstone2prakingsystem.Service.PassService;
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
@RequestMapping("/api/v1/pass")
@RequiredArgsConstructor
public class PassController {

    private final PassService passService;

    @GetMapping("/get")
    public ResponseEntity getPasses(){

        return ResponseEntity.status(HttpStatus.OK).body(passService.getPasses());
    }


    @PostMapping("/add")
    public ResponseEntity addPass(@Valid @RequestBody Pass pass, Errors errors){

        if(errors.hasErrors()){

            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        passService.addPass(pass);
        return ResponseEntity.status(HttpStatus.OK).body("pass added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePass(@PathVariable Integer id ,@Valid @RequestBody Pass pass, Errors errors){
        if(errors.hasErrors()){

            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        passService.updatePass(id, pass);
        return ResponseEntity.status(HttpStatus.OK).body("pass updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePass(@PathVariable Integer id){
        passService.deletePass(id);

        return ResponseEntity.status(HttpStatus.OK).body("pass deleted");
    }
    @PutMapping("/extend/{id}/{user_id}/{hours}")
    public ResponseEntity extendTime(@PathVariable Integer id , @PathVariable Integer user_id,@PathVariable Integer hours){

        passService.extend(id, user_id, hours);
        return ResponseEntity.status(HttpStatus.OK).body("pass time extended");
    }


    @GetMapping("/history/{userid}")
    public ResponseEntity getHistory(@PathVariable Integer userid){

        return ResponseEntity.status(HttpStatus.OK).body(passService.getHistory(userid));
    }
    @GetMapping("/getByDate/{start}/{end}")
    public ResponseEntity getBetweenDate(@PathVariable  LocalDateTime start, @PathVariable LocalDateTime end){
        return ResponseEntity.status(HttpStatus.OK).body(passService.getBetweenDate(start, end));
    }
    @GetMapping("/getByState/{state}")
    public ResponseEntity getByState(@PathVariable String state){
        return ResponseEntity.status(HttpStatus.OK).body(passService.getByState(state));
    }
}
