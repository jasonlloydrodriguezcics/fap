<%@page import="utility.Cryptographer"%>
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
        <title>Student Details - Active Learning</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setDateHeader("Expires", 0);

        Object currentLogin = session.getAttribute("current-login");

        if (currentLogin != null) {
            record.LoginRecord record = (record.LoginRecord) currentLogin;
            request.setAttribute("name", record.getUsername());
            request.setAttribute("role", record.getRole());
        }
    %>
    <body class="flex flex-col h-screen">
        <jsp:include page="/component/header.jsp"/>
        <div class="flex h-[calc(100vh-10rem)] w-full">
            <jsp:include page="/component/sidebar.jsp"/>
            <main class="gap-5 p-10 w-full flex flex-col overflow-y-scroll">

                <%
                    LoginRecord loginRecord = (LoginRecord) session.getAttribute("current-login");

                    if (loginRecord.getRole().equals("Admin")) {
                        Object loginRecords = request.getAttribute("login-records");

                        if (loginRecords != null) {
                %>
                <table class="w-8/12 text-sm text-center text-gray-500 self-center">
                    <thead class="text-lg text-gray-50 uppercase bg-amber-600">
                        <tr>
                            <th>Username</th>
                            <th>Role</th>
                        </tr>
                    </thead>
                    <tbody class="text-lg bg-stone-100 border-b">
                        <%
                            for (LoginRecord record : (ArrayList<LoginRecord>) loginRecords) {
                        %>
                        <tr>
                            <td><%=(loginRecord.equals(record) ? "*" : "")%> <%=record.getUsername()%></td>
                            <td><%=record.getRole()%></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
                <%
                    }

                    Object studentRecords = request.getAttribute("student-records");

                    if (studentRecords != null) {
                %>
                <table class="w-8/12 text-sm text-center text-gray-500 self-center">
                    <thead class="text-lg text-gray-50 uppercase bg-amber-600">
                        <tr>
                            <th>Student ID</th>
                            <th>Username</th>
                            <th>Progress Tracker</th>
                            <th>Training Start</th>
                            <th>Training End</th>
                        </tr>
                    </thead>
                    <tbody class="text-lg bg-stone-100 border-b">
                        <%
                            for (StudentRecord record : (ArrayList<StudentRecord>) studentRecords) {
                        %>
                        <tr>
                            <td><%=record.getId()%></td>
                            <td><%=record.getUsername()%></td>
                            <td><%=record.getProgress()%>%</td>
                            <td><%=record.getStartDate()%></td>
                            <td><%=record.getEndDate()%></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
                <%
                    }

                    Object trainingRecords = request.getAttribute("training-records");

                    if (trainingRecords != null) {
                %>
                <table class="w-8/12 text-sm text-center text-gray-500 self-center">
                    <thead class="text-lg text-gray-50 uppercase bg-amber-600">
                        <tr>
                            <th>Training ID</th>
                            <th>Training</th>
                            <th>Trainor</th>
                        </tr>
                    </thead>
                    <tbody class="text-lg bg-stone-100 border-b">
                        <%
                            for (TrainingRecord record : (ArrayList<TrainingRecord>) trainingRecords) {
                        %>
                        <tr class="training-record">
                            <td><%=record.getId()%></td>
                            <td><%=record.getName()%></td>
                            <td><%=record.getTrainorName()%></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
                <%
                    }
                } else if (loginRecord.getRole().equals("Trainor")) {
                    Object studentRecords = request.getAttribute("student-records");

                    if (studentRecords != null) {
                %>
                <table class="w-8/12 text-sm text-center text-gray-500 self-center">
                    <thead class="text-lg text-gray-50 uppercase bg-amber-600">
                        <tr>
                            <th>Student ID</th>
                            <th>Username</th>
                            <th>Progress Tracker</th>
                            <th>Training Start</th>
                            <th>Training End</th>
                        </tr>
                    </thead>
                    <tbody class="text-lg bg-stone-100 border-b">
                        <%
                            for (StudentRecord record : (ArrayList<StudentRecord>) studentRecords) {
                        %>
                        <tr>
                            <td><%=record.getId()%></td>
                            <td><%=record.getUsername()%></td>
                            <td><%=record.getProgress()%>%</td>
                            <td><%=record.getStartDate()%></td>
                            <td><%=record.getEndDate()%></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
                <%
                    }

                    Object trainingRecords = request.getAttribute("training-records");

                    if (trainingRecords != null) {
                %>
                <table class="w-8/12 text-sm text-center text-gray-500 self-center">
                    <thead class="text-lg text-gray-50 uppercase bg-amber-600">
                        <tr>
                            <th>Training ID</th>
                            <th>Training</th>
                            <th>Trainor</th>
                        </tr>
                    </thead>
                    <tbody class="text-lg bg-stone-100 border-b">
                        <%
                            for (TrainingRecord record : (ArrayList<TrainingRecord>) trainingRecords) {
                        %>
                        <tr class="training-record">
                            <td><%=record.getId()%></td>
                            <td><%=record.getName()%></td>
                            <td><%=record.getTrainorName()%></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
                <%
                    }
                } else if (loginRecord.getRole().equals("Student")) {
                    StudentRecord studentRecord = (StudentRecord) request.getAttribute("student-record");

                    if (studentRecord != null) {
                %>
                <table class="w-8/12 text-sm text-center text-gray-500 self-center">
                    <thead class="text-lg text-gray-50 uppercase bg-amber-600">
                        <tr>
                            <th>Student ID</th>
                            <th>Username</th>
                            <th>Progress Tracker</th>
                            <th>Training Start</th>
                            <th>Training End</th>
                        </tr>
                    </thead>
                    <tbody class="text-lg bg-stone-100 border-b">
                        <tr>
                            <td><%=studentRecord.getId()%></td>
                            <td><%=studentRecord.getUsername()%></td>
                            <td><%=studentRecord.getProgress()%>%</td>
                            <td><%=studentRecord.getStartDate()%></td>
                            <td><%=studentRecord.getEndDate()%></td>
                        </tr>
                    </tbody>
                </table>
                <%
                    }

                    Object courseRecords = request.getAttribute("course-records");

                    if (courseRecords != null) {
                %>
                <table class="w-8/12 text-sm text-center text-gray-500 self-center">
                    <thead class="text-lg text-gray-50 uppercase bg-amber-600">
                        <tr>
                            <th>Course ID</th>
                            <th>Course</th>
                            <th>Description</th>
                        </tr>
                    </thead>
                    <tbody class="text-lg bg-stone-100 border-b">
                        <%
                            for (CourseRecord record : (ArrayList<CourseRecord>) courseRecords) {
                        %>
                        <tr class="course-record">
                            <td><%=record.getId()%></td>
                            <td><%=record.getName()%></td>
                            <td><%=record.getDescription()%></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
                <%
                    }
                } else if (loginRecord.getRole().equals("Guest")) {
                %>
                <table class="w-8/12 text-sm text-center text-gray-500 self-center">
                    <thead class="text-lg text-gray-50 uppercase bg-amber-600">
                        <tr>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Role</th>
                        </tr>
                    </thead>
                    <tbody class="text-lg bg-stone-100 border-b">
                        <tr>
                            <td><%=loginRecord.getUsername()%></td>
                            <td><%=Cryptographer.decrypt(config.getServletContext(), loginRecord.getPassword())%></td>
                            <td><%=loginRecord.getRole()%>%</td>
                        </tr>
                    </tbody>
                </table>
                <%
                    }
                    if (!loginRecord.getRole().equals("Guest")) {
                %>
                <div class="m-20 p-5 max-w-xs w-6/12 border-2 border-black rounded flex flex-col items-center self-center">
                    <form class="flex flex-col" action="${pageContext.request.contextPath}/report" method="POST">
                        <%
                            if (loginRecord.getRole().equals("Trainor")) {
                        %>
                        <label for="start-date">Start Date</label>
                        <input class="mb-5 block w-full rounded-md border-0 p-1.5 text-gray-900 ring-1 ring-inset ring-gray-300" type="date" name="start-date" id="start-date">
                        <label for="end-date">End Date</label>
                        <input class="mb-5 block w-full rounded-md border-0 p-1.5 text-gray-900 ring-1 ring-inset ring-gray-300" type="date" name="end-date" id="end-date">
                        <%
                            }
                        %>
                        <input class="p-3 justify-self-center self-center rounded bg-amber-600 text-white text-xl" type="submit" value="Generate PDF">
                    </form>
                </div>
                <%
                    }
                %>

            </main>
        </div>
        <jsp:include page="/component/footer.jsp"/>
    </body>

</html>
