package com.epam.multithreading.t03;

import java.util.Random;

/**
 * Date: 12.02.2017
 *
 * @author Karapetyan N.K
 */
@SuppressWarnings("WeakerAccess")
public class UserResourceThread {
    public String startThreads() throws InterruptedException {
        SharedResource res = new SharedResource();
        IntegerSetterGetter t1 = new IntegerSetterGetter("1", res);
        IntegerSetterGetter t2 = new IntegerSetterGetter("2", res);
        IntegerSetterGetter t3 = new IntegerSetterGetter("3", res);
        IntegerSetterGetter t4 = new IntegerSetterGetter("4", res);
        IntegerSetterGetter t5 = new IntegerSetterGetter("5", res);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        Thread.sleep(100);

        t1.stopThread();
        t2.stopThread();
        t3.stopThread();
        t4.stopThread();
        t5.stopThread();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();

        return "main";
    }
}


class IntegerSetterGetter extends Thread {
    private final SharedResource resource;
    private boolean run;

    private Random rand = new Random();

    @SuppressWarnings("WeakerAccess")
    public IntegerSetterGetter(String name, SharedResource resource) {
        super(name);
        this.resource = resource;
        run = true;
    }

    @SuppressWarnings("WeakerAccess")
    public void stopThread() {
        run = false;
    }

    public void run() {
        int action;
        try {
            while (run) {
                action = rand.nextInt(1000);
                if (action % 2 == 0) {
                    getIntegersFromResource();
                } else {
                    setIntegersIntoResource();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Чтобы избежать блокировки поменяли wait() на wait(timeout)
     * по истечении которого, поток вместо того, чтобы ждать записи,
     * сам записывает число.
     */
    private void getIntegersFromResource() throws InterruptedException {
        Integer number;

        synchronized (resource) {
            number = resource.getElement();
            while (number == null) {
                    resource.wait(100);
                number = resource.getElement();
                    if(number==null) setIntegersIntoResource();
            }
        }
    }

    private void setIntegersIntoResource() throws InterruptedException {
        Integer number = rand.nextInt(500);
        synchronized (resource) {
            resource.setElement(number);
            resource.notify();
        }
    }
}

