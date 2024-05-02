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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String captcha = request.getParameter("captcha");

        if (user.equals("") && pass.equals("")) {
            throw new NullValueException("Fields must not be blank.");
        }
        if (user.equals("")) {
            request.setAttribute("message", "Username Must Not Be Blank");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }
        if (pass.equals("")) {
            request.setAttribute("message", "Password Must Not Be Blank");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }
        if (captcha.equals("")) {
            request.setAttribute("message", "Captcha Must Not Be Blank");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        LoginRecord record = DatabaseManager.getLoginRecord(this.context, user);

        if (record == null) {
            request.setAttribute("message", "Account Not Found");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        try {
            pass = Cryptographer.encrypt(this.context, pass);
        } catch (Exception exception) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, exception);
        }

        if (!pass.equals(record.getPass())) {
            request.setAttribute("message", "Invalid Password");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        if (!captcha.equals(session.getAttribute("captcha"))) {
            request.setAttribute("message", "Invalid Captcha");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        session.setAttribute("current-login", record);
        response.sendRedirect("home");
    }
}
