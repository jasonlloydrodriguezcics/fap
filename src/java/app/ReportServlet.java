package app;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import exception.InvalidDateException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

public class ReportServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setDateHeader("Expires", 0);

            HttpSession session = request.getSession(false);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String inputStartDate = request.getParameter("start-date");
            String inputEndDate = request.getParameter("end-date");

            if (inputStartDate.equals("") || inputEndDate.equals("")) {
                throw new InvalidDateException("Fields must not be blank.");
            }

            Date startDate = dateFormat.parse(inputStartDate);
            Date endDate = dateFormat.parse(inputEndDate);

            if (endDate.before(startDate)) {
                throw new InvalidDateException("Start Date Must Occur Before End Date");
            }

            System.out.println("try");
            System.out.println(dateFormat.format(dateFormat.parse((String) request.getParameter("start-date"))));

//        if (session.getAttribute("passed") == null) {
//            response.sendRedirect(root + "/error/error_session.jsp");
//            return;
//
//        }
//
//        if (!(boolean) session.getAttribute("passed")) {
//            response.sendRedirect(root + "/error/error_session.jsp");
//            return;
//        }
//
//        Record current = (Record) session.getAttribute("current");
//
//        if (current == null) {
//            response.sendRedirect(root + "/error/error_session.jsp");
//            return;
//        }
//
//        Document report = new Document(PageSize.LETTER);
//
//        if (current.getRole().equals("Guest")) {
//            report = new Document(PageSize.A7);
//        }
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        PdfWriter.getInstance(report, outputStream);
//        Font font = FontFactory.getFont(BaseFont.HELVETICA, 14);
//
//        report.open();
//
//        font.setStyle(Font.BOLDITALIC);
//        Paragraph title = new Paragraph(String.format("%s Report", current.getRole()), font);
//        title.setAlignment(1);
//        report.add(title);
//        report.add(new Paragraph("\n"));
//
//        font.setSize(12f);
//        font.setStyle(Font.NORMAL);
//        if (current.getRole().equals("Admin")) {
//            PdfPTable recordTable = new PdfPTable(3);
//            ArrayList<Record> records = Connector.getAllRecords(this.context);
//
//            for (int i = 0; i < records.size(); i++) {
//                recordTable.addCell(new Phrase(String.valueOf(i + 1), font));
//                recordTable.addCell(new Phrase(records.get(i).getUser(), font));
//                recordTable.addCell(new Phrase(records.get(i).getRole(), font));
//            }
//
//            report.add(recordTable);
//        } else {
//            Paragraph userParagraph = new Paragraph(String.format("USERNAME: %s", current.getUser()));
//            userParagraph.setFont(font);
//            userParagraph.setAlignment(1);
//            report.add(userParagraph);
//
//            Paragraph passParagraph = new Paragraph(String.format("PASSWORD: %s", Cryptographer.decrypt(context, current.getPass())));
//            passParagraph.setFont(font);
//            passParagraph.setAlignment(1);
//            report.add(passParagraph);
//        }
//
//        report.close();
//        outputStream.close();
//
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
//
//        PdfReader reader = new PdfReader(inputStream);
//        PdfStamper stamper = new PdfStamper(reader, response.getOutputStream());
//
//        for (int i = 0; i < reader.getNumberOfPages(); i++) {
//            PdfContentByte overContent = stamper.getOverContent(i + 1);
//            overContent.setFontAndSize(BaseFont.createFont(BaseFont.HELVETICA_OBLIQUE, BaseFont.CP1257, BaseFont.EMBEDDED), 10);
//            overContent.beginText();
//            overContent.setTextMatrix(report.left(), report.bottom() - 10);
//            overContent.showText(String.format("Owner: %s", current.getUser()));
//            overContent.endText();
//            overContent.beginText();
//            overContent.setTextMatrix(report.right() - 60, report.bottom() - 10);
//            overContent.showText(String.format("Page: %d of %d", i + 1, reader.getNumberOfPages()));
//            overContent.endText();
//        }
//
//        stamper.close();
//        reader.close();
//        outputStream.close();
//        inputStream.close();
        } catch (ParseException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

//        if (session.getAttribute("passed") == null) {
//            response.sendRedirect(root + "/error/error_session.jsp");
//            return;
//
//        }
//
//        if (!(boolean) session.getAttribute("passed")) {
//            response.sendRedirect(root + "/error/error_session.jsp");
//            return;
//        }
//
//        Record current = (Record) session.getAttribute("current");
//
//        if (current == null) {
//            response.sendRedirect(root + "/error/error_session.jsp");
//            return;
//        }
//
//        Document report = new Document(PageSize.LETTER);
//
//        if (current.getRole().equals("Guest")) {
//            report = new Document(PageSize.A7);
//        }
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        PdfWriter.getInstance(report, outputStream);
//        Font font = FontFactory.getFont(BaseFont.HELVETICA, 14);
//
//        report.open();
//
//        font.setStyle(Font.BOLDITALIC);
//        Paragraph title = new Paragraph(String.format("%s Report", current.getRole()), font);
//        title.setAlignment(1);
//        report.add(title);
//        report.add(new Paragraph("\n"));
//
//        font.setSize(12f);
//        font.setStyle(Font.NORMAL);
//        if (current.getRole().equals("Admin")) {
//            PdfPTable recordTable = new PdfPTable(3);
//            ArrayList<Record> records = Connector.getAllRecords(this.context);
//
//            for (int i = 0; i < records.size(); i++) {
//                recordTable.addCell(new Phrase(String.valueOf(i + 1), font));
//                recordTable.addCell(new Phrase(records.get(i).getUser(), font));
//                recordTable.addCell(new Phrase(records.get(i).getRole(), font));
//            }
//
//            report.add(recordTable);
//        } else {
//            Paragraph userParagraph = new Paragraph(String.format("USERNAME: %s", current.getUser()));
//            userParagraph.setFont(font);
//            userParagraph.setAlignment(1);
//            report.add(userParagraph);
//
//            Paragraph passParagraph = new Paragraph(String.format("PASSWORD: %s", Cryptographer.decrypt(context, current.getPass())));
//            passParagraph.setFont(font);
//            passParagraph.setAlignment(1);
//            report.add(passParagraph);
//        }
//
//        report.close();
//        outputStream.close();
//
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
//
//        PdfReader reader = new PdfReader(inputStream);
//        PdfStamper stamper = new PdfStamper(reader, response.getOutputStream());
//
//        for (int i = 0; i < reader.getNumberOfPages(); i++) {
//            PdfContentByte overContent = stamper.getOverContent(i + 1);
//            overContent.setFontAndSize(BaseFont.createFont(BaseFont.HELVETICA_OBLIQUE, BaseFont.CP1257, BaseFont.EMBEDDED), 10);
//            overContent.beginText();
//            overContent.setTextMatrix(report.left(), report.bottom() - 10);
//            overContent.showText(String.format("Owner: %s", current.getUser()));
//            overContent.endText();
//            overContent.beginText();
//            overContent.setTextMatrix(report.right() - 60, report.bottom() - 10);
//            overContent.showText(String.format("Page: %d of %d", i + 1, reader.getNumberOfPages()));
//            overContent.endText();
//        }
//
//        stamper.close();
//        reader.close();
//        outputStream.close();
//        inputStream.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
