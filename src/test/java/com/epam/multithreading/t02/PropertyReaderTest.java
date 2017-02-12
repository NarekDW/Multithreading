package com.epam.multithreading.t02;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Date: 11.02.2017
 *
 * @author Karapetyan N.K
 */
public class PropertyReaderTest {
    @Test
    public void getValue() throws Exception {
        PropertyReader pr = new PropertyReader("src/test/resources/prop.properties");
        assertThat(pr.getValue("db1"), is("first element"));
        assertThat(pr.getValue("db3"), is(""));
    }
}