package com.epam.multithreading.t02;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Date: 11.02.2017
 *
 * @author Karapetyan N.K
 */
@SuppressWarnings("WeakerAccess")
public class PropertyReader {
    private  static ConcurrentHashMap<String, String> hashMap = new ConcurrentHashMap<>();
    private static Lock lock = new ReentrantLock();
    private static Logger logger = Logger.getLogger("src/main/resources/log4j");

    public PropertyReader(File file){
        loading(file);
    }
    // Перегруженный конструктор
    public PropertyReader(String file){
        this(new File(file));
    }

    public String getValue(String key){
        if(hashMap.containsKey(key))
            return hashMap.get(key);
        throw new KeyNotExistException();
    }

    /*
    * При создании объекта данного класса, происходит загрузка данных
    * из файла в ConcurrentHashMap.
    */
    private ConcurrentHashMap loading(File file){
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String str;
            lock.lock();
            while((str = br.readLine())!=null){
                String[] temp = str.split("=");
                if(temp.length>1){
                    hashMap.put(temp[0].trim(), temp[1].trim());
                }else hashMap.put(temp[0].trim(), "");
            }
            lock.unlock();
        } catch (IOException e) {
            logger.error("IOException in loading", e);
        }
        return hashMap;
    }
}
