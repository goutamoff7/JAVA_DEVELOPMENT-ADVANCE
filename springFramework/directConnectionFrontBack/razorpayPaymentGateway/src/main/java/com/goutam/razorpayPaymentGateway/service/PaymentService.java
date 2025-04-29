package com.goutam.razorpayPaymentGateway.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Value("${razorpay.api.key_id}")
    private String apiKeyId;

    @Value("${razorpay.api.key_secret}")
    private String apiKeySecret;

    public String createOrder(int amount,
                              String currency,
                              String receipt) throws RazorpayException {

        RazorpayClient razorpayClient = new RazorpayClient(apiKeyId,apiKeySecret);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount",amount*100);
        orderRequest.put("currency",currency);
        orderRequest.put("receipt",receipt);

        Order order = razorpayClient.orders.create(orderRequest);

        return  order.toString();
    }

}
