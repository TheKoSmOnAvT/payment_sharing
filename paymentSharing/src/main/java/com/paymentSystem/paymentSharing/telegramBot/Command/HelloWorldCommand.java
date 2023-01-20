package com.paymentSystem.paymentSharing.telegramBot.Command;

import com.paymentSystem.paymentSharing.Exception.InsertException;
import com.paymentSystem.paymentSharing.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class HelloWorldCommand extends AbstractCommand {

    private final UserService userService;

    public HelloWorldCommand(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void execute(AbsSender sender, Update update) throws TelegramApiException, InsertException {
        if (update.getMessage().getText().startsWith("/start")) {

            String text = "";
            var result = userService.registrationUser(update);
            if (result) {
                text = "Велком ту зе клаб.";
            } else {
                text = "Вы уже есть в системе.";
            }

            sender.execute(SendMessage.builder()
                    .chatId(update.getMessage().getChatId())
                    .text(text)
                    .build());
        } else {
            next(sender, update);
        }
    }
}
