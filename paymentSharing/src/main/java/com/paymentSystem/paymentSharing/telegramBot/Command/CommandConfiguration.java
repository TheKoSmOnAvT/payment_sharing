package com.paymentSystem.paymentSharing.telegramBot.Command;


import com.paymentSystem.paymentSharing.Service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class CommandConfiguration {

    private final UserService userService;

    public CommandConfiguration(UserService userService) {
        this.userService = userService;
    }


    @Bean
    public AbstractCommand abstractCommand(){
        return AbstractCommand.createChains(new HelloWorldCommand(userService));
    }
}
