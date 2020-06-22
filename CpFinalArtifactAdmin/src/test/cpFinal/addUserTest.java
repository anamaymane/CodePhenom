package cpFinal;

import junit.framework.TestCase;
import org.apache.taglibs.standard.lang.jstl.NullLiteral;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Collection;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeast;

public class addUserTest extends TestCase {

    public void testDoGet() {
    }

    public void testDoPost() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("username")).thenReturn("username-test");
        when(request.getParameter("email")).thenReturn("email-test");
        when(request.getParameter("fullName")).thenReturn("fullname-test");
        when(request.getParameter("password")).thenReturn("password-test");

        addUser adduser = new addUser();
        adduser.doPost(request, response);

        //verify(request, atLeast(1)).getParameter("LanguageName"); // only if you want to verify username was called...
        //DAO dao = mock(DAO.class);
        assertEquals(DAO.isUsernameRegistered("username-test"), 1);
    }
}