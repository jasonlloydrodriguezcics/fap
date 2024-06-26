package app;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import exception.InvalidDateException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import record.CourseRecord;
import record.LoginRecord;
import record.StudentRecord;
import utility.Cryptographer;
import utility.DatabaseManager;

public class ReportServlet extends HttpServlet {

    private ServletContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setDateHeader("Expires", 0);

        HttpSession session = request.getSession(false);
        Object currentLogin = session.getAttribute("current-login");

        if (currentLogin == null) {
            // error
            return;
        }

        LoginRecord loginRecord = (LoginRecord) currentLogin;

        Document report = new Document(PageSize.LETTER.rotate());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(report, outputStream);
        } catch (DocumentException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        Font font = FontFactory.getFont(BaseFont.HELVETICA, 14);

        report.open();

        font.setStyle(Font.BOLDITALIC);
        Paragraph title = new Paragraph(String.format("%s Report", loginRecord.getRole()), font);
        title.setAlignment(1);
        try {
            report.add(title);
        } catch (DocumentException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            report.add(new Paragraph("\n"));
        } catch (DocumentException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        font.setSize(12f);
        font.setStyle(Font.NORMAL);

        ArrayList<LoginRecord> loginRecords = DatabaseManager.getAllLoginRecords(this.context);
        ArrayList<StudentRecord> studentRecords = DatabaseManager.getAllStudentRecords(this.context);

        PdfPTable recordTable;

        if (loginRecord.getRole().equals("Admin")) {
            recordTable = new PdfPTable(3);

            recordTable.addCell(new Phrase("ROW NUMBER", font));
            recordTable.addCell(new Phrase("USERNAME", font));
            recordTable.addCell(new Phrase("ROLE", font));

            for (int i = 0; i < loginRecords.size(); i++) {
                LoginRecord record = loginRecords.get(i);

                recordTable.addCell(new Phrase(String.valueOf(i + 1), font));
                recordTable.addCell(new Phrase((loginRecord.equals(record) ? "* " : "") + record.getUsername(), font));
                recordTable.addCell(new Phrase(record.getRole(), font));
            }

            try {
                report.add(recordTable);
            } catch (DocumentException ex) {
                Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (loginRecord.getRole().equals("Trainor")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String inputStartDate = request.getParameter("start-date");
            String inputEndDate = request.getParameter("end-date");

            if (inputStartDate.equals("") || inputEndDate.equals("")) {
                throw new InvalidDateException("Fields must not be blank.");
            }

            Date startDate = null;
            try {
                startDate = dateFormat.parse(inputStartDate);
            } catch (ParseException ex) {
                Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            Date endDate = null;
            try {
                endDate = dateFormat.parse(inputEndDate);
            } catch (ParseException ex) {
                Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (endDate.before(startDate)) {
                throw new InvalidDateException("Start Date Must Occur Before End Date");
            }

            recordTable = new PdfPTable(6);

            recordTable.addCell(new Phrase("ROW NUMBER", font));
            recordTable.addCell(new Phrase("STUDENT USERNAME", font));
            recordTable.addCell(new Phrase("ENROLLED TRAINING", font));
            recordTable.addCell(new Phrase("PROGRESS TRACKER", font));
            recordTable.addCell(new Phrase("START OF TRAINING", font));
            recordTable.addCell(new Phrase("END OF TRAINING", font));

            int counter = 1;

            for (int i = 0; i < studentRecords.size(); i++) {
                StudentRecord record = studentRecords.get(i);

                Date currentStartDate = null;
                try {
                    currentStartDate = dateFormat.parse(record.getStartDate());
                } catch (ParseException ex) {
                    Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                Date currentEndDate = null;
                try {
                    currentEndDate = dateFormat.parse(record.getEndDate());
                } catch (ParseException ex) {
                    Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (currentStartDate.before(startDate)) {
                    continue;
                }

                if (currentEndDate.after(endDate)) {
                    continue;
                }

                recordTable.addCell(new Phrase(String.valueOf(counter++), font));
                recordTable.addCell(new Phrase(record.getUsername(), font));
                recordTable.addCell(new Phrase(record.getTrainingName(), font));
                recordTable.addCell(new Phrase(String.format("%d%%", record.getProgress()), font));
                recordTable.addCell(new Phrase(record.getStartDate(), font));
                recordTable.addCell(new Phrase(record.getEndDate(), font));
            }

            try {
                report.add(recordTable);
            } catch (DocumentException ex) {
                Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (loginRecord.getRole().equals("Student")) {
            StudentRecord studentRecord = DatabaseManager.getStudentRecord(this.context, loginRecord.getUsername());

            Paragraph userParagraph = new Paragraph(String.format("USERNAME: %s", studentRecord.getUsername()));
            userParagraph.setFont(font);
            try {
                report.add(userParagraph);
            } catch (DocumentException ex) {
                Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            Paragraph passParagraph = null;
            try {
                passParagraph = new Paragraph(String.format("PASSWORD: %s", Cryptographer.decrypt(context, loginRecord.getPassword())));
            } catch (Exception ex) {
                Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            passParagraph.setFont(font);
            try {
                report.add(passParagraph);
            } catch (DocumentException ex) {
                Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            Paragraph trainingParagraph = new Paragraph(String.format("ENROLLED IN: %s", studentRecord.getTrainingName()));
            trainingParagraph.setFont(font);
            try {
                report.add(trainingParagraph);
            } catch (DocumentException ex) {
                Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            Paragraph progressParagraph = new Paragraph(String.format("PROGRESS TRACKER: %d%%", studentRecord.getProgress()));
            progressParagraph.setFont(font);
            try {
                report.add(progressParagraph);
            } catch (DocumentException ex) {
                Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            Paragraph startDateParagraph = new Paragraph(String.format("START OF TRIANING: %s", studentRecord.getStartDate()));
            startDateParagraph.setFont(font);
            try {
                report.add(startDateParagraph);
            } catch (DocumentException ex) {
                Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            Paragraph endDateParagraph = new Paragraph(String.format("END OF TRAINING: %s", studentRecord.getEndDate()));
            endDateParagraph.setFont(font);
            try {
                report.add(endDateParagraph);
            } catch (DocumentException ex) {
                Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                report.add(new Paragraph("\n"));
            } catch (DocumentException ex) {
                Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            ArrayList<CourseRecord> courseRecords = DatabaseManager.getCourseRecords(this.context, String.valueOf(studentRecord.getTrainingId()));

            recordTable = new PdfPTable(3);

            recordTable.addCell(new Phrase("ROW NUMBER", font));
            recordTable.addCell(new Phrase("COURSE NAME", font));
            recordTable.addCell(new Phrase("DESCRIPTION", font));

            for (int i = 0; i < courseRecords.size(); i++) {
                CourseRecord record = courseRecords.get(i);

                recordTable.addCell(new Phrase(String.valueOf(i + 1), font));
                recordTable.addCell(new Phrase(record.getName(), font));
                recordTable.addCell(new Phrase(record.getDescription(), font));
            }

            try {
                report.add(recordTable);
            } catch (DocumentException ex) {
                Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        report.close();
        outputStream.close();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        Date currentDate = new Date();
        SimpleDateFormat nameDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat pdfDateFormat = new SimpleDateFormat("MM-dd-yyyy");

        FileOutputStream fileOutputStream = new FileOutputStream(String.format("%s/%sLIST_%s.pdf", System.getProperty("user.home"), loginRecord.getRole().toUpperCase(), nameDateFormat.format(currentDate)));

        PdfReader reader = new PdfReader(inputStream);
        PdfStamper stamper = null;
        try {
            stamper = new PdfStamper(reader, fileOutputStream);
        } catch (DocumentException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < reader.getNumberOfPages(); i++) {
            PdfContentByte overContent = stamper.getOverContent(i + 1);
            try {
                overContent.setFontAndSize(BaseFont.createFont(BaseFont.HELVETICA_OBLIQUE, BaseFont.CP1257, BaseFont.EMBEDDED), 10);
            } catch (DocumentException ex) {
                Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            overContent.beginText();
            overContent.setTextMatrix(report.left(), report.bottom() - 10);
            overContent.showText(String.format("Owner: %s Date Generated: %s", loginRecord.getUsername(), pdfDateFormat.format(currentDate)));
            overContent.endText();

            overContent.beginText();
            overContent.setTextMatrix(report.right() - 60, report.bottom() - 10);
            overContent.showText(String.format("Page: %d of %d", i + 1, reader.getNumberOfPages()));
            overContent.endText();
        }

        try {
            stamper.close();
        } catch (DocumentException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        reader.close();
        fileOutputStream.close();
        outputStream.close();
        inputStream.close();

        response.sendRedirect("details");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
