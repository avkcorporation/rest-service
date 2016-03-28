package ua.avk.rest.service.util;

import junit.framework.Assert;
import org.junit.Test;

public class FilterTest {

    @Test
    public void testGetFilter(){

        Filter filter = new Filter("^A.*$");

        Assert.assertTrue(filter.getFilter("Alex"));
        Assert.assertFalse(filter.getFilter("alex"));
        Assert.assertFalse(filter.getFilter("anacondA"));

    }
}