package persistence.repository;

import model.Spectacol;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

@Component
public class SpectacolRepository implements IRepositorySpectacol {
    private static SessionFactory sessionFactory;

    public SpectacolRepository() {
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
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                int count = ((Long) session.createQuery("select count(*) from Spectacol").uniqueResult()).intValue();
                tx.commit();
                return count;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return 0;
    }

    @Override
    public Integer save(Spectacol entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(entity);
                String hql = "SELECt max(id) FROM Spectacol";

                Query query = session.createQuery(hql);
                Integer idEntity = (Integer) query.getResultList().get(0);
                tx.commit();
                return idEntity;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

    @Override
    public Spectacol delete(Integer integer) {
        Spectacol spectacol = this.findOne(integer);
        if (spectacol == null) return null;
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String hql = "DELETE FROM Spectacol " +
                        "WHERE id = :usr_id";
                Query query = session.createQuery(hql);
                query.setParameter("usr_id", integer);
                int result = query.executeUpdate();
                System.out.println("Rows affected: " + result);

                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return spectacol;
    }

    @Override
    public Spectacol update(Integer integer, Spectacol entity) {
        if (this.findOne(integer) == null) return null;
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Query qry = session.createQuery("update Spectacol set denumire=:denumire, " +
                        "descriere =:descriere where id=:id");
                qry.setParameter("denumire", entity.getDenumire());
                qry.setParameter("descriere", entity.getDescriere());
                qry.setParameter("id", integer);

                qry.executeUpdate();
                tx.commit();

            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return entity;
    }

    @Override
    public Spectacol findOne(Integer integer) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                System.out.println("Am intrat1");
                String hql = "FROM Spectacol WHERE id = :id";

                Query query = session.createQuery(hql);
                query.setParameter("id", integer);
                Spectacol spectacol = (Spectacol) query.getResultList().get(0);

                tx.commit();
                return spectacol;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

    @Override
    public Spectacol[] findAll() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Spectacol> from_users =
                        session.createQuery("from Spectacol ", Spectacol.class).
                                list();
                System.out.println(from_users.size() + " show(s) found:");
                tx.commit();
                Spectacol[] itemsArray = new Spectacol[from_users.size()];
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
