package com.aleksandrbogomolov.vote_restaurant.repository.restaurant.jpa;

import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Dish;
import com.aleksandrbogomolov.vote_restaurant.model.restaurant.Menu;
import com.aleksandrbogomolov.vote_restaurant.repository.restaurant.MenuDishRepository;
import org.springframework.dao.support.DataAccessUtils;
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
    public Dish save(Dish dish, int menu_id) {
        if (dish.isNew()) {
            dish.setId(null);
            dish.setMenu(em.getReference(Menu.class, menu_id));
            em.persist(dish);
            return dish;
        } else {
            int result = em.createNamedQuery(Dish.UPDATE).setParameter("id", dish.getId()).setParameter("menu_id", menu_id).setParameter("name", dish.getName()).setParameter("price", dish.getPrice()).executeUpdate();
            return result != 0 ? dish : null;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int menu_id) {
        return em.createNamedQuery(Dish.DELETE).setParameter("id", id).setParameter("menu_id", menu_id).executeUpdate() != 0;
    }

    @Override
    public Dish get(int id, int menu_id) {
        List<Dish> dishes = em.createNamedQuery(Dish.GET, Dish.class).setParameter("id", id).setParameter("menu_id", menu_id).getResultList();
        return DataAccessUtils.singleResult(dishes);
    }

    @Override
    public List<Dish> getAll(int menu_id) {
        return em.createNamedQuery(Dish.GET_ALL, Dish.class).setParameter("menu_id", menu_id).getResultList();
    }
}
