package com.paymentSystem.paymentSharing.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;

@AllArgsConstructor
@Getter
public class ItemsUsersPOJO {
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_PAYMENT")
    private Long paymentId;

    @Column(name = "USER_ID")
    private Long userId;
}
