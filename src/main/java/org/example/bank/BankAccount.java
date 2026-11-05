package org.example.bank;

public class BankAccount {

    private final int id;
    private int balance;

    public BankAccount (int balance, int id) {
        this.balance = balance;
        this.id = id;

    }

    public void withdraw(int amount) {
        int newВalance = this.balance - amount;
        this.setBalance(newВalance);
    }

    public void deposit(int amount) {
        int newВalance = this.balance + amount;
        this.setBalance(newВalance);
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}
