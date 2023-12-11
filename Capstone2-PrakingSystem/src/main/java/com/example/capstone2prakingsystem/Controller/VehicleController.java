package com.example.capstone2prakingsystem.Controller;

import com.example.capstone2prakingsystem.Model.Vehicle;
import com.example.capstone2prakingsystem.Service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;


    @GetMapping("/get")
    public ResponseEntity getVehicle(){

        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.getVehicle());
    }

    @PostMapping("/add")
    public ResponseEntity addVehicle(@Valid@RequestBody Vehicle vehicle, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        vehicleService.addVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.OK).body("vehicle added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity UpdateVehicle(@PathVariable Integer id, @Valid@RequestBody Vehicle vehicle, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        vehicleService.updateVehicle(id, vehicle);
        return ResponseEntity.status(HttpStatus.OK).body("vehicle updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteVehicle(@PathVariable Integer id){

        vehicleService.deleteVehicle(id);
        return ResponseEntity.status(HttpStatus.OK).body("vehicle deletd");
    }
    @GetMapping("/getByUserId/{userid}")
    public ResponseEntity getByUserId(@PathVariable Integer userid){

        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.getByUserId(userid));
    }
}
