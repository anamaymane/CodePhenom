package cpFinal;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
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
 * Servlet implementation class addProblem
 */
@MultipartConfig
public class addProblem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String PROBLEMS_PATH = "C:\\Users\\AIT RAMI\\github\\CodePhenomRemastered\\CpFinalArtifactAdmin\\src\\main\\webapp\\problems";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public addProblem() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/addProblem.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
				String problemName = request.getParameter("problemName");
				String problemId = request.getParameter("problemId");
				String Type = request.getParameter("Type");
				String memlimit = request.getParameter("memlimit");
				String timelimit = request.getParameter("timelimit");
				String problemtext = request.getParameter("problemtext");

				int points = Integer.parseInt(request.getParameter("points"));
			    Date dateAjout = new Date() ;
			    String visibility = request.getParameter("visibility");

		        if(DAO.isProblemIdExist(problemId) == 0) {
					Long Id = DAO.getMaxDocument("Problem");
					Id = Id+ 1 ;
				Document problem = new Document().append("name",problemName)
						.append("_id",Id)
						.append("problemId", problemId)
						.append("type",Type).append("problemText",problemtext)
						.append("memlimit",Integer.parseInt(memlimit))
						.append("timelimit",Integer.parseInt( timelimit))
						.append("difficulty", points)
						.append("dateAjout", dateAjout)
						.append("visible",visibility)
						.append("solved", 0)
						.append("commentaries", Arrays.asList());
				mongodbConnection.getCollection("Problem").insertOne(problem);


				String path = PROBLEMS_PATH;
				path = path + problemId ;
			    File file = new File(path);
			      //Creating the directory
			    file.mkdir();
				request.getRequestDispatcher("/problemSet?display=all").forward(request, response);
		        }
		        else {   
		            response.setIntHeader("Refresh",0);
		        }
			}
}