package com.agridence.microservice.Assignment.service;

import com.agridence.microservice.Assignment.Entity.User;
import com.agridence.microservice.Assignment.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {return userRepository.findAll(); }

    public User findUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        User user1 = user.orElse(null);
        return  user1;
    }
}