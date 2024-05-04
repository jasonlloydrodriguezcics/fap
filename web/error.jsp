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
    <body class="flex flex-col h-screen">
        <jsp:include page="/component/header.jsp"/>
        <%
            int status = response.getStatus();

            if (exception != null) {
                request.setAttribute("message", exception);
            } else {
                if (status == 404) {
                    request.setAttribute("message", "404 Page Not Found");
                } else if (status == 405) {
                    request.setAttribute("message", "405 HTTP GET Is Not Supported");
                } else if (status == 500) {
                    request.setAttribute("message", "500 Internal Server Issue");
                }
            }
        %>
        <div class="flex h-[calc(100vh-10rem)] w-full">
            <main class="p-10 w-full flex flex-col">
                <p class="mb-6 justify-self-center self-center text-4xl">${message}</p>
                <a class="p-3 justify-self-center self-center rounded bg-amber-600 text-white text-xl" href="${pageContext.request.contextPath}/home">Go Back</a>
            </main>
        </div>
        <jsp:include page="/component/footer.jsp"/>
    </body>
</html>
