package persistence.repository;

import model.Rezervare;
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
public class RezervareRepository implements IRepositoryRezervare {
    private static SessionFactory sessionFactory;

    public RezervareRepository() {
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
                int count = ((Long) session.createQuery("select count(*) from Rezervare ").uniqueResult()).intValue();
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
    public Integer save(Rezervare entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(entity);
                String hql = "SELECt max(id) FROM Rezervare ";

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
    public Rezervare delete(Integer integer) {
        Rezervare rezervare = this.findOne(integer);
        if (rezervare == null) return null;
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String hql = "DELETE FROM Rezervare " +
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
        return rezervare;
    }

    @Override
    public Rezervare update(Integer integer, Rezervare entity) {
        if (this.findOne(integer) == null) return null;
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Query qry = session.createQuery("update Rezervare set pozitieX=:pozX, " +
                        "pozitieY =:pozY, numar=:nr, pret =: pr where id=:id");
                qry.setParameter("pozX", entity.getPozitieX());
                qry.setParameter("pozY", entity.getPozitieY());
                qry.setParameter("nr", entity.getNumar());
                qry.setParameter("pr", entity.getPret());

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
    public Rezervare findOne(Integer integer) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                System.out.println("Am intrat1");
                String hql = "FROM Rezervare WHERE id = :id";

                Query query = session.createQuery(hql);
                query.setParameter("id", integer);
                Rezervare rezervare = (Rezervare) query.getResultList().get(0);

                tx.commit();
                return rezervare;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

    @Override
    public Rezervare[] findAll() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Rezervare> from_users =
                        session.createQuery("from Rezervare ", Rezervare.class).
                                list();
                System.out.println(from_users.size() + " reservation(s) found:");
                tx.commit();
                Rezervare[] itemsArray = new Rezervare[from_users.size()];
                itemsArray = from_users.toArray(itemsArray);
                return itemsArray;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

    public Rezervare[] findAll(Spectacol spectacol) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String hql = "FROM Rezervare WHERE spectacol = :id";

                Query query = session.createQuery(hql);
                query.setParameter("id", spectacol.getID());
                List rezervareList = query.getResultList();
                Rezervare[] itemsArray = new Rezervare[rezervareList.size()];
                itemsArray = (Rezervare[]) rezervareList.toArray(itemsArray);
                System.out.println(rezervareList.size() + " reservation(s) found:");
                tx.commit();
                return itemsArray;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

}
