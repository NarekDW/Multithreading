package com.epam.multithreading.t01.transactions;


import com.epam.multithreading.t01.account.Account;
import com.epam.multithreading.t01.account.AccountManager;
import org.xml.sax.Attributes;

import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 11.02.2017
 *
 * @author Karapetyan N.K
 */
public class TransactionHandler extends DefaultHandler{
    private Map<Integer,Account> accounts  = new HashMap<>();
    private Account from;
    private Account to;
    public Map<Integer,Account> getAccounts(){
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
                from = new Account(fromId);
            } else{
                from = accounts.get(fromId);
            }
            if(!accounts.containsKey(toId)){
                to = new Account(toId);
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
        new AccountManager(from, to, amount);
        }
    }
}