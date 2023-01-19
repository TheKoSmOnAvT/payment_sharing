package com.paymentSystem.paymentSharing.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;


@AllArgsConstructor
@Getter
public class HistoryBilPOJO {
    @Column(name = "id")
    private Long id;
    @Column(name = "ID_PAYMENT")
    private Long idPayment;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "BIL")
    @JsonIgnore
    private Double bil;


    public HistoryBilPOJO(UserBilPOJO user, Long idPayment){
        this.idPayment = idPayment;
        this.userId = user.getUserId();
        this.bil = user.getTotal();
    }
}
