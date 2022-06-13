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

    <!-- Main Style -->
    <link rel="stylesheet" href="assets/css/style.css" />
    <link rel="stylesheet" href="assets/css/shop-left-sidebar.css" />
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
                <h2 class="breadcrumb-title">Shop</h2>
                <!-- breadcrumb-list start -->
                <ul class="breadcrumb-list">
                    <li class="breadcrumb-item"><a href="<c:url value="/home" />">Home</a></li>
                    <li class="breadcrumb-item active">Shop</li>
                </ul>
                <!-- breadcrumb-list end -->
            </div>
        </div>
    </div>
</div>
<!-- breadcrumb-area end -->



<!-- Shop Page Start  -->
<div class="shop-category-area pt-100px pb-100px">
    <div class="container">
        <div class="row">
            <div class="col-lg-9 order-lg-last col-md-12 order-md-first">
                <div class="sidebar-widget-image">
                    <a href="#" class="single-banner">
                        <img src="assets/images/banner/12.jpg" alt="">
                    </a>
                </div>
                <!-- Shop Top Area Start -->
                <div class="desktop-tab">
                    <div class="shop-top-bar d-flex">
                        <!-- Left Side End -->
                        <div class="shop-tab nav">
                            <a onclick="onClickDisplay(1)" class="active" href="#shop-grid" data-bs-toggle="tab">
                                <i class="fa fa-th" aria-hidden="true"></i>
                            </a>
                            <a onclick="onClickDisplay(2)" href="#shop-list" data-bs-toggle="tab">
                                <i class="fa fa-list" aria-hidden="true"></i>
                            </a>
                        </div>
                        <!-- Right Side Start -->
                        <div class="select-shoing-wrap d-flex align-items-center">
                            <div class="shot-product">
                                <p>Sort By:</p>
                            </div>
                            <div class="shop-select">
                                <select class="shop-sort" id="sort" onchange="onChangeSort()">
                                    <option value="1" selected> Name, A to Z</option>
                                    <option value="2"> Name, Z to A</option>
                                    <option value="3"> Price, low to high</option>
                                    <option value="4"> Price, high to low</option>
                                </select>

                            </div>
                        </div>
                        <!-- Right Side End -->
                        <!-- Left Side start -->
                        <div class="shot-product" style="display: flex;"><p style="margin: 5px;">Search:</p>
                            <input oninput="onInputSearching(this.value);" type="text" placeholder="Searching" style="border-radius: 5px;height: 38px;"></div>
                    </div>
                </div>
                <!-- Shop Top Area End -->

                <!-- Mobile shop bar -->
                <div class="shop-top-bar mobile-tab">
                    <!-- Left Side End -->
                    <div class="shop-tab nav d-flex justify-content-between">
                        <div class="shop-tab nav">
                            <a onclick="onClickDisplay(1)" class="active" href="#shop-grid" data-bs-toggle="tab">
                                <i class="fa fa-th" aria-hidden="true"></i>
                            </a>
                            <a onclick="onClickDisplay(2)" href="#shop-list" data-bs-toggle="tab">
                                <i class="fa fa-list" aria-hidden="true"></i>
                            </a>
                        </div>
                        <!-- Right Side Start -->
                        <div class="select-shoing-wrap d-flex align-items-center">
                            <div class="shot-product">
                                <p>Sort By:</p>
                            </div>
                            <div class="shop-select">
                                <select class="shop-sort" id="sort-mobile" onchange="onChangeSortMobile()">
                                    <option value="1"> Name, A to Z</option>
                                    <option value="2"> Name, Z to A</option>
                                    <option value="3"> Price, low to high</option>
                                    <option value="4"> Price, high to low</option>
                                </select>

                            </div>
                        </div>
                    </div>
                    <!-- Right Side End -->
                    <!-- Right Side Start -->
                    <div class="shot-product" style="display: flex;"><p style="margin: 5px;">Search:</p>
                        <input oninput="onInputSearching(this.value);" type="text" placeholder="Searching" style="border-radius: 5px;height: 38px;"></div>
                </div>
                <!-- Right Side End -->
                <!-- Left Side start -->
                <!-- Mobile shop bar -->

                <!-- Shop Bottom Area Start -->
                <div class="shop-bottom-area">

                    <!-- Tab Content Area Start -->
                    <div class="row">
                        <div class="col">
                            <div class="tab-content">
                                <div class="tab-pane fade show active" id="shop-grid">
                                    <div id="rowList" class="row mb-n-30px">
                                        <c:forEach var="item" items="${AllProduct}" >
                                            <div class="col-lg-4 col-md-6 col-sm-6 col-xs-6 mb-30px" data-aos="fade-up"
                                                 data-aos-delay="200">
                                                <!-- Single Prodect -->
                                                <div class="product">
                                                    <div class="thumb">
                                                        <a href="singleproduct?productId=${item.getId()}" class="image">
                                                            <img src="${item.getImage()}" alt="Product" />
                                                            <img class="hover-image" src="${item.getImage()}"
                                                                 alt="Product" />
                                                        </a>
                                                        <span class="badges">
                                                    <c:if test="${item.isProductStatus()}">
                                                        <span class="sale">-${item.getDiscount_percent()}%</span>
                                                    </c:if>
                                                    <c:forEach var="tag" items="${item.getTagsName()}">
                                                        <span class="new">${tag.toString().trim()}</span>
                                                    </c:forEach>
                                                </span>
                                                        <div class="actions">
                                                            <a href="wishlist.jsp" class="action wishlist" title="Wishlist"><i
                                                                    class="pe-7s-like"></i></a>
                                                            <a onclick="onClickLoadData(${item.getId()}, 2)" href="#" class="action quickview" data-link-action="quickview"
                                                               title="Quick view" data-bs-toggle="modal"
                                                               data-bs-target="#exampleModal"><i class="pe-7s-look"></i></a>
                                                            <a href="compare.jsp" class="action compare" title="Compare"><i
                                                                    class="pe-7s-refresh-2"></i></a>
                                                        </div>
                                                    </div>
                                                    <div class="content">
                                                        <div class="rating-product">
                                                            <c:set var="star" scope="session" value="${item.getAvgReview()}" />
                                                            <c:forEach begin="1" end="${star}" varStatus="loop" >
                                                                <i class="fa fa-star" style="color: #ffde00"></i>
                                                            </c:forEach>

                                                            <c:forEach begin="${star + 1}" end="5" varStatus="loop" >
                                                                <i class="fa fa-star" style="color: #bcbebf"></i>
                                                            </c:forEach>
                                                        </div>
                                                        <h5 class="title"><a href="singleproduct?productId=${item.getId()}">${item.getName()}
                                                        </a>
                                                        </h5>
                                                        <span class="price">
                                                    <c:choose>
                                                        <c:when test="${item.isProductStatus()}">
                                                            <span class="new">$${item.getDiscountPrice()}</span>
                                                            <span class="old">$${item.getRegularPrice()}</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="new">$${item.getRegularPrice()}</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </span>
                                                    </div>
                                                    <form action="/AddorCheckRedirectController" method="post">
                                                        <input hidden type="text" name="path" value="shop" >
                                                        <input hidden type="text" name="redi" value=-1 >
                                                        <input hidden type="text" name="colorRedi" value=-1 >
                                                        <input hidden type="text" name="tagRedi" value=-1 >
                                                        <input hidden type="text" name="productId" value=${item.getId()} >
                                                        <input hidden type="text" name="quantity" value=1 >
                                                        <input hidden type="text" name="colorId" value=${item.getColorsId().get(0)} >
                                                        <button type="submit" title="Add To Cart" class=" add-to-cart">Add To Cart</button>
                                                    </form>
<%--                                                    <button onclick="onClickAddToCart(${item.getId()}, ${item.getQuantity()}, ${item.getColorsId().get(0)})" title="Add To Cart" class=" add-to-cart">Add--%>
<%--                                                        To Cart</button>--%>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
<%--                                <div class="tab-pane fade" id="shop-list">--%>
<%--                                    <c:forEach var="item" items="${AllProduct}" >--%>
<%--                                        <div class="shop-list-wrapper">--%>
<%--                                            <div class="row">--%>
<%--                                                <div class="col-md-5 col-lg-5 col-xl-4">--%>
<%--                                                    <div class="product">--%>
<%--                                                        <div class="thumb">--%>
<%--                                                            <a href="singleproduct?productId=${item.getId()}" class="image">--%>
<%--                                                                <img src="${item.getImage()}"--%>
<%--                                                                     alt="Product" />--%>
<%--                                                                <img class="hover-image"--%>
<%--                                                                     src="${item.getImage()}"--%>
<%--                                                                     alt="Product" />--%>
<%--                                                            </a>--%>
<%--                                                            <span class="badges">--%>
<%--                                                                <c:if test="${item.isProductStatus()}">--%>
<%--                                                                    <span class="sale">-${item.getDiscount_percent()}%</span>--%>
<%--                                                                </c:if>--%>
<%--                                                                <c:forEach var="tag" items="${item.getTagsName()}">--%>
<%--                                                                    <span class="new">${tag.toString().trim()}</span>--%>
<%--                                                                </c:forEach>--%>
<%--                                                            </span>--%>
<%--                                                        </div>--%>
<%--                                                    </div>--%>
<%--                                                </div>--%>
<%--                                                <div class="col-md-7 col-lg-7 col-xl-8">--%>
<%--                                                    <div class="content-desc-wrap">--%>
<%--                                                        <div class="content">--%>
<%--                                                            <div class="rating-product">--%>
<%--                                                                <c:set var="star" scope="session" value="${item.getAvgReview()}" />--%>
<%--                                                                <c:forEach begin="1" end="${star}" varStatus="loop" >--%>
<%--                                                                    <i class="fa fa-star" style="color: #ffde00"></i>--%>
<%--                                                                </c:forEach>--%>

<%--                                                                <c:forEach begin="${star + 1}" end="5" varStatus="loop" >--%>
<%--                                                                    <i class="fa fa-star" style="color: #bcbebf"></i>--%>
<%--                                                                </c:forEach>--%>
<%--                                                            </div>--%>
<%--                                                            <h5 class="title">--%>
<%--                                                                <a href="single-product.jsp">${item.getName()}</a>--%>
<%--                                                            </h5>--%>
<%--                                                            <p>${item.getDescription().split("\\.")[0]} </p>--%>
<%--                                                        </div>--%>
<%--                                                        <div class="box-inner">--%>
<%--                                                            <span class="price">--%>
<%--                                                                <c:choose>--%>
<%--                                                                    <c:when test="${item.isProductStatus()}">--%>
<%--                                                                        <span class="new">$${item.getDiscountPrice()}</span>--%>
<%--                                                                        <span class="old">$${item.getRegularPrice()}</span>--%>
<%--                                                                    </c:when>--%>
<%--                                                                    <c:otherwise>--%>
<%--                                                                        <span class="new">$${item.getRegularPrice()}</span>--%>
<%--                                                                    </c:otherwise>--%>
<%--                                                                </c:choose>--%>
<%--                                                            </span>--%>
<%--                                                            <div class="actions">--%>
<%--                                                                <a href="wishlist.jsp" class="action wishlist"--%>
<%--                                                                   title="Wishlist"><i class="pe-7s-like"></i></a>--%>
<%--                                                                <a onclick="onClickLoadData(${item.getId()}, 2)"--%>
<%--                                                                   href="#" class="action quickview"--%>
<%--                                                                   data-link-action="quickview" title="Quick view"--%>
<%--                                                                   data-bs-toggle="modal"--%>
<%--                                                                   data-bs-target="#exampleModal"><i--%>
<%--                                                                        class="pe-7s-search"></i></a>--%>
<%--                                                                <a href="compare.jsp" class="action compare"--%>
<%--                                                                   title="Compare"><i class="pe-7s-refresh-2"></i></a>--%>
<%--                                                            </div>--%>
<%--                                                            <button onclick="onClickAddToCart(${item.getId()}, ${item.getQuantity()}, ${item.getColorsId().get(0)})" title="Add To Cart" class=" add-to-cart">Add--%>
<%--                                                                To Cart</button>--%>
<%--                                                        </div>--%>
<%--                                                    </div>--%>
<%--                                                </div>--%>
<%--                                            </div>--%>
<%--                                        </div>--%>
<%--                                    </c:forEach>--%>
<%--                                </div>--%>
                            </div>
                        </div>
                    </div>
                    <!-- Tab Content Area End -->

                    <!--  Pagination Area Start -->
                    <div class="pro-pagination-style text-center text-lg-end" data-aos="fade-up" data-aos-delay="200">
                        <div class="pages">
                            <ul>
                                <li id="Previous" onclick="onClickPreNex(this.id);"><a class="page-link" href="#"><i class="fa fa-angle-left"></i></a>
                                </li>
                                <span id="paging">

                                </span>
                                <li id="Next" onclick="onClickPreNex(this.id);"><a class="page-link" href="#"><i class="fa fa-angle-right"></i></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!--  Pagination Area End -->
                </div>
                <!-- Shop Bottom Area End -->
            </div>
            <!-- Sidebar Area Start -->
            <div class="col-lg-3 order-lg-first col-md-12 order-md-last">
                <div class="shop-sidebar-wrap">
                    <!-- Sidebar single item -->
                    <div class="sidebar-widget">
                        <h4 class="sidebar-title">Categories</h4>
                        <div class="sidebar-widget-category">
                            <ul class="ul-cate">
                                <li id="cate-all" onclick="onClickHandleId(this.id, 'cate')" class="li-cate">
                                    <a href="#" class="selected m-0">
                                        <i class="fa fa-angle-right"></i>
                                        All
                                        <span>(${allProductSize})</span>
                                    </a>
                                </li>
                                <c:forEach var="item" items="${categoriesShopDTOList}">
                                    <li id="cate-${item.getId()}" onclick="onClickHandleId(this.id, 'cate')" class="li-cate">
                                        <a href="#" class="">
                                            <i class="fa fa-angle-right"></i>
                                                ${item.getName()}
                                            <span>(${item.getSize()})</span>
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <!-- Sidebar single item -->
                    <div class="sidebar-widget">
                        <h4 class="sidebar-title">Color</h4>
                        <div class="sidebar-widget-color">
                            <ul>
                                <li id="colo-all" onclick="onClickHandleId(this.id, 'color')" class="li-color">
                                    <a href="#" class="selected m-0">
                                        <i class="fa fa-angle-right"></i>
                                        All
                                        <span>(${allProductSize})</span>
                                    </a>
                                </li>
                                <c:forEach var="item" items="${colorShopDTOList}">
                                    <li id="colo-${item.getId()}" onclick="onClickHandleId(this.id, 'color')" class="li-color">
                                        <a href="#" class="">
                                            <i class="fa fa-angle-right"></i>
                                                ${item.getName()}
                                            <span>(${item.getSize()})</span>
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <!-- Sidebar single item -->
                    <div class="sidebar-widget mt-8">
                        <h4 class="sidebar-title">Price Filter</h4>
                        <div class="price-filter">
                            <div class="range-slider" >
                                <h6 style="color: #6c6c6c">$ Min: </h6>
                                <input onclick="getMin()" class="range-slider__range" type="range" value="0" min="0" max="${max}" step="100">
                                <span id="min" class="range-slider__value" >0</span>
                            </div>

                            <div class="range-slider">
                                <h6 style="color: #6c6c6c">$ Max: </h6>
                                <input onclick="getMax()" class="range-slider__range" type="range" value="${max}" min="0" max="${max}" step="100">
                                <span id="max" class="range-slider__value">0</span>
                            </div>
                        </div>
                    </div>
                    <!-- Sidebar single item -->
                    <div class="sidebar-widget-image ">
                        <a href="#" class="single-banner">
                            <img src="assets/images/banner/11.jpg" alt="">
                        </a>
                    </div>
                    <!-- Sidebar single item -->
                    <div class="sidebar-widget tag m-0">
                        <h4 class="sidebar-title">Tags</h4>
                        <div class="sidebar-widget-tag">
                            <ul>
                                <li id="tags-all" onclick="onClickHandleId(this.id, 'tags')" class="li-tags">
                                    <a style="color: white" class="tag-selected" href="#">Default</a>
                                </li>
                                <c:forEach var="item" items="${tagShopDTOList}">
                                    <li id="tags-${item.getId()}" onclick="onClickHandleId(this.id, 'tags')" class="li-tags">
                                        <a href="#">${item.getName()}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <!-- Sidebar single item -->
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Shop Page End  -->

<!-- Footer Area Start -->
<jsp:include page="footer.jsp" />
<!-- Footer Area End -->

<!-- Modal -->
<div class="modal modal-2 fade" id="exampleModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row" id="modal">

                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal end -->

<!-- Global Vendor, plugins JS -->

<!-- Vendor JS -->
<script src="assets/js/vendor/jquery-3.5.1.min.js"></script>

<script src="assets/js/vendor/bootstrap.bundle.min.js"></script>
<script src="assets/js/vendor/jquery-migrate-3.3.0.min.js"></script>
<script src="assets/js/vendor/modernizr-3.11.2.min.js"></script>

<!--Plugins JS-->
<script src="assets/js/plugins/swiper-bundle.min.js"></script>
<%--<script src="assets/js/plugins/jquery-ui.min.js"></script>--%>
<script src="assets/js/plugins/jquery.nice-select.min.js"></script>
<script src="assets/js/plugins/countdown.js"></script>
<script src="assets/js/plugins/scrollup.js"></script>
<script src="assets/js/plugins/jquery.zoom.min.js"></script>
<script src="assets/js/plugins/venobox.min.js"></script>

<!-- Main Js -->
<script src="assets/js/main.js"></script>

<script>
    let currentPage = 1;
    let display = 1;
    let cateId = "-1";
    let colorId = "-1";
    let tagId = "-1";
    let min = 0;
    let max = ${max};
    let sort = 1;
    let numPage = parseInt(${totalPages}, 10);
    let flag = "page"; //sort
    let searching = "";
    //let scroll = 0;

    function onClickAddToCart(idItem, quantity, idColor) {
        if (quantity === 0) {
            event.preventDefault();
            alert("We apologize for this inconvenience." +
                "\nThe item is currently out of stock, please comeback later");
        }
        else {
            let href = '/AddorCheckRedirectController?productId=' + idItem + '&quantity=1&colorId=' + idColor + "&path=shop&redi=" + cateId + "&colorRedi=" + colorId + "&tagRedi=" + tagId;
            window.location.href = href;
        }
    }

    function onInputSearching(item) {
        searching = item;
        this.sortAndFilter_LiveSearch();
    }

    function onChangeSortMobile() {
        sort = document.getElementById("sort-mobile").value;
        sortAndFilter_LiveSearch();
    }

    function onChangeSort() {
        sort = document.getElementById("sort").value;
        this.sortAndFilter_LiveSearch();
    }

    function getMin() {
        min = Number($("#min").text());
        this.sortAndFilter_LiveSearch();
    }

    function getMax() {
        max = Number($("#max").text());
        this.sortAndFilter_LiveSearch();
    }

    function onClickHandleId(id, flag) {
        // "colo-" + ID
        // "tags-" + ID
        // "cate-" + ID
        let ID = id.slice(5);
        if (ID === "all") {
            ID = "-1";
        }
        if (flag === "cate") {
            cateId = ID;
        }
        else if (flag === "color") {
            colorId = ID;
        }
        else {
            tagId = ID;
        }
        this.sortAndFilter_LiveSearch();
    }

    function sortAndFilter_LiveSearch() {
        flag = "sort";
        currentPage = 1;
        let curPage = currentPage;
        let startPos = (curPage - 1) * 9;
        let type = "get";
        display === 1 ? type = "get" : type = "post";
        let data;
        let temp = $.ajax({
            async: false,
            url: "<c:url value="/api-sort-filter-search" />",
            type: type,
            data: {
                cateId: cateId,
                colorId: colorId,
                tagId: tagId,
                min: min,
                max: max,
                sort: sort,
                searching: searching,
                startPos: startPos,
            },
            success: function(value) {
                data = value;
            }
        })

        temp.done(() => {
            let temp = "shop-list";
            display === 1 ? temp = "rowList" : temp = "shop-list";
            document.getElementById(temp).innerHTML = data;
            numPage = parseInt(document.getElementById("phantrang").value, 10);
            // scroll = tagId != -1 ? 1 : 0;
            onClickPagination(Number(curPage), numPage);
            if (numPage === 0 || numPage === 1) {
                document.getElementById("Next").style.visibility = "hidden";
                document.getElementById("Previous").style.visibility = "hidden";
            }
            else {
                document.getElementById("Next").style.visibility = "visible";
                document.getElementById("Previous").style.visibility = "visible";
            }
        })
    }

    $("body").on("click", ".li-tags", function() {
        event.preventDefault()
        $(".li-tags").children().removeClass("tag-selected");
        $(".li-tags").children().css('color', '#7e7e7e')
        $(this).children().addClass("tag-selected");
        $(this).children().css('color', 'white');
    })

    $("body").on("click", ".li-color", function() {
        event.preventDefault()
        $(".li-color").children().removeClass("selected");
        $(this).children().addClass("selected");
    })

    $("body").on("click", ".li-cate", function() {
        event.preventDefault()
        $(".li-cate").children().removeClass("selected");
        $(this).children().addClass("selected");
    })

    $(document).ready(function(){
        let slider = $(".range-slider"),
            range = $(".range-slider__range"),
            value = $(".range-slider__value");
        slider.each(function () {
            value.each(function () {
                let value = $(this).prev().attr("value");
                $(this).html(value);
            });
            range.on("input", function () {
                $(this).next(value).html(this.value);
            });
        });
    });

    function onClickDisplay(value) {
        display = value;
        let curPage = currentPage;
        let startPos = (curPage - 1) * 9;
        onClickPagination(Number(curPage), numPage);
        let type = "get";
        display === 1 ? type = "get" : type = "post";
        let url = flag === "sort" ? "<c:url value="/api-sort-filter-search" />" : "<c:url value="/api-paging-shop" />";
        $.ajax({
            async: false,
            url: url,
            type: type,
            data: {
                cateId: cateId,
                colorId: colorId,
                tagId: tagId,
                min: min,
                max: max,
                sort: sort,
                searching: searching,
                startPos: startPos,
            },
            success: function(value) {
                let temp = "shop-list";
                display === 1 ? temp = "rowList" : temp = "shop-list";
                document.getElementById(temp).innerHTML = value;
            }
        })
    }

    function onClickPreNex(id) {
        event.preventDefault();
        if ((currentPage === 1 && id === "Previous") || (currentPage === numPage && id === "Next")) {
            return;
        }
        else {
            id === "Next" ? currentPage += 1 : currentPage -= 1;
            let curPage = currentPage;
            let startPos = (curPage - 1) * 9;
            onClickPagination(Number(curPage), numPage);
            let type = "get";
            display === 1 ? type = "get" : type = "post";
            let url = flag === "sort" ? "<c:url value="/api-sort-filter-search" />" : "<c:url value="/api-paging-shop" />";
            $.ajax({
                url: url,
                type: type,
                data: {
                    cateId: cateId,
                    colorId: colorId,
                    tagId: tagId,
                    min: min,
                    max: max,
                    sort: sort,
                    searching: searching,
                    startPos: startPos,
                },
                success: function(value) {
                    let temp = "shop-list";
                    display === 1 ? temp = "rowList" : temp = "shop-list";
                    document.getElementById(temp).innerHTML = value;
                }
            })
        }
    }

    $("body").on("click", ".li", function() {
        event.preventDefault();
        let curPage = $(this).text();
        currentPage = Number(curPage);
        let startPos = (curPage - 1) * 9;
        onClickPagination(Number(curPage), numPage);
        let type = "get";
        display === 1 ? type = "get" : type = "post";
        let url = flag === "sort" ? "<c:url value="/api-sort-filter-search" />" : "<c:url value="/api-paging-shop" />";
        $.ajax({
            url: url,
            type: type,
            data: {
                cateId: cateId,
                colorId: colorId,
                tagId: tagId,
                min: min,
                max: max,
                sort: sort,
                searching: searching,
                startPos: startPos,
            },
            success: function(value) {
                let temp = "shop-list";
                display === 1 ? temp = "rowList" : temp = "shop-list";
                document.getElementById(temp).innerHTML = value;
            }
        })
    })

    function onClickPagination(curPage, numPage) {
        let i = 0;
        let html = "";
        let temp = this.pagination(curPage, numPage);
        let index = temp.indexOf(curPage);
        let index3Cham = temp.indexOf("...");
        for (i; i < temp.length; i++) {
            if (i !== index) {
                html += "<li class='li'><a class='page-link' style='cursor: pointer'>" + temp[i] + "</a></li>";
            }
            else if (i === index3Cham) {
                html += "<li class='li' style='pointer-events: none'><a class='page-link'>" + temp[i] + "</a></li>";
            }
            else if (i === index) {
                html += "<li class='li'><a class='page-link active' style='cursor: pointer'>" + temp[index] + "</a></li>";
            }
        }
        document.getElementById("paging").innerHTML = html;
        // if (scroll == 1) {
        //     document.body.scrollTop = 450;
        //     document.documentElement.scrollTop = 450;
        //     scroll = 0;
        // }
    }

    window.onload = function () {
        let redi = ${empty redi ? -1 : redi};
        let colorRedi = ${empty colorRedi ? -1 : colorRedi};
        let tagRedi = ${empty tagRedi ? -1 : tagRedi};

        if (redi !== -1) {
            let id = "cate-" + redi;
            document.getElementById(id).click();
        }
        if (colorRedi !== -1) {
            let id = "colo-" + colorRedi;
            document.getElementById(id).click();
        }
        if (tagRedi !== -1) {
            let id = "tags-" + tagRedi;
            let id_temp = ".tags-" + tagRedi;
            document.getElementById(id).click();
        }

        else {
            let i = 0;
            let html = "";
            let temp = this.pagination(1, numPage);
            let index = temp.indexOf("...");
            for (i; i < temp.length; i++) {
                if (i === 0) {
                    html += "<li class='li'><a class='page-link active' style='cursor: pointer'>" + temp[i] + "</a></li>";
                }
                else {
                    if (i === index) {
                        html += "<li class='li' style='pointer-events: none'><a class='page-link'>" + temp[i] + "</a></li>";
                    }
                    else {
                        html += "<li class='li'><a class='page-link' style='cursor: pointer'>" + temp[i] + "</a></li>";
                    }
                }
            }
            document.getElementById("paging").innerHTML = html;
        }
    }

    function pagination(c, m) {
        let current = c,
            last = m,
            delta = 2,
            left = current - delta,
            right = current + delta + 1,
            range = [],
            rangeWithDots = [],
            l;

        for (let i = 1; i <= last; i++) {
            if (i == 1 || i == last || i >= left && i < right) {
                range.push(i);
            }
        }

        for (let i of range) {
            if (l) {
                if (i - l === 2) {
                    rangeWithDots.push(l + 1);
                } else if (i - l !== 1) {
                    rangeWithDots.push('...');
                }
            }
            rangeWithDots.push(i);
            l = i;
        }

        return rangeWithDots;
    }

    const onClickLoadData = (ID, flag) => {
        //flag == 1 --> get data 1
        //flag == 2 --> load modal
        let URL = "<c:url value="/api-top8product" />";
        if (flag == 2) {
            URL = "<c:url value="/api-modal" />"
        }

        $.ajax({
            url: URL,
            type: "get",
            data: {
                ID: ID,
                // action: (function() {
                //     if (flag === 1) return "tab-data";
                //     else return "modal";
                // })
            },
            success: function (response) {
                flag === 1 ? document.getElementById("tab-data").innerHTML = response
                    : document.getElementById("modal").innerHTML = response;
            },
            error: function (xhr) {
                alert("Loading data not success. Please comeback later <3")
            }
        })
    }
</script>

</body>

</html>