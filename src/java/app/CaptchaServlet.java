package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        int width = captcha.length() * 50;
        int height = 50;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.fillRect(0, 0, width, height);
        graphics.setFont(new Font("Arial", Font.BOLD, 30));
        graphics.setColor(Color.BLACK);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Random random = new Random();

        for (int i = 0; i < captcha.length(); i++) {
            graphics.drawString(String.valueOf(captcha.charAt(i)), captcha.length() * 10 + i * 25 + random.nextInt(10), 20 + random.nextInt(20));
        }

        graphics.dispose();

        return image;
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String captcha = generateCaptcha(this.length);

        HttpSession session = request.getSession(false);
        session.setAttribute("captcha", captcha);

        response.setContentType("image/jpeg");
        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(generateImage(captcha), "jpeg", outputStream);
        outputStream.close();
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
