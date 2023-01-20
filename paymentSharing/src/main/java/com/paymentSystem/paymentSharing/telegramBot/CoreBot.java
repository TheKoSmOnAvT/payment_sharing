package com.paymentSystem.paymentSharing.telegramBot;

import com.paymentSystem.paymentSharing.telegramBot.Command.AbstractCommand;
import jakarta.annotation.PostConstruct;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class CoreBot extends TelegramLongPollingBot {

    private static final Logger logger = LoggerFactory.getLogger(CoreBot.class);

    private BotConfig botConfig;
    private final AbstractCommand abstractCommand;


    public CoreBot(BotConfig botConfig, AbstractCommand abstractCommand) {
        this.botConfig = botConfig;
        this.abstractCommand = abstractCommand;
    }


    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        abstractCommand.execute(this, update);
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }


    @PostConstruct
    public void start() {
        logger.info("username: {}, token: {}", botConfig.getBotName(), botConfig.getToken());
    }
}