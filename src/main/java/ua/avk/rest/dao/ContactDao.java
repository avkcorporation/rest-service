package ua.avk.rest.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.avk.rest.entity.Contact;

import java.util.List;

/**
 * @author Alex Kononenko
 * @version 1.0.0
 *          <p/>
 *          The Contact dao realization with contact dao interface.
 */
@Service
@Transactional
class ContactDao implements IContactDao {

    @Autowired
    SessionFactory sessionFactory;

    /**
     * Get list of the contact by limits from database
     *
     * @param startId long contact id
     * @param count   long count row
     * @return list of the contact
     */
    public List<Contact> getContactByLimit(long startId, long count) {
        List<Contact> contactList = null;
        contactList = (List<Contact>) sessionFactory.getCurrentSession().getNamedQuery(Contact.GET_CONTACT_BY_LIMIT).setParameter("id", startId).setParameter("counter", startId + count).list();
        return contactList;
    }

    /**
     * Get all contacts from database
     *
     * @return list of the contacts
     */
    public List<Contact> getAllContacts() {
        List<Contact> contactList = null;
        contactList = sessionFactory.getCurrentSession().createCriteria(Contact.class).list();
        return contactList;
    }
}
