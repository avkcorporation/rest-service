package ua.avk.rest.service.util;

import ua.avk.rest.entity.Contact;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alex Kononenko
 * @version 1.0.0
 *
 * Filter class
 */
public class Filter {
    private Pattern pattern;
    private Matcher matcher;

    /**
     *
     * @param regExp string
     */
    public Filter(String regExp){
        pattern = Pattern.compile(regExp);
    }

    /**
     * Check validation string with regular expression
     *
     * @param contact string
     * @return true or false
     */
    public boolean getFilter(String contact){
        matcher = pattern.matcher(contact);
        if (matcher.matches()){
            return true;
        } else {
            return false;
        }
    }


}
