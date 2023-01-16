package com.paymentSystem.paymentSharing.Model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;


//@Builder
@Getter
@AllArgsConstructor
public class PaymentPOJO {
    @Column(name = "ID")
    public Long id;

    @Column(name = "NAME")
    @NotNull
    public String name;

    @Column(name = "user_paid")
    public Long userPaid;

    @Column(name = "datatime")
    public Instant dateTime;

    @Column(name = "ItemsPayment")
    public List<ItemsPaymentPOJO> items;

//    public PaymentPOJO() {
//    }
//
//
//    public PaymentPOJO(Long id, String name, Long userPaidId, Instant dateTime) {
//        this.id = id;
//        this.name = name;
//        this.userPaidId = userPaidId;
//        this.dateTime = dateTime;
//    }
//
//
//    public PaymentPOJO(Long id, String name, Long userPaidId, LocalDateTime dateTime) {
//        this.id = id;
//        this.name = name;
//        this.userPaidId = userPaidId;
//        this.dateTime = dateTime.toInstant(ZoneOffset.UTC);
//    }
//
//
//    public PaymentPOJO(String name, Long userPaidId, Instant dateTime) {
//        this.name = name;
//        this.userPaidId = userPaidId;
//        this.dateTime = dateTime;
//    }
}
