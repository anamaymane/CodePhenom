package MainPackage;

import Dao.ProblemDao;
import Model.Problem;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;

@WebServlet("/problemPageRedirection")
public class ProblemPageRedirection extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String problemId = (String) request.getParameter("id");
            if (problemId != null) {

                Problem problem = new ProblemDao().getProblemById(Long.parseLong(problemId));
                System.out.println("here");
                request.setAttribute("problem", problem);
                request.getRequestDispatcher("/problemPage.xhtml").forward(request, response);

            } else {
                request.getRequestDispatcher("/404.xhtml").forward(request, response);
            }

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
