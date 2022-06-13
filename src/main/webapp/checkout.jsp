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
    <link rel="stylesheet" href="assets/css/myaccout.css"/>

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
    <script src="assets/js/myaccount.js"></script>

</head>

<body>


<!-- Header Area Start -->
<jsp:include page="header.jsp" />
<!-- Header Area End -->
<div class="offcanvas-overlay"></div>


<!-- breadcrumb-area start -->
<div class="breadcrumb-area">
    <div class="container">
        <div class="row align-items-center justify-content-center">
            <div class="col-12 text-center">
                <h2 class="breadcrumb-title">Checkout</h2>
                <!-- breadcrumb-list start -->
                <ul class="breadcrumb-list">
                    <li class="breadcrumb-item"><a href="/home">Home</a></li>
                    <li class="breadcrumb-item active">Checkout</li>
                </ul>
                <!-- breadcrumb-list end -->
            </div>
        </div>
    </div>
</div>
<!-- breadcrumb-area end -->
<c:if test="${requestScope.isSuccess != null}">
    <c:if test="${requestScope.isSuccess == false}">
        <div class="alert danger-alert">
            <h3>Update Failed</h3>
            <a class="close">&times;</a>
        </div>
        <c:if test="${requestScope.message != null}">
            <div class="alert danger-alert">
                <h3><c:out  value="${requestScope.message}"/></h3>
                <a class="close">&times;</a>
            </div>
            <script>

            </script>
        </c:if>
    </c:if>
    <c:if test="${requestScope.isSuccess == true}">
        <div class="alert success-alert">
            <h3>Update Successfully</h3>
            <a class="close">&times;</a>
        </div>
    </c:if>
</c:if>
<!-- checkout area start -->
<div class="checkout-area pt-100px pb-100px">
    <div class="container" >
        <div class="row">
            <div class="col-lg-7">
                <div class="billing-info-wrap">
                    <h3>Billing Details</h3>
                    <div class="row">
                        <form action="/UpdateInfoController" method="post" id="form-1">
                            <input type="hidden" name="csrfToken" value="<%= csrfToken %>"/>
                            <div class="col-lg-12">
                                <div class="billing-info mb-4">
                                    <label>First Name</label>
                                    <input id="firstname" placeholder="Enter Your First Name" type="text" name="firstname" value="<c:out value="${sessionScope.loginedUser.getFirstname()}"/>" >
                                    <span class="form-message" style="color: red"></span>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="billing-info mb-4">
                                    <label>Last Name</label>
                                    <input type="text" id="lastname" placeholder="Enter Your Last Name" name="lastname" value="<c:out value="${sessionScope.loginedUser.getLastname()}"/>">
                                    <span class="form-message" style="color: red"></span>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="billing-info mb-4">
                                    <label>Phone</label>
                                    <input type="text" id="phone" placeholder="Enter Your Phone Number" name="phone" value="<c:out value="${sessionScope.loginedUser.getPhone()}"/>">
                                    <span class="form-message" style="color: red"></span>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="billing-info mb-4">
                                    <label>Email Address</label>
                                    <input type="text"  id="email" placeholder="Enter Your Email" name="email"  value="<c:out value="${sessionScope.loginedUser.getEmail()}"/>">
                                    <span class="form-message" style="color: red"></span>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="billing-info mb-4">
                                    <label>House Number</label>
                                    <input  id="number" type="text" placeholder="Enter Your House Number" name="number" value="<c:out value="${sessionScope.loginedUser.address.getNumber()}"/>">
                                    <span class="form-message" style="color: red"></span>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="billing-info mb-4">
                                    <label>Street</label>
                                    <input type="text"  id="street" name="street" placeholder="Enter Your Street"  value="<c:out value="${sessionScope.loginedUser.address.getStreet()}"/>">
                                    <span class="form-message" style="color: red"></span>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="billing-info mb-4">
                                    <label>Commune</label>
                                    <input type="text"  id="commune" name="commune" placeholder="Enter Your Commune" value="<c:out value="${sessionScope.loginedUser.address.getCommune()}"/>">
                                    <span class="form-message" style="color: red"></span>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="billing-info mb-4">
                                    <label>Distric</label>
                                    <input type="text"  id="district" name="district" placeholder="Enter Your District" value="<c:out value="${sessionScope.loginedUser.address.getDistrict()}"/>">
                                    <span class="form-message" style="color: red"></span>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="billing-info mb-4">
                                    <label>Province</label>
                                    <input type="text"  id="province" name="province"  placeholder="Enter Your Province" value="<c:out value="${sessionScope.loginedUser.address.getProvince()}"/>">
                                    <span class="form-message" style="color: red"></span>
                                </div>
                            </div>
                            <div class="save_button mt-3" >
                                <button class="btn" type="submit">Update Information</button>
                            </div>
                        </form>
                    </div>

                </div>
            </div>

            <div class="col-lg-5 mt-md-30px mt-lm-30px">
                <div class="your-order-area">
                    <h3>Your order</h3>
                    <div class="your-order-wrap gray-bg-4">
                        <div class="your-order-product-info">
                            <div class="your-order-top">
                                <ul>
                                    <li>Product</li>
                                    <li>Total</li>
                                </ul>
                            </div>
                            <div class="your-order-middle">
                                <ul>
                                    <c:forEach items="${sessionScope.orderProductDTOList}" var="orderProductDTO" >
                                        <li>
                                            <span class="order-middle-left"> ${orderProductDTO.getName()} X ${orderProductDTO.getQuantity()}</span>
                                            <span class="order-price">$${orderProductDTO.getSubtotal()} </span>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                            <div class="your-order-bottom">
                                <ul>
                                    <li class="your-order-shipping">Shipping</li>
                                    <li>Free shipping</li>
                                </ul>
                            </div>
                            <div class="your-order-total">
                                <ul>
                                    <li class="order-total">Total</li>
                                    <li>
                                        <span class="order-price">$${sessionScope.sumsubtotal} </span>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="payment-method" >
                            <div class="payment-accordion element-mrg">
                                <div id="faq" class="panel-group">
                                    <div class="panel panel-default single-my-account m-0">
                                        <div class="panel-heading my-account-title">
                                            <h4 class="panel-title"><a data-bs-toggle="collapse"
                                                                       href="#my-account-1" class="collapsed"
                                                                       aria-expanded="true">Direct bank transfer</a>
                                            </h4>
                                        </div>
                                        <div id="my-account-1" class="panel-collapse collapse show"
                                             data-bs-parent="#faq">

                                            <div class="panel-body">
                                                <p>Please choose another payment-method.Shop does not support Direct bank transfer</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel panel-default single-my-account m-0">
                                        <div class="panel-heading my-account-title">
                                            <h4 class="panel-title"><a data-bs-toggle="collapse"
                                                                       href="#my-account-2" aria-expanded="false"
                                                                       class="collapsed">Check payments</a></h4>
                                        </div>
                                        <div id="my-account-2" class="panel-collapse collapse"
                                             data-bs-parent="#faq">

                                            <div class="panel-body">
                                                <p>Please choose another payment-method.Shop does not support Check payments.</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel panel-default single-my-account m-0">
                                        <div class="panel-heading my-account-title">
                                            <h4 class="panel-title"><a data-bs-toggle="collapse"
                                                                       href="#my-account-3">Cash on delivery</a></h4>
                                        </div>
                                        <div id="my-account-3" class="panel-collapse collapse"
                                             data-bs-parent="#faq">

                                            <div class="panel-body">
                                                <p>Please choose another payment-method.Shop does not support Cash on delivery.</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="Place-order mt-25">
                        <a class="btn-hover" href="/PlaceOrderController?sale_orderid=${sessionScope.salesOrdersEntity.getId()}">Place Order</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- checkout area end -->

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
<script src="./assets/js/Validator.js"></script>
<script >
    Validator(
        {
            form:'#form-1',
            formGroupSelector:'.billing-info',
            rules : [
                Validator.isRequired('#firstname'),
                Validator.isRequired('#lastname'),
                Validator.isRequired('#email'),
                Validator.isRequired('#street'),
                Validator.isRequired('#commune'),
                Validator.isRequired('#phone'),
                Validator.isRequired('#province'),
                Validator.isRequired('#district'),
                Validator.isRequired('#number'),
                Validator.isEmail('#email'),
            ],
        }
    )
</script>

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
<script src="assets/js/myaccount.js"></script>
</body>

</html>