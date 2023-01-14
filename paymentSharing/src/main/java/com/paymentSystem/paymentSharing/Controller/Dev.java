package com.paymentSystem.paymentSharing.Controller;

import jooq.generated.tables.Users;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.meta.postgres.information_schema.tables.Tables;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Column;
import java.util.Optional;

@RestController
@RequestMapping("/test")
public class Dev {

    private final DSLContext context;

    public Dev(DSLContext context) {
        this.context = context;
    }

    @GetMapping
    public ResponseEntity ping(Users user) {
        var a = context.insertInto(Users.USERS)
                .set(Users.USERS.TELEGRAM_ID, 123L)
                .set(Users.USERS.FIRST_NAME, "NAME")
                .returning()
                .fetchOne();

        var users = context.select().from(Users.USERS);


        //context.selectFrom(Tables)
        return ResponseEntity.ok(users.fetchInto(USERS1.class));
    }
}
