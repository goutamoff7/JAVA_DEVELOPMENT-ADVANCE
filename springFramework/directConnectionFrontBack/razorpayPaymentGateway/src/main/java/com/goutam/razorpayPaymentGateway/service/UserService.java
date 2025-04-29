package com.goutam.razorpayPaymentGateway.service;

import com.goutam.razorpayPaymentGateway.model.User;
import com.goutam.razorpayPaymentGateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findByPhoneNumber(String phoneNumber){
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
