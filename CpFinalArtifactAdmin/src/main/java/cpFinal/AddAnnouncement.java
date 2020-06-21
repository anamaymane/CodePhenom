package cpFinal;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

/**
 * Servlet implementation class AddAnnouncement
 */
@MultipartConfig
public class AddAnnouncement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAnnouncement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/AddAnnouncement.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String Title = (String)request.getParameter("Title");
		String Body = (String)request.getParameter("Body");
		Date dateAnnouncment = new Date();
		Document Annonce = new Document().append("title",Title)
				.append("body", Body)
				.append("dateAnnouncement",dateAnnouncment);
		mongodbConnection.getCollection("Announcement").insertOne(Annonce);
		request.getRequestDispatcher("/Announcement?add=success").forward(request, response);		
		
}
}
