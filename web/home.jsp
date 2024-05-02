<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard - Active Learning</title>
    </head>
    <%
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    %>
    <body>
        <jsp:include page="/component/header.jsp"/>
        <jsp:include page="/component/nav.jsp"/>
        <main>
            <p>content</p>
        </main>
        <jsp:include page="/component/footer.jsp"/>
    </body>

</html>