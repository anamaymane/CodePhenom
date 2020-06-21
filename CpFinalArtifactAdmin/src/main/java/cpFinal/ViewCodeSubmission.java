package cpFinal;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewCodeSubmission
 */
public class ViewCodeSubmission extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewCodeSubmission() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Iterator<org.bson.Document> cursorUserData = DAO.getSubmissionDescription((String)request.getParameter("submissionId"));
		String username = "None",problemId= "0",verdict= "None",codeSource ="None";
		String dateSubmissions="None", timeResult="0", memoryResult="0";
		System.out.println((String)request.getParameter("submissionId"));
		while (cursorUserData.hasNext()) {
			org.bson.Document elementuserData =  cursorUserData.next();
			problemId =  elementuserData.get("problemId").toString();
			verdict =  elementuserData.get("verdict").toString();
			username = elementuserData.get("username").toString();
			timeResult = elementuserData.get("timeResult").toString();
			memoryResult = elementuserData.get("memoryResult").toString();
			codeSource = elementuserData.get("codeSource").toString();
			dateSubmissions = elementuserData.get("dateSubmission").toString();

		}

		request.setAttribute("problemId", problemId);
		request.setAttribute("verdict", verdict);
		request.setAttribute("codeSource", codeSource);
		request.setAttribute("dateSubmissions",dateSubmissions);
		request.setAttribute("username", username);
		request.setAttribute("timeResult", timeResult);
		request.setAttribute("memoryResult", memoryResult);
		request.setAttribute("submissionId",(String)request.getParameter("submissionId"));
		
		request.getRequestDispatcher("viewCodeSubmission.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
