<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="h-full" lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login - Active Learning</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setDateHeader("Expires", 0);
    %>
    <body class="flex flex-col h-screen">
        <jsp:include page="/component/header.jsp"/>
        <div class="flex h-full w-full">
            <main class="p-10 h-full w-full flex flex-col">
                <div class="m-20 p-5 max-w-xs w-6/12 border-2 border-black rounded flex flex-col items-center self-center">
                    <p class="text-2xl font-bold">LOGIN</p>
                    <form action="${pageContext.request.contextPath}/login" method="POST">
                        <label for="user">Username</label>
                        <input class="block w-full rounded-md border-0 p-1.5 text-gray-900 ring-1 ring-inset ring-gray-300"
                               type="text" name="user" id="user">
                        <label for="pass">Password</label>
                        <input class="block w-full rounded-md border-0 p-1.5 text-gray-900 ring-1 ring-inset ring-gray-300"
                               type="password" name="pass" id="pass">
                        <img class="w-full h-18 self-center" src="${pageContext.request.contextPath}/captcha" alt="captcha" />
                        <input
                            class="mb-5 block w-full rounded-md border-0 p-1.5 text-gray-900 ring-1 ring-inset ring-gray-300"
                            type="text" name="captcha" id="captcha">
                        <input
                            class="w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white"
                            type="submit" value="Login">
                    </form>
                </div>
            </main>
        </div>
        <jsp:include page="/component/footer.jsp"/>
    </body>

</html>