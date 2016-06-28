package com.aleksandrbogomolov.vote_restaurant.repository.restaurant.jpa;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;
import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Menu;
import com.aleksandrbogomolov.vote_restaurant.repository.restaurant.MenuDishRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaDishRepositoryImpl implements MenuDishRepository<Dish> {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Dish save(Dish dish, Integer id) {
        if (dish.isNew()) {
            dish.setId(null);
            dish.setMenu(em.getReference(Menu.class, id));
            em.persist(dish);
            return dish;
        } else {
            dish.setMenu(em.getReference(Menu.class, id));
            return em.merge(dish);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Dish.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public Dish get(int id) {
        return em.find(Dish.class, id);
    }

    @Override
    public List<Dish> getAll() {
        return em.createNamedQuery(Dish.GET_ALL, Dish.class).getResultList();
    }
}
