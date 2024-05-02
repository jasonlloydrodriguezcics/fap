<nav>
    <a href="${pageContext.request.contextPath}/home">Home</a>
    <a href="${pageContext.request.contextPath}/details">Details</a>
    <a href="">Courses</a>
    <%
        if (session.getAttribute("current-login") != null) {
    %>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
    <%
    } else {
    %>
    <a href="${pageContext.request.contextPath}/home">Login</a>
    <%
        }
    %>
</nav>