package com.example.smsOTPdemo.controller;

import com.example.smsOTPdemo.model.OtpValidationRequest;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.smsOTPdemo.service.OtpService;


@RestController
public class SmsController {

    @Autowired
    OtpService otpService;

    private static final String ACCOUNT_SID = "AC8d651c84a76cfd55485822cc5c6f62a4";
    public static final String AUTH_TOKEN = "e84c652b6dce460c59d78b56b6fdab4c";
    public static final String fromMobileNo = "+17753053489";

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody OtpValidationRequest userRequest) {
        try{
            String mobileNo = userRequest.getMobileNo();
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message.creator(new PhoneNumber(userRequest.getMobileNo()), new PhoneNumber(fromMobileNo), otpService.generateOtpFor(mobileNo))
                    .create();
            return new ResponseEntity<>("OTP send successfully", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Failed to send OTP "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<?> validateOtp(@RequestBody OtpValidationRequest userRequest) {
        try{
            String mobileNo = userRequest.getMobileNo();
            String userOtp = userRequest.getUserOtp();
            if(otpService.validateOtp(mobileNo,userOtp))
                return new ResponseEntity<>("User successfully Verified", HttpStatus.OK);
            return new ResponseEntity<>("User Verification Failed", HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
