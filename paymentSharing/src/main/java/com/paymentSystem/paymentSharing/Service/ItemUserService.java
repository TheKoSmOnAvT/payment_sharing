package com.paymentSystem.paymentSharing.Service;

import com.paymentSystem.paymentSharing.Exception.InsertException;
import com.paymentSystem.paymentSharing.Exception.UpdateException;
import com.paymentSystem.paymentSharing.Model.ItemsUsersPOJO;
import com.paymentSystem.paymentSharing.Repository.ItemUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemUserService {

    private final ItemUserRepository itemUserRepository;

    public ItemUserService(ItemUserRepository itemUserRepository) {
        this.itemUserRepository = itemUserRepository;
    }

    public List<ItemsUsersPOJO> getItemsUsers() {
        return itemUserRepository.getItemsUsers();
    }
    public List<ItemsUsersPOJO> getItemsUserByPaymentId(Long id) {
        return itemUserRepository.getItemsUserByPaymentId(id);
    }

    public ItemsUsersPOJO addItemUser(Long paymentId, Long userId) throws InsertException {
        return itemUserRepository.addItemUser(paymentId, userId);
    }

    public ItemsUsersPOJO updateItemUser(ItemsUsersPOJO item) throws UpdateException {
        return itemUserRepository.updateItem(item);
    }

    public boolean deleteItemUser(Long id){
        return itemUserRepository.deleteItem(id);
    }
}
