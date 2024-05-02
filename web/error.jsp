<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error - Active Learning</title>
    </head>
    <%
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    %>
    <body>
        <jsp:include page="/component/header.jsp"/>
        <jsp:include page="/component/nav.jsp"/>
        <%
            int status = response.getStatus();

            if (status == 404) {
                request.setAttribute("message", "404 Page Not Found");
            } else if (status == 405) {
                request.setAttribute("message", "405 HTTP GET Is Not Supported");
            }

            if (exception != null) {
                request.setAttribute("message", exception.getMessage());
            }
        %>
        <p>${message}</p>
        <a href="${pageContext.request.contextPath}/home">Go Back</a>
        <jsp:include page="/component/footer.jsp"/>
    </body>
</html>
