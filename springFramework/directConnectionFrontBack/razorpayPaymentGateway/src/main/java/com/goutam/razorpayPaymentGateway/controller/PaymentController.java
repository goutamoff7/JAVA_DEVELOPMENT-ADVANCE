package com.goutam.razorpayPaymentGateway.controller;

import com.goutam.razorpayPaymentGateway.model.Payments;
import com.goutam.razorpayPaymentGateway.service.PaymentService;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/create-order")
    public String createOrder(@RequestParam int amount) {
        try {
            return paymentService.createOrder(amount);

        } catch (Exception e) {
            String message = e.getMessage();
            System.out.println(message);
            throw new RuntimeException(message);
        }
    }

    @PostMapping("/payment-callback")
    public RedirectView paymentCallback(
            @RequestParam("razorpay_order_id") String razorpayOrderId,
            @RequestParam("razorpay_payment_id") String razorpayPaymentId,
            @RequestParam("razorpay_signature") String razorpaySignature) throws RazorpayException {
        try {
            boolean isValid = paymentService
                    .verifyPayment(razorpayOrderId, razorpayPaymentId, razorpaySignature);
            if (isValid) {
                Payments payments = paymentService.savePaymentDetails(razorpayPaymentId);
                if(payments!=null)
                    return new RedirectView("/success.html?orderId=" + razorpayOrderId);
            }
            return new RedirectView("/failure.html");


        } catch (RazorpayException e) {
            System.err.println("Razorpay Exception during callback " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("General Exception during callback " + e.getMessage());
            throw new RazorpayException("General Exception during callback");
        }
    }

    @PostMapping("/get-key")
    public String getKey() {
        return paymentService.getKey();
    }
}

