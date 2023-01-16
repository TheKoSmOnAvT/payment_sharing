package com.paymentSystem.paymentSharing.Service;

import com.paymentSystem.paymentSharing.Exception.InsertException;
import com.paymentSystem.paymentSharing.Exception.UpdateException;
import com.paymentSystem.paymentSharing.Model.UserPOJO;
import com.paymentSystem.paymentSharing.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserPOJO> getUsers() {
        return userRepository.getUsers();
    }

    public UserPOJO addUser(String firstName, String secondName, Long telegramId) throws InsertException {
       return userRepository.addUser(firstName,secondName,telegramId);
    }

    public UserPOJO updateUser(UserPOJO user) throws UpdateException {
        return userRepository.updateUser(user);
    }

    public boolean deleteUser(Long id) {
        return userRepository.deleteUser(id);
    }
}
