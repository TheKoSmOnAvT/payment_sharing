package com.paymentSystem.paymentSharing.Controller;


import com.paymentSystem.paymentSharing.Exception.NotFoundException;
import com.paymentSystem.paymentSharing.Service.HistoryBilService;
import com.paymentSystem.paymentSharing.telegramBot.CoreBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RestController
@RequestMapping("/telegram")
public class TelegramNotificationController {

    private final HistoryBilService historyBilService;

    public TelegramNotificationController(HistoryBilService historyBilService) {
        this.historyBilService = historyBilService;
    }

    @GetMapping("/{idPayment}")
    public boolean test(@PathVariable Long idPayment) throws TelegramApiException, NotFoundException {
        return historyBilService.sendPayment(idPayment);
    }

}
