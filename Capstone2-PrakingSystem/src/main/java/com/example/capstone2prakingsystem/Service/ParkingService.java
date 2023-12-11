package com.example.capstone2prakingsystem.Service;

import com.example.capstone2prakingsystem.Api.ApiException;
import com.example.capstone2prakingsystem.Model.Parking;
import com.example.capstone2prakingsystem.Repository.ParkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingService {

    private final ParkingRepository parkingRepository;


    public List<Parking> getParking(){

        return parkingRepository.findAll();
    }


    public void addParking(Parking parking){

        parkingRepository.save(parking);
    }


    public void updateParking(Integer id , Parking parking){

        Parking oldParking = parkingRepository.findParkingById(id);

        if(oldParking==null){
            throw new ApiException("id not found");
        }

        oldParking.setLocation(parking.getLocation());
        oldParking.setAvailable(parking.isAvailable());
        oldParking.setPrice(parking.getPrice());
        oldParking.setPosition(parking.getPosition());
        oldParking.setCategory(parking.getCategory());

        parkingRepository.save(oldParking);
    }


    public void deleteParking(Integer id){
        Parking oldParking = parkingRepository.findParkingById(id);

        if(oldParking==null){
            throw new ApiException("id not found");
        }

        parkingRepository.delete(oldParking);

    }


    public List<Parking> getByPrice(double price){
        if(parkingRepository.findParkingByPriceLessThan(price).isEmpty()){

            throw new ApiException("no parking less than this price");
        }
        return parkingRepository.findParkingByPriceLessThan(price);
    }

    public List<Parking> getByPosition(String position){

        if(parkingRepository.findParkingByPosition(position).isEmpty()){
            throw new ApiException("no parking in this position");
        }
        return parkingRepository.findParkingByPosition(position);
    }

    public List<Parking> getByAvailable(){
        List<Parking> parking = parkingRepository.findParkingByAvailableTrue();

        if(parking.isEmpty()){
            throw new ApiException("no parking available");
        }
        return parking;
    }

    public List<Parking> getByCategory(String category){
        List<Parking> parking = parkingRepository.findParkingByCategory(category);

        if(parking.isEmpty()){
            throw new ApiException("no parking with this category");
        }
        return parking;
    }




}
