package app;

import exception.NullValueException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import record.LoginRecord;
import utility.Cryptographer;
import utility.DatabaseManager;

public class LoginServlet extends HttpServlet {

    private ServletContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
        DatabaseManager.encryptAllLoginPasswords(context);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestUser = request.getParameter("user");
        String requestPass = request.getParameter("pass");

        if (requestUser.equals("") && requestPass.equals("")) {
            throw new NullValueException("Fields must not be blank.");
        }
        if (requestUser.equals("")) {
            request.setAttribute("message", "Username Must Not Be Blank");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }
        if (requestPass.equals("")) {
            request.setAttribute("message", "Password Must Not Be Blank");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        LoginRecord record = DatabaseManager.getLoginRecord(this.context, requestUser);

        if (record == null) {
            request.setAttribute("message", "Account Not Found");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession(true);

        try {
            requestPass = Cryptographer.encrypt(this.context, requestPass);
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (requestPass.equals(record.getPass())) {
            session.setAttribute("current", record);
            response.sendRedirect("captcha");
        } else {
            request.setAttribute("message", "Invalid Password");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
