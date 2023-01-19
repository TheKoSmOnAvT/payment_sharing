package com.paymentSystem.paymentSharing.Controller;

import com.paymentSystem.paymentSharing.Exception.InsertException;
import com.paymentSystem.paymentSharing.Model.UserBilPOJO;
import com.paymentSystem.paymentSharing.Service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/calculate")
public class CalculateController {

    private final CalculateService calculateService;

    public CalculateController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @GetMapping("/{idPayment}")
    public List<UserBilPOJO> calculate(@PathVariable Long idPayment) throws InsertException {
        return calculateService.calculate(idPayment);
    }
}
