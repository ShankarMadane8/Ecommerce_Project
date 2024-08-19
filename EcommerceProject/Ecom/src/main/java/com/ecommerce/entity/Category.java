package com.ecommerce.entity;

import jakarta.persistence.*;


@Entity

public class Category {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @Column(length = 255, nullable = false, unique = true)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
