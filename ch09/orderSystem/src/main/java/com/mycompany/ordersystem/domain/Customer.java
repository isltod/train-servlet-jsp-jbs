package com.mycompany.ordersystem.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
@Access(AccessType.FIELD)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long id;
    @Column(name = "customer_name")
    private String name;
    @Column(name = "customer_address")
    private String address;
    @Column(name = "customer_email")
    private String email;

    // JPQL이나 Criteria API를 사용해 DTO를 만들면 hibernate가 모든 필드를 받는 생성자를 요구한다...
    // 그냥 Native SQL이 답인가...

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
