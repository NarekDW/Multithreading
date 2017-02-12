package com.epam.multithreading.t03;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Date: 12.02.2017
 *
 * @author Karapetyan N.K
 */
public class UserResourceThreadTest {
    @Test
    public void startThreads() throws InterruptedException {
        for(int i=0; i<5; i++){
            UserResourceThread userResourceThread = new UserResourceThread();
            assertThat(userResourceThread.startThreads(), is("main"));
        }
    }
}