package com.epam.multithreading.t01.account;

/**
 * Date: 11.02.2017
 *
 * @author Karapetyan N.K
 */
public class Account {
    private int id;
    private int balance;

    @SuppressWarnings("WeakerAccess")
    public Account(int id, int balance){
        this.balance = balance;
        this.id = id;
    }

    public Account(int id){
        this(id, 500);
    }

    // Атомарная операция
    public int getId(){
        return id;
    }

    // Атомарная операция
    @SuppressWarnings("unused")
    public int getBalance(){
        return balance;
    }

    void deposit(int amount){
        assert amount>=0;
        this.balance+=amount;
    }

    void withdraw(int amount){
        assert amount>=0;
        this.balance-=amount;
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
