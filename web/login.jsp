<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login - Active Learning</title>
    </head>
    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setDateHeader("Expires", 0);
    %>
    <body>
        <jsp:include page="/component/header.jsp"/>
        <jsp:include page="/component/nav.jsp"/>
        <main>
            <p>LOGO MALAKING SQUARE</p>
            <div class="card">
                <form action="${pageContext.request.contextPath}/login" method="POST">
                    <div class="login-input">
                        <label for="user">Username</label>
                        <input type="text" name="user" id="user">
                    </div>
                    <div class="login-input">
                        <label for="pass">Password</label>
                        <input type="password" name="pass" id="pass">
                    </div>
                    <div class="captcha-input">
                        <img src="${pageContext.request.contextPath}/captcha" alt="captcha"/>
                        <input type="text" name="captcha" id="captcha">
                    </div>
                    <input class="button" type="submit" value="Login">
                </form>
            </div>
        </main>
        <jsp:include page="/component/footer.jsp"/>
    </body>

</html>