package com.paymentSystem.paymentSharing.telegramBot.Command;

import com.paymentSystem.paymentSharing.Exception.InsertException;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class AbstractCommand {
    private AbstractCommand next;

    public static AbstractCommand createChains(AbstractCommand first, AbstractCommand... next) {
        var head = first;
        for (var command : next) {
            first.next = command;
            first = first.next;
        }
        return head;
    }

    public abstract void execute(AbsSender sender, Update update) throws TelegramApiException, InsertException;

    public void next(AbsSender sender, Update update) throws TelegramApiException, InsertException {
        if(next != null){
            this.next.execute(sender, update);
        }
    }

}
