package cpFinal;


import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.model.Filters;

import cpFinal.DAO;
import cpFinal.mongodbConnection;

/**
 * Servlet implementation class EditAnnouncement
 */
@MultipartConfig
public class EditAnnouncement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAnnouncement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Id = (String)request.getParameter("id");
		Iterator<org.bson.Document> cursorAnnonceData = DAO.getAnnouncementInfo(Id);
		String title = "None", Body ="None";
		Date dateAnnouncement = new Date();
		while (cursorAnnonceData.hasNext()) {
			org.bson.Document elementAnnoncerData =  cursorAnnonceData.next();
			title = elementAnnoncerData.get("title").toString();
			Body =  elementAnnoncerData.get("Body").toString();
			dateAnnouncement = (Date) elementAnnoncerData.get("BodateAnnouncement");
		}
		request.setAttribute("id", Id);
		request.setAttribute("Body", Body);
		request.setAttribute("title", title);
		request.setAttribute("dateAnnouncement", dateAnnouncement);
		request.getRequestDispatcher("/EditAnnouncement.jsp").forward(request, response);

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String title = request.getParameter("titre");
		System.out.println("le titre est:"+title);
		String Body = request.getParameter("Body");
		String id =  request.getParameter("id");
		Document Announcemdoc = new Document().append("title",title)
				.append("Body", Body);		
		 mongodbConnection.getCollection("announcement").updateOne(Filters.eq("_id", id),
				new Document("$set", Announcemdoc));
		request.getRequestDispatcher("/Announcement?edit=success").forward(request, response);
	}

}
