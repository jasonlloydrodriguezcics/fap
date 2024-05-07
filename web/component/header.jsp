<header class="p-5 h-20 flex justify-between items-center bg-stone-900 text-white">
    <img class="h-12" src="${pageContext.request.contextPath}/logo-white.png" alt="logo">
    <%
        if (session.getAttribute("current-login") != null) {
    %>
    <div class="flex gap-5">
        <a class="block md:hidden pb-2 hover:text-amber-500" href="${pageContext.request.contextPath}/home">Home</a>
        <a class="block md:hidden pb-2 hover:text-amber-500" href="${pageContext.request.contextPath}/details">Details</a>
        <a class="block md:hidden hover:text-amber-500" href="${pageContext.request.contextPath}/logout">Logout</a>
        <p>${role} Account</p>
    </div>
    <%
        }
    %>
</header>