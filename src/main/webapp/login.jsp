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
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="robots" content="index, follow"/>
    <title>Mioca - Handmade Goods eCommerce HTML Template</title>
    <meta name="description" content="Mioca - Handmade Goods eCommerce HTML Template"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <!-- Add site Favicon -->
    <link rel="shortcut icon" href="assets/images/favicon/favicon.ico" type="image/png">


    <!-- vendor css (Icon Font) -->
    <link rel="stylesheet" href="assets/css/vendor/bootstrap.bundle.min.css"/>
    <link rel="stylesheet" href="assets/css/vendor/pe-icon-7-stroke.css"/>
    <link rel="stylesheet" href="assets/css/vendor/font.awesome.css"/>

    <!-- plugins css (All Plugins Files) -->
    <link rel="stylesheet" href="assets/css/plugins/animate.css"/>
    <link rel="stylesheet" href="assets/css/plugins/swiper-bundle.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/jquery-ui.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/nice-select.css"/>
    <link rel="stylesheet" href="assets/css/plugins/venobox.css"/>

    <!-- Use the minified version files listed below for better performance and remove the files listed above -->
    <!-- <link rel="stylesheet" href="assets/css/vendor/vendor.min.css" />
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css" />
    <link rel="stylesheet" href="assets/css/style.min.css"> -->

    <!-- Main Style -->
    <link rel="stylesheet" href="assets/css/style.css"/>
    <link rel="stylesheet" href="assets/css/styleLogin.css"/>
</head>

<body>


<!-- Header Area Start -->
<jsp:include page="header.jsp"/>

<!-- breadcrumb-area start -->
<div class="breadcrumb-area">
    <div class="container">
        <div class="row align-items-center justify-content-center">
            <div class="col-12 text-center">
                <h2 class="breadcrumb-title">Login & Register</h2>
                <!-- breadcrumb-list start -->
                <ul class="breadcrumb-list">
                    <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
                    <li class="breadcrumb-item active">Login</li>
                </ul>
                <!-- breadcrumb-list end -->
            </div>
        </div>
    </div>
</div>
<!-- breadcrumb-area end -->


<!-- login area start -->
<div class="login-register-area pt-100px pb-100px">
    <div class="container">
        <div class="row">
            <div class="col-lg-7 col-md-12 ml-auto mr-auto">
                <div class="login-register-wrapper">
                    <div class="login-register-tab-list nav">
                        <a onclick="lg_regClick();" id="lg" class="active" data-bs-toggle="tab" href="#lg1">
                            <h4>login</h4>
                        </a>
                        <a onclick="lg_regClick();" id="reg" data-bs-toggle="tab" href="#lg2">
                            <h4>register</h4>
                        </a>
                        <a id="forgot-passwd" data-bs-toggle="tab" href="#lg3" style="display: none;">
                            <h4>forgot password</h4>
                        </a>
                    </div>
                    <div class="tab-content">
                        <c:if test ="${requestScope.errorMessage != null}">
                            <div id="errorMessage" class="error-msg">
                                <i class="fa fa-times-circle"></i>
                                <c:out value="${requestScope.errorMessage}"/>
                            </div>
                        </c:if>
                        <c:if test ="${requestScope.Message != null}">
                            <c:if test ="${requestScope.isSuccess != null}">
                                <c:if test ="${requestScope.isSuccess != true}">
                                    <div id="errorMessage" class="error-msg">
                                        <i class="fa fa-times-circle"></i>
                                        <c:out value="${requestScope.Message}"/>
                                    </div>
                                </c:if>
                                <c:if test ="${requestScope.isSuccess == true}">
                                    <div id="errorMessage" class="success-msg">
                                        <i class="fa fa-times-circle"></i>
                                        <c:out value="${requestScope.Message}"/>
                                    </div>
                                </c:if>
                            </c:if>
                        </c:if>
                        <div id="lg1" class="tab-pane active">
                            <div class="login-form-container">
                                <div class="login-register-form">
                                    <form action="/login" method="post">
                                        <input type="hidden" name="csrfToken" value="<%= csrfToken %>"/>
                                        <input type="text" name="username" placeholder="Username" required/>
                                        <input type="password" name="password" placeholder="Password" required/>
                                        <div class="button-box">
                                            <div class="login-toggle-btn">
                                                <a href="#" onclick="forgotPassword(event);">Forgot Password?</a>
                                            </div>
                                            <button type="submit"><span>Login</span></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <%--   EndLogin--%>
                        <div id="lg2" class="tab-pane">
                            <div class="login-form-container">
                                <div class="login-register-form">
                                    <form action="/register" method="post">
                                        <input type="hidden" name="csrfToken" value="<%= csrfToken %>"/>
                                        <input name="email" placeholder="Email" type="email" required/>
                                        <input type="text" name="username" placeholder="Username" required/>
                                        <input id="password-register" type="password" name="password" placeholder="Password" required/>
                                        <input id="repassword-register" type="password" name="retypepassword" placeholder="Retype password" required/>
                                        <div class="button-box" style="text-align: center;">
                                            <button type="submit"><span>Register</span></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div id="lg3" class="tab-pane">
                            <div class="login-form-container">
                                <div class="login-register-form">
                                    <form action="/forgetpass" method="post">
                                        <input type="hidden" name="csrfToken" value="<%= csrfToken %>"/>
                                        <input name="email" placeholder="Email" type="email" />
                                        <input id="passwordforget" type="password" name="password" placeholder="Password" required/>
                                        <input id="repasswordforget"  type="password" name="retypepassword" placeholder="Retype password" required/>
                                        <div class="button-box" style="text-align: center;">
                                            <button type="submit"><span>Reset</span></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- login area end -->

<!-- Footer Area Start -->
<jsp:include page="footer.jsp" />
<!-- Footer Area End -->

<!-- Search Modal Start -->
<div class="modal popup-search-style" id="searchActive">
    <button type="button" class="close-btn" data-bs-dismiss="modal"><span aria-hidden="true">&times;</span></button>
    <div class="modal-overlay">
        <div class="modal-dialog p-0" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <h2>Search Your Product</h2>
                    <form class="navbar-form position-relative" role="search">
                        <input type="hidden" name="csrfToken" value="<%= csrfToken %>"/>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search here...">
                        </div>
                        <button type="submit" class="submit-btn"><i class="pe-7s-search"></i></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Search Modal End -->

<!-- Global Vendor, plugins JS -->

<!-- Vendor JS -->
<script src="assets/js/vendor/jquery-3.5.1.min.js"></script>

<script src="assets/js/vendor/bootstrap.bundle.min.js"></script>
<script src="assets/js/vendor/jquery-migrate-3.3.0.min.js"></script>
<script src="assets/js/vendor/modernizr-3.11.2.min.js"></script>

<!--Plugins JS-->
<script src="assets/js/plugins/swiper-bundle.min.js"></script>
<script src="assets/js/plugins/jquery-ui.min.js"></script>
<script src="assets/js/plugins/jquery.nice-select.min.js"></script>
<script src="assets/js/plugins/countdown.js"></script>
<script src="assets/js/plugins/scrollup.js"></script>
<script src="assets/js/plugins/jquery.zoom.min.js"></script>
<script src="assets/js/plugins/venobox.min.js"></script>


<!-- Use the minified version files listed below for better performance and remove the files listed above -->
<!-- <script src="assets/js/vendor/vendor.min.js"></script>
<script src="assets/js/plugins/plugins.min.js"></script> -->

<!-- Main Js -->

<script src="assets/js/Loginscript.js"></script>

<script src="assets/js/main.js"></script>


<c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/register'}">
    <script type="text/javascript">
        document.getElementById("lg1").classList.remove("active");
        document.getElementById("lg2").classList.add("active");
        document.getElementById("lg").classList.remove("active");
        document.getElementById("reg").classList.add("active");
    </script>
</c:if>
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/forgetpass'}">
    <script type="text/javascript">
        document.getElementById("forgot-passwd").click();
        document.getElementById("forgot-passwd").style.display = 'block';
        // document.getElementById("lg").style.display = 'none';
        // document.getElementById("reg").style.display = 'none';
    </script>
</c:if>
</body>

</html>