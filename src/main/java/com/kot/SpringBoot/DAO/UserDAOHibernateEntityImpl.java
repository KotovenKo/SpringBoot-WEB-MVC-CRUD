package com.kot.SpringBoot.DAO;
import com.kot.SpringBoot.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public class UserDAOHibernateEntityImpl implements UserDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getNumberOfUsers(int counter) {
        List<User> query = getUsers();
        if (counter > query.size()) {
            counter = query.size();
        }
        return query.subList(0, counter);
    }

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(long id, User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(long id) {
        entityManager.createQuery("delete from User where id=:id").setParameter("id", id)
                .executeUpdate();
    }
}
