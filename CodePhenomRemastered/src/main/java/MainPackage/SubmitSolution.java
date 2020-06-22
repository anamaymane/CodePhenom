package MainPackage;

import Dao.ProblemDao;
import Dao.UserDao;
import Model.AvailableLanguage;
import org.apache.maven.plugin.logging.SystemStreamLog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/submitSolution")
public class SubmitSolution extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                if (request.getParameter("problemId") != null && request.getParameter("username") != null) {

                    List<AvailableLanguage> languagesName = new ProblemDao().getAvailableLanguages();
                    String problemId = request.getParameter("problemId");
                    request.setAttribute("languagesName", languagesName);
                    request.setAttribute("problemId", problemId);
                    request.getRequestDispatcher("/submitSolution.xhtml").forward(request, response);

                } else request.getRequestDispatcher("/404.xhtml").forward(request, response);
            }
            else{
                response.sendRedirect("./signIn");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
