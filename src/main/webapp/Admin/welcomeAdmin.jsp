<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11/11/2021
  Time: 08:50 am
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ page import="Utils.CSRFUltils" %>
<%
    // generate a random CSRF token
    String csrfToken = CSRFUltils.getToken();
// place the CSRF token in a cookie
    javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("csrfToken", csrfToken);
    cookie.setHttpOnly(true);
    response.addCookie(cookie);
%>

<script type="text/javascript">
    document.onreadystatechange = function () {
        var state = document.readyState;
        if (state == 'complete') {
            fnInit("csrfToken", "<%= csrfToken %>");
        }
    };
</script>
<jsp:include page="headerAdmin.jsp"/>
<main class="page-content">
    <div class="container">
        <div class="page-auth__bg">
            <img class="auth-bg-image-light" src="https://scontent.xx.fbcdn.net/v/t1.15752-9/260822143_283058373763593_6664632680958818183_n.png?_nc_cat=107&ccb=1-5&_nc_sid=aee45a&_nc_ohc=jrasXsSrX5EAX8kzRtf&_nc_ad=z-m&_nc_cid=0&_nc_ht=scontent.xx&oh=a31dc05c7e7223b2052700b8bbb9fb11&oe=61CF901F" alt="#">
            <img class="auth-bg-image-dark" src="https://scontent.xx.fbcdn.net/v/t1.15752-9/260822143_283058373763593_6664632680958818183_n.png?_nc_cat=107&ccb=1-5&_nc_sid=aee45a&_nc_ohc=jrasXsSrX5EAX8kzRtf&_nc_ad=z-m&_nc_cid=0&_nc_ht=scontent.xx&oh=a31dc05c7e7223b2052700b8bbb9fb11&oe=61CF901F" alt="#">
        </div>
        <div class="page-auth__right">
            <h1 class="page-auth__title">Welcome to <span class="text-theme" style="color: darksalmon;">Mioca</span> Admin Dashboard</h1>
        </div>
    </div>
</main>
<jsp:include page="footerAdmin.jsp"/>