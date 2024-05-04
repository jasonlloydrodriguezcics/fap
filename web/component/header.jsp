<header class="p-5 h-20 flex justify-between items-center bg-stone-900 text-white">
    <img class="h-12" src="logo-white.png" alt="logo">
    <%
        if (session.getAttribute("current-login") != null) {
    %>
    <p>${role} Account</p>
    <%
        }
    %>
</header>