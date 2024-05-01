package app;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import record.LoginRecord;

public class HomeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            LoginRecord current = (LoginRecord) session.getAttribute("current-login");

            if (session.getAttribute("last-action") != null) {
                String lastAction = (String) session.getAttribute("last-action");

                if (lastAction.equals("login")) {
                    response.sendRedirect("login");
                    return;
                }
                if (lastAction.equals("captcha")) {
                    response.sendRedirect("captcha");
                    return;
                }
            }

            if (session.getAttribute("captcha-passed") != null) {
                if (current != null && (boolean) session.getAttribute("captcha-passed")) {
                    request.getRequestDispatcher("home.jsp").forward(request, response);
                    return;
                }
            }
        }

        response.sendRedirect("login");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
