package com.paymentSystem.paymentSharing.Service;

import com.paymentSystem.paymentSharing.Exception.InsertException;
import com.paymentSystem.paymentSharing.Exception.UpdateException;
import com.paymentSystem.paymentSharing.Model.HistoryBilPOJO;
import com.paymentSystem.paymentSharing.Repository.HistoryBilRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryBilService {

    private final HistoryBilRepository historyBilRepository;

    public HistoryBilService(HistoryBilRepository historyBilRepository) {
        this.historyBilRepository = historyBilRepository;
    }

    public List<HistoryBilPOJO> getBil(Long paymentId) {
        return historyBilRepository.getBil(paymentId);
    }





}
