package com.epam.multithreading.t01.concurrent.transactionscon;

import com.epam.multithreading.t01.concurrent.accountconc.AccountConcurrent;
import com.epam.multithreading.t01.concurrent.accountconc.AccountManagerConcurrent;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Date: 11.02.2017
 *
 * @author Karapetyan N.K
 */
public class TransactionHandlerConcurrent extends DefaultHandler{
    private Map<Integer,AccountConcurrent> accounts  = new HashMap<>();
    private AccountConcurrent from;
    private AccountConcurrent to;
    public Map<Integer,AccountConcurrent> getAccounts(){
        return accounts;
    }

    /**
     * Метод читает xml файл и создает новые объекты для транзакций,
     * если они еще не были созданы.
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attr){
        if(localName.equals("transaction")){
            int fromId = Integer.parseInt(attr.getValue(0));
            int toId = Integer.parseInt(attr.getValue(1));
            if(!accounts.containsKey(fromId)){
                from = new AccountConcurrent(fromId);
            } else{
                from = accounts.get(fromId);
            }
            if(!accounts.containsKey(toId)){
                to = new AccountConcurrent(toId);
            } else{
                to = accounts.get(toId);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName){
        if(localName.equals("transaction")){
            accounts.put(from.getId(), from);
            accounts.put(to.getId(), to);
        }
    }

    /**
     * Метод читает значение которое нужно перевести, и запускает
     * отдельный поток для выполнения транзакции.
     */
    @Override
    public void characters(char[] ch, int start, int length){
        String s = new String(ch, start, length).trim();
        if(s.length()!=0) {
        Integer amount = Integer.parseInt(s);
        ExecutorService exec = Executors.newFixedThreadPool(2);
        exec.execute(new AccountManagerConcurrent(from, to, amount));
        }
    }
}