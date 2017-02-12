package com.epam.multithreading.t01.concurrent.accountconc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Date: 11.02.2017
 *
 * @author Karapetyan N.K
 */
public class AccountConcurrent {
    private int id;
    private AtomicInteger balance;

    @SuppressWarnings("WeakerAccess")
    public AccountConcurrent(int id, AtomicInteger balance){
        this.balance = balance;
        this.id = id;
    }

    @SuppressWarnings("unused")
    public AccountConcurrent(int id){
        this(id, new AtomicInteger(500));
    }

    // Атомарная операция
    @SuppressWarnings("unused")
    public int getId(){
        return id;
    }

    // Атомарная операция
    @SuppressWarnings("unused")
    public AtomicInteger getBalance(){
        return balance;
    }

    void deposit(int amount){
        assert amount>=0;
        this.balance.addAndGet(amount);
    }

    void withdraw(int amount){
        assert amount>=0;
        this.balance.addAndGet(-amount);
    }

    @Override
    public String toString(){
        //noinspection StringBufferReplaceableByString
        StringBuilder sb = new StringBuilder();
        sb.append("id = ").append(id).append("\n");
        sb.append("balance = ").append(balance).append("\n");
        return sb.toString();
    }
}
