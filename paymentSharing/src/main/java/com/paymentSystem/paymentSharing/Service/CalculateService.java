package com.paymentSystem.paymentSharing.Service;

import com.paymentSystem.paymentSharing.Exception.InsertException;
import com.paymentSystem.paymentSharing.Model.HistoryBilPOJO;
import com.paymentSystem.paymentSharing.Model.ItemsWithUsersPaymentPOJO;
import com.paymentSystem.paymentSharing.Model.UserBilPOJO;
import com.paymentSystem.paymentSharing.Model.UserPOJO;
import com.paymentSystem.paymentSharing.Repository.HistoryBilRepository;
import com.paymentSystem.paymentSharing.Repository.ItemRepository;
import com.paymentSystem.paymentSharing.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CalculateService {

    private final ItemRepository itemRepository;

    private final UserRepository userRepository;

    private final HistoryBilRepository historyBilRepository;

    public CalculateService(ItemRepository itemRepository, UserRepository userRepository, HistoryBilRepository historyBilRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.historyBilRepository = historyBilRepository;
    }

    public List<UserBilPOJO> calculate(Long idPayment) throws InsertException {
        var items = itemRepository.getItemsWithUsers(idPayment);

        var userIds = items.stream().flatMap(x -> x.getUsersId().stream()).distinct().toList();
        var usersBil = userRepository.getUsers(userIds).stream().collect(Collectors.toMap(
                UserPOJO::getId,
                user -> new UserBilPOJO(user.getId(), user.getFirstName(), user.getSecondName(), 0d)
        ));

        var result = calculateBil(usersBil, items);

        saveHistoryBil(idPayment, result);
        return result;
    }

    private List<UserBilPOJO> calculateBil(Map<Long, UserBilPOJO> usersBil, List<ItemsWithUsersPaymentPOJO> items) {
        for (var item : items) {
            Double bilPart = item.getCost() / item.getUsersId().size();
            for (var userId : item.getUsersId()) {
                var bil = usersBil.get(userId);
                bil.setTotal(bil.getTotal() + bilPart);
            }
        }
        return usersBil.values().stream().toList();
    }

    private void saveHistoryBil(Long idPayment, List<UserBilPOJO> bils) throws InsertException {
        var toSave = bils.stream().map(x -> new HistoryBilPOJO(x, idPayment)).toList();
        historyBilRepository.addBils(toSave);
    }
}
