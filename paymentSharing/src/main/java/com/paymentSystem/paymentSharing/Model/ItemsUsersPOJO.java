package com.paymentSystem.paymentSharing.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;

@AllArgsConstructor
@Getter
public class ItemsUsersPOJO {
    @Column(name = "ID")
    private Long id;

    @Column(name = "id_item")
    private Long id_item;

    @Column(name = "USER_ID")
    private Long userId;
}
