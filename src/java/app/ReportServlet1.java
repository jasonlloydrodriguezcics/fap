package app;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import record.CourseRecord;
import record.LoginRecord;
import record.StudentRecord;

public class ReportServlet1 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/pdf");
        HttpSession session = request.getSession();

        //get current user's detail
        LoginRecord currentUser = (LoginRecord) session.getAttribute("current");

        Document report = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(report, outputStream);

            Font font = FontFactory.getFont(BaseFont.HELVETICA, 14);

            report.open();

            font.setStyle(Font.BOLDITALIC);
            Paragraph title = new Paragraph(String.format("%s Report", currentUser.getRole()), font);
            title.setAlignment(1);
            report.add(title);
            report.add(new Paragraph("\n"));

            font.setSize(12f);
            font.setStyle(Font.NORMAL);

            //details of the PDF 
            ArrayList<CourseRecord> courseRecord = (ArrayList<CourseRecord>) session.getAttribute("courseRecord");
            if (currentUser.getRole().equalsIgnoreCase("admin")) {
                ArrayList<StudentRecord> studentRecord = (ArrayList<StudentRecord>) session.getAttribute("studentRecord");
            } else if (currentUser.getRole().equalsIgnoreCase("student")) {
                StudentRecord studentRecord = (StudentRecord) session.getAttribute("studentRecord");
            }

        } catch (DocumentException ex) {
            Logger.getLogger(ReportServlet1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
