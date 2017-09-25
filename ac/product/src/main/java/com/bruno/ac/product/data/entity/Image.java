package com.bruno.ac.product.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
create table image(
        image_id bigint auto_increment primary key,
        product_id bigint not null,
        name varchar(16) not null,
        description varchar(50),
        source blob
        );
*/
public class Image {

    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long imageId;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private  String description;

    @Column(name = "source")
    private byte[] source;

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
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

    public byte[] getSource() {
        return source;
    }

    public void setSource(byte[] source) {
        this.source = source;
    }

}
