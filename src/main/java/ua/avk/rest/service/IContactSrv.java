package ua.avk.rest.service;

import org.json.simple.JSONObject;
import ua.avk.rest.entity.Contact;

import java.util.List;

/**
 * @author Alex Kononenko
 * @version 1.0.0
 *
 * The contact interface.
 */
public interface IContactSrv {

    /**
     * Get json object of the contacts by limit from database
     *
     * @param regExp
     * @param startId
     * @param count
     * @return json object
     */
    public JSONObject getJsonContactsByFilter(String regExp, long startId, long count);

    /**
     * Created json object from list of the contacts
     *
     * @param contacts list of the contacts
     * @return JSON Object
     */
    public JSONObject toJson(List<Contact> contacts);

    /**
     * Filtered the list of the contacts
     *
     * @param contacts list of the contacts
     * @param regExp regular expression
     * @return filtered list
     */
    public List<Contact> toFilter(List<Contact> contacts, String regExp);
}
