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
public class UserService {

    private final UserRepository userRepository;
    private final ParkingRepository parkingRepository;
    private final PassRepository passRepository;


    public List<User> getUser(){

        return userRepository.findAll();

    }


    public void addUser(User user){

        userRepository.save(user);
    }


    public void updateUser(Integer id,User user){
        User oldUser = userRepository.findUserById(id);

        if(oldUser==null){

            throw new ApiException("id not found");
        }
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setSubscription(user.isSubscription());
        oldUser.setSubscription_expired_date(user.getSubscription_expired_date());
        oldUser.setEmail(user.getEmail());
        oldUser.setBalance(user.getBalance());
        oldUser.setBithdate(user.getBithdate());
        userRepository.save(oldUser);
    }

    public void deleteUser(Integer id){
        User oldUser = userRepository.findUserById(id);

        if(oldUser==null){

            throw new ApiException("id not found");
        }

        userRepository.delete(oldUser);

    }



    public void login (String username , String password){
        User user = userRepository.findUserByUsernameAndPassword(username, password);
        if(user==null){

            throw new ApiException("invalid username or password");
        }

    }

    public void addBalance(Integer id,double amount){

        User user = userRepository.findUserById(id);

        if(user==null){

            throw new ApiException("id not found");
        }

        user.setBalance(user.getBalance()+amount);
        userRepository.save(user);

    }


    public void rentParking(Integer id , Integer parking_id,Integer hours){
        User user = userRepository.findUserById(id);
        Parking parking =parkingRepository.findParkingById(parking_id);
        Pass pass = new Pass();
        if(user==null){
            throw new ApiException("user id not found");
        }
        if(parking==null){
            throw new ApiException("parking id not found");
        }
        if(parking.isAvailable()){
            double total = parking.getPrice()*hours;
            if(user.isSubscription()){
                total=total-total*0.30;
            }if(user.getBalance()>=total){
               user.setBalance(user.getBalance()-total);
               parking.setAvailable(false);
               pass.setState("active");
               pass.setParkingid(parking_id);
               pass.setUserid(id);
               pass.setExpired_date(LocalDateTime.now().plusMinutes(hours));
               passRepository.save(pass);
               parkingRepository.save(parking);
               userRepository.save(user);
            }else {
                throw new ApiException("user dont have enough money");}
        }else throw new ApiException("parking not available");


    }

    public void subscribe(Integer id){
        User user = userRepository.findUserById(id);
        if(user==null){
            throw new ApiException("id not found");
        }
        if(user.getBalance()>=30){

            user.setBalance(user.getBalance()-30);
            user.setSubscription(true);
            user.setSubscription_expired_date(LocalDateTime.now().plusMinutes(2));
            userRepository.save(user);
        }else {
            throw new ApiException("user dont have enough money to subscribe");
        }
    }

    public void forgetPassword(Integer id , String bithDate,String password){
        User user = userRepository.findUserById(id);

        if(user==null){
            throw new ApiException("username not found or wrong birthdate");
        }
        if(user.getBithdate().toString().contains(bithDate)){
            user.setPassword(password);
            userRepository.save(user);
        }else {
            throw new ApiException("wrong birthdate");
        }



    }

    public List<User> getAllSubscribed(){
        List<User> users = userRepository.findUserBySubscriptionTrue();
       if(users.isEmpty()){
           throw new ApiException("no user subscribed");
       }
       return users;
    }
}
