<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
    <meta name="robots" content="index, follow" />
    <title>Mioca - Handmade Goods eCommerce HTML Template</title>
    <meta name="description" content="Mioca - Handmade Goods eCommerce HTML Template" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

    <!-- Add site Favicon -->
    <link rel="shortcut icon" href="assets/images/favicon/favicon.ico" type="image/png">


    <!-- vendor css (Icon Font) -->
    <link rel="stylesheet" href="assets/css/vendor/bootstrap.bundle.min.css" />
    <link rel="stylesheet" href="assets/css/vendor/pe-icon-7-stroke.css" />
    <link rel="stylesheet" href="assets/css/vendor/font.awesome.css" />

    <!-- plugins css (All Plugins Files) -->
    <link rel="stylesheet" href="assets/css/plugins/animate.css" />
    <link rel="stylesheet" href="assets/css/plugins/swiper-bundle.min.css" />
    <link rel="stylesheet" href="assets/css/plugins/jquery-ui.min.css" />
    <link rel="stylesheet" href="assets/css/plugins/nice-select.css" />
    <link rel="stylesheet" href="assets/css/plugins/venobox.css" />

    <!-- Use the minified version files listed below for better performance and remove the files listed above -->
    <!-- <link rel="stylesheet" href="assets/css/vendor/vendor.min.css" />
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css" />
    <link rel="stylesheet" href="assets/css/style.min.css"> -->

    <!-- Main Style -->
    <link rel="stylesheet" href="assets/css/style.css" />

</head>

<body>

<!-- Header Area Start -->
<jsp:include page="header.jsp" />
<!-- Header Area End -->

<!-- breadcrumb-area start -->
<div class="breadcrumb-area">
    <div class="container">
        <div class="row align-items-center justify-content-center">
            <div class="col-12 text-center">
                <h2 class="breadcrumb-title">Contact Us</h2>
                <!-- breadcrumb-list start -->
                <ul class="breadcrumb-list">
                    <li class="breadcrumb-item"><a href="/home">Home</a></li>
                    <li class="breadcrumb-item active">Contact</li>
                </ul>
                <!-- breadcrumb-list end -->
            </div>
        </div>
    </div>
</div>
<!-- breadcrumb-area end -->

<!-- Contact Area Start -->
<div class="contact-area pt-100px pb-100px">
    <div class="container">
        <div class="contact-wrapper">
            <div class="row">
                <div class="col-lg-5">
                    <div class="contact-info">
                        <span class="sub-title">Get In Touch</span>
                        <h4 class="heading">Visit One Of Our Shop
                            Contact Us Now</h4>
                        <div class="single-contact">
                            <div class="icon-box">
                                <i class="pe-7s-call"></i>
                            </div>
                            <div class="info-box">
                                <h5 class="title">Phone:</h5>
                                <p><a href="tel:0123456789">012 345 67 89</a></p>
                            </div>
                        </div>
                        <div class="single-contact">
                            <div class="icon-box">
                                <i class="pe-7s-mail"></i>
                            </div>
                            <div class="info-box">
                                <h5 class="title">Email:</h5>
                                <p><a href="mailto:demo@example.com">demo@example.com</a></p>
                            </div>
                        </div>
                        <div class="single-contact">
                            <div class="icon-box">
                                <i class="pe-7s-map-marker"></i>
                            </div>
                            <div class="info-box">
                                <h5 class="title">Address:</h5>
                                <p>Your address goes here</p>
                            </div>
                        </div>
                        <ul class="social">
                            <li class="m-0">
                                <a href="#"><i class="fa fa-facebook"></i></a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-twitter"></i></a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-pinterest-p"></i></a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-dribbble"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-7">
                    <div class="contact-form">
                        <div class="contact-title mb-30">
                            <h2 class="title" data-aos="fade-up" data-aos-delay="200">Leave a Message</h2>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod incididunt ut labore et dolore magna aliqua. </p>
                        </div>
                        <form action="<c:url value="/comingsoon" />" class="contact-form-style" id="contact-form"
                               method="get">
                            <input type="hidden" name="csrfToken" value="<%= csrfToken %>"/>
                            <div class="row">
                                <div class="col-lg-6" data-aos="fade-up" data-aos-delay="200">
                                    <input  placeholder="Name*" type="text" />
                                </div>
                                <div class="col-lg-6" data-aos="fade-up" data-aos-delay="200">
                                    <input  placeholder="Email*" type="email" />
                                </div>
                                <div class="col-lg-6" data-aos="fade-up" data-aos-delay="200">
                                    <input  placeholder="Subject*" type="text" />
                                </div>
                                <div class="col-lg-12" data-aos="fade-up" data-aos-delay="200">
                                    <textarea  placeholder="Your Message*"></textarea>
                                    <button class="btn btn-primary mt-4" data-aos="fade-up" data-aos-delay="200"
                                            type="submit">Send Message</button>
                                </div>
                            </div>
                        </form>
                        <p class="form-messege"></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Contact Area End -->

<!-- map Area Start -->

<div class="contact-map">
    <div id="mapid">
        <div class="mapouter">

            <div class="gmap_canvas">
                <iframe id="gmap_canvas"
                        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3918.4797039824516!2d106.77141781411703!3d10.851072060780224!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752774d7e357c3%3A0x7cc744cb226ad64!2zc-G7kSAxIMSQLiBWw7UgVsSDbiBOZ8OibiwgTGluaCBDaGnhu4N1LCBUaOG7pyDEkOG7qWMsIFRow6BuaCBwaOG7kSBI4buTIENow60gTWluaCwgVmnhu4d0IE5hbQ!5e0!3m2!1svi!2s!4v1634398581742!5m2!1svi!2s"
                        frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
                <a href="https://sites.google.com/view/maps-api-v2/mapv2"></a>
            </div>
        </div>
    </div>
</div>
<!-- map Area End -->

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
<script src="assets/js/main.js"></script>
</body>

</html>