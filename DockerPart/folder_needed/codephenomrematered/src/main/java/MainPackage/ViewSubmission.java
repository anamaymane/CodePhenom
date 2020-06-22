package MainPackage;

import Dao.UserDao;
import Model.Submission;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewSubmission")
public class ViewSubmission extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("begin");
            String problemId = request.getParameter("problemId").toString();
            if (problemId != null){
                System.out.println("not null");
                List<Submission> submissions = new UserDao().getSubmission(problemId);
                request.setAttribute("submissions",submissions);
                request.getRequestDispatcher("/viewSubmission.xhtml").forward(request, response);
            }
            else {
                request.getRequestDispatcher("/404.xhtml").forward(request, response);
            }


        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
