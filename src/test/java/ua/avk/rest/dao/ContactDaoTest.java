package ua.avk.rest.dao;

import junit.framework.Assert;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.avk.rest.entity.Contact;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/testApplicationContext.xml"})
public class ContactDaoTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private IContactDao contactDao;

    @Test
    @Transactional
    @Rollback(true)
    public void testGetContactByLimit(){

        Contact c1 = new Contact();
        c1.setName("N.Tesla");

        Contact c2 = new Contact();
        c2.setName("Alex");

        Contact c3 = new Contact();
        c3.setName("adam");

        Contact c4 = new Contact();
        c4.setName("anakondA");

        Contact c5 = new Contact();
        c5.setName("Alan");

        List<Contact> contactList = new ArrayList<Contact>();
        contactList.add(c1);
        contactList.add(c2);
        contactList.add(c3);
        contactList.add(c4);
        contactList.add(c5);

        List<Contact> actualContactList = new ArrayList<Contact>();
        actualContactList.add(c2);
        actualContactList.add(c3);

        for(int i=0; i<contactList.size(); i++){
            sessionFactory.getCurrentSession().saveOrUpdate(contactList.get(i));
        }

        List<Contact> expContactList = contactDao.getContactByLimit(2, 2);

        Assert.assertTrue(expContactList.size() == actualContactList.size());

        for(int i=0; i<expContactList.size(); i++){
            Assert.assertTrue(expContactList.get(i).equals(actualContactList.get(i)));
        }

    }

}