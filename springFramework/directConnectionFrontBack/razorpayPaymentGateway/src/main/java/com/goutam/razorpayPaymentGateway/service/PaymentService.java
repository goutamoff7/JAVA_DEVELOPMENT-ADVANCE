package com.goutam.razorpayPaymentGateway.service;

import com.goutam.razorpayPaymentGateway.model.Payments;
import com.goutam.razorpayPaymentGateway.model.User;
import com.goutam.razorpayPaymentGateway.repository.PaymentsRepository;
import com.razorpay.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Value("${razorpay.api.key_id}")
    private String apiKeyId;

    @Value("${razorpay.api.key_secret}")
    private String apiKeySecret;

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    UserService userService;

    public String createOrder(int amount) throws RazorpayException {

        RazorpayClient razorpayClient = new RazorpayClient(apiKeyId, apiKeySecret);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount * 100);
        orderRequest.put("currency", "INR");

        Order order = razorpayClient.orders.create(orderRequest);

        System.out.println(order);
        return order.toString();
    }

    public String getKey() {
        return apiKeyId;
    }

    public String getSecret() {
        return apiKeySecret;
    }

    public boolean verifyPayment(String razorpayOrderId,
                                 String razorpayPaymentId,
                                 String razorpaySignature) throws RazorpayException {
        String keySecret = getSecret();
        String signature = razorpayOrderId + "|" + razorpayPaymentId;
        return Utils.verifySignature(signature, razorpaySignature, keySecret);
    }

    public Payments savePaymentDetails(String paymentId) throws RazorpayException {
        RazorpayClient razorpayClient = new RazorpayClient(apiKeyId, apiKeySecret);
        Payment payment = razorpayClient.payments.fetch(paymentId);

        String phoneNumber = (String)payment.get("contact");
        User user = userService.findByPhoneNumber(phoneNumber);

        Payments payments = new Payments();
        payments.setOrderId((String)payment.get("order_id"));
        payments.setPaymentId(paymentId);
        payments.setMethod((String)payment.get("method"));
        payments.setAmount(payment.get("amount").toString());
        payments.setCurrency((String)payment.get("currency"));
        payments.setStatus((String)payment.get("status"));
        payments.setEmail((String)payment.get("email"));
        payments.setContact(phoneNumber);
        payments.setCreatedAt(payment.get("created_at").toString());

        payments.setUser(user);

        return paymentsRepository.save(payments);
    }
}
