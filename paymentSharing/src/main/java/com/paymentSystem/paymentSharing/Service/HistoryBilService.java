package com.paymentSystem.paymentSharing.Service;

import com.paymentSystem.paymentSharing.Exception.NotFoundException;
import com.paymentSystem.paymentSharing.Model.HistoryBilPOJO;
import com.paymentSystem.paymentSharing.Model.PaymentPOJO;
import com.paymentSystem.paymentSharing.Model.UserPOJO;
import com.paymentSystem.paymentSharing.Repository.HistoryBilRepository;
import com.paymentSystem.paymentSharing.telegramBot.CoreBot;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HistoryBilService {

    private final PaymentService paymentService;
    private final HistoryBilRepository historyBilRepository;
    private final UserService userService;
    private final CoreBot coreBot;


    public HistoryBilService(PaymentService paymentService, HistoryBilRepository historyBilRepository, UserService userService, CoreBot coreBot) {
        this.paymentService = paymentService;
        this.historyBilRepository = historyBilRepository;
        this.userService = userService;
        this.coreBot = coreBot;
    }

    public List<HistoryBilPOJO> getBil(Long paymentId) {
        return historyBilRepository.getBil(paymentId);
    }


    public boolean sendPayment(Long idPayment) throws TelegramApiException, NotFoundException {
        var payment = paymentService.getPayment(idPayment);

        var bils = historyBilRepository.getBil(idPayment);

        var usersIds = bils.stream().map(HistoryBilPOJO::getUserId).collect(Collectors.toList());
        usersIds.add(payment.getUserPaid());

        var users = userService.getUsers(usersIds)
                .stream()
                .collect(Collectors.toMap(UserPOJO::getId, x -> x));


        for (var bil : bils) {
            var message = createTextMessage(payment, bil, users);
            sendMessage(users.get(bil.getUserId()).getTelegramId(), message);
        }

        return true;
    }

    void sendMessage(Long telegramId, String msg) throws TelegramApiException {
        coreBot.execute(SendMessage.builder()
                .chatId(telegramId)
                .text(msg)
                .build());
    }

    private String createTextMessage(PaymentPOJO payment, HistoryBilPOJO bil, Map<Long,UserPOJO> users) {
        var payTo = users.get(payment.getUserPaid());
        var payFrom = users.get(bil.getUserId());

        var payToName = "";

        if (payTo.getFirstName() != null) {
            payToName = payToName + " " + payTo.getFirstName();
        }
        if (payTo.getSecondName() != null) {
            payToName = payToName + " " + payTo.getSecondName();
        }

        var rubles = bil.getBil()/100;

        return "Человек с именем " + payFrom.getFirstName() + "! Тебе нужно отправить другому кожаному с именем " + payToName + " на сумму: " + String.format("%.2f", rubles);
    }
}
