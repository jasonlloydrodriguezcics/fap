package app;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import record.LoginRecord;

public class CaptchaServlet extends HttpServlet {

    private int length;

    @Override
    public void init(ServletConfig config) {
        this.length = Integer.parseInt(config.getInitParameter("length"));
    }

    private String generateCaptcha(int n) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        String captcha = "";

        while (n-- > 0) {
            captcha += characters.charAt((int) (Math.random() * 62));
        }

        return captcha;
    }

    private BufferedImage generateImage(String captcha) {
        BufferedImage image = new BufferedImage(captcha.length() * 500, 50, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.createGraphics();
        graphics.drawString(captcha, 20, 20);
        graphics.dispose();

        return image;
    }

//    private BufferedImage generateCaptchaImage(String captchaText) {
//        int charWidth = 25;
//        int padding = 20;
//        int width = captchaText.length() * charWidth + 2 * padding;
//        int height = 50;
//
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        Graphics2D g2d = image.createGraphics();
//
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//        g2d.setColor(Color.WHITE);
//        g2d.fillRect(0, 0, width, height);
//
//        Font font = new Font("Verdana", Font.BOLD, 24);
//        g2d.setFont(font);
//        g2d.setColor(Color.BLACK);
//
//        Random random = new Random();
//
//        for (int i = 0; i < captchaText.length(); i++) {
//            int x = padding + i * charWidth + random.nextInt(10) - 5;
//            int y = 30 + random.nextInt(10) - 5;
//            g2d.drawString(String.valueOf(captchaText.charAt(i)), x, y);
//        }
//
//        int numCurves = random.nextInt(5) + 1;
//        for (int i = 0; i < numCurves; i++) {
//            int x1 = random.nextInt(width);
//            int y1 = random.nextInt(height);
//            int ctrlx1 = random.nextInt(width);
//            int ctrly1 = random.nextInt(height);
//            int ctrlx2 = random.nextInt(width);
//            int ctrly2 = random.nextInt(height);
//            int x2 = random.nextInt(width);
//            int y2 = random.nextInt(height);
//
//            CubicCurve2D curve = new CubicCurve2D.Double();
//            curve.setCurve(x1, y1, ctrlx1, ctrly1, ctrlx2, ctrly2, x2, y2);
//            g2d.draw(curve);
//        }
//
//        g2d.dispose();
//
//        return image;
//    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("last-action", "captcha");

        if (session == null) {
            session.setAttribute("last-action", "login");
            request.setAttribute("message", "Session Does Not Exist");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        LoginRecord current = (LoginRecord) session.getAttribute("current-login");

        if (current == null) {
            session.setAttribute("last-action", "login");
            request.setAttribute("message", "User Has Not Logged In Yet");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        if (session.getAttribute("captcha-passed") != null) {
            if ((boolean) session.getAttribute("captcha-passed")) {
                request.setAttribute("message", "Captcha Has Already Been Answered");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }
        }

        session.setAttribute("captcha-passed", false);

        String captcha = (String) session.getAttribute("captcha");

        if (captcha == null) {
            captcha = generateCaptcha(this.length);
            session.setAttribute("captcha", captcha);
        }

        String userCaptcha = request.getParameter("captcha");

        if (userCaptcha == null) {
            OutputStream outputStream = response.getOutputStream();
            ImageIO.write(generateImage(captcha), "jpeg", outputStream);
            outputStream.close();
//            request.setAttribute("message", "Captcha Must Not Be Blank");
//            request.getRequestDispatcher("error.jsp").forward(request, response);
//            return;
            return;
        }

        if (!userCaptcha.equals(captcha)) {
            request.setAttribute("message", "Invalid Captcha, Try Again");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        session.setAttribute("captcha-passed", true);
        response.sendRedirect("home");
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
