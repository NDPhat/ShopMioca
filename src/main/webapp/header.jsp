<%@ page import="Utils.CSRFUltils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Header Area Start -->
<%
    // generate a random CSRF token
    String csrfToken = CSRFUltils.getToken();
// place the CSRF token in a cookie
    javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("csrfToken", csrfToken);
    cookie.setHttpOnly(true);
    response.addCookie(cookie);
%>
<header>
    <div class="header-main sticky-nav ">
        <div class="container position-relative">
            <div class="row">
                <div class="col-auto align-self-center">
                    <div class="header-logo">
                        <a href="<c:url value="/home" />"><img src="assets/images/logo/logo.png" alt="Site Logo" /></a>
                    </div>
                </div>
                <div class="col align-self-center d-none d-lg-block">
                    <div class="main-menu">
                        <ul>
                            <li class="dropdown"><a href="<c:url value="/home" />">Home</a>
                            </li>
                            <li><a href="/about">About</a></li>
                            <li class="position-static"><a href="<c:url value="/shop" />">Shop</a>
                            </li>
                            <li class=""><a href="/comingsoon">Blog</a>
                            </li>
                            <li><a href="/contact">Contact</a></li>
                        </ul>
                    </div>
                </div>
                <!-- Header Action Start -->
                <div class="col col-lg-auto align-self-center pl-0">
                    <div class="header-actions">
                        <!-- Single Wedge Start -->
                        <a href="#" class="header-action-btn" data-bs-toggle="modal" data-bs-target="#searchActive">
                            <i class="pe-7s-search"></i>
                        </a>
                        <!-- Single Wedge End -->
                        <div class="header-bottom-set dropdown">
                            <button class="dropdown-toggle header-action-btn" data-bs-toggle="dropdown"><i
                                    class="pe-7s-users"></i></button>
                            <ul class="dropdown-menu dropdown-menu-right">
                                <li><a class="dropdown-item" href="<c:url value='/myaccount'/>">My account</a></li>
                                <c:if test="${sessionScope.loginedUser != null}">
                                    <li><a class="dropdown-item" href="/CheckOutController">Checkout</a></li>
                                </c:if>
                                <c:choose>
                                    <c:when test="${sessionScope.loginedUser == null}">
                                        <li><a class="dropdown-item" href="<c:url value='/login'/>">Sign in/Log in</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a class="dropdown-item" href="<c:url value='/logout'/>">Log out</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </div>
                        <!-- Single Wedge Start -->
                        <!-- Single Wedge End -->
                        <a onclick="onClickHeaderCartList();" href="#offcanvas-cart"
                           class="header-action-btn header-action-btn-cart offcanvas-toggle pr-0">
                            <i class="pe-7s-shopbag"></i>
<%--                            <c:if test="${cookie.products.value != null}">--%>
<%--                                <c:set var="temp" value="${cookie.products.value.split('p')}" />--%>
<%--                                <c:set var="numProduct" value="${0}" />--%>
<%--                                <c:forEach var="item" items="${temp}" >--%>
<%--&lt;%&ndash;                                    <c:out value="${item}" />&ndash;%&gt;--%>
<%--                                    <c:set var="numProduct" value="${numProduct + 1}"></c:set>--%>
<%--                                </c:forEach>--%>
                            <c:if test="${cookie.numOfProducts.value != null && cookie.numOfProducts.value != 0}">
                                <c:set var="numProduct" value="${cookie.numOfProducts.value}" />

<%--                                <c:set var="same" value="0" />--%>
<%--                                <c:set var="i" value="0" />--%>
<%--                                <c:if test="${numProduct > 1}" >--%>
<%--                                    <c:forEach begin="${i}" end="${numProduct - 2}" >--%>
<%--                                        <c:set var="j" value="${i+1}" />--%>
<%--                                        <c:--%>
<%--                                        <c:forEach begin="${j}" end="${numProduct - 1}" >--%>
<%--                                            <c:if test="${temp[i] == temp[j]}">--%>
<%--                                                <c:set var="same" value="${same + 1}" />--%>
<%--                                            </c:if>--%>
<%--                                            <c:set var="j" value="${j+1}" />--%>
<%--                                        </c:forEach>--%>
<%--                                        <c:set var="i" value="${i+1}" />--%>
<%--                                    </c:forEach>--%>
<%--                                </c:if>--%>
                                    <span class="header-action-num">${numProduct}</span>
                            </c:if>
                        </a>
                        <a href="#offcanvas-mobile-menu"products
                           class="header-action-btn header-action-btn-menu offcanvas-toggle d-lg-none">
                            <i class="pe-7s-menu"></i>
                        </a>
                    </div>
                    <!-- Header Action End -->
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Header Area End -->
<div class="offcanvas-overlay"></div>

<!-- OffCanvas Wishlist Start -->

<!-- OffCanvas Wishlist End -->
<!-- OffCanvas Cart Start -->
<div id="offcanvas-cart" class="offcanvas offcanvas-cart">
    <div class="inner">
        <div class="head">
            <span class="title">Cart</span>
            <button class="offcanvas-close">×</button>
        </div>

        <div class="body customScroll">
            <ul id="cartList" class="minicart-product-list">

            </ul>
        </div>
        <div class="foot">
            <div class="buttons mt-30px">
                <a href="/cart" class="btn btn-dark btn-hover-primary mb-30px">view cart</a>
                <c:if test="${sessionScope.loginedUser != null}">
                    <a href="<c:url value='/CheckOutController'/>" class="btn btn-outline-dark current-btn">checkout</a>
                </c:if>
            </div>
        </div>
    </div>
</div>
<!-- OffCanvas Cart End -->

<!-- OffCanvas Menu Start -->
<div id="offcanvas-mobile-menu" class="offcanvas offcanvas-mobile-menu">
    <button class="offcanvas-close"></button>

    <div class="inner customScroll">

        <div class="offcanvas-menu mb-4">
            <ul>
                <li><a href="<c:url value='/home'/>"><span class="menu-text">Home</span></a>
                </li>
                <li><a href="<c:url value='/about'/>">About</a></li>

                <li><a href="<c:url value='/shop'/>"><span class="menu-text">Shop</span></a>
                </li>
                <li><a href="<c:url value='/comingsoon'/>"><span class="menu-text">Blog</span></a>
                </li>
                <li><a href="<c:url value='/contact'/>">Contact Us</a></li>
            </ul>
        </div>
        <!-- OffCanvas Menu End -->
        <div class="offcanvas-social mt-auto">
            <ul>
                <li>
                    <a href="#"><i class="fa fa-facebook"></i></a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-twitter"></i></a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-google"></i></a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-youtube"></i></a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-instagram"></i></a>
                </li>
            </ul>
        </div>
    </div>
</div>
<!-- OffCanvas Menu End -->
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

<script>
    function onClickHeaderCartList() {
        event.preventDefault();
        $.ajax({
            url: "<c:url value="/api-header-cart-list" />",
            type: "get",
            success: function (response) {
                document.getElementById("cartList").innerHTML = response;
            },
            error: function (xhr) {
                alert("Loading data not success. Please comeback later <3")
            }
        })
    }

    function onRemove(productId, colorId, quantity) {
        event.preventDefault();
        $.ajax({
            url: "<c:url value="/api-remove-product-header" />",
            type: "get",
            data: {
                productId: productId,
                colorId: colorId,
                quantity: quantity,
            },
            success: function (response) {
                document.getElementById("cartList").innerHTML = response;
                window.location.reload(true);
            },
            error: function (xhr) {
                alert("Loading data not success. Please comeback later <3")
            }
        })
    }
</script>