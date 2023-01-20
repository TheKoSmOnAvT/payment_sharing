package com.paymentSystem.paymentSharing.Repository;

import com.paymentSystem.paymentSharing.Exception.InsertException;
import com.paymentSystem.paymentSharing.Exception.UpdateException;
import com.paymentSystem.paymentSharing.Model.UserPOJO;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jooq.generated.tables.Users.USERS;

@Repository
public class UserRepository {

    private final DSLContext context;

    public UserRepository(DSLContext context) {
        this.context = context;
    }

    public List<UserPOJO> getUsers() {
        return context.selectFrom(USERS).fetch().into(UserPOJO.class);
    }

    public List<UserPOJO> getUsers(List<Long> usersId) {
        return context.selectFrom(USERS).where(USERS.ID.in(usersId)).fetch().into(UserPOJO.class);
    }

    public UserPOJO getUsers(Long usersId) {
        var user = context.selectFrom(USERS).where(USERS.ID.eq(usersId)).fetchOne();
        if (user == null) {
            return null;
        }
        return user.into(UserPOJO.class);
    }


    public UserPOJO addUser(String firstName, String secondName, Long telegramId) throws InsertException {
        return context
                .insertInto(USERS)
                .set(USERS.FIRST_NAME, firstName)
                .set(USERS.SECOND_NAME, secondName)
                .set(USERS.TELEGRAM_ID, telegramId)
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new InsertException("Error inserting entity: " + firstName))
                .into(UserPOJO.class);
    }

    public UserPOJO updateUser(UserPOJO user) throws UpdateException {
        return context
                .update(USERS)
                .set(context.newRecord(USERS, user))
                .where(USERS.ID.eq(user.getId()))
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new UpdateException("Error updating entity: " + user.getId()))
                .into(UserPOJO.class);
    }

    public boolean deleteUser(Long id) {
        return context
                .delete(USERS)
                .where(USERS.ID.eq(id)).execute() == 1;
    }
}
