package sujalmandal.torncityservicesclub.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import sujalmandal.torncityservicesclub.models.Payment;

public interface PaymentRepository extends CrudRepository<Payment,String>{

    Optional<Payment> findByIdAndFromPlayerId(String paymentId, String senderId);
    Optional<Payment> findByIdAndToPlayerId(String paymentId, String toPlayerId);
}