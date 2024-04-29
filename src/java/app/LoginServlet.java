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
import utility.Connector;
import utility.Cryptographer;

public class LoginServlet extends HttpServlet {

    private ServletContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // invalid login method temp only
        String requestUser = request.getParameter("user");
        String requestPass = request.getParameter("pass");

        LoginRecord record = Connector.getLoginRecord(this.context, requestUser);

        HttpSession session = request.getSession(true);

        if (requestUser.equals("") || requestPass.equals("")) {
            throw new NullValueException("Fields must not be blank.");
        }

        if (record == null) {
            // user not found or some shit
            return;
        }

        try {
            requestPass = Cryptographer.encrypt(this.context, requestPass);
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (requestPass.equals(record.getPass())) {
            // passed every test
            session.setAttribute("current", record);
        } else {
            // invalid password ka bro
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
