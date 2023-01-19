package com.paymentSystem.paymentSharing.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import java.util.List;

@AllArgsConstructor
@Getter
public class ItemsWithUsersPaymentPOJO {
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_PAYMENT")
    private Long paymentId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COST")
    private Double cost;

    @Column(name = "users")
    private List<Long> usersId;
}
