package cpFinal;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

/**
 * Servlet implementation class SendMessage
 */
public class SendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String receiver = (String)request.getParameter("receiver");
		String sender = (String)request.getParameter("sender");
		String content = (String)request.getParameter("content");
		String object = (String)request.getParameter("object");
		if(receiver != null && sender != null && content != null && object != null) {
		Date date = new Date();
		if(DAO.isUsernameRegistered(receiver) == 1) {
		Document message = new Document().append("receiver",receiver).append("sender", sender).append("content",content).append("object",object).append("date", date);
		mongodbConnection.getCollection("Message").insertOne(message);
		}
		response.sendRedirect("./MailBox");
		} else {
			request.getRequestDispatcher("/404.jsp").forward(request, response);
		}
	}

}
