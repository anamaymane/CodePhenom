package cpFinal;

import junit.framework.TestCase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class deleteUserTest extends TestCase {

    public void testDoGet() {

    }

    public void testDoPost() throws Exception{
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("username")).thenReturn("username-test");

        deleteUser deleteuser = new deleteUser();
        deleteuser.doPost(request, response);

        //verify(request, atLeast(1)).getParameter("LanguageName"); // only if you want to verify username was called...
        //DAO dao = mock(DAO.class);
        assertEquals(DAO.isUsernameRegistered("username-test"), 0);
    }
}