package com.epam.multithreading.t01.transactions;

import com.epam.multithreading.t01.concurrent.accountconc.AccountConcurrent;
import com.epam.multithreading.t01.concurrent.transactionscon.TransactionHandlerConcurrent;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Date: 11.02.2017
 *
 * @author Karapetyan N.K
 */
public class TransactionHandlerConcurrentTest {

    private TransactionHandlerConcurrent t;

    @Before
    public void transaction() throws IOException, SAXException {
        t = new TransactionHandlerConcurrent();
        XMLReader reader = XMLReaderFactory.createXMLReader();
        reader.setContentHandler(t);
        reader.parse("src/test/resources/transactions.xml");
    }

    @Test
    public void results() throws InterruptedException {
        Thread.sleep(100);
        Map<Integer, AccountConcurrent> m = t.getAccounts();
        assertThat(m.get(1).getBalance().get(), is(700));
        assertThat(m.get(2).getBalance().get(), is(100));
        assertThat(m.get(3).getBalance().get(), is(700));
    }
}