package ua.avk.rest.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Alex Kononenko
 * @version 1.0.0
 *
 * The start page viewer
 */
@Path(value="/")
public class StartPage {
    private String msg = "<h1>The REST service</h1>";

    /**
     *  for unit test
     *
     * @param msg string
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHello(){
        return msg;
    }

}
