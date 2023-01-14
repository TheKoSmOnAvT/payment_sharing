package com.paymentSystem.paymentSharing.Service;

import com.paymentSystem.paymentSharing.Exception.InsertException;
import com.paymentSystem.paymentSharing.Exception.UpdateException;
import com.paymentSystem.paymentSharing.Model.User;
import com.paymentSystem.paymentSharing.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User addUser(String firstName, String secondName, Long telegramId) throws InsertException {
       return userRepository.addUser(firstName,secondName,telegramId);
    }

    public User updateUser(User user) throws UpdateException {
        return userRepository.updateUser(user);
    }

    public boolean deleteUser(Integer id) {
        return userRepository.deleteUser(id);
    }
}
