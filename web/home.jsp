<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="h-full" lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home - Active Learning</title>
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
        <div class="flex h-full w-full">
            <jsp:include page="/component/sidebar.jsp"/>
            <main class="p-10 h-full w-full flex flex-col">
                <h1 class="text-4xl font-bold" >Welcome, ${name}!</h1>
            </main>
        </div>
        <jsp:include page="/component/footer.jsp"/>
    </body>

</html>