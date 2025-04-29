package com.goutam.razorpayPaymentGateway.repository;

import com.goutam.razorpayPaymentGateway.model.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments,Integer> {
}
