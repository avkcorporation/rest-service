package ua.avk.rest.web.utils;

import org.json.simple.JSONObject;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Create response
 */
public class ResponseCreator {

	/**
	 * Static method create response error
	 *
	 * @param status integer status
	 * @param errorCode integer error code
	 * @param description string description error
	 * @return response
	 */
	public static Response error(int status, int errorCode, String description) {
		Response.ResponseBuilder response = Response.serverError();
		response.header("errorcode", errorCode);
		response.header("content-type", MediaType.APPLICATION_JSON);
		JSONObject js = new JSONObject();
		js.put("status:", status);
		js.put("code:", errorCode);
		js.put("description:", description);
		response.entity(js);
		return response.build();
	}

	/**
	 * Static method create response success
	 *
	 * @param object response entity
	 * @return response
	 */
	public static Response success(Object object) {
		Response.ResponseBuilder response = Response.ok();
		response.header("content-type", MediaType.APPLICATION_JSON);
		if (object != null) {
			response.entity(object);
		} else {
			response.entity("none");
		}
		return response.build();
	}
}
