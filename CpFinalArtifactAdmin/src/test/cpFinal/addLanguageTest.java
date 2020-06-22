package cpFinal;

import junit.framework.TestCase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class addLanguageTest extends TestCase {

    public void testDoGet() {
    }

    public void testDoPost() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("LanguageName")).thenReturn("TestName");

        addLanguage addlanguage = new addLanguage();
        addlanguage.doPost(request, response);

        verify(request, atLeast(1)).getParameter("LanguageName"); // only if you want to verify username was called...
        //DAO dao = mock(DAO.class);
        assertEquals(DAO.isLanguageNameExiste("TestName"), 1);
    }
}