package persistence.repository;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;
@Component
public class UserRepository implements IRepositoryUser {

   // private static final Logger logger = LogManager.getLogger();
    private static SessionFactory sessionFactory;

    public UserRepository() {
    }

    public void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            System.out.println("Exceptie " + e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Override
    public int size() {
//        logger.traceEntry();
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                int count = ((Long) session.createQuery("select count(*) from User").uniqueResult()).intValue();
                tx.commit();
                return count;
            } catch (RuntimeException ex) {
//                logger.error(ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return 0;
    }

    @Override
    public String save(User entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(entity);
                tx.commit();
                return entity.getID();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

    @Override
    public User delete(String s) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String hql = "DELETE FROM User " +
                        "WHERE username like :usr_id";
                Query query = session.createQuery(hql);
                query.setParameter("usr_id", s);
                int result = query.executeUpdate();
                System.out.println("Rows affected: " + result);

                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

    @Override
    public User update(String s, User entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Query qry = session.createQuery("update User set hash=:parola, tip =:t where username=:id");
                qry.setParameter("parola", entity.getHash());
                qry.setParameter("t", entity.getTip());
                qry.setParameter("id", s);

                qry.executeUpdate();
                tx.commit();

            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

    public User[] findOneByTip(String tip) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String hql = "FROM User WHERE tip =:tipUser";

                Query query = session.createQuery(hql);
                query.setParameter("tipUser", tip);
                List us = query.getResultList();
                User[] users = new User[us.size()];
                int index = 0;
                for (Object user : us
                ) {
                    users[index] = (User) user;
                    index += 1;
                }
                tx.commit();
                return users;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
                System.out.println("---------------------- ERROR ---------------------");
                System.out.println(ex);
            }
        }
        return null;
    }

    @Override
    public User findOne(String s) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                System.out.println("Am intrat1");
                String hql = "FROM User WHERE username = :id";

                Query query = session.createQuery(hql);
                query.setParameter("id", s);
                User user = (User) query.getResultList().get(0);

                tx.commit();
                return user;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
                System.out.println("---------------------- ERROR ---------------------");
                System.out.println(ex);
            }
        }
        return null;
    }

    @Override
    public User[] findAll() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<User> from_users =
                        session.createQuery("from User", User.class).
                                //  setFirstResult(1).setMaxResults(5).
                                        list();
                System.out.println(from_users.size() + " user(s) found:");
                tx.commit();
                User[] itemsArray = new User[from_users.size()];
                itemsArray = from_users.toArray(itemsArray);
                return itemsArray;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();

            }
        }
        return null;
    }
}