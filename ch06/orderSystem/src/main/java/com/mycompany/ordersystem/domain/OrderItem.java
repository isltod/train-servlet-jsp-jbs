package com.mycompany.ordersystem.domain;

public class OrderItem {
    private long id;
    private Product product;
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
