package ua.avk.rest.web;

import org.json.simple.JSONObject;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.avk.rest.service.IContactSrv;
import ua.avk.rest.web.exceptions.Error;
import ua.avk.rest.web.utils.ResponseCreator;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

/**
 * @author Alex Kononenko
 * @version 1.0.0
 *          <p/>
 *          The rest service for contacts
 */
@Controller
@Path(value = "/contacts")
public class ContactPage implements IContactPage {
    public static final int RESPONSE_ERROR = 500;

    IContactSrv contactSrv;
    private JSONObject responseContactDetailsJson;

    /**
     * Default constructor
     */
    public ContactPage() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"classpath:/spring/applicationContext.xml"}, true);
        contactSrv = (IContactSrv) context.getBean("contactSrv");
    }

    /**
     * For unit tests
     *
     * @param contactSrv Contact service class
     */
    public void setContactSrv(IContactSrv contactSrv) {
        this.contactSrv = contactSrv;
    }

    /**
     * For unit tests
     *
     * @param responseContactDetailsJson object
     */
    public void setResponseContactDetailsJson(JSONObject responseContactDetailsJson) {
        this.responseContactDetailsJson = responseContactDetailsJson;
    }

    /**
     * Get data by regular expression from database
     *
     * @param nameFilter string regular expression
     * @return response obj
     */
    @GET
    @Path(value = "/")
    @Produces("application/json")
    public Response getNameFilter(@DefaultValue("^") @QueryParam("nameFilter") String nameFilter,
                                  @DefaultValue("1") @QueryParam("id") long startId,
                                  @DefaultValue("100") @QueryParam("count") long count) {

        JSONObject responseContactDetailsJson2 = new JSONObject();
        setResponseContactDetailsJson(contactSrv.getJsonContactsByFilter(nameFilter, startId, count));

        if (responseContactDetailsJson != null) {
            responseContactDetailsJson2.put("contacts", responseContactDetailsJson);

            GenericEntity<JSONObject> entity = new GenericEntity<JSONObject>(
                    responseContactDetailsJson2) {
            };

            return ResponseCreator.success(entity);

        } else {
            return ResponseCreator.error(RESPONSE_ERROR,
                    Error.SERVER_ERROR.getCode(),
                    Error.SERVER_ERROR.getDescription());
        }

    }

}
