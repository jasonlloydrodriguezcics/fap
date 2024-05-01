<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Details - Active Learning</title>
</head>

<body>
    <header>
        <p>LOGO 8=D</p>
        <a href="">Courses</a>
        <a href="">News</a>
        <a href="">Careers</a>
        <a href="">Company</a>
        <a href="">Contacts</a>
    </header>
    <nav>
        <a href="">Dashboard</a>
        <a href="">Details</a>
        <a href="">Courses</a>
        <a href="">Login</a>
    </nav>
    <main>
        <div class="mainContent">
            <% 
                record.LoginRecord user = (record.LoginRecord) session.getAttribute("current-login");
                if (user.getRole().equalsIgnoreCase("student")) {
                    ArrayList<record.CourseRecord> courseList = (ArrayList<record.CourseRecord>) request.getSession().getAttribute("courseList"); 
                    record.CourseRecord course = null;

                    for (record.CourseRecord p : courseList) {
                        course = p;
                        break;
                    }
            %>
            <div class="studentDetails">
                <h6>Training: <%=  ((record.StudentRecord) request.getSession().getAttribute("studentRecord")).getTraining() %></h6>
                <h6>Progress Tracker: <%=  ((record.StudentRecord) request.getSession().getAttribute("studentRecord")).getProgressTracker() %></h6>
                <div class="courseList">
                    <c:forEach var="course" items="${sessionScope.courseList}">
                        <p>Course: ${course.course}</p>
                        <p>Training: ${course.training}</p>
                    </c:forEach>
                </div>
            </div>
            <% 
                } else if (user.getRole().equalsIgnoreCase("admin")) {
                    ArrayList<record.CourseRecord> courseLists = (ArrayList<record.CourseRecord>) request.getSession().getAttribute("courseList"); 
                    record.CourseRecord courses = null;

                    for (record.CourseRecord q : courseLists) {
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
    <footer>
        <p>ACTIVE LEARNING 8=D</p>
    </footer>
</body>

</html>
