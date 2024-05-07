package app;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import record.LoginRecord;
import record.StudentRecord;
import utility.DatabaseManager;

public class StudentDetailServlet extends HttpServlet {

    private ServletContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoginRecord record = (LoginRecord) session.getAttribute("current-login");

        if (record.getRole().equalsIgnoreCase("Trainor")) {
            request.setAttribute("student-records", DatabaseManager.getAllStudentRecords(context));
            request.setAttribute("training-records", DatabaseManager.getTrainingRecords(context));
        } else if (record.getRole().equalsIgnoreCase("Student")) {
            StudentRecord studentRecord = DatabaseManager.getStudentRecord(context, record.getUsername());
            request.setAttribute("student-record", studentRecord);
            System.out.println(studentRecord.getTrainingId());
            request.setAttribute("course-records", DatabaseManager.getCourseRecords(context, String.valueOf(studentRecord.getTrainingId())));
        } else if (record.getRole().equalsIgnoreCase("Admin")) {
            request.setAttribute("login-records", DatabaseManager.getAllLoginRecords(context));
            request.setAttribute("student-records", DatabaseManager.getAllStudentRecords(context));
            request.setAttribute("training-records", DatabaseManager.getTrainingRecords(context));
        }

        request.getRequestDispatcher("details.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
