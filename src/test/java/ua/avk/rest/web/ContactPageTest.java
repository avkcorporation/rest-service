package ua.avk.rest.web;

import org.easymock.EasyMock;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.avk.rest.service.IContactSrv;
import ua.avk.rest.web.utils.ResponseCreator;

import javax.ws.rs.core.Response;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/testApplicationContext.xml"})
public class ContactPageTest {

    private IContactSrv contactSrv;
    private ContactPage contactPage;

    @Before
    public void setUp() throws Exception {

        contactPage = EasyMock.createMockBuilder(ContactPage.class)
                .createMock();

        contactSrv = EasyMock.createMock(IContactSrv.class);

    }

    @After
    public void tearDown() throws Exception {
        contactSrv = null;
        contactPage = null;
    }

    /**
     * test method for response.ok()
     */
    @org.junit.Test
    public void testGetNameFilter1() {
        JSONObject mockJSonObj = EasyMock.createMock(JSONObject.class);
        replay(mockJSonObj);

        expect(contactSrv.getJsonContactsByFilter("*", 1, 100)).andStubReturn(mockJSonObj);
        replay(contactSrv);

        Response expResponse = ResponseCreator.success(mockJSonObj);

        contactPage.setContactSrv(contactSrv);
        contactPage.setResponseContactDetailsJson(mockJSonObj);
        replay(contactPage);

        assertEquals(contactSrv.getJsonContactsByFilter("*", 1, 100), mockJSonObj);

        Response actualResponse = contactPage.getNameFilter("*", 1, 100);
        assertEquals(expResponse.getStatus(), actualResponse.getStatus());

        verify(contactSrv);
        verify(contactPage);
    }

    /**
     * test method for response.server_error
     */
    @org.junit.Test
    public void testGetNameFilter2() {
        JSONObject mockJSonObj = null;

        expect(contactSrv.getJsonContactsByFilter("^A.*$", 1, 100)).andStubReturn(mockJSonObj);
        replay(contactSrv);

        Response expResponse = ResponseCreator.error(500, 5001, "Error");

        contactPage.setContactSrv(contactSrv);
        contactPage.setResponseContactDetailsJson(mockJSonObj);
        replay(contactPage);

        assertEquals(contactSrv.getJsonContactsByFilter("^A.*$", 1, 100), mockJSonObj);

        Response actualResponse = contactPage.getNameFilter("^A.*$", 1, 100);
        assertEquals(expResponse.getStatus(), actualResponse.getStatus());

        verify(contactSrv);
        verify(contactPage);
    }

}

