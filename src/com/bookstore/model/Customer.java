package com.bookstore.model;

public class Customer {

    public int customerId; //unique number of customer
    private String name; //name of customer
    private int age; //age of customer

    public static int fakeCustomerId = 1;

    public Customer(int id, String name, int age) {
        this.customerId = fakeCustomerId++;
        this.name = name;
        this.age = age;
    }

    public Customer() {
        this.customerId = fakeCustomerId++;
    }

    public int getId() {
        return customerId;
    }

    public void setId(int id) {
        this.customerId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
