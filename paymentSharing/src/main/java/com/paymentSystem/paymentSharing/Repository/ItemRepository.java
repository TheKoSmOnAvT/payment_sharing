package com.paymentSystem.paymentSharing.Repository;

import com.paymentSystem.paymentSharing.Exception.InsertException;
import com.paymentSystem.paymentSharing.Exception.UpdateException;
import com.paymentSystem.paymentSharing.Model.ItemsPaymentPOJO;
import com.paymentSystem.paymentSharing.Model.ItemsWithUsersPaymentPOJO;
import jooq.generated.tables.ItemsPayment;
import jooq.generated.tables.ItemsUsers;
import org.jooq.DSLContext;
import org.jooq.Records;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jooq.generated.tables.Users.USERS;
import static org.jooq.impl.DSL.multiset;
import static org.jooq.impl.DSL.select;

@Repository
public class ItemRepository {

    private final DSLContext context;

    public ItemRepository(DSLContext context) {
        this.context = context;
    }

    public List<ItemsPaymentPOJO> getItems() {
        return context.selectFrom(ItemsPayment.ITEMS_PAYMENT).fetch().into(ItemsPaymentPOJO.class);
    }

    public List<ItemsPaymentPOJO> getItems(Long idPayment) {
        return context.selectFrom(ItemsPayment.ITEMS_PAYMENT).where(ItemsPayment.ITEMS_PAYMENT.ID_PAYMENT.eq(idPayment)).fetch().into(ItemsPaymentPOJO.class);
    }

    public List<ItemsWithUsersPaymentPOJO> getItemsWithUsers(Long idPayment) {
        return context
                .select(
                        ItemsPayment.ITEMS_PAYMENT.ID,
                        ItemsPayment.ITEMS_PAYMENT.ID_PAYMENT,
                        ItemsPayment.ITEMS_PAYMENT.NAME,
                        ItemsPayment.ITEMS_PAYMENT.COST,
                        multiset(
                                select(
                                        ItemsUsers.ITEMS_USERS
                                )
                                        .from(ItemsUsers.ITEMS_USERS)
                                        .where(ItemsUsers.ITEMS_USERS.ID_ITEM.eq(ItemsPayment.ITEMS_PAYMENT.ID))
                        )
                                .as("users")
                               // .convertFrom(r -> r.map(Records.mapping(Long::new)))
                )
                .from(ItemsPayment.ITEMS_PAYMENT)
                .where(ItemsPayment.ITEMS_PAYMENT.ID_PAYMENT.eq(idPayment))
                .fetch()
                .into(ItemsWithUsersPaymentPOJO.class);
    }


    public ItemsPaymentPOJO addItem(Long paymentId, String name, Double cost) throws InsertException {
        return context
                .insertInto(ItemsPayment.ITEMS_PAYMENT)
                .set(ItemsPayment.ITEMS_PAYMENT.ID_PAYMENT, paymentId)
                .set(ItemsPayment.ITEMS_PAYMENT.NAME, name)
                .set(ItemsPayment.ITEMS_PAYMENT.COST, cost)
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new InsertException("Error inserting entity: " + name))
                .into(ItemsPaymentPOJO.class);
    }

    public ItemsPaymentPOJO updateItem(ItemsPaymentPOJO item) throws UpdateException {
        return context
                .update(ItemsPayment.ITEMS_PAYMENT)
                .set(context.newRecord(ItemsPayment.ITEMS_PAYMENT, item))
                .where(ItemsPayment.ITEMS_PAYMENT.ID.eq(item.getId()))
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new UpdateException("Error updating entity: " + item.getId()))
                .into(ItemsPaymentPOJO.class);
    }

    public boolean deleteItem(Long id) {
        return context
                .delete(ItemsPayment.ITEMS_PAYMENT)
                .where(ItemsPayment.ITEMS_PAYMENT.ID.eq(id)).execute() == 1;
    }
}
