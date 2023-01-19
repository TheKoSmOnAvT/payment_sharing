package com.paymentSystem.paymentSharing.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;


@AllArgsConstructor
@Getter
public class UserPOJO {
    @Column(name = "id")
    private Long id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "SECOND_NAME")
    private String secondName;
    @Column(name = "TELEGRAM_ID")
    @JsonIgnore
    private Long telegramId;
}

