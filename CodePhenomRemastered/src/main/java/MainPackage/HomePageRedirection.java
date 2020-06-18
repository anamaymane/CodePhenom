package MainPackage;

import Dao.AnnouncementDao;
import Model.Announcement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("")
public class HomePageRedirection extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //new UserInsert().setUpEntityManagerFactoryAndPopulateTheDatastore();

            request.getRequestDispatcher("/index.xhtml").forward(request, response);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
