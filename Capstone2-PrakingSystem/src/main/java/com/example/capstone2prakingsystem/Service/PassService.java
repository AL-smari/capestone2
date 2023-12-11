package com.example.capstone2prakingsystem.Service;

import com.example.capstone2prakingsystem.Api.ApiException;
import com.example.capstone2prakingsystem.Model.Parking;
import com.example.capstone2prakingsystem.Model.Pass;
import com.example.capstone2prakingsystem.Model.User;
import com.example.capstone2prakingsystem.Repository.ParkingRepository;
import com.example.capstone2prakingsystem.Repository.PassRepository;
import com.example.capstone2prakingsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PassService {

    private final PassRepository passRepository;
    private final ParkingRepository parkingRepository;
    private final UserRepository userRepository;

    public List<Pass> getPasses(){

        return passRepository.findAll();
    }


    public void addPass(Pass pass){

        User user = userRepository.findUserById(pass.getUserid());
        Parking parking = parkingRepository.findParkingById(pass.getParkingid());

        if(user==null){

            throw new ApiException("user id not found");
        }

        if(parking==null){

            throw new ApiException(("parking id not found"));
        }

        pass.setExpired_date(LocalDateTime.now().plusMinutes(1));
        passRepository.save(pass);

    }


    public void updatePass(Integer id , Pass pass){
        Pass oldPass=passRepository.findPassById(id);
        User user = userRepository.findUserById(pass.getUserid());
        Parking parking = parkingRepository.findParkingById(pass.getParkingid());
        if(oldPass==null){
            throw new ApiException("id not found");
        }
        if(user==null){

            throw new ApiException("user id not found");
        }

        if(parking==null){

            throw new ApiException(("parking id not found"));
        }

        oldPass.setExpired_date(pass.getExpired_date());
        oldPass.setState(pass.getState());
        oldPass.setUserid(pass.getUserid());
        oldPass.setParkingid(pass.getParkingid());

        passRepository.save(oldPass);

    }

    public void deletePass(Integer id){
        Pass oldPass=passRepository.findPassById(id);
        if(oldPass==null){
            throw new ApiException("id not found");
        }

        passRepository.delete(oldPass);
    }

    public void extend(Integer id , Integer user_id,Integer hours){
        User user = userRepository.findUserById(user_id);
        Pass pass = passRepository.findPassById(id);
        if(user==null){
            throw new ApiException("user id not found");
        }
        if(pass==null){
            throw new ApiException("pass id not found");
        }
        Parking parking = parkingRepository.findParkingById(pass.getParkingid());
        if(pass.getUserid().equals(user_id)){
            if(pass.getState().equals("active")){
                double total = parking.getPrice()*hours;
                if(user.getBalance()>=total){
                    user.setBalance(user.getBalance()-total);
                    pass.setExpired_date(pass.getExpired_date().plusMinutes(hours));
                    passRepository.save(pass);
                    userRepository.save(user);
                }else {
                    throw new ApiException("user dont have enough money");
                }
            }else {
                throw new ApiException("this pass is expired");
            }
        }else {
            throw new ApiException("this pass is not for this user");
        }
    }

    public List<Pass> getHistory(Integer userid){
        String state = "expired";
       List<Pass> pass = passRepository.findPassByUseridAndState(userid,state);
       if(pass.isEmpty()){
           throw new ApiException("history is empty");
       }
       return pass;
    }


    public List<Pass> getBetweenDate(LocalDateTime start , LocalDateTime end){

        List<Pass> passes = passRepository.PassByExpired_dateBetween(start, end);
        if(passes.isEmpty()){
            throw new ApiException("no passes between these date");
        }
        return passes;

    }

    public List<Pass> getByState(String state){
        List<Pass> passes = passRepository.findPassByState(state);
        if(passes.isEmpty()){
            throw new ApiException("no passes with this state");
        }

        return passes;
    }
}
