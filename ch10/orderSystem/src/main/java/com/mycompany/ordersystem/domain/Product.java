package com.mycompany.ordersystem.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "product_description")
    private String description;
    @Column(name = "product_price")
    private long price;
    // JDBC에서는 없었는데 JPA로 오면서 필요해진 것들
    // 먼저 조인 필드, 독립적인 것은 상대방 필드명, CascadeType 지정
    @OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE)
    private Inventory inventory;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

}
