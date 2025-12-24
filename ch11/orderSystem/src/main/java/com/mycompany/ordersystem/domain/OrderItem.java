package com.mycompany.ordersystem.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private long id;
    // FK 종속에서는 보통 간단하게 cardinality
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "order_item_quantity")
    private long quantity;

    public OrderItem(Product product, long quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public OrderItem() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
