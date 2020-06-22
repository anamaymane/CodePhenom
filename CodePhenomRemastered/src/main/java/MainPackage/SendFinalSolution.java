package MainPackage;

import Dao.ProblemDao;
import Model.Problem;
import Model.Submission;
import Utility.Dockerisation;
import com.mongodb.BasicDBObject;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/sendFinalSolution")
public class SendFinalSolution extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String programmingLanguage = request.getParameter("programmingLanguage");
            String codeSource = request.getParameter("codeSource");
            String username = request.getParameter("username");
            String problemId = request.getParameter("problemId");

            System.out.print("problemId : " + problemId + " programmingLanguage : " + programmingLanguage);
            if (programmingLanguage != null && codeSource != null && username != null && problemId != null) {
                String category = new ProblemDao().getProblemCategoryFromId(problemId);
                Submission submission = new Submission();
                submission.setProblemId(problemId);
                submission.setCodeSource(codeSource);
                submission.setUsername(username);
                submission.setDateSubmission(new Timestamp(System.currentTimeMillis()));
                submission.setLanguageName(programmingLanguage);
                submission.setMemoryResult("waiting");
                submission.setTimeResult("waiting");
                submission.setVerdict("Waiting for results");
                submission.setType(category);
                submission.setTotalVerdict("waiting");

                new ProblemDao().insertSubmission(submission);

                String fileName = String.valueOf(submission.getSubmissionId());

                //
                //Writing to files

                PrintWriter writer = new PrintWriter(new File("/submissions/" + fileName));
                writer.write(codeSource);
                writer.close();
                //Getting time and memory
                Problem currentProblem = new ProblemDao().getProblemByProblemId(problemId);
                int timeLimit = currentProblem.getTimelimit();
                int MemLimit = currentProblem.getMemlimit();

                timeLimit =  timeLimit * 1000;
                MemLimit = MemLimit * 1024 * 1024;

                PrintWriter wr = new PrintWriter(new File("/submissions/test"));
                wr.println("timeLimit : " + timeLimit + "\nMemLimit + "+ MemLimit + "\n\n\n\n\n\n\n\n\n\n\n");
                wr.close();
                //Waiting for result from the container
                Dockerisation dockerInvoker = new Dockerisation(programmingLanguage,fileName,problemId,Integer.toString(timeLimit),Integer.toString(MemLimit));
                while(new File("/submissions/" + fileName + "_result").exists() == false) {

                }
                BufferedReader reader = new BufferedReader(new FileReader("/submissions/" + fileName + "_result"));
                String line = reader.readLine();
                String memoryResult = "";
                String timeResult = "";
                String verdict = "";
                String totalVerdict = "";
                while (line != null) {
                    if(line.equals("CE")) {
                        totalVerdict = "Compilation error"; memoryResult= "0"; timeResult = "0"; verdict = verdict;
                    }
                    else if(line.contains("TLE")) {
                        String[] parts = line.split(":");
                        totalVerdict = "Time limit exceeded on test :" + parts[1]; memoryResult= parts[3]; timeResult = parts[2]; verdict = "Time limit exceeded";
                    }
                    else if(line.contains("WA")) {
                        String[] parts = line.split(":");
                        totalVerdict = "Wrong answer on test :" + parts[1]; memoryResult= parts[3]; timeResult = parts[2]; verdict ="Wrong answer" ;
                    }
                    else if(line.contains("MLE")) {
                        String[] parts = line.split(":");
                        totalVerdict = "Memory limit exceeded on test :" + parts[1]; memoryResult= parts[3]; timeResult = parts[2]; verdict = "Memory limit exceeded";
                    }
                    else if(line.contains("RTE")) {
                        String[] parts = line.split(":");
                        totalVerdict = "Runtime error on test :" + parts[1]; memoryResult= "0"; timeResult = "0"; verdict = "Runtime error";
                    }
                    else if(line.contains("Accepted")) {
                        String[] parts = line.split(":");
                        totalVerdict = "Accepted"; memoryResult= parts[2]; timeResult = parts[1]; verdict = "Accepted";
                    }
                    line = reader.readLine();
                }

                if(verdict.equals("Compilation error") == false && verdict.equals("Runtime error") == false) {
                    memoryResult = String.valueOf((Integer.parseInt(memoryResult) / 1000));
                }
                new ProblemDao().updateSubmission(submission.getSubmissionId(),memoryResult,timeResult,verdict,totalVerdict);

                response.sendRedirect("./viewSubmission?problemId=" + currentProblem.getProblemId());
                //
            } else {
                request.getRequestDispatcher("/404.xhtml").forward(request, response);
            }

        }
        catch(Exception e){
            System.out.print(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
