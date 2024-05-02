<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Details - Active Learning</title>
    </head>
    <%
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    %>
    <body>
        <jsp:include page="/component/header.jsp"/>
        <jsp:include page="/component/nav.jsp"/>
        <main>
            <div class="mainContent">
                <%
                    record.LoginRecord user = (record.LoginRecord) session.getAttribute("current-login");
                    if (user.getRole().equalsIgnoreCase("student")) {
                        ArrayList<record.TrainingRecord> courseList = (ArrayList<record.TrainingRecord>) request.getSession().getAttribute("courseList");
                        record.TrainingRecord course = null;

                        for (record.TrainingRecord p : courseList) {
                            course = p;
                            break;
                        }
                %>
                <div class="studentDetails">
                    <h6>Training: <%=  ((record.StudentRecord) request.getSession().getAttribute("studentRecord")).getTraining()%></h6>
                    <h6>Progress Tracker: <%=  ((record.StudentRecord) request.getSession().getAttribute("studentRecord")).getProgressTracker()%></h6>
                    <div class="courseList">
                        <c:forEach var="course" items="${sessionScope.courseList}">
                            <p>Course: ${course.course}</p>
                            <p>Training: ${course.training}</p>
                        </c:forEach>
                    </div>
                </div>
                <%
                } else if (user.getRole().equalsIgnoreCase("Trainor")) {
                    ArrayList<record.TrainingRecord> courseLists = (ArrayList<record.TrainingRecord>) request.getSession().getAttribute("courseList");
                    record.TrainingRecord courses = null;

                    for (record.TrainingRecord q : courseLists) {
                        courses = q;
                        break;
                    }
                    ArrayList<record.StudentRecord> studentRecord = (ArrayList<record.StudentRecord>) request.getSession().getAttribute("studentRecord");
                    record.StudentRecord studentRecords = null;

                    for (record.StudentRecord p : studentRecord) {
                        studentRecords = p;
                        break;
                    }
                %>
                <div class="trainorDetails">
                    <div class="studentRecord">
                        <c:forEach var="studentRecords" items="${sessionScope.studentRecord}">
                            <p>Username: ${studentRecords.username}</p>
                            <p>Training: ${studentRecords.training}</p>
                            <p>Progress: ${studentRecords.progressTracker}</p>
                        </c:forEach>
                    </div>
                    <div class="courseList">
                        <c:forEach var="courses" items="${sessionScope.courseLists}">
                            <p>Course: ${courses.course}</p>
                            <p>Training: ${courses.training}</p>
                        </c:forEach>
                    </div>
                </div>
               <%
                } else if (user.getRole().equalsIgnoreCase("Admin")) {

                    ArrayList<record.LoginRecord> loginRecords = (ArrayList<record.LoginRecord>) request.getSession().getAttribute("loginRecord");
                    record.LoginRecord login = null;
                    for (record.LoginRecord m : loginRecords) {
                        login = m;
                        break;
                    }
                    ArrayList<record.TrainingRecord> courseLists = (ArrayList<record.TrainingRecord>) request.getSession().getAttribute("courseList");
                    record.TrainingRecord courses = null;

                    for (record.TrainingRecord q : courseLists) {
                        courses = q;
                        break;
                    }
                    ArrayList<record.StudentRecord> studentRecord = (ArrayList<record.StudentRecord>) request.getSession().getAttribute("studentRecord");
                    record.StudentRecord studentRecords = null;

                    for (record.StudentRecord p : studentRecord) {
                        studentRecords = p;
                        break;
                    }
                %>
                <div class="adminDetails">
                    <div class="loginRecord">
                        <c:forEach var="login" items="${sessionScope.loginRecord}">
                            <p>Username: ${login.username}</p>
                            <p>Role: ${login.role}</p>
                        </c:forEach>
                    </div>
                    <div class="studentRecord">
                        <c:forEach var="studentRecords" items="${sessionScope.studentRecord}">
                            <p>Username: ${studentRecords.username}</p>
                            <p>Training: ${studentRecords.training}</p>
                            <p>Progress: ${studentRecords.progressTracker}</p>
                        </c:forEach>
                    </div>
                    <div class="courseList">
                        <c:forEach var="courses" items="${sessionScope.courseLists}">
                            <p>Course: ${courses.course}</p>
                            <p>Training: ${courses.training}</p>
                        </c:forEach>
                    </div>
                </div>
                
            <% } %>    
            </div>
            <div class="report">
                <form action="${pageContext.request.contextPath}/controller/GenerateReport" method="get" target="_blank">
                    <input type="submit" value="Generate PDF">
                </form>
            </div>
        </main>
        <jsp:include page="/component/footer.jsp"/>
    </body>

</html>
