package com.bookstore.model;

public class Profit {
    private int amount; //total amount of sold booksMain
    private double totalSum; //total sum of prices of sold booksMain

    public Profit(int amount, double totalSum) {
        this.amount = amount;
        this.totalSum = totalSum;
    }

    public Profit() {
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }

    @Override
    public String toString() {
        return "всего" + amount +
                " книг(и) на сумму " + totalSum;
    }
}
