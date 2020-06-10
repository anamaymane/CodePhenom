package MainPackage;

import Dao.UserDao;
import Model.User;
import Session.SessionBean;
import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

@WebServlet("/SignInWork")
public class SignInWork extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            if (email != null && password != null) {
               System.out.println("email : " + email + " password : "+ password);
                User user = new UserDao().getUser(email, password);
                if(user != null){
                    //
                    HttpSession session = request.getSession();
                    session.setAttribute("userId",user.getUserId());
                    session.setAttribute("username",user.getUsername());
                    session.setAttribute("email",user.getEmail());
                    session.setAttribute("fullName",user.getFullName());
                    session.setAttribute("description",user.getDescription());
                    System.out.println("entered here");
                }

                request.getRequestDispatcher("/index.xhtml").forward(request, response);
            } else {
                request.getRequestDispatcher("/404.xhtml").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
