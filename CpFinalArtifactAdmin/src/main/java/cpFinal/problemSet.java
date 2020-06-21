
package cpFinal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

/**
 * Servlet implementation class problemSetRedirection
 */
public class problemSet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String display = (String) request.getParameter("display");
		Iterator<org.bson.Document> cursor = DAO.problemListforAdmin(display, "null", "null", "null");
		request.setAttribute("problemList", cursor);
		/*
		 * Iterator<org.bson.Document> cursor2 = functionController.topUsersList();
		 * request.setAttribute("topUsersList", cursor2);
		 */
		ArrayList<Document> cursor3 = DAO.problemCategory();
		request.setAttribute("categoryList", cursor3);

		request.getRequestDispatcher("/problemSet.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String display = (String) request.getParameter("display");
		String min, max;
		if (display.equals("difficulty")) {
			min = (String) request.getParameter("min");
			max = (String) request.getParameter("max");
		} else {
			min = "";
			max = "";
		}
		String type;
		if (display.equals("difficulty"))
			type = "null";
		else
			type = (String) request.getParameter("type");
		Iterator<org.bson.Document> cursor = DAO.problemListforAdmin(display, min, max, type);
		request.setAttribute("problemList", cursor);
		/*
		 * Iterator<org.bson.Document> cursor2 = functionController.topUsersList();
		 * request.setAttribute("topUsersList", cursor2);
		 */
		ArrayList<Document> cursor3 = DAO.problemCategory();
		request.setAttribute("categoryList", cursor3);
		request.getRequestDispatcher("/problemSet.jsp").forward(request, response);
	}

}