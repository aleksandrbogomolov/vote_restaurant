package com.aleksandrbogomolov.vote_restaurant.repository.restaurant.jpa;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Menu;
import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Restaurant;
import com.aleksandrbogomolov.vote_restaurant.repository.restaurant.MenuDishRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMenuRepositoryImpl implements MenuDishRepository<Menu> {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Menu save(Menu entity, int restaurant_id) {
        if (entity.isNew()) {
            entity.setRestaurant(em.getReference(Restaurant.class, restaurant_id));
            em.persist(entity);
            return entity;
        } else {
            int result = em.createNamedQuery(Menu.UPDATE).setParameter("id", entity.getId()).setParameter("registered", entity.getRegistered()).setParameter("restaurant_id", restaurant_id).executeUpdate();
            return result != 0 ? entity : null;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int restaurant_id) {
        return em.createNamedQuery(Menu.DELETE).setParameter("id", id).setParameter("restaurant_id", restaurant_id).executeUpdate() != 0;
    }

    @Override
    public Menu get(int id, int restaurant_id) {
        List<Menu> menus = em.createNamedQuery(Menu.GET, Menu.class).setParameter("id", id).setParameter("restaurant_id", restaurant_id).getResultList();
        return DataAccessUtils.singleResult(menus);
    }

    @Override
    public List<Menu> getAll(int restaurant_id) {
        return em.createNamedQuery(Menu.GET_ALL, Menu.class).setParameter("restaurant_id", restaurant_id).getResultList();
    }
}
