package com.smartosc.training.dao;

import com.smartosc.training.entities.User;
import lombok.Builder;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

/**
 * Spring-Boot-Hibernate
 *
 * @author Hieupv
 * @created_at 10/08/2020 - 5:00 PM
 * @created_by Hieupv
 * @since 10/08/2020
 */
public class UserDAO extends AbstractCrudDao<User>{
    public UserDAO(Session session) {
        super(session, User.class, "User");
    }

    public static UserDAO build(Session session){
        return new UserDAO(session);
    }
}
