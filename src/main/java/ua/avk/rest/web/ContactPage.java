package ua.avk.rest.web;

import org.json.simple.JSONObject;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.avk.rest.service.IContactSrv;
import ua.avk.rest.web.exceptions.Error;
import ua.avk.rest.web.utils.ResponseCreator;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * @author Alex Kononenko
 * @version 1.0.0
 *
 * The rest service for contacts
 */
@Path(value="/contacts")
public class ContactPage implements IContactPage{

	private JSONObject responseContactDetailsJson;

    IContactSrv contactSrv;

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
	 * Default constructor
	 */
	public ContactPage() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[]{"classpath:/spring/applicationContext.xml"}, true);
		contactSrv = (IContactSrv) context.getBean("contactSrv");
	}

	/**
   	 * Get data by regular expression from database
	 *
	 * @param nameFilter string regular expression
   	 * @return response obj
   	 */
	@GET
	@Path(value="/")
   	@Produces("application/json")
	public Response getNameFilter(@DefaultValue("^")@QueryParam("nameFilter") String nameFilter,
									@DefaultValue("1")@QueryParam("id") long startId,
									@DefaultValue("100")@QueryParam("count") long count) {

		JSONObject responseContactDetailsJson2 = new JSONObject();
		setResponseContactDetailsJson(contactSrv.getJsonContactsByFilter(nameFilter, startId, count));

		if (responseContactDetailsJson != null) {
			responseContactDetailsJson2.put("contacts", responseContactDetailsJson);

			GenericEntity<JSONObject> entity = new GenericEntity<JSONObject>(
					responseContactDetailsJson2) {
			};

			return ResponseCreator.success(entity);

		} else {
			return ResponseCreator.error(500,
					Error.SERVER_ERROR.getCode(),
					Error.SERVER_ERROR.getDescription());
		}

	}

}
