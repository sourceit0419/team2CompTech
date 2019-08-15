package com.bookstore.model;

public enum OrderStatus {
    New, //recently formed
    NotPayed, //has not payed yet
    Payed, //has already payed
    Delivering, //currently book is delivering to customer
    Done, //Customer receives the book
    Cancel //customer canceled the order or something went wrong
}
