package MainPackage;

import Dao.UserDao;
import Model.Message;
import Model.User;
import Session.SessionBean;
import org.bson.Document;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet("/userArea")
public class UserPageRedirection extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {


            if (SessionBean.session != null) {
                String username = SessionBean.session.getAttribute("username").toString();
                System.out.println("Username : " + username);
                List<Message> messagesReceived = new UserDao().getUserMessagesReceived(username);
                request.setAttribute("messageReceivedList", messagesReceived);

                User userDesc = new UserDao().getUserByUsername(username);
                System.out.println("Userdesc : "  + userDesc.getDescription());
                request.setAttribute("user", userDesc);

                request.getRequestDispatcher("/userPage.xhtml").forward(request, response);
            } else {
                request.getRequestDispatcher("/signIn.xhtml").forward(request, response);
            }
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
