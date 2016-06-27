package com.aleksandrbogomolov.vote_restaurant.repository.restaurant.jpa;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Menu;
import com.aleksandrbogomolov.vote_restaurant.repository.restaurant.MenuRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMenuRepositoryImpl implements MenuRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Menu save(Menu entity) {
        if (entity.isNew()) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Menu.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public Menu get(int id) {
        return em.find(Menu.class, id);
    }

    @Override
    public List<Menu> getAll() {
        return em.createNamedQuery(Menu.GET_ALL, Menu.class).getResultList();
    }

    @Override
    @Transactional
    public boolean clearAll() {
        return em.createNamedQuery(Menu.CLEAR_ALL).executeUpdate() != 0;
    }
}
