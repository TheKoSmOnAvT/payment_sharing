package com.paymentSystem.paymentSharing.Controller;


import com.paymentSystem.paymentSharing.Model.HistoryBilPOJO;
import com.paymentSystem.paymentSharing.Service.HistoryBilService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bil")
public class HistoryBilController {
    private final HistoryBilService historyBilService;


    public HistoryBilController(HistoryBilService historyBilService) {
        this.historyBilService = historyBilService;
    }

    @GetMapping("/{paymentId}")
    public List<HistoryBilPOJO> getBils(@PathVariable Long paymentId){
        return historyBilService.getBil(paymentId);
    }
}
