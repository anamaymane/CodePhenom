package MainPackage;

import Dao.ProblemDao;
import Dao.UserDao;
import Model.Problem;
import Model.User;
import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet("/problemSetRedirection")
public class ProblemSetRedirection extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String display = (String) request.getParameter("display");
            if (display != null) {
                String min, max;
                if (display.equals("difficulty")) {
                    min = (String) request.getParameter("min");
                    max = (String) request.getParameter("max");
                } else {
                    min = "";
                    max = "";
                }
                String type;
                if (display.equals("difficulty")) type = "null";
                else type = (String) request.getParameter("type");

                List<Problem> problemList = new ProblemDao().getProblems(display, min, max, type);
                request.setAttribute("problemList", problemList);

                List<User> topUsers = new UserDao().getUserOrderedByScore();
                request.setAttribute("topUsersList", topUsers);

                List<String> categories = new ProblemDao().getProblemsCategories();
                request.setAttribute("categories", categories);


                request.getRequestDispatcher("/problemSet.xhtml").forward(request, response);
            } else {
                request.getRequestDispatcher("/404.xhtml").forward(request, response);
            }

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String display = (String) request.getParameter("display");
            if (display != null) {
                List<Problem> problemList = new ProblemDao().getProblems("all",null,null,null);
                request.setAttribute("problemList", problemList);

                List<User> topUsers = new UserDao().getUserOrderedByScore();
                request.setAttribute("topUsersList", topUsers);

                List<String> categories = new ProblemDao().getProblemsCategories();
                request.setAttribute("categories", categories);

                request.getRequestDispatcher("/problemSet.xhtml").forward(request, response);
            } else {
                request.getRequestDispatcher("/404.xhtml").forward(request, response);
            }
        }
        catch(Exception e){

        }
    }
}
