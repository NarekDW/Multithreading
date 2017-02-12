package com.epam.multithreading.t01.account;

/**
 * Date: 11.02.2017
 *
 * @author Karapetyan N.K
 */
public class AccountManager extends Thread {
    private final Account from;
    private final Account to;
    private int amount;
    public AccountManager(Account from, Account to, int amount){
        assert from!=null;
        assert amount>=0;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.start();
    }

    @Override
    public void run(){
        synchronized (from){
            synchronized (to){
                for(int i=0; i<10; i++){
                    from.withdraw(amount);
                    to.deposit(amount);
                }
            }
        }
    }
}
