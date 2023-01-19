package com.paymentSystem.paymentSharing.Repository;

import com.paymentSystem.paymentSharing.Exception.InsertException;
import com.paymentSystem.paymentSharing.Exception.UpdateException;
import com.paymentSystem.paymentSharing.Model.ItemsUsersPOJO;
import jooq.generated.tables.ItemsUsers;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemUserRepository {

    private final DSLContext context;

    public ItemUserRepository(DSLContext context) {
        this.context = context;
    }

    public List<ItemsUsersPOJO> getItemsUsers() {
        return context.selectFrom(ItemsUsers.ITEMS_USERS).fetch().into(ItemsUsersPOJO.class);
    }

    public List<ItemsUsersPOJO> getItemsUserByPaymentId(Long id) {
        return context.selectFrom(ItemsUsers.ITEMS_USERS).where(ItemsUsers.ITEMS_USERS.ID_ITEM.eq(id)).fetch().into(ItemsUsersPOJO.class);
    }

    public ItemsUsersPOJO addItemUser(Long itemID, Long userId) throws InsertException {
        return context
                .insertInto(jooq.generated.tables.ItemsPayment.ITEMS_PAYMENT)
                .set(ItemsUsers.ITEMS_USERS.ID_ITEM, itemID)
                .set(ItemsUsers.ITEMS_USERS.USER_ID, userId)
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new InsertException("Error inserting entity: " + itemID))
                .into(ItemsUsersPOJO.class);
    }

    public boolean addItemUsers(List<ItemsUsersPOJO> items) throws InsertException {
        return context
                .insertInto(jooq.generated.tables.ItemsPayment.ITEMS_PAYMENT)
                .values(items)
                .execute() == 1;
    }

    public ItemsUsersPOJO updateItem(ItemsUsersPOJO item) throws UpdateException {
        return context
                .update(ItemsUsers.ITEMS_USERS)
                .set(context.newRecord(ItemsUsers.ITEMS_USERS, item))
                .where(ItemsUsers.ITEMS_USERS.ID.eq(item.getId()))
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new UpdateException("Error updating entity: " + item.getId()))
                .into(ItemsUsersPOJO.class);
    }

    public boolean deleteItem(Long id) {
        return context
                .delete(jooq.generated.tables.ItemsPayment.ITEMS_PAYMENT)
                .where(jooq.generated.tables.ItemsPayment.ITEMS_PAYMENT.ID.eq(id)).execute() == 1;
    }
}
