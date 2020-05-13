package com.microservice.controller;

import com.microservice.entity.User;
import com.microservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/")
public class UserRegistryController {

    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "/registry")
    public ResponseEntity<User> registry(@RequestBody User user){
        String password = user.getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encryptPassword);
        userRepository.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<String> get(){
        return new ResponseEntity<String>("Selam", HttpStatus.OK);
    }

}