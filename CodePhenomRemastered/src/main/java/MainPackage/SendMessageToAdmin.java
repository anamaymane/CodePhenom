package MainPackage;

import Dao.UserDao;
import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@WebServlet("/sendMessageToAdmin")
public class SendMessageToAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String sender = (String) request.getParameter("sender");
            String content = (String) request.getParameter("content");
            String object = (String) request.getParameter("object");
            if (sender != null && content != null && object != null) {

                Timestamp date = new Timestamp(System.currentTimeMillis());
                new UserDao().insertMessage("admin",sender,content,object,date);
                response.sendRedirect("./userArea");

            } else {
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
