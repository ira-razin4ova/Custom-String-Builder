package org.example.bank;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentBank {

    private final ConcurrentHashMap<Integer, BankAccount> accounts = new ConcurrentHashMap<>();

    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public BankAccount createAccount (int initialBalance) {
        int id = idGenerator.getAndIncrement();
        BankAccount account = new BankAccount(initialBalance, id);
        accounts.put(id, account);
        return account;
    }

    public void transfer(BankAccount from, BankAccount to, int amount) {
        if (from == to) return;
        var firstLock = from.getId() > to.getId() ? from : to;
        var secondLock = from.getId() > to.getId() ? to : from;
        synchronized (firstLock) {
            synchronized (secondLock) {
                from.withdraw(amount);
                to.deposit(amount);
            }
        }
    }

    public int getTotalBalance() {
        int total = 0;
        for (BankAccount account : accounts.values()) {
            total += account.getBalance();
        }
        return total;
    }
}
