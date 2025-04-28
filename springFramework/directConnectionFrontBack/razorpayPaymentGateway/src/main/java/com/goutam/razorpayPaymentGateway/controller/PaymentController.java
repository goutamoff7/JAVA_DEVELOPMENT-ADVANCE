package com.goutam.razorpayPaymentGateway.controller;

import com.goutam.razorpayPaymentGateway.model.PaymentDesc;
import com.goutam.razorpayPaymentGateway.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/create-order")
    public String createOrder(@RequestBody PaymentDesc paymentDesc) {
        try {
            return paymentService.createOrder(
                    paymentDesc.getAmount(),
                    paymentDesc.getCurrency(),
                    paymentDesc.getReceipt());

        } catch (Exception e) {
            String message = e.getMessage();
            System.out.println(message);
            throw new RuntimeException(message);
        }
    }
}

