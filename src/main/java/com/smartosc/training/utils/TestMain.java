package com.smartosc.training.utils;

import com.smartosc.training.dao.UserDAO;
import com.smartosc.training.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Spring-Boot-Hibernate
 *
 * @author Hieupv
 * @created_at 10/08/2020 - 11:34 AM
 * @created_by Hieupv
 * @since 10/08/2020
 */
public class TestMain {
    public static void main(String[] args) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        UserDAO userDAO =  new UserDAO(session);
        session.beginTransaction();

        User user = new User(1L, "3", "33", "323", 1, null);

        userDAO.save(user);

        session.getTransaction().commit();
        System.out.println("Done");
    }
}
