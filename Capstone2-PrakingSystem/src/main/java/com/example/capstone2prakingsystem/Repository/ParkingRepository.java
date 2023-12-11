package com.example.capstone2prakingsystem.Repository;

import com.example.capstone2prakingsystem.Model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingRepository extends JpaRepository<Parking,Integer> {

    Parking findParkingById(Integer id);

    List<Parking> findParkingByPriceLessThan(double price);
    List<Parking> findParkingByPosition(String position);
    List<Parking> findParkingByAvailableTrue();
    List<Parking> findParkingByCategory(String category);
}
