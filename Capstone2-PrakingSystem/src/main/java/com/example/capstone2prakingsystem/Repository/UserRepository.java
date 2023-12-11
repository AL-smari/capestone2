package com.example.capstone2prakingsystem.Repository;

import com.example.capstone2prakingsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserById(Integer id);
    User findUserByUsernameAndPassword(String username , String password);

    List<User> findUserBySubscriptionTrue();



}
