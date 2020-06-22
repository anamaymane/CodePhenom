package cpFinal;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class editUser
 */
public class editUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public editUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Iterator<org.bson.Document> cursorUserData = DAO
				.getUserDescription((String) request.getParameter("username"));
		String description = "None", score = "0", username = "None", fullName = "None", email = "None";
		String dateRegistration = "Non", lastLogin = "None";
		while (cursorUserData.hasNext()) {
			org.bson.Document elementuserData = cursorUserData.next();
			description = elementuserData.get("description").toString();
			score = elementuserData.get("score").toString();
			username = elementuserData.get("username").toString();
			fullName = elementuserData.get("fullName").toString();
			email = elementuserData.get("email").toString();

			dateRegistration = elementuserData.get("dateRegistration").toString();
			lastLogin = elementuserData.get("lastRegistration").toString();

		}
		request.setAttribute("description", description);
		request.setAttribute("score", score);
		request.setAttribute("fullName", fullName);
		request.setAttribute("email", email);
		request.setAttribute("username", username);
		request.setAttribute("dateRegistration", dateRegistration);
		request.setAttribute("lastLogin", lastLogin);
		request.getRequestDispatcher("editUser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
