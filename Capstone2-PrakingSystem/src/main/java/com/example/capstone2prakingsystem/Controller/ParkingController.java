package com.example.capstone2prakingsystem.Controller;

import com.example.capstone2prakingsystem.Model.Parking;
import com.example.capstone2prakingsystem.Service.ParkingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    @GetMapping("/get")
    public ResponseEntity getParking(){

        return ResponseEntity.status(HttpStatus.OK).body(parkingService.getParking());
    }
    @PostMapping("/add")
    public ResponseEntity addParking(@Valid@RequestBody Parking parking, Errors errors){

        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        parkingService.addParking(parking);
        return ResponseEntity.status(HttpStatus.OK).body("parking added");

    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateParking(@PathVariable Integer id, @Valid@RequestBody Parking parking, Errors errors){

        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        parkingService.updateParking(id, parking);
        return ResponseEntity.status(HttpStatus.OK).body("parking update");

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteParking(@PathVariable Integer id){

        parkingService.deleteParking(id);
        return ResponseEntity.status(HttpStatus.OK).body("parking deleted");


    }

    @GetMapping("/getByPrice/{price}")
    public ResponseEntity getByPrice(@PathVariable double price){
            return ResponseEntity.status(HttpStatus.OK).body(parkingService.getByPrice(price));
    }
    @GetMapping("/getByPosition/{position}")
    public ResponseEntity getByPosition(@PathVariable String position){
       return ResponseEntity.status(HttpStatus.OK).body(parkingService.getByPosition(position));
    }
    @GetMapping("/getByAvailable")
    public ResponseEntity getByAvailable(){
        return ResponseEntity.status(HttpStatus.OK).body(parkingService.getByAvailable());
    }
    @GetMapping("/getByCategory/{category}")
    public ResponseEntity getByCategory(@PathVariable String category){
        return ResponseEntity.status(HttpStatus.OK).body(parkingService.getByCategory(category));
    }

}
