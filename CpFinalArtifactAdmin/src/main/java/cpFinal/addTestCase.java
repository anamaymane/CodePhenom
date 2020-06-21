
package cpFinal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.apache.commons.io.FileUtils;
import org.bson.Document;

/**
 * Servlet implementation class addTestCase
 */
@MultipartConfig

public class addTestCase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "/home/sabir/projects/CodePhenomRemastered/CpFinalArtifactAdmin/src/main/webapp/problems/";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addTestCase() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String problemId = (String)request.getParameter("problemId");
		request.setAttribute("problemId", problemId);
		request.getRequestDispatcher("./addTestCase.jsp").forward(request, response);

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String problemId = request.getParameter("problemId");
		Part inputfile = request.getPart("inputfile");
		Part outputfile = request.getPart("outputfile");
		String typeOfTest=request.getParameter("typeOfTest");
		
	    InputStream fileinputContent = inputfile.getInputStream();
	    InputStream fileoutputContent = outputfile.getInputStream();
	    int numberTest = DAO.getNumberOfTestCases(problemId)+1;
	    
	    String in =  UPLOAD_DIRECTORY +problemId+"/"+ numberTest +".in";
	    String out = UPLOAD_DIRECTORY +problemId+"/"+ numberTest +".out";
	    
	    String pathForDownin =  "problems/"+problemId + "/"+numberTest+".in";
	    String pathForDownout = "problems/"+problemId + "/"+numberTest+".out";
	    
	    System.out.println(in);
	    System.out.println(out);
	    
	    File targetFile = new File(in);
	    File targetFileo = new File(out);

		try{
			boolean sucess1 = targetFile.createNewFile();
			boolean sucess2   = targetFileo.createNewFile();
			System.out.println(sucess1);
			System.out.println(sucess2);

		}catch(Exception e){}

	    
	    FileUtils.copyInputStreamToFile(fileinputContent, targetFile);
	    FileUtils.copyInputStreamToFile(fileoutputContent, targetFileo);
	    Date DateAjout =new Date();
	    DAO.savefileIntoMongoDB(problemId, typeOfTest, pathForDownin, pathForDownout,DateAjout);
	    
	    response.sendRedirect("./problemSet?display=all");
	}
}
