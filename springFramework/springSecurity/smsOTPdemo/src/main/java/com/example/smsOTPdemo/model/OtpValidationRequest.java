package com.example.smsOTPdemo.model;

import org.springframework.stereotype.Component;

@Component
public class OtpValidationRequest {
    private String mobileNo;
    private String userOtp;

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getUserOtp() {
        return userOtp;
    }

    public void setUserOtp(String userOtp) {
        this.userOtp = userOtp;
    }
}


