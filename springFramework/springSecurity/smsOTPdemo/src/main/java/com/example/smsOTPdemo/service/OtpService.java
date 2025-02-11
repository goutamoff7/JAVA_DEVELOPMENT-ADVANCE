package com.example.smsOTPdemo.service;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {
    private Map<String,String> otpMap = new HashMap<>();

    public String generateOtpFor(String mobileNo) {
        Random random = new Random();
        String otp = String.valueOf(random.nextInt(99999)+100000);
        otpMap.put(mobileNo,otp);
        System.out.println(otpMap);
        return otp;
    }

    public boolean validateOtp(String mobileNo,String userOtp){
        if(otpMap.containsKey(mobileNo))
            return (userOtp.equals(otpMap.get(mobileNo)));
        else
            return false;
    }
}
