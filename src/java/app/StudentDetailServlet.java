
package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import utility.DatabaseManager;

@WebServlet(name = "StudentDetailServlet", urlPatterns = {"/StudentDetailServlet"})
public class StudentDetailServlet extends HttpServlet {

    private ServletContext context;
    
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
        DatabaseManager.encryptAllLoginPasswords(context);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //check user role to know the allowed credential and details viewing
        HttpSession session = request.getSession();
        LoginRecord record = (LoginRecord) session.getAttribute("current-login");
        
        if (record.getRole().equalsIgnoreCase("admin")) {
            ArrayList<StudentRecord> studentRecord = DatabaseManager.getAllStudentRecords(context);
            ArrayList<CourseRecord> courseList = DatabaseManager.getCourseList(context);
            session.setAttribute("studentRecord", studentRecord);
            session.setAttribute("courseList", courseList);
            System.out.println("student training: " + studentRecord.toString());
        }
        
        else if(record.getRole().equalsIgnoreCase("student")) {
            StudentRecord studentRecord = DatabaseManager.getStudentRecord(context, record.getUser());
            ArrayList<CourseRecord> courseList = DatabaseManager.getCourseRecord(context, record.getUser());
            session.setAttribute("studentRecord", studentRecord);
            session.setAttribute("courseList", courseList);
            System.out.println("student training: " + studentRecord.getTraining());
        }
         
        response.sendRedirect("details.jsp");
            
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoginRecord record = (LoginRecord) session.getAttribute("current-login");
        
        if (record.getRole().equalsIgnoreCase("admin")) {
            ArrayList<StudentRecord> studentRecord = DatabaseManager.getAllStudentRecords(context);
            ArrayList<CourseRecord> courseList = DatabaseManager.getCourseList(context);
            session.setAttribute("studentRecord", studentRecord);
            session.setAttribute("courseList", courseList);
            System.out.println("student training: " + studentRecord.toString());
        }
        
        else if(record.getRole().equalsIgnoreCase("student")) {
            StudentRecord studentRecord = DatabaseManager.getStudentRecord(context, record.getUser());
            ArrayList<CourseRecord> courseList = DatabaseManager.getCourseRecord(context, record.getUser());
            session.setAttribute("studentRecord", studentRecord);
            session.setAttribute("courseList", courseList);
            System.out.println("student training: " + studentRecord.getTraining());
        }
         
        response.sendRedirect("details.jsp");
    }

    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
