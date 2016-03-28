package ua.avk.rest.dao;

import ua.avk.rest.entity.Contact;
import java.util.List;

/**
 * @author Alex Kononenko
 * @version 1.0.0
 *
 * The contact interface.
 */
public interface IContactDao {
    /**
     * Get list of the contact by limits from database
     *
     * @param startId contact
     * @return list of the contact
     */
    public List<Contact> getContactByLimit(long startId, long count);

    /**
     * Get all contacts from database
     *
     * @return list of the contacts
     */
    public List<Contact> getAllContacts();
}
