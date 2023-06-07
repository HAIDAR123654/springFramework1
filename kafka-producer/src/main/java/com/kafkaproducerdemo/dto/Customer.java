package com.kafkaproducerdemo.dto;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String address;
    private int age;

    public Customer(int id, String name, String email, String address, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.age = age;
    }

}
