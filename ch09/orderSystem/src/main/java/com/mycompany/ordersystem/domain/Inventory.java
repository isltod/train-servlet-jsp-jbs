package com.mycompany.ordersystem.domain;

import jakarta.persistence.*;

@Entity
// 뭔가 이유는 모르겠는데, 이걸 안넣으니까 id setter에서 예외가 발생한다...이름 같아도 그냥 넣어줘야 하나보다...
@Table(name = "inventory")
public class Inventory {
    @Id
    // 이건 따로 생성되는 것이 아니라 product의 키에 의존하니 @GeneratedValue 어노테이션은 필요 없고
    @Column(name = "product_id")
    private long id;
    // 자체 이름이나 가격이 있는 건 아니고 product에서 가져오는 거니까 임시 표시
    @Transient
    private String name;
    @Transient
    private long price;
    @Column(name = "inventory_quantity")
    private long quantity;

    // JDBC에서는 없었지만 JPA에서는 필요해진 것
    // 먼저 조인 필드가 객체로 연결되는데, 종속적인 것은 그냥 Cardinality만...
    @OneToOne
    // 가서 붙을 독립 엔터티의 키
    @JoinColumn(name = "product_id")
    // 그리고 FK를 PK로 사용하겠다는 @MapsId
    @MapsId
    private Product product;

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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
