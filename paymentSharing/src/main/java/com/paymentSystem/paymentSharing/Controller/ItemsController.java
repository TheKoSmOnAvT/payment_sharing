package com.paymentSystem.paymentSharing.Controller;

import com.paymentSystem.paymentSharing.Exception.InsertException;
import com.paymentSystem.paymentSharing.Exception.NotFoundException;
import com.paymentSystem.paymentSharing.Exception.UpdateException;
import com.paymentSystem.paymentSharing.Model.ItemsPaymentPOJO;
import com.paymentSystem.paymentSharing.Model.PaymentPOJO;
import com.paymentSystem.paymentSharing.Service.ItemService;
import com.paymentSystem.paymentSharing.Service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemsController {

    private final ItemService itemService;

    public ItemsController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<ItemsPaymentPOJO> getItems() {
        return itemService.getItems();
    }

    @PostMapping
    public ItemsPaymentPOJO addPayment(@RequestBody ItemsPaymentPOJO payment) throws InsertException {
        return itemService.addItem(payment.getPaymentId(), payment.getName(), payment.getCost());
    }

    @PutMapping
    public ItemsPaymentPOJO updatePayment(@RequestBody ItemsPaymentPOJO paymentPOJO) throws UpdateException {
        return itemService.updateItem(paymentPOJO);
    }

    @DeleteMapping("/{id}")
    public boolean deletePayment(@PathVariable Long id) {
        return itemService.deleteItem(id);
    }
}
