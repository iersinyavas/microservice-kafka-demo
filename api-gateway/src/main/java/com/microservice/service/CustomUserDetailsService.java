package com.microservice.service;

import com.microservice.entity.User;
import com.microservice.repository.UserRepository;
import com.microservice.util.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        CustomUserDetails customUserDetails = null;
        if (user != null){
            customUserDetails = new CustomUserDetails();
            customUserDetails.setUser(user);
        }else{
            throw new UsernameNotFoundException("User not exist with name : "+username);
        }
        return customUserDetails;
    }
}
