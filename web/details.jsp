<%@page import="record.CourseRecord"%>
<%@page import="record.TrainingRecord"%>
<%@page import="record.LoginRecord"%>
<%@page import="record.StudentRecord"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Details - Active Learning</title>
    </head>
    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setDateHeader("Expires", 0);
    %>
    <body>
        <jsp:include page="/component/header.jsp"/>
        <jsp:include page="/component/nav.jsp"/>
        <main>
            <%
                LoginRecord loginRecord = (LoginRecord) session.getAttribute("current-login");

                if (loginRecord.getRole().equals("Admin")) {
                    Object loginRecords = request.getAttribute("login-records");

                    if (loginRecords != null) {
                        for (LoginRecord record : (ArrayList<LoginRecord>) loginRecords) {
            %>
            <div class="login-record">
                <p><%=(loginRecord.equals(record) ? "*" : "")%> Username: <%=record.getUsername()%></p>
                <p>Role: <%=record.getRole()%></p>
            </div>
            <%
                    }
                }

                Object studentRecords = request.getAttribute("student-records");

                if (studentRecords != null) {
                    for (StudentRecord record : (ArrayList<StudentRecord>) studentRecords) {
            %>
            <div class="student-record">
                <p>Student ID: <%=record.getId()%></p>
                <p>Username: <%=record.getUsername()%></p>
                <p>Progress: <%=record.getProgress()%>%</p>
                <p>Start Date: <%=record.getStartDate()%></p>
                <p>End Date: <%=record.getEndDate()%></p>
            </div>
            <%
                    }
                }

                Object trainingRecords = request.getAttribute("training-records");

                if (trainingRecords != null) {
                    for (TrainingRecord record : (ArrayList<TrainingRecord>) trainingRecords) {
            %>
            <div class="training-record">
                <p>Training ID: <%=record.getId()%></p>
                <p>Training: <%=record.getName()%></p>
                <p>Trainor: <%=record.getTrainorName()%></p>
            </div>
            <%
                    }
                }
            } else if (loginRecord.getRole().equals("Trainor")) {
                Object studentRecords = request.getAttribute("student-records");

                if (studentRecords != null) {
                    for (StudentRecord record : (ArrayList<StudentRecord>) studentRecords) {
            %>
            <div class="student-record">
                <p>Student ID: <%=record.getId()%></p>
                <p>Username: <%=record.getUsername()%></p>
                <p>Progress: <%=record.getProgress()%>%</p>
                <p>Start Date: <%=record.getStartDate()%></p>
                <p>End Date: <%=record.getEndDate()%></p>
            </div>
            <%
                    }
                }

                Object trainingRecords = request.getAttribute("training-records");

                if (trainingRecords != null) {
                    for (TrainingRecord record : (ArrayList<TrainingRecord>) trainingRecords) {
            %>
            <div class="training-record">
                <p>Training ID: <%=record.getId()%></p>
                <p>Training: <%=record.getName()%></p>
                <p>Trainor: <%=record.getTrainorName()%></p>
            </div>
            <%
                    }
                }
            } else if (loginRecord.getRole().equals("Student")) {
                StudentRecord studentRecord = (StudentRecord) request.getAttribute("student-record");

                if (studentRecord != null) {
            %>
            <div class="student-record">
                <p>Student ID: <%=studentRecord.getId()%></p>
                <p>Username: <%=studentRecord.getUsername()%></p>
                <p>Progress: <%=studentRecord.getProgress()%>%</p>
                <p>Start Date: <%=studentRecord.getStartDate()%></p>
                <p>End Date: <%=studentRecord.getEndDate()%></p>
            </div>
            <%
                }

                Object courseRecords = request.getAttribute("course-records");

                if (courseRecords != null) {
                    for (CourseRecord record : (ArrayList<CourseRecord>) courseRecords) {
            %>
            <div class="course-record">
                <p>Course ID: <%=record.getId()%></p>
                <p>Course: <%=record.getName()%></p>
                <p>Description: <%=record.getDescription()%></p>
            </div>
            <%
                        }
                    }
                }
            %>
            <%
                if (!loginRecord.getRole().equals("Guest")) {
            %>
            <div class="report">
                <form action="${pageContext.request.contextPath}/report">
                    <%                        if (loginRecord.getRole().equals("Trainor")) {
                    %>
                    <input type="date" name="start-date" id="start-date">
                    <input type="date" name="end-date" id="end-date">
                    <%
                        }
                    %>
                    <input type="submit" value="Generate PDF">
                </form>
            </div>
            <%
                }
            %>
        </main>
        <jsp:include page="/component/footer.jsp"/>
    </body>

</html>
