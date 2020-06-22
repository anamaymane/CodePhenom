package MainPackage;

import Dao.UserDao;
import org.apache.commons.io.FileUtils;
import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@MultipartConfig
@WebServlet("/signUpWork")
public class signUpWork extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String fullName = request.getParameter("fullName");
            String password = request.getParameter("password");
            Part filePart = request.getPart("fileName");
            System.out.println("usr : " + username + ", email : " + email + ", fullName : " + fullName + ", password: " + password);
            if (username != null && email != null && fullName != null && password != null && username.equals("Admin") == false) {
                InputStream fileContent = filePart.getInputStream();
                File targetFile = new File("/home/aym/Documents/gitHub/CodePhenomRemastered/CodePhenomRemastered/src/main/webapp/img/users/" + username);
                targetFile.createNewFile();

                int isUserRegistered = 0;

                isUserRegistered = new UserDao().isUserAlreadyRegistred(username, email);

                if (isUserRegistered == 0) {


                    new UserDao().insertUser(username, email, fullName, password);
                    FileUtils.copyInputStreamToFile(fileContent, targetFile);
                    request.getRequestDispatcher("/index.xhtml").forward(request, response);


                } else request.getRequestDispatcher("/register.xhtml").forward(request, response);

            } else {
                request.getRequestDispatcher("/404.xhtml").forward(request, response);
            }
        }
        catch (Exception e){
            System.out.print(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
