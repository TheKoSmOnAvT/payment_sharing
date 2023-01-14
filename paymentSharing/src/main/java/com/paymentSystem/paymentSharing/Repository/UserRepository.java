package com.paymentSystem.paymentSharing.Repository;

import com.paymentSystem.paymentSharing.Exception.InsertException;
import com.paymentSystem.paymentSharing.Exception.UpdateException;
import com.paymentSystem.paymentSharing.Model.User;
import jooq.generated.tables.Users;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public class UserRepository {

    private final DSLContext context;

    public UserRepository(DSLContext context) {
        this.context = context;
    }

    public List<User> getUsers() {
        return context.selectFrom(Users.USERS).fetch().into(User.class);
    }

    public User addUser(String firstName, String secondName, Long telegramId) throws InsertException {
        return context
                .insertInto(Users.USERS)
                .set(Users.USERS.FIRST_NAME, firstName)
                .set(Users.USERS.SECOND_NAME, secondName)
                .set(Users.USERS.TELEGRAM_ID, telegramId)
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new InsertException("Error inserting entity: " + firstName))
                .into(User.class);
    }

    public User updateUser(User user) throws UpdateException {
        return context
                .update(Users.USERS)
                .set(context.newRecord(Users.USERS, user))
                .where(Users.USERS.ID.eq(user.getId()))
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new UpdateException("Error updating entity: " + user.getId()))
                .into(User.class);
    }

    public boolean deleteUser(Integer id){
        return context
                .delete(Users.USERS)
                .where(Users.USERS.ID.eq(id)).execute() == 1;
    }
}
