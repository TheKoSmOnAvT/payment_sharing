package com.paymentSystem.paymentSharing.Controller;

import lombok.AllArgsConstructor;

import javax.persistence.Column;
import java.util.Optional;

@AllArgsConstructor
public class USERS1 {
    @Column(name = "id")
    public Long id;
    @Column(name = "FIRST_NAME")
    public String fnmae;
    @Column(name = "SECOND_NAME")
    public Optional<String> sname;
    @Column(name = "TELEGRAM_ID")
    public Optional<Long> telegram;
}
