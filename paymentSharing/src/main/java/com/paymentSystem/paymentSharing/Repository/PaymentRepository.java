package com.paymentSystem.paymentSharing.Repository;

import com.paymentSystem.paymentSharing.Exception.InsertException;
import com.paymentSystem.paymentSharing.Exception.NotFoundException;
import com.paymentSystem.paymentSharing.Exception.UpdateException;
import com.paymentSystem.paymentSharing.Model.ItemsPaymentPOJO;
import com.paymentSystem.paymentSharing.Model.PaymentPOJO;
import jooq.generated.tables.ItemsPayment;
import jooq.generated.tables.Payment;
import org.jooq.*;
import org.jooq.Record;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static jooq.generated.tables.Users.USERS;
import static org.jooq.impl.DSL.*;

@Repository
public class PaymentRepository {

    private final DSLContext context;

    public PaymentRepository(DSLContext context) {
        this.context = context;
    }

    public List<PaymentPOJO> getPayments() {
        var payments = context
                .select(
                        Payment.PAYMENT.ID,
                        Payment.PAYMENT.NAME,
                        Payment.PAYMENT.USER_PAID,
                        Payment.PAYMENT.DATETIME,
                        multiset(
                                select(
                                        ItemsPayment.ITEMS_PAYMENT.ID,
                                        ItemsPayment.ITEMS_PAYMENT.ID_PAYMENT,
                                        ItemsPayment.ITEMS_PAYMENT.NAME,
                                        ItemsPayment.ITEMS_PAYMENT.COST
                                )
                                        .from(ItemsPayment.ITEMS_PAYMENT)
                                        .where(ItemsPayment.ITEMS_PAYMENT.ID_PAYMENT.eq(Payment.PAYMENT.ID))
                        )
                                .as("ItemsPayment")
                                .convertFrom(r -> r.map(Records.mapping(ItemsPaymentPOJO::new)))
                )
                .from(Payment.PAYMENT)
                .fetch();
        return payments.into(PaymentPOJO.class);
    }

    public PaymentPOJO addPayment(PaymentPOJO payment) throws InsertException {
        return context
                .insertInto(Payment.PAYMENT)
                .set(Payment.PAYMENT.NAME, payment.getName())
                .set(Payment.PAYMENT.USER_PAID, payment.getUserPaid())
                .set(Payment.PAYMENT.DATETIME, LocalDateTime.ofInstant(payment.getDateTime(), ZoneOffset.UTC))
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new InsertException("Error inserting entity: " + payment.getName()))
                .into(PaymentPOJO.class);
    }

    public PaymentPOJO updatePayment(PaymentPOJO payment) throws UpdateException {
        return context
                .update(Payment.PAYMENT)
                .set(context.newRecord(Payment.PAYMENT, payment))
                .where(Payment.PAYMENT.ID.eq(payment.getId()))
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new UpdateException("Error updating entity: " + payment.getId()))
                .into(PaymentPOJO.class);
    }

    public boolean deleteUser(Long id) {
        return context
                .delete(Payment.PAYMENT)
                .where(Payment.PAYMENT.ID.eq(id)).execute() == 1;
    }

    public PaymentPOJO getPayment(Long id) throws NotFoundException {
        var payment = context
                .select(
                        Payment.PAYMENT.ID,
                        Payment.PAYMENT.NAME,
                        Payment.PAYMENT.USER_PAID,
                        Payment.PAYMENT.DATETIME,
                        multiset(
                                select(
                                        ItemsPayment.ITEMS_PAYMENT.ID,
                                        ItemsPayment.ITEMS_PAYMENT.ID_PAYMENT,
                                        ItemsPayment.ITEMS_PAYMENT.NAME,
                                        ItemsPayment.ITEMS_PAYMENT.COST
                                )
                                        .from(ItemsPayment.ITEMS_PAYMENT)
                                        .where(ItemsPayment.ITEMS_PAYMENT.ID_PAYMENT.eq(Payment.PAYMENT.ID))
                        )
                                .as("ItemsPayment")
                                .convertFrom(r -> r.map(Records.mapping(ItemsPaymentPOJO::new)))
                )
                .from(Payment.PAYMENT)
                .where(Payment.PAYMENT.ID.eq(id))
                .fetchOne();
        if (payment == null) {
            throw new NotFoundException();
        }

        return payment.into(PaymentPOJO.class);
    }

    public boolean deletePayment(Long id) {
        return context
                .delete(Payment.PAYMENT)
                .where(Payment.PAYMENT.ID.eq(id)).execute() == 1;
    }
}
