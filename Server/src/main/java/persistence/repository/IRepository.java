package persistence.repository;

public interface IRepository<ID, T> {
    int size();

    ID save(T entity);

    T delete(ID id);

    T update(ID id, T entity);

    T findOne(ID id);

    T[] findAll();
}