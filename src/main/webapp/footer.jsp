<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Utils.CSRFUltils" %>
<%
    // generate a random CSRF token
    String csrfToken = CSRFUltils.getToken();
// place the CSRF token in a cookie
    javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("csrfToken", csrfToken);
    cookie.setHttpOnly(true);
    response.addCookie(cookie);
%>
<div class="newsletter-area ">
    <div class="container line-shape-bottom">
        <div class="row align-items-center">
            <div class="col-lg-6">
                <div class="newsletter-content mb-lm-30px">
                    <i class="pe-7s-mail-open-file"></i>
                    <div class="newsletter-text">
                        <h3 class="title">Newsletter & Get Updates</h3>
                        <p>Sign up for our newsletter to get update from us</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="news-letter-form">
                    <div id="mc_embed_signup" class="subscribe-form">
                        <form id="mc-embedded-subscribe-form" class="validate" novalidate="" target="_blank"
                              name="mc-embedded-subscribe-form" method="post"
                              action="#"
                        >
                            <input type="hidden" name="csrfToken" value="<%= csrfToken %>"/>
                            <div id="mc_embed_signup_scroll" class="mc-form">
                                <input class="email" type="email" required=""
                                       placeholder="Enter Your Mail Here......." name="EMAIL" value="">
                                <div class="mc-news d-none" aria-hidden="true">
                                    <input type="text" value="" tabindex="-1"
                                           name="b_6bbb9b6f5827bd842d9640c82_05d85f18ef">
                                </div>
                                <div class="clear">
                                    <!-- submit -->
                                    <button id="mc-embedded-subscribe" class="button" type="button" name="subscribe"
                                            value="" onclick="window.location.href='/404'"> Submit</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer Area Start -->
<div class="footer-area">
    <div class="footer-container">
        <div class="footer-top">
            <div class="container">
                <div class="row">
                    <!-- Start single blog -->
                    <div class="col-md-6 col-sm-6 col-lg-3 mb-md-30px mb-lm-30px">
                        <div class="single-wedge">
                            <h4 class="footer-herading">Information</h4>
                            <div class="footer-links">
                                <div class="footer-row">
                                    <ul class="align-items-center">
                                        <li class="li"><a class="single-link" href="/about">About us</a></li>
                                        <li class="li"><a class="single-link" href="/privacy">Privacy
                                            Policy</a></li>
                                        <li class="li"><a class="single-link" href="/faq">FAQ</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-lg-3 col-sm-6 mb-lm-30px">
                        <div class="single-wedge">
                            <h4 class="footer-herading">Account</h4>
                            <div class="footer-links">
                                <div class="footer-row">
                                    <ul class="align-items-center">
                                        <li class="li"><a class="single-link" href="/myaccount"> My account</a>
                                        </li>
                                        <li class="li"><a class="single-link" href="/cart">My orders</a></li>
                                        <!-- <li class="li"><a class="single-link"
                                                href="shop-left-sidebar.jsp">Shipping</a></li> -->
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-lg-2 col-sm-6 mb-sm-30px">
                        <div class="single-wedge">
                            <h4 class="footer-herading">Store </h4>
                            <div class="footer-links">
                                <div class="footer-row">
                                    <ul class="align-items-center">
                                        <li class="li"><a class="single-link" href="/home">Home</a></li>
                                        <li class="li"><a class="single-link"
                                                          href="/shop">Bestsellers</a></li>
                                        <li class="li"><a class="single-link" href="/contact">Contact Us</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-lg-4 col-md-6 col-sm-6 pl-120px line-shape">
                        <div class="single-wedge ">

                            <h4 class="footer-herading">Contact Us</h4>
                            <div class="footer-links">
                                <!-- News letter area -->
                                <p class="mail">If you have any question.please <br>
                                    contact us at <a href="mailto:demo@example.com">demo@example.com</a> </p>
                                <!-- <p class="address"><i class="pe-7s-map-marker"></i> <span>Your address goes here.
                                        <br>
                                        123, Address.</span> </p> -->
                                <p class="phone m-0"><i class="pe-7s-phone"></i><span><a href="tel:0123456789">+ 0 123
                                                456 789</a> <br> <a href="tel:0123456789">+ 0 123 456 789</a></span></p>

                                <!-- News letter area  End -->
                            </div>
                        </div>
                    </div>
                    <!-- End single blog -->
                </div>
            </div>
        </div>
        <div class="footer-bottom">
            <div class="container">
                <div class="line-shape-top">
                    <div class="row flex-md-row-reverse align-items-center">
                        <div class="col-md-6 text-center text-md-end">
                            <div class="payment-mth"><a href="#"><img class="img img-fluid"
                                                                      src="assets/images/icons/payment.png" alt="payment-image"></a></div>
                        </div>
                        <div class="col-md-6 text-center text-md-start">
                            <p class="copy-text"> © 2021 <strong>Mioca.</strong> Made With <i class="fa fa-heart"
                                                                                              aria-hidden="true"></i> By <a class="company-name"
                                                                                                                            href="<c:url value="/home" />">
                                <strong> Aslan Team</strong></a>.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div style="bottom: 60px; left: 20px " class="zalo-chat-widget" data-oaid="2896394760101211601" data-welcome-message="Rất vui khi được hỗ trợ bạn!" data-autopopup="0" data-width="300" data-height="300"></div>
<script src="https://sp.zalo.me/plugins/sdk.js"></script>
<!-- Footer Area End -->
