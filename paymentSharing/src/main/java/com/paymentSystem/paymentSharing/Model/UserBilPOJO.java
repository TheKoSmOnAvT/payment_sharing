package com.paymentSystem.paymentSharing.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.Column;

@AllArgsConstructor
@Getter
@Setter
public class UserBilPOJO   {
    Long userId;
    String firstName;
    String secondName;
    Double total;
}
