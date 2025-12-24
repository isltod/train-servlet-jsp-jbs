package com.mycompany.ordersystem.order.repository;

import com.mycompany.ordersystem.domain.Customer;
import com.mycompany.ordersystem.domain.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.List;

public class OrderRepositoryImplJPA implements OrderRepository {

    private EntityManager em;

    public OrderRepositoryImplJPA(EntityManager em) {
        this.em = em;
    }


    @Override
    public Order findById(long id) {
        return em.find(Order.class, id);
    }

    @Override
    public List<Order> findAll(Customer customer) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> root = cq.from(Order.class);
        cq.select(root);
        // WHERE 조건절을 id가 아니라 객체로 그냥 넘긴다...
        Path<Order> path = root.get("customer");
        Predicate predicate = cb.equal(path, customer);
        cq.where(predicate);
        TypedQuery<Order> query = em.createQuery(cq);
        List<Order> orders = query.getResultList();
        return orders;
    }

    @Override
    public void save(Order order) {
        em.getTransaction().begin();
        // 현재 주문은 갱신 없고 무조건 insert만 있으니까...
        em.persist(order);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Order order) {
        em.getTransaction().begin();
        Order entity = findById(order.getId());
        if (entity != null) {
            em.remove(entity);
        }
        em.getTransaction().commit();
    }
}
