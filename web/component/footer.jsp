<footer class="p-5 flex justify-between bg-stone-900 text-white">
    <p>© 2024 Active Learning, Inc. All Rights Reserved.</p>
    <%
        if (session.getAttribute("current-login") != null) {
    %>
    <p>${role} Account</p>
    <%
        }
    %>
</footer>