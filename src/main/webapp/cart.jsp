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
        <div class="offcanvas-overlay"></div>

        <!-- OffCanvas Wishlist Start -->
        <!-- OffCanvas Wishlist End -->
        <!-- OffCanvas Cart Start -->

        <!-- OffCanvas Cart End -->

        <!-- OffCanvas Menu Start -->
        <!-- OffCanvas Menu End -->

        <!-- breadcrumb-area start -->
        <div class="breadcrumb-area">
            <div class="container">
                <div class="row align-items-center justify-content-center">
                    <div class="col-12 text-center">
                        <h2 class="breadcrumb-title">Cart</h2>
                        <!-- breadcrumb-list start -->
                        <ul class="breadcrumb-list">
                            <li class="breadcrumb-item"><a href="/home">Home</a></li>
                            <li class="breadcrumb-item active">Cart</li>
                        </ul>
                        <!-- breadcrumb-list end -->
                    </div>
                </div>
            </div>
        </div>
        <!-- breadcrumb-area end -->

        <!-- Cart Area Start -->
        <div class="cart-main-area pt-100px pb-100px">
            <div class="container">
                <h3 class="cart-page-title">Your cart items</h3>
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                        <div class="table-content table-responsive cart-table-content">
                            <table>
                                <thead>
                                    <tr>
                                        <th>Image</th>
                                        <th>Product Name</th>
                                        <th>Color</th>
                                        <th>Until Price</th>
                                        <th>Qty</th>
                                        <th>Subtotal</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody id="tbodyProduct">
                                    <c:forEach var="product" items="${productDisplayCartDTOList}">
                                        <form action="<c:url value="AddorCheckRedirectController/editQuantity" />">
                                            <input type="hidden" name="csrfToken" value="<%= csrfToken %>"/>
                                            <input type="hidden" name="path" value="cart">
                                            <tr>
                                                <td class="product-thumbnail">
                                                    <a href="#"><img class="img-responsive ml-15px"
                                                                     src="${product.getImage()}" alt="" /></a>
                                                </td>
                                                <td class="product-name"><a href="#">${product.getName()}</a></td>
                                                <td class="product-color"><a href="#">${product.getColorDTO().getName()}</a></td>
                                                <td class="product-price-cart"><span class="amount">$${product.getPrice()}</span></td>
                                                <td class="product-quantity">
                                                    <div class="cart-plus-minus updateQuantity">
                                                        <input  class="cart-plus-minus-box" type="text" name="inputQuantity"
                                                                value="${product.getQuantity()}" />
                                                    </div>
                                                </td>
                                                <td class="product-subtotal">$${product.getTotal()}</td>
                                                <td class="product-remove">
                                                    <input type="hidden" name="productId" value="${product.getId()}">
                                                    <input type="hidden" name="colorId" value="${product.getColorDTO().getId()}">
                                                    <input type="hidden" name="quantity" value="${product.getQuantity()}">
                                                    <button><i class="fa fa-pencil"></i></button>
                                                    <button formaction="<c:url value='AddorCheckRedirectController/removeProduct' />"><i class="fa fa-times"></i></button>
                                                </td>
                                            </tr>
                                        </form>

                                    </c:forEach>

                                    <%--                            <tr>--%>
                                    <%--                                <td class="product-thumbnail">--%>
                                    <%--                                    <a href="#"><img class="img-responsive ml-15px"--%>
                                    <%--                                                     src="assets/images/product-image/2.jpg" alt="" /></a>--%>
                                    <%--                                </td>--%>
                                    <%--                                <td class="product-name"><a href="#">Product Name</a></td>--%>
                                    <%--                                <td class="product-color"><a href="#">Dark Orange</a></td>--%>
                                    <%--                                <td class="product-price-cart"><span class="amount">$50.00</span></td>--%>
                                    <%--                                <td class="product-quantity">--%>
                                    <%--                                    <div class="cart-plus-minus">--%>
                                    <%--                                        <input class="cart-plus-minus-box" type="text" name="qtybutton"--%>
                                    <%--                                               value="1" />--%>
                                    <%--                                    </div>--%>
                                    <%--                                </td>--%>
                                    <%--                                <td class="product-subtotal">$80.00</td>--%>
                                    <%--                                <td class="product-remove">--%>
                                    <%--                                    <!-- <a href="#"><i class="fa fa-pencil"></i></a> -->--%>
                                    <%--                                    <a href="#"><i class="fa fa-times"></i></a>--%>
                                    <%--                                </td>--%>
                                    <%--                            </tr>--%>
                                    <%--                            <tr>--%>
                                    <%--                                <td class="product-thumbnail">--%>
                                    <%--                                    <a href="#"><img class="img-responsive ml-15px"--%>
                                    <%--                                                     src="assets/images/product-image/3.jpg" alt="" /></a>--%>
                                    <%--                                </td>--%>
                                    <%--                                <td class="product-name"><a href="#">Product Name</a></td>--%>
                                    <%--                                <td class="product-color"><a href="#">Spring Green</a></td>--%>
                                    <%--                                <td class="product-price-cart"><span class="amount">$70.00</span></td>--%>
                                    <%--                                <td class="product-quantity">--%>
                                    <%--                                    <div class="cart-plus-minus">--%>
                                    <%--                                        <input class="cart-plus-minus-box" type="text" name="qtybutton"--%>
                                    <%--                                               value="1" />--%>
                                    <%--                                    </div>--%>
                                    <%--                                </td>--%>
                                    <%--                                <td class="product-subtotal">$90.00</td>--%>
                                    <%--                                <td class="product-remove">--%>
                                    <%--                                    <!-- <a href="#"><i class="fa fa-pencil"></i></a> -->--%>
                                    <%--                                    <a href="#"><i class="fa fa-times"></i></a>--%>
                                    <%--                                </td>--%>
                                    <%--                            </tr>--%>
                                </tbody>
                            </table>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="cart-shiping-update-wrapper">
                                    <div class="cart-shiping-update">
                                        <a href="/shop">Continue Shopping</a>
                                    </div>
                                    <div class="cart-clear">
                                        <button id="btnUpdateCart" onclick="alreadyExistingFunc();">Update Shopping Cart</button>
                                        <a href="/cart/clear">Clear Shopping Cart</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="mt-md-30px">
                                <div class="grand-totall">
                                    <div class="title-wrap">
                                        <h4 class="cart-bottom-title section-bg-gary-cart">Cart Total</h4>
                                    </div>
                                    <div class="total-shipping">
                                        <h5>Total shipping</h5>
                                        <ul>
                                            <li><input type="checkbox" /> Standard <span>$20.00</span></li>
                                            <li><input type="checkbox" /> Express <span>$30.00</span></li>
                                        </ul>
                                    </div>
                                    <h4 class="grand-totall-title">Grand Total <span>$${total}</span></h4>
                                    <a href="/CheckOutController">Proceed to Checkout</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Cart Area End -->

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
        <script>
            // function onRemove() {
            //     let href = "AddorCheckRedirectController/removeProduct";
            //     window.location.href = href;
            // }

            function alreadyExistingFunc() {
                var values = $("input[name='inputQuantity']")
                    .map(function(){return $(this).val();}).get();
                var inputQuantities = {
                    name: values
                }
                $.ajax({
                    url: 'cart/editAll',
                    type: 'POST',
                    data: {
                        inputQuantities: JSON.stringify(inputQuantities)
                    },
                    success(response) {
                        location.reload();
                    },
                    error: function(response) {
                        alert("Error");
                    }
                });
            }
            function ajaxSuccess()
            {
                document.getElementById('btnUpdateCart').onClick = function() {
                    alreadyExistingFunc();
                }
            }
        </script>
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