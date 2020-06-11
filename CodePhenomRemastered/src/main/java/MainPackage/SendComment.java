package MainPackage;

import Dao.ProblemDao;
import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/sendComment")
public class SendComment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String username = request.getParameter("username");
            String content = request.getParameter("content");
            String _id = request.getParameter("id");
            System.out.println("username : " + username + " / ccontent = " + content + " _id : " + _id);
            if(username != null && content != null && _id != null) {
                try {

                    new ProblemDao().insertCommentIntoProblem(Long.valueOf(_id),username,content);
                    response.sendRedirect("./problemPageRedirection?id="+_id);

                }catch(Exception e) {
                    System.out.println(e);
                }
            }
            else {
                request.getRequestDispatcher("/404.xhtml").forward(request, response);
            }
        }
        catch(Exception e){

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
