package com.paymentSystem.paymentSharing.Service;

import com.paymentSystem.paymentSharing.Exception.InsertException;
import com.paymentSystem.paymentSharing.Exception.UpdateException;
import com.paymentSystem.paymentSharing.Model.UserPOJO;
import com.paymentSystem.paymentSharing.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

import static jooq.generated.tables.Users.USERS;

@Service
public class UserService {

    private final UserRepository userRepository;

    public boolean registrationUser(Update update) throws TelegramApiException, InsertException {
        var user = update.getMessage().getFrom();
        if (userRepository.getUsers(user.getId()) != null) {
            return false;
        }
        var result = userRepository.addUser(user.getFirstName(), user.getLastName(), user.getId());
        return result != null;
    }


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserPOJO> getUsers() {
        return userRepository.getUsers();
    }

    public List<UserPOJO> getUsers(List<Long> usersId) {
        return userRepository.getUsers(usersId);
    }

    public UserPOJO addUser(String firstName, String secondName, Long telegramId) throws InsertException {
        return userRepository.addUser(firstName, secondName, telegramId);
    }

    public UserPOJO updateUser(UserPOJO user) throws UpdateException {
        return userRepository.updateUser(user);
    }

    public boolean deleteUser(Long id) {
        return userRepository.deleteUser(id);
    }
}
