package cpFinal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.io.FileUtils;
import org.bson.Document;


/**
 * Servlet implementation class addUser
 */
@MultipartConfig
public class addUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.getRequestDispatcher("/addUser.jsp").forward(request, response);

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		long id = DAO.getMaxDocument("User") + 1;
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		//Part filePart = request.getPart("fileName");
		String description = "user Description";
		String score = "0";
	    //InputStream fileContent = filePart.getInputStream();
	    //File targetFile = new File("C:\\Users\\AIT RAMI\\github\\CodePhenomRemastered\\CpFinalArtifactAdmin\\src\\main\\webapp\\img\\users\\default_user_pic");
	    //targetFile.createNewFile();
	    //FileUtils.copyInputStreamToFile(fileContent, targetFile);
	    Date dateRegistration = new Date() ;
	    Date lastLogin = new Date();
        if(DAO.isUserRegisteredToPlateform(username,email) == 0) {
		Document user = new Document().append("_id", id).append("email",email).append("username", username)
				.append("password",password).append("fullName",fullName)
				.append("score", score).append("description", description)
				.append("lastRegistration
", lastLogin).append("dateRegistration", dateRegistration)
				.append("role","user");
		mongodbConnection.getCollection("User").insertOne(user);
		request.getRequestDispatcher("/Users.jsp?add=success").forward(request, response);
        }
        else {       
            response.setIntHeader("Refresh",0);
        }
	}
}
