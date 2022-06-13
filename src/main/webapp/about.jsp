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
                <h2 class="breadcrumb-title">About Us</h2>
                <!-- breadcrumb-list start -->
                <ul class="breadcrumb-list">
                    <li class="breadcrumb-item"><a href="/home">Home</a></li>
                    <li class="breadcrumb-item active">About Us</li>
                </ul>
                <!-- breadcrumb-list end -->
            </div>
        </div>
    </div>
</div>
<!-- breadcrumb-area end -->

<!-- Service Area Start -->

<div class="service-area pt-100px">
    <div class="container">
        <div class="row d-flex justify-content-center align-items-center">
            <div class="col-md-12 col-lg-5">
                <div class="service-left">
                    <img src="assets/images/about-image/srevice-left-img.png" alt="" class="service-left-image">
                    <img src="assets/images/about-image/srevice-left-shape.png" alt="" class="service-left-image-2">
                    <div class="promo-video">
                        <a href="https://www.youtube.com/watch?v=UyzRTR02jqE" class="venobox overlay-box" data-vbtype="video"><span
                                class="fa fa-play"><i class="ripple"></i></span></a>
                    </div>
                </div>
            </div>
            <div class="col-md-12 col-lg-7">
                <div class="service-right-content">
                    <h2 class="title">Our Stories</h2>
                    <p class="para-1">We are students from  Ho Chi Minh City University of Technology and Education</p>
                    <p class="para-2">We spent 3 months to create this website. We tried to made the best to this website but some functions aren't completed so we will update late</p>
                    <div class="mision-vision">
                        <div class="mision">
                            <span class="heading">Our Mission</span>
                            <p class="mision-desc">Upgade us skill to have a good job.</p>
                        </div>
                        <div class="vision">
                            <span class="heading">Our Mission</span>
                            <p class="vision-desc">Get grade 10 for us subject: Web Programming</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Service Area End -->

<!-- Feature Area Srart -->
<div class="feature-area pt-100px">
    <div class="container">
        <div class="feature-wrapper">
            <div class="single-feture-col">
                <!-- single item -->
                <div class="single-feature">
                    <div class="feature-icon">
                        <img src="assets/images/icons/1.png" alt="">
                    </div>
                    <div class="feature-content">
                        <h4 class="title">Free Shipping</h4>
                        <span class="sub-title">Capped at $39 per order</span>
                    </div>
                </div>
            </div>
            <!-- single item -->
            <div class="single-feture-col ">
                <div class="single-feature">
                    <div class="feature-icon">
                        <img src="assets/images/icons/2.png" alt="">
                    </div>
                    <div class="feature-content">
                        <h4 class="title">Card Payments</h4>
                        <span class="sub-title">12 Months Installments</span>
                    </div>
                </div>
            </div>
            <!-- single item -->
            <div class="single-feture-col">
                <div class="single-feature">
                    <div class="feature-icon">
                        <img src="assets/images/icons/3.png" alt="">
                    </div>
                    <div class="feature-content">
                        <h4 class="title">Easy Returns</h4>
                        <span class="sub-title">Shop With Confidence</span>
                    </div>
                </div>
                <!-- single item -->
            </div>
        </div>
    </div>
</div>
<!-- Feature Area End -->
<!-- Team Area Start -->

<div class="team-area pt-100px">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="section-title text-center">
                    <h2 class="title line-height-1">Team Member</h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod incididunt ut labore
                        et dolore magna aliqua. </p>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xl-3 col-lg-6 col-md-6 col-sm-6 mb-lm-30px mb-lg-30px mb-md-30px">
                <!-- Single Team -->
                <div class="team-wrapper ">
                    <div class="team-image overflow-hidden">
                        <img src="assets/images/team/thanh.jpg" alt="">
                    </div>
                    <div class="team-inner">
                        <div class="team-content">
                            <h6 class="title">TRUNG THÀNH</h6>
                            <span class="sub-title">DEVELOPER</span>
                        </div>
                        <ul class="team-social d-flex">
                            <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                            <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                            <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                            <li><a href="#"><i class="fa fa-pinterest-p"></i></a></li>
                        </ul>
                    </div>
                </div>
                <!-- Single Team -->
            </div>
            <div class="col-xl-3 col-lg-6 col-md-6 col-sm-6 mb-lm-30px mb-lg-30px mb-md-30px">
                <!-- Single Team -->
                <div class="team-wrapper">
                    <div class="team-image overflow-hidden">
                        <img src="assets/images/team/dan.jpg" alt="">
                    </div>
                    <div class="team-inner">
                        <div class="team-content">
                            <h6 class="title">HIẾU ĐAN</h6>
                            <span class="sub-title">DEVELOPER</span>
                        </div>
                        <ul class="team-social d-flex">
                            <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                            <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                            <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                            <li><a href="#"><i class="fa fa-pinterest-p"></i></a></li>
                        </ul>
                    </div>
                </div>
                <!-- Single Team -->
            </div>
            <div class="col-xl-3 col-lg-6 col-sm-6 col-md-6 mb-lm-30px ">
                <!-- Single Team -->
                <div class="team-wrapper">
                    <div class="team-image overflow-hidden">
                        <img src="assets/images/team/phat.jpg" alt="">
                    </div>
                    <div class="team-inner">
                        <div class="team-content">
                            <h6 class="title">ĐẠI PHÁT</h6>
                            <span class="sub-title">DEVELOPER</span>
                        </div>
                        <ul class="team-social d-flex">
                            <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                            <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                            <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                            <li><a href="#"><i class="fa fa-pinterest-p"></i></a></li>
                        </ul>
                    </div>
                </div>
                <!-- Single Team -->
            </div>
            <div class="col-xl-3 col-md-6 col-sm-6 col-lg-6">
                <!-- Single Team -->
                <div class="team-wrapper">
                    <div class="team-image overflow-hidden">
                        <img src="assets/images/team/thang.JPG" alt="">
                    </div>
                    <div class="team-inner">
                        <div class="team-content">
                            <h6 class="title">ĐỨC THẮNG</h6>
                            <span class="sub-title">DEVELOPER</span>
                        </div>
                        <ul class="team-social d-flex">
                            <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                            <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                            <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                            <li><a href="#"><i class="fa fa-pinterest-p"></i></a></li>
                        </ul>
                    </div>
                </div>
                <!-- Single Team -->
            </div>
        </div>
    </div>
</div>

<!-- Team Area End -->

<!-- Testimonial Area Start -->
<div class="testimonial-area pt-100px">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="section-title text-center">
                    <h2 class="title line-height-1">What Client Says</h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod incididunt ut labore
                        et dolore magna aliqua. </p>
                </div>
            </div>
        </div>
        <!-- Slider Start -->
        <div class="testimonial-wrapper swiper-container">
            <div class="swiper-wrapper">
                <!-- Slider Single Item -->
                <div class="swiper-slide">
                    <div class="testi-inner">
                        <div class="author-img">
                            <img src="assets/images/testimonial-image/1.png" alt="">
                        </div>
                        <div class="testi-content">
                            <p>Lorem ipsum dolor sit ametju consectadipisicing elit, sed do eiusmod tempor
                                incididunt ut labore et dolore magna aliqua. Ut enim ad minim
                            </p>
                        </div>
                        <div class="testi-author text-center">
                            <div class="author-name">
                                <h4 class="name">Britney Cooper</h4>
                                <span class="title">Client-HRO</span>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Slider Single Item -->
            </div>
            <!-- Add Arrows -->
            <div class="swiper-buttons">
                <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>
            </div>
        </div>
        <!-- Slider Start -->
    </div>
</div>
<!-- Testimonial Area End -->

<!-- Brand area start -->
<div class="brand-area pt-100px pb-100px">
    <div class="container">
        <div class="brand-slider swiper-container">
            <div class="swiper-wrapper align-items-center">
                <div class="swiper-slide brand-slider-item text-center">
                    <a href="#"><img class=" img-fluid" src="assets/images/brand-logo/1.png" alt="" /></a>
                </div>
                <div class="swiper-slide brand-slider-item text-center">
                    <a href="#"><img class=" img-fluid" src="assets/images/brand-logo/2.png" alt="" /></a>
                </div>
                <div class="swiper-slide brand-slider-item text-center">
                    <a href="#"><img class=" img-fluid" src="assets/images/brand-logo/3.png" alt="" /></a>
                </div>
                <div class="swiper-slide brand-slider-item text-center">
                    <a href="#"><img class=" img-fluid" src="assets/images/brand-logo/4.png" alt="" /></a>
                </div>
                <div class="swiper-slide brand-slider-item text-center">
                    <a href="#"><img class=" img-fluid" src="assets/images/brand-logo/5.png" alt="" /></a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Brand area end -->

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