package com.epam.multithreading.t01.concurrent.transactionscon;

import com.epam.multithreading.t01.account.Account;
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
public class TransactionHandlerTest {

    private com.epam.multithreading.t01.transactions.TransactionHandler t;

    @Before
    public void transaction() throws IOException, SAXException {
        t = new com.epam.multithreading.t01.transactions.TransactionHandler();
        XMLReader reader = XMLReaderFactory.createXMLReader();
        reader.setContentHandler(t);
        reader.parse("src/test/resources/transactions.xml");
    }

    @Test
    public void results() throws InterruptedException {
        Thread.sleep(100);
        Map<Integer, Account> m = t.getAccounts();
        assertThat(m.get(1).getBalance(), is(700));
        assertThat(m.get(2).getBalance(), is(100));
        assertThat(m.get(3).getBalance(), is(700));
    }
}