package com.bruno.ac.product.entity;

import javax.persistence.*;

/*
* create table product(
  product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  parent_product_id bigint foreign key product(product_id),
  name VARCHAR(16) NOT NULL,
  description VARCHAR(50)
);
* */
@Entity(name="product")
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;

    @Column(name = "parent_product_id")
    private int parentProductId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getParentProductId() {
        return parentProductId;
    }

    public void setParentProductId(int parentProductId) {
        this.parentProductId = parentProductId;
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
}
