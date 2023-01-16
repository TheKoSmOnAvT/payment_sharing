package com.paymentSystem.paymentSharing.Service;

import com.paymentSystem.paymentSharing.Exception.InsertException;
import com.paymentSystem.paymentSharing.Exception.NotFoundException;
import com.paymentSystem.paymentSharing.Exception.UpdateException;
import com.paymentSystem.paymentSharing.Model.PaymentPOJO;
import com.paymentSystem.paymentSharing.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    public List<PaymentPOJO> getPayments() {
        return paymentRepository.getPayments();
    }

    public PaymentPOJO getPayment(Long id) throws NotFoundException {
        return paymentRepository.getPayment(id);
    }

    public PaymentPOJO addPayment(PaymentPOJO payment) throws InsertException {
       return paymentRepository.addPayment(payment);
    }

    public boolean deletePayment(Long id) {
        return paymentRepository.deletePayment(id);
    }

    public PaymentPOJO updatePayment(PaymentPOJO paymentPOJO) throws UpdateException {
        return paymentRepository.updatePayment(paymentPOJO);
    }
}
