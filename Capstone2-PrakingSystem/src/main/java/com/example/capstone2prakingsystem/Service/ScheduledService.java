package com.example.capstone2prakingsystem.Service;

import com.example.capstone2prakingsystem.Model.Parking;
import com.example.capstone2prakingsystem.Model.Pass;
import com.example.capstone2prakingsystem.Model.User;
import com.example.capstone2prakingsystem.Repository.ParkingRepository;
import com.example.capstone2prakingsystem.Repository.PassRepository;
import com.example.capstone2prakingsystem.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class ScheduledService {
    private final PassRepository passRepository;
    private final ParkingRepository parkingRepository;
    private final UserRepository userRepository;




    @Scheduled(fixedRate = 60000)
   public void PassScheduled(){
        List<Pass> pass = passRepository.findPassByState("active");
        if(pass!=null) {
        for (Pass n : pass) {

        if (LocalDateTime.now().compareTo(n.getExpired_date()) > 0) {
            n.setState("expired");
            passRepository.save(n);
            Parking parking = parkingRepository.findParkingById(n.getParkingid());
            parking.setAvailable(true);
            parkingRepository.save(parking);
        }
    }
}

    }

    @Scheduled(fixedRate = 60000)
    public void subscribeScheduled(){
        List<User> users = userRepository.findUserBySubscriptionTrue();

        if(users!=null){
           for(User u : users){
               if(LocalDateTime.now().compareTo(u.getSubscription_expired_date())>0){
                   u.setSubscription(false);
                   u.setSubscription_expired_date(null);
                   userRepository.save(u);
               }
           }
        }
    }
}
