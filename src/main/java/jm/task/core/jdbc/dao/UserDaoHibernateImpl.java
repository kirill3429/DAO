package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    SessionFactory factory;

    public UserDaoHibernateImpl() {
        factory = Util.getSessionFactory();
    }


    @Override
    public void createUsersTable() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS `users` (\n" +
                    "  `id` int NOT NULL AUTO_INCREMENT,\n" +
                    "  `first_name` varchar(45) NOT NULL,\n" +
                    "  `last_name` varchar(45) NOT NULL,\n" +
                    "  `age` int NOT NULL,\n" +
                    "  PRIMARY KEY (`id`))").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(new User(name,lastName,age));
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("FROM User", User.class).getResultList();
            session.getTransaction().commit();
            return users;
        }

    }

    @Override
    public void cleanUsersTable() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        }

    }
}
