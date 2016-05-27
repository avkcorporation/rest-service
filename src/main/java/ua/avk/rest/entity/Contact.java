package ua.avk.rest.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Alex Kononenko
 * @version 1.0.0
 *          <p/>
 *          The contact entity.
 */
@Entity

@NamedQueries({
        @NamedQuery(name = Contact.GET_CONTACT_BY_REGEXP_QUERY, query = "select c from Contact c where c.id = :id"),
        @NamedQuery(name = Contact.GET_CONTACT_BY_LIMIT, query = "select c from Contact c  where c.id >= :id and c.id < :counter"),
        @NamedQuery(name = Contact.GET_ALL_CONTACT_FROM_DATABASE_QUERY, query = "select c from Contact c")
})

@Table(name = "CONTACT")
public class Contact implements Serializable {
    public static final String GET_ALL_CONTACT_FROM_DATABASE_QUERY = "getAllContactFromDatabase";
    public static final String GET_CONTACT_BY_REGEXP_QUERY = "getUserByLogin";
    public static final String GET_CONTACT_BY_LIMIT = "getContactByLimit";
    private static final long serialVersionUID = -11111110000001L;
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID", unique = true, nullable = false, length = 25)
    private long id;

    @Column(name = "NAME", unique = false, nullable = false, length = 25)
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * The method create copy object of the contact
     *
     * @param contact - the object of the contact
     */
    public void copy(Contact contact) {
        this.setName(contact.getName());
    }

    /**
     * Override hashCode for correct work equals
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append(this.name).
                toHashCode();
    }

    /**
     * Compares of the contact
     *
     * @param obj for compare
     * @return true if equals
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Contact)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Contact contact = (Contact) obj;
        return new EqualsBuilder().
                append(this.name, contact.name).
                isEquals();
    }


}
