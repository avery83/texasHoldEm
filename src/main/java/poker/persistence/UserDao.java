package poker.persistence;

import poker.Entity.*;


import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulawaite on 2/2/16.
 */
public class UserDao {

    private final Logger log = Logger.getLogger(this.getClass());


    public List<Users> getAllUsers() {
        List<Users> users = new ArrayList<>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        users = session.createCriteria(Users.class).list();
        return users;
    }

    public Users getUser(String userName) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Users user = (Users) session.get(Users.class, userName);
        return user;
    }

    public void updateUser(Users user) {

    }

    public void deleteUser(Users user) {
        AbstractDao dao = new AbstractDao(Users.class);
        dao.delete(user);
    }

    public String addUser(Users user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        String userName = null;
        try {
            tx = session.beginTransaction();
            userName = (String) session.save(user);
            session.save(createUserRole(user));
            tx.commit();
            log.info("Added user: " + user + " with id of: " + userName);
            log.debug("Added user: " + user + " with id of: " + userName);
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            log.error(e);
        } finally {
            session.close();
        }
        return userName;
    }

    private UserRoles createUserRole(Users user) {

        UserRoles usersRoles = new UserRoles();
        usersRoles.setUserName(user.getUserName());
        usersRoles.setRole("user");
        return usersRoles;
    }
}
