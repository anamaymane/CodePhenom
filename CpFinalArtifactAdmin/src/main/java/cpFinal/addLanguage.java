package cpFinal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.persistence.Id;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.bson.Document;

/**
 * Servlet implementation class addLanguage
 */
@MultipartConfig
public class addLanguage extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addLanguage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/addLanguage.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("LanguageName");
        if(DAO.isLanguageNameExiste(name) == 0) {
        	
		Document user;
		long ID = DAO.getMaxDocument("TestCase") +1 ;
		user = new Document().append("-id", ID).append("name",name);
			mongodbConnection.getCollection("AvailableLanguage").insertOne(user);
		request.getRequestDispatcher("/addLanguage.jsp?add=success").forward(request, response);
        }
        else {       
            response.setIntHeader("Refresh",0);
        }
	}
}
