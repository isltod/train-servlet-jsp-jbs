package com.mycompany.domain;

import jakarta.persistence.*;

@Entity
// 이건 이름이 같아서 안해도 된다고...
// @Table(name = "customer")
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

    // 매개변수 있는 생성자가 있다면 default 생성자가 죽으니까 반드시 매개변수 없는 생성자를 명시적으로 만들어야 한다.

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
