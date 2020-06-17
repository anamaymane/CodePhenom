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

@WebServlet("/viewPersonnalSubmission")

public class ViewPersonnalSubmission extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // TODO Auto-generated method stub
            HttpSession session = request.getSession(false);
            if (session != null) {

                String username = session.getAttribute("username").toString();
                String problemId = request.getParameter("problemId");
                System.out.println("username = " + username + " id = " + problemId);
                List<Submission> submissions = new UserDao().getPersonnalSubmission(username,problemId);
                System.out.println("after");
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
