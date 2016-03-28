package ua.avk.rest.web;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

public class StartPageTest {

    @Test
    public void testSayHello(){
        String str = "Hello";
        StartPage startPage = new StartPage();
        startPage.setMsg(str);
        Assert.assertEquals(startPage.sayHello(), str);
    }

}