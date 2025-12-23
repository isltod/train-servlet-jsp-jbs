package com.mycompany;

import com.mycompany.domain.Customer;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("default")) {
            EntityManager em = emf.createEntityManager();
            printCustomersNative(em);
            printCustomerNative(em, "김일");
            printCustomersJPQL(em);
            printCustomerJPQL(em, "김삼");
            printCustomersCriteria(em);
            printCustomerCriteria(em, "김오");
            findCustomer(em, 4);
            // saveCustomer(em);
            // updateCustomer(em, 6, "울프", "수원시", "wolf@teoal.net");
            deleteCustomer(em, 6);
        }
    }

    public static void deleteCustomer(EntityManager em, int id) {
        em.getTransaction().begin();
        Customer customer = em.find(Customer.class, id);
        em.remove(customer);
        em.getTransaction().commit();
        printCustomersJPQL(em);
    }

    public static void updateCustomer(EntityManager em, long id, String name, String address, String email) {
        em.getTransaction().begin();
        Customer customer = em.find(Customer.class, id);
        customer.setName(name);
        customer.setAddress(address);
        customer.setEmail(email);
        em.getTransaction().commit();
        printCustomersNative(em);
    }

    public static void saveCustomer(EntityManager em) {
        Customer customer = new Customer();
        customer.setName("김육");
        customer.setAddress("제주시");
        customer.setEmail("kim6@test.com");
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        printCustomersCriteria(em);
    }

    public static void findCustomer(EntityManager em, long customer_id) {
        Customer customer = em.find(Customer.class, customer_id);
        System.out.printf(
                "id: %d, 이름: %s, 주소: %s, 이메일: %s\n",
                customer.getId(), customer.getName(), customer.getAddress(), customer.getEmail());
    }

    public static void printCustomerCriteria(EntityManager em, String name)
    {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> root = cq.from(Customer.class);
        cq.select(root);
        Predicate predicate = cb.like(root.get("name"), name);
        cq.where(predicate);
        TypedQuery<Customer> query = em.createQuery(cq);
        List<Customer> customers = query.getResultList();
        for (Customer customer : customers) {
            System.out.printf(
                    "id: %d, 이름: %s, 주소: %s, 이메일: %s\n",
                    customer.getId(), customer.getName(), customer.getAddress(), customer.getEmail());
        }
    }
    
    public static void printCustomersCriteria(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> root = cq.from(Customer.class);
        cq.select(root);
        TypedQuery<Customer> query = em.createQuery(cq);
        List<Customer> customers = query.getResultList();
        for (Customer customer : customers) {
            System.out.printf(
                    "id: %d, 이름: %s, 주소: %s, 이메일: %s\n",
                    customer.getId(), customer.getName(), customer.getAddress(), customer.getEmail());
        }
    }

    public static void printCustomerJPQL(EntityManager entityManager, String name) {
        // 뭘 설명 안했는지는 모르겠지만 XML mapping을 사용하면 Customer 클래스를 못 찾는다..
        String jpql = "select c from Customer c where c.name = :name";
        TypedQuery<Customer> query = entityManager.createQuery(jpql, Customer.class);
        query.setParameter("name", name);
        List<Customer> customers = query.getResultList();
        for(Customer customer : customers) {
            System.out.printf("id: %d, 이름: %s, 주소: %s, 이메일: %s\n",
                    customer.getId(), customer.getName(), customer.getAddress(), customer.getEmail());
        }
    }

    public static void printCustomersJPQL(EntityManager entityManager) {
        // 뭘 설명 안했는지는 모르겠지만 XML mapping을 사용하면 Customer 클래스를 못 찾는다..
        String jpql = "SELECT c FROM Customer c";
        TypedQuery<Customer> query = entityManager.createQuery(jpql, Customer.class);
        List<Customer> customers = query.getResultList();
        for(Customer customer : customers) {
            System.out.printf("id: %d, 이름: %s, 주소: %s, 이메일: %s\n",
                    customer.getId(), customer.getName(), customer.getAddress(), customer.getEmail());
        }
    }

    public static void printCustomerNative(EntityManager em, String name) {
        String sql = "SELECT * FROM customer WHERE customer_name = ?";
        Query query = em.createNativeQuery(sql, Customer.class);
        query.setParameter(1, name);
        List<Customer> customers = query.getResultList();
        for (Customer customer : customers) {
            System.out.printf(
                    "id: %d, 이름: %s, 주소: %s, 이메일: %s\n",
                    customer.getId(), customer.getName(), customer.getAddress(), customer.getEmail()
            );
        }
    }

    public static void printCustomersNative(EntityManager em) {
        String sql = "SELECT * FROM customer";
        Query query = em.createNativeQuery(sql, Customer.class);
        List<Customer> customers = query.getResultList();
        for (Customer customer : customers) {
            System.out.printf(
                    "id: %d, 이름: %s, 주소: %s, 이메일: %s\n",
                    customer.getId(), customer.getName(), customer.getAddress(), customer.getEmail()
            );
        }
    }
}
