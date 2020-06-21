package cpFinal;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.UnknownHostException;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoException;
import com.mongodb.client.model.Filters;

import org.bson.Document;
@MultipartConfig
/**
 * Servlet implementation class editUser
 */
public class editProblem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editProblem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Iterator<org.bson.Document> cursorUserData = DAO.getproblemInfo((String)request.getParameter("problemId"));
		String name = "None", problemtext ="None",problemId="None";
		String  solved = "None", visible="visible", dateAjout ="dateAjout", type="0";
		int  timelimit = 0, difficulty=0, memlimit = 0;
		while (cursorUserData.hasNext()) {
			org.bson.Document elementuserData =  cursorUserData.next();
			type = elementuserData.get("type").toString();
			name =  elementuserData.get("name").toString();
			problemId =  elementuserData.get("problemId").toString();
			problemtext = elementuserData.get("problemText").toString();
			memlimit =  (int) elementuserData.get("memlimit");
			solved = elementuserData.get("solved").toString();
			difficulty = (int) elementuserData.get("difficulty") ;
			visible = elementuserData.get("visible").toString();
			dateAjout = elementuserData.get("dateAjout").toString();
			timelimit = (int)elementuserData.get("timelimit");
		}

		request.setAttribute("name", name);
		request.setAttribute("score", problemId);
		request.setAttribute("problemtext", problemtext);
		request.setAttribute("memlimit", memlimit);
		request.setAttribute("timelimit", timelimit);
		request.setAttribute("difficulty", difficulty);
		request.setAttribute("dateAjout",dateAjout);
		request.setAttribute("solved", solved);
		request.setAttribute("type", type);
		request.setAttribute("problemId",problemId);
		request.getRequestDispatcher("editProblem.jsp").forward(request, response);
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
				System.out.println(memlimit);

				String timelimit = request.getParameter("timelimit").toString();
				String problemtext = request.getParameter("problemtext");
				
				String difficulty = request.getParameter("points").toString();
			    String visibility = request.getParameter("visibility");
			    	
				Document problemdoc = new Document().append("name",problemName)
						.append("problemTitle", problemName)
						.append("problemId", problemId)
						.append("type",Type).append("problemtext",problemtext)
						.append("memlimit",Integer.parseInt(memlimit))
						.append("timelimit", Integer.parseInt(timelimit))
						.append("difficulty", Integer.parseInt(difficulty))
						.append("visible",visibility);
				
				 mongodbConnection.getCollection("Problem").updateMany(Filters.eq("problemId", problemId),
						new Document("$set", problemdoc));
				
				request.getRequestDispatcher("/problemSet?display=all").forward(request, response);
		        }  

			}

