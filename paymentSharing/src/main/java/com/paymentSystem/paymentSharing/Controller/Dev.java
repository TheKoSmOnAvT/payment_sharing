package com.paymentSystem.paymentSharing.Controller;

import jooq.generated.tables.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/dev")
public class Dev {

    @GetMapping("/ping")
    public ResponseEntity ping(Users user) {
        return ResponseEntity.ok("pong");
    }
}
