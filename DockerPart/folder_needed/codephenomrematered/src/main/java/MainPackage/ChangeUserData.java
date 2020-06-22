package MainPackage;

import Dao.UserDao;
import com.mongodb.BasicDBObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

@WebServlet("/changeUserData")
public class ChangeUserData extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    try{
        HttpSession session = request.getSession(false);
        if(session != null) {

            String email = session.getAttribute("email").toString();
            String username = session.getAttribute("username").toString();
            String password = request.getParameter("password");
            String oldPassword = request.getParameter("oldPassword");
            String description = request.getParameter("description");

            if(new UserDao().getUser(email,oldPassword) != null) {

                if (!password.equals("keep")) {
                    new UserDao().changeUserPassword(username, password);
                }
                if (!description.equals("keep")) {
                    new UserDao().changeUserDescription(username, description);
                }
                response.sendRedirect("./userArea");
            }

            else {
                response.sendRedirect("./userArea");
                response.getWriter().println("<script>>alert('Your old password is incorrect')</script>");
            }


        }
        else {
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
    }
    catch(Exception e){
        System.out.println(e.getMessage());
    }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
