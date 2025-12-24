package com.mycompany.ordersystem.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;
    // 1:1 종속 뿐 아니라 N:1 연결에서도 외래키 가진 종속에서는 간단하게 @ManyToOne하고 @JoinColumn을 지정
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column(name = "order_date")
    private LocalDate date;
    // 이건 반대로 내가 독립이고 상대가 종속인 1:N, 내가 fetch와 cascade 지정한다...
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // 근데 @JoinColumn을 내가 지정하나? 이건 테이블 구조가 아니라 프로그램 로직에서 조인을 요청할 테이블에 붙는 모양...
    @JoinColumn(name = "order_id")
    private List<OrderItem> items;

    public Order(Customer customer, List<OrderItem> items) {
        this.customer = customer;
        this.date = LocalDate.now();
        this.items = items;
    }

    public Order() {

    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public List<OrderItem> getItems() {
        return items;
    }
    public long getTotal() {
        long total = 0;
        for(OrderItem item : items) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }
}
