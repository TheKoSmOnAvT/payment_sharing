package com.paymentSystem.paymentSharing.Controller;

import com.paymentSystem.paymentSharing.Exception.InsertException;
import com.paymentSystem.paymentSharing.Exception.UpdateException;
import com.paymentSystem.paymentSharing.Model.User;
import com.paymentSystem.paymentSharing.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getList() {
        return userService.getUsers();
    }

    @PostMapping
    public User addUser(@RequestBody User user) throws InsertException {
        return userService.addUser(user.getFirstName(), user.getSecondName(), user.getTelegramId() );
    }

    @PutMapping
    public User updateUser(@RequestBody User user) throws UpdateException {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Integer id){
        return userService.deleteUser(id);
    }
}
