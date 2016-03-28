package ua.avk.rest.web.utils;

import junit.framework.Assert;
import org.eclipse.jetty.http.HttpStatus;
import org.json.simple.JSONObject;
import org.junit.Test;
import javax.ws.rs.core.Response;

public class ResponseCreatorTest {

    @Test
    public void testError(){
        JSONObject js = new JSONObject();
        js.put("status:", HttpStatus.INTERNAL_SERVER_ERROR_500);
        js.put("code:", 5001);
        js.put("description:", "Error");

        Response expResponse = ResponseCreator.error(500, 5001, "Error");
        Assert.assertEquals(expResponse.getStatus(), HttpStatus.INTERNAL_SERVER_ERROR_500);
        Assert.assertEquals(expResponse.getEntity(), js);

    }

    @Test
    public void testSuccess() throws Exception {
        String js = "test";

        Response expResponse = ResponseCreator.success(js);
        Assert.assertEquals(expResponse.getStatus(), HttpStatus.OK_200);
        Assert.assertEquals(expResponse.getEntity(), js);

        expResponse = ResponseCreator.success(null);
        Assert.assertEquals(expResponse.getEntity(), "none");
    }
}