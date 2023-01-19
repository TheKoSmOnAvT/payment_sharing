package com.paymentSystem.paymentSharing.Repository;

import com.paymentSystem.paymentSharing.Exception.InsertException;
import com.paymentSystem.paymentSharing.Exception.UpdateException;
import com.paymentSystem.paymentSharing.Model.HistoryBilPOJO;
import com.paymentSystem.paymentSharing.Model.ItemsPaymentPOJO;
import jooq.generated.tables.HistoryBil;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HistoryBilRepository {
    private final DSLContext context;

    public HistoryBilRepository(DSLContext context) {
        this.context = context;
    }


    public List<HistoryBilPOJO> getBil() {
        return context.selectFrom(HistoryBil.HISTORY_BIL).fetch().into(HistoryBilPOJO.class);
    }

    public List<HistoryBilPOJO> getBil(Long paymentId) {
        return context.selectFrom(HistoryBil.HISTORY_BIL)
                .where(HistoryBil.HISTORY_BIL.ID_PAYMENT.eq(paymentId))
                .fetch()
                .into(HistoryBilPOJO.class);
    }




    public HistoryBilPOJO addBil(Long paymentId, Long userId, Double bil) throws InsertException {
        return context
                .insertInto(HistoryBil.HISTORY_BIL)
                .set(HistoryBil.HISTORY_BIL.ID_PAYMENT, paymentId)
                .set(HistoryBil.HISTORY_BIL.USER_ID, userId)
                .set(HistoryBil.HISTORY_BIL.BIL, bil)
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new InsertException("Error inserting entity: " + paymentId))
                .into(HistoryBilPOJO.class);
    }

    public boolean addBils(List<HistoryBilPOJO> bils) throws InsertException {
        return context
                .insertInto(HistoryBil.HISTORY_BIL)
                .values(bils)
                .execute() == 1;
    }


    public HistoryBilPOJO updateBill(HistoryBilPOJO item) throws UpdateException {
        return context
                .update(HistoryBil.HISTORY_BIL)
                .set(context.newRecord(HistoryBil.HISTORY_BIL, item))
                .where(HistoryBil.HISTORY_BIL.ID.eq(item.getId()))
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new UpdateException("Error updating entity: " + item.getId()))
                .into(HistoryBilPOJO.class);
    }

    public boolean deleteItem(Long id) {
        return context
                .delete(HistoryBil.HISTORY_BIL)
                .where(HistoryBil.HISTORY_BIL.ID.eq(id)).execute() == 1;
    }
}
