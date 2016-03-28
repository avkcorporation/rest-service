package ua.avk.rest.web;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 *
 */
public interface IContactPage {

    /**
     *
     * @param nameFilter
     * @param startId
     * @param count
     * @return
     */
    public Response getNameFilter(@DefaultValue("^")@QueryParam("nameFilter") String nameFilter,
   									@DefaultValue("1")@QueryParam("id") long startId,
   									@DefaultValue("1000")@QueryParam("count") long count);
}
