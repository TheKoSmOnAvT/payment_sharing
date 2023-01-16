package com.paymentSystem.paymentSharing.Service;

import com.paymentSystem.paymentSharing.Exception.InsertException;
import com.paymentSystem.paymentSharing.Exception.UpdateException;
import com.paymentSystem.paymentSharing.Model.ItemsPaymentPOJO;
import com.paymentSystem.paymentSharing.Repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {


    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    public List<ItemsPaymentPOJO> getItems() {
        return itemRepository.getItems();
    }

    public ItemsPaymentPOJO addItem(Long paymentId, String name, Double cost) throws InsertException {
        return itemRepository.addItem(paymentId, name, cost);
    }

    public ItemsPaymentPOJO updateItem(ItemsPaymentPOJO item) throws UpdateException {
        return itemRepository.updateItem(item);
    }

    public boolean deleteItem(Long id){
        return itemRepository
                .deleteItem(id);
    }
}
