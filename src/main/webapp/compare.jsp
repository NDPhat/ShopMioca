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

<!-- breadcrumb-area start -->
<div class="breadcrumb-area">
    <div class="container">
        <div class="row align-items-center justify-content-center">
            <div class="col-12 text-center">
                <h2 class="breadcrumb-title">Single Product</h2>
                <!-- breadcrumb-list start -->
                <ul class="breadcrumb-list">
                    <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
                    <li class="breadcrumb-item active">Product</li>
                </ul>
                <!-- breadcrumb-list end -->
            </div>
        </div>
    </div>
</div>
<!-- breadcrumb-area end -->




<!-- Compare Area Start -->
<div class="compare-area pt-100px pb-100px">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <form action="#">
                    <input type="hidden" name="csrfToken" value="<%= csrfToken %>"/>
                    <!-- Compare Table -->
                    <div class="compare-table table-responsive">
                        <table class="table mb-0">
                            <tbody>
                            <tr>
                                <td class="first-column">Product</td>
                                <td class="product-image-title">
                                    <a href="#" class="image"><img class="img-responsive"
                                                                   src="assets/images/product-image/1.jpg" alt="Compare Product" /></a>
                                    <a href="#" class="category">Mortar</a>
                                    <a href="#" class="title">Hand-Made Garlic Mortar</a>
                                </td>
                                <td class="product-image-title">
                                    <a href="#" class="image"><img class="img-responsive"
                                                                   src="assets/images/product-image/2.jpg" alt="Compare Product" /></a>
                                    <a href="#" class="category">Pottery</a>
                                    <a href="#" class="title">Handmade Ceramic Pottery</a>
                                </td>
                                <td class="product-image-title">
                                    <a href="#" class="image"><img class="img-responsive"
                                                                   src="assets/images/product-image/3.jpg" alt="Compare Product" /></a>
                                    <a href="#" class="category">Bowls</a>
                                    <a href="#" class="title">Hand Painted Bowls</a>
                                </td>
                            </tr>
                            <tr>
                                <td class="first-column">Description</td>
                                <td class="pro-desc">
                                    <p>Lorem ipsum dolor sit amet, consect adipisicing elit, sed do eiusmod tempor incidi ut labore
                                        et dolore magna aliqua.</p>
                                </td>
                                <td class="pro-desc">
                                    <p>Lorem ipsum dolor sit amet, consect adipisicing elit, sed do eiusmod tempor incidi ut labore
                                        et dolore magna aliqua.</p>
                                </td>
                                <td class="pro-desc">
                                    <p>Lorem ipsum dolor sit amet, consect adipisicing elit, sed do eiusmod tempor incidi ut labore
                                        et dolore magna aliqua. </p>
                                </td>
                            </tr>
                            <tr>
                                <td class="first-column">Price</td>
                                <td class="pro-price">$295</td>
                                <td class="pro-price">$275</td>
                                <td class="pro-price">$395</td>
                            </tr>
                            <tr>
                                <td class="first-column">Color</td>
                                <td class="pro-color">Black</td>
                                <td class="pro-color">Black</td>
                                <td class="pro-color">Black</td>
                            </tr>
                            <tr>
                                <td class="first-column">Stock</td>
                                <td class="pro-stock">In Stock</td>
                                <td class="pro-stock">In Stock</td>
                                <td class="pro-stock">In Stock</td>
                            </tr>
                            <tr>
                                <td class="first-column">Add to cart</td>
                                <td class="pro-addtocart">
                                    <a href="#" class="add-to-cart" tabindex="0"><span>ADD TO CART</span></a>
                                </td>
                                <td class="pro-addtocart">
                                    <a href="#" class="add-to-cart" tabindex="0"><span>ADD TO CART</span></a>
                                </td>
                                <td class="pro-addtocart">
                                    <a href="#" class="add-to-cart" tabindex="0"><span>ADD TO CART</span></a>
                                </td>
                            </tr>
                            <tr>
                                <td class="first-column">Delete</td>
                                <td class="pro-remove">
                                    <button><i class="fa fa-trash-o"></i></button>
                                </td>
                                <td class="pro-remove">
                                    <button><i class="fa fa-trash-o"></i></button>
                                </td>
                                <td class="pro-remove">
                                    <button><i class="fa fa-trash-o"></i></button>
                                </td>
                            </tr>
                            <tr>
                                <td class="first-column">Rating</td>
                                <td class="pro-ratting">
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                </td>
                                <td class="pro-ratting">
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                </td>
                                <td class="pro-ratting">
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Compare Area End -->

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