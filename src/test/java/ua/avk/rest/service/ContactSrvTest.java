package ua.avk.rest.service;

import org.easymock.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.avk.rest.dao.IContactDao;
import ua.avk.rest.entity.Contact;
import ua.avk.rest.service.util.Filter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;


public class ContactSrvTest {

    Contact c1;
    private IMocksControl control = EasyMock.createControl();
    private IContactDao contactDao;

    @TestSubject
    private ContactSrv contactSrv;

    @Before
    public void setUp(){
        contactSrv = new ContactSrv();
        contactDao = control.createMock(IContactDao.class);
    }

    @After
    public void tearDown(){
        contactDao = null;
        contactSrv = null;
    }

    @org.junit.Test
    public void testGetJsonContactsByFilter3(){
        String regExp = "^A.*$";
        List<Contact> list = EasyMock.createMock(List.class);
      	replay(list);

        //
        JSONObject mockJsonObjectGetJsonContactsByFilter = EasyMock.createMock(JSONObject.class);
      	replay(mockJsonObjectGetJsonContactsByFilter);

        expect(contactDao.getContactByLimit(1, 100)).andStubReturn(list);
        replay(contactDao);
        contactSrv.setContactDao(contactDao);

        contactSrv = EasyMock.createMockBuilder(ContactSrv.class)
                .addMockedMethod("toFilter", List.class, String.class)
                .addMockedMethod("toJson", List.class)
                .addMockedMethod("getJsonContactsByFilter", String.class, long.class, long.class)
                .createMock();
        expect(contactSrv.toFilter(list, regExp)).andStubReturn(list);
        expect(contactSrv.toJson(list)).andStubReturn(mockJsonObjectGetJsonContactsByFilter);
        expect(contactSrv.getJsonContactsByFilter(regExp, 1, 100)).andStubReturn(mockJsonObjectGetJsonContactsByFilter);
        replay(contactSrv);

        verify(contactDao);
        verify(contactSrv);

    }


    @Test
    public void testToJson(){
        Contact c1 = new Contact();
        c1.setId(1);
        c1.setName("N.Tesla");

        Contact c2 = new Contact();
        c2.setId(2);
        c2.setName("Alex");

        Contact c3 = new Contact();
        c3.setId(3);
        c3.setName("adam");

        Contact c4 = new Contact();
        c4.setId(4);
        c4.setName("anakondA");

        List<Contact> contacts = new ArrayList<Contact>();
        contacts.add(c1);
        contacts.add(c2);
        contacts.add(c3);
        contacts.add(c4);

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < contacts.size(); i++) {
            JSONObject contactDetailsJson = new JSONObject();
            contactDetailsJson.put("id", contacts.get(i).getId());
            contactDetailsJson.put("name", contacts.get(i).getName());

            jsonArray.add(contactDetailsJson);
        }
        JSONObject responseContactDetailsJson = new JSONObject();
        responseContactDetailsJson.put("contact", jsonArray);

        Assert.assertEquals(contactSrv.toJson(contacts), responseContactDetailsJson);
    }

    @Test
    public void testToFilter(){
        List<Contact> contacts = new ArrayList<Contact>();

        for(int i=0; i<5; i++){
            Contact c1 = new Contact();
            c1.setId(i+1);
            c1.setName("N.Tesla");

            contacts.add(c1);
        }



        Filter filter = EasyMock.createMock(Filter.class);
        expect(filter.getFilter("N.Tesla")).andReturn(false).anyTimes();
        replay(filter);

        for (Iterator<Contact> iter = contacts.listIterator(); iter.hasNext();) {
            Contact c = iter.next();
            if(c.getName()!=null && c.getName()!="") {
                if (filter.getFilter(c.getName())) {
                    iter.remove();
                    //System.out.println("REMOVE: "+c.getName());
                }
            }
        }

        Assert.assertTrue(contacts.size() == 5);



        Filter filter2 = EasyMock.createMock(Filter.class);
        expect(filter2.getFilter("N.Tesla")).andReturn(true).anyTimes();
        replay(filter2);

        for (Iterator<Contact> iter = contacts.listIterator(); iter.hasNext();) {
            Contact c = iter.next();
            if(c.getName()!=null && c.getName()!="") {
                if (filter2.getFilter(c.getName())) {
                    iter.remove();
                    //System.out.println("REMOVE: "+c.getName());
                }
            }
        }

        Assert.assertTrue(contacts.size() == 0);
    }

}

