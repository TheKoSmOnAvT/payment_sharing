package com.paymentSystem.paymentSharing.Controller;

import com.paymentSystem.paymentSharing.Exception.InsertException;
import com.paymentSystem.paymentSharing.Exception.UpdateException;
import com.paymentSystem.paymentSharing.Model.ItemsUsersPOJO;
import com.paymentSystem.paymentSharing.Service.ItemUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemUser")
public class ItemUserController {

    private final ItemUserService itemUserService;

    public ItemUserController(ItemUserService itemUserService) {
        this.itemUserService = itemUserService;
    }

    @GetMapping
    public List<ItemsUsersPOJO> getItemsUsers() {
        return itemUserService.getItemsUsers();
    }
    @GetMapping("/{paymentId}")
    public List<ItemsUsersPOJO> getItemsUserByPaymentId(@PathVariable Long paymentId) {
        return itemUserService.getItemsUserByPaymentId(paymentId);
    }

    @PostMapping
    public ItemsUsersPOJO addItemUser(@RequestBody ItemsUsersPOJO item) throws InsertException {
        return itemUserService.addItemUser(item.getPaymentId(), item.getUserId());
    }

    @PutMapping
    public ItemsUsersPOJO updateItemUser(@RequestBody ItemsUsersPOJO item) throws UpdateException {
        return itemUserService.updateItemUser(item);
    }

    @DeleteMapping("/{id}")
    public boolean deleteItemUser(@PathVariable Long id) {
        return itemUserService.deleteItemUser(id);
    }
}
