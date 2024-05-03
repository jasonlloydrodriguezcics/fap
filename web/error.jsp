<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html class="h-full">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error - Active Learning</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setDateHeader("Expires", 0);
    %>
    <body class="flex flex-col h-full">
        <jsp:include page="/component/header.jsp"/>
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
        <main class="p-10 h-full w-full flex flex-col">
            <p class="mb-6 justify-self-center self-center text-4xl">${message}</p>
            <a class="p-3 justify-self-center self-center rounded bg-amber-600 text-white text-xl" href="${pageContext.request.contextPath}/home">Go Back</a>
        </main>
        <jsp:include page="/component/footer.jsp"/>
    </body>
</html>
