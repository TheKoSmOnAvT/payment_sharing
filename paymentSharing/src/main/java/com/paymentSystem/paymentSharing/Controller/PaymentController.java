package com.paymentSystem.paymentSharing.Controller;

import com.paymentSystem.paymentSharing.Exception.InsertException;
import com.paymentSystem.paymentSharing.Exception.NotFoundException;
import com.paymentSystem.paymentSharing.Exception.UpdateException;
import com.paymentSystem.paymentSharing.Model.PaymentPOJO;
import com.paymentSystem.paymentSharing.Service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<PaymentPOJO> getPayments() {
        return paymentService.getPayments();
    }

    @GetMapping("/{id}")
    public PaymentPOJO getPayment(@PathVariable Long id) throws NotFoundException {
        return paymentService.getPayment(id);
    }

    @PostMapping
    public PaymentPOJO addPayment(@RequestBody PaymentPOJO payment) throws InsertException {
        return paymentService.addPayment(payment);
    }

    @PutMapping
    public PaymentPOJO updatePayment(@RequestBody PaymentPOJO paymentPOJO) throws UpdateException {
        return paymentService.updatePayment(paymentPOJO);
    }

    @DeleteMapping("/{id}")
    public boolean deletePayment(@PathVariable Long id) {
        return paymentService.deletePayment(id);
    }
}
