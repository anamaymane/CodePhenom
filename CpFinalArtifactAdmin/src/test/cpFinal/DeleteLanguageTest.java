package cpFinal;

import junit.framework.TestCase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeast;

public class DeleteLanguageTest extends TestCase {

    public void testDoGet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn(Long.toString(DAO.getLanguageIdByName("TestName")));

        DeleteLanguage deleteLanguage = new DeleteLanguage();
        deleteLanguage.doGet(request, response);

        //verify(request, atLeast(1)).getParameter("LanguageName"); // only if you want to verify username was called...
        //DAO dao = mock(DAO.class);
        assertEquals(DAO.isLanguageNameExiste("TestName"), 0);
    }

    public void testDoPost() {
    }
}