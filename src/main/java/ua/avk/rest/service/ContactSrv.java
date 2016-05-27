package ua.avk.rest.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.avk.rest.dao.IContactDao;
import ua.avk.rest.entity.Contact;
import ua.avk.rest.service.util.Filter;

import java.util.Iterator;
import java.util.List;

/**
 * @author Alex Kononenko
 * @version 1.0.0
 *          <p/>
 *          The contact.
 */
@Service
class ContactSrv implements IContactSrv {

    @Autowired
    IContactDao contactDao;

    /**
     * For unit test
     *
     * @param contactDao object
     */
    public void setContactDao(IContactDao contactDao) {
        this.contactDao = contactDao;
    }

    /**
     * Get json object of the contacts by limit from database
     *
     * @param regExp
     * @param startId
     * @param count
     * @return json object
     */
    public JSONObject getJsonContactsByFilter(String regExp, long startId, long count) {
        // get all contacts from the database
        List<Contact> contacts = contactDao.getContactByLimit(startId, count);

        // set filter
        contacts = this.toFilter(contacts, regExp);

        return this.toJson(contacts);
    }

    /**
     * Created json object from list of the contacts
     *
     * @param contacts list of the contacts
     * @return JSON Object
     */
    public JSONObject toJson(List<Contact> contacts) {
        JSONObject responseContactDetailsJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < contacts.size(); i++) {
            JSONObject contactDetailsJson = new JSONObject();
            contactDetailsJson.put("id", contacts.get(i).getId());
            contactDetailsJson.put("name", contacts.get(i).getName());

            jsonArray.add(contactDetailsJson);
        }
        responseContactDetailsJson.put("contact", jsonArray);
        return responseContactDetailsJson;
    }

    /**
     * Filtered the list of the contacts
     *
     * @param contacts list of the contacts
     * @param regExp   regular expression
     * @return filtered list
     */
    public List<Contact> toFilter(List<Contact> contacts, String regExp) {
        Filter filter = new Filter(regExp);
        for (Iterator<Contact> iter = contacts.listIterator(); iter.hasNext(); ) {
            Contact c = iter.next();
            if (c.getName() != null && c.getName() != "" && filter.getFilter(c.getName())) {
                iter.remove();
            }
        }
        return contacts;
    }

}
