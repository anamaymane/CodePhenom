package cpFinal;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteTestCase
 */
public class DeleteTestCase extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public String TEST_CASES_PATH = "/home/aym/jee_workspace/CpFinalArtifactAdmin/src/main/webapp/";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTestCase() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		String testCaseId = (String)request.getParameter("id");
		String ProblemId= (String)request.getParameter("problemId");
		
		Iterator<org.bson.Document> TestCaseCursor = DAO.getTestCaseInfo(testCaseId);
		String inPutFilePath = "None", outPutFilePath ="None";
		
		while (TestCaseCursor.hasNext()) {
			org.bson.Document elementuserData =  TestCaseCursor.next();
			inPutFilePath = elementuserData.get("input").toString();
			outPutFilePath =  elementuserData.get("output").toString();
			File inputFile = new File(TEST_CASES_PATH + inPutFilePath);
			File ouputFile = new File(TEST_CASES_PATH + outPutFilePath);
			inputFile.delete() ;
			ouputFile.delete() ;
		}
		int res = DAO.deleteTestCase(testCaseId);
		if (res == 1) {
			
			response.sendRedirect("./problemSet?display=all&delete=success");
		}
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
