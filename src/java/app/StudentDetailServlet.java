
package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import record.CourseRecord;
import record.LoginRecord;
import record.StudentRecord;
import record.TrainingRecord;
import utility.DatabaseManager;

@WebServlet(name = "StudentDetailServlet", urlPatterns = {"/StudentDetailServlet"})
public class StudentDetailServlet extends HttpServlet {

    private ServletContext context;
    
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
        DatabaseManager.encryptAllLoginPasswords(context);
    }
    
     protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         HttpSession session = request.getSession();
        LoginRecord record = (LoginRecord) session.getAttribute("current-login");
        
        if (record.getRole().equalsIgnoreCase("trainor")) {
            ArrayList<StudentRecord> studentRecord = DatabaseManager.getAllStudentRecords(context);
            ArrayList<TrainingRecord> courseList = DatabaseManager.getCourseList(context);
            session.setAttribute("studentRecord", studentRecord);
            session.setAttribute("courseList", courseList);
            System.out.println("student training: " + studentRecord.toString());
        }
        
        else if (record.getRole().equalsIgnoreCase("student")) {
            StudentRecord studentRecord = DatabaseManager.getStudentRecord(context, record.getUsername());
            String training = studentRecord.getTraining();
            ArrayList<TrainingRecord> courseList = DatabaseManager.getCourseRecord(context, training);
            System.out.println("Training: "+studentRecord.getTraining());
            session.setAttribute("studentRecord", studentRecord);
            session.setAttribute("courseList", courseList);
        }
        
        else if (record.getRole().equalsIgnoreCase("admin")) {
            ArrayList<LoginRecord> loginRecords = DatabaseManager.getAllLoginRecords(context);
            ArrayList<StudentRecord> studentRecord = DatabaseManager.getAllStudentRecords(context);
            ArrayList<TrainingRecord> courseList = DatabaseManager.getCourseList(context);
            session.setAttribute("loginRecord", loginRecords);
            session.setAttribute("studentRecord", studentRecord);
            session.setAttribute("courseList", courseList);
            System.out.println("student training: " + studentRecord.toString());
        }
         
        response.sendRedirect("details.jsp");
     }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       processRequest(request, response);
            
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
