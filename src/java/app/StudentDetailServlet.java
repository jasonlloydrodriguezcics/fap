
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
import record.LoginRecord;
import record.StudentRecord;
import utility.DatabaseManager;

/**
 *
 * @author charm
 */
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
        LoginRecord record = (LoginRecord) session.getAttribute("current");
        
        if (record.getRole().equalsIgnoreCase("admin")) {
            ArrayList<StudentRecord> studentRecords = DatabaseManager.getAllStudentRecords(context);
        }
        
        else if(record.getRole().equalsIgnoreCase("student")) {
            StudentRecord studentRecord = DatabaseManager.getStudentRecord(context, record.getUser());
        }
         
        System.out.println("Send to Homepage");
            
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
