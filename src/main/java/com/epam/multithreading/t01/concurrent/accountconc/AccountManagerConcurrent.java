package com.epam.multithreading.t01.concurrent.accountconc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Date: 11.02.2017
 *
 * @author Karapetyan N.K
 */
public class AccountManagerConcurrent implements Runnable {
    private final AccountConcurrent from;
    private final AccountConcurrent to;
    private int amount;
    private static Lock lock = new ReentrantLock();
    public AccountManagerConcurrent(AccountConcurrent from, AccountConcurrent to, int amount){
        assert from!=null;
        assert amount>=0;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public void run(){
        lock.lock();
        for(int i=0; i<10; i++) {
            from.withdraw(amount);
            to.deposit(amount);
        }
        lock.unlock();
    }
}
