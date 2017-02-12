package com.epam.multithreading.t02;

/**
 * Date: 03.02.2017
 *
 * @author Karapetyan N.K
 */
public class KeyNotExistException extends RuntimeException {
    @SuppressWarnings({"unused", "WeakerAccess"})
    public KeyNotExistException() {
        super();
    }

    @SuppressWarnings({"WeakerAccess", "unused"})
    public KeyNotExistException(String message) {
        super(message);
    }
}
