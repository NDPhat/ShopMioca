<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <script src="https://www.gstatic.com/firebasejs/7.13.1/firebase-app.js"></script>
    <script src="https://www.gstatic.com/firebasejs/7.13.1/firebase-storage.js"></script>
    <link rel="stylesheet" href="assets/css/style.css" />
    <link rel="stylesheet" href="assets/css/myaccout.css" />
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
                <h2 class="breadcrumb-title">My Account</h2>
                <!-- breadcrumb-list start -->
                <ul class="breadcrumb-list">
                    <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
                    <li class="breadcrumb-item active">Account</li>
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
<!-- account area start -->
<div class="account-dashboard pt-100px pb-100px">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 col-md-3 col-lg-3">
                <!-- Nav tabs -->
                <div class="dashboard_tab_button" data-aos="fade-up" data-aos-delay="0">
                    <ul role="tablist" class="nav flex-column dashboard-list">
                        <li><a href="#dashboard" id="dashbordtab" data-bs-toggle="tab" class="nav-link active">Dashboard</a></li>
                        <li><a id="Orderstab" onclick="loadOrder()" href="#orders" data-bs-toggle="tab" class="nav-link">Orders</a></li>
                        <li><a id="Addresstab" href="#address" data-bs-toggle="tab" class="nav-link">Addresses</a></li>
                        <li><a id="UpdateIn4" href="#update-information" data-bs-toggle="tab" class="nav-link">Update information</a></li>
                        <li><a id="changePassword" href="#change-password" data-bs-toggle="tab" class="nav-link">Change password</a></li>
                        <li><a href="<c:url value='/logout'/>" class="nav-link">logout</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-sm-12 col-md-9 col-lg-9">
                <!-- Tab panes -->
                <div class="tab-content dashboard_content" data-aos="fade-up" data-aos-delay="200">
                    <div class="tab-pane fade show active" id="dashboard">
                        <h4>
                            <c:if test="${sessionScope.loginedUser.getImage()==null || sessionScope.loginedUser.getImage()==''}">
                                <img style="width: 100px;height: 100px; border-radius: 50%; margin: 0 auto" src="assets/images/noimage.png">
                            </c:if>
                            <c:if test="${sessionScope.loginedUser.getImage()!=null && sessionScope.loginedUser.getImage()!=''}">
                                <img style="width: 100px;height: 100px; border-radius: 50%;" src="${sessionScope.loginedUser.getImage()}">
                            </c:if>
                            Dashboard (<c:out value="${sessionScope.loginedUser.getLastname()}"/>  <c:out value="${sessionScope.loginedUser.getFirstname()}"/>)
                        </h4>

                        <p role="tablist">From your account dashboard. you can easily check &amp; view your <a onclick="onTab(event, 'Orders')" href="#orders">recent
                            orders</a>, manage your <a onclick="onTab(event, 'Address')" href="#">shipping and billing addresses</a> and <a
                                onclick="onTab(event, 'changePassword')" href="#">Edit your password </a> and <a onclick="onTab(event, 'UpdateIn4')" href="#">account details.</a></p>
                        <script>
                            const onTab = (event, clsName) => {
                                event.preventDefault();
                                document.getElementById(clsName).click();
                            }
                        </script>
                    </div>
                    <div class="tab-pane fade" id="orders">
                        <h4>Orders</h4>
                        <div class="table_page table-responsive">
                            <c:if test="${sessionScope.loginedUser!=null}">
                                <table>
                                    <thead>
                                    <tr>
                                        <th>Order</th>
                                        <th>Date</th>
                                        <th>Status</th>
                                        <th>Total</th>
                                        <th>Actions</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
                            </c:if>


                        </div>
                    </div>
                    <div class="tab-pane" id="address">
                        <p>The following addresses will be used on the checkout page by default.</p>
                        <h5 class="billing-address">Billing address</h5>
                        <p class="mb-2"><strong> Welcome <c:out value="${sessionScope.loginedUser.getLastname()}"/>  <c:out value="${sessionScope.loginedUser.getFirstname()}"/></strong></p>
                        <address>
                            <span class="mb-1 d-inline-block"><strong>Number:  </strong><c:out value="${requestScope.address.getNumber()}"/></span>
                            <br>
                            <span class="mb-1 d-inline-block"><strong>Street:  </strong> <c:out value="${requestScope.address.getStreet()}"/></span>
                            <br>
                            <span class="mb-1 d-inline-block"><strong>Commune:  </strong> <c:out value="${requestScope.address.getCommune()}"/></span>
                            <br>
                            <span class="mb-1 d-inline-block"><strong>District: </strong>  <c:out value="${requestScope.address.getDistrict()}"/></span>
                            <br>
                            <span class="mb-1 d-inline-block"><strong>Province:  </strong> <c:out value="${requestScope.address.getProvince()}"/></span>
                        </address>
                    </div>
                    <div class="tab-pane fade" id="update-information">
                        <h3>Update information </h3>
                        <div class="login">
                            <div class="login_form_container">
                                <div class="account_login_form">
                                    <form action="/myaccount" method="post">
                                        <input type="hidden" name="csrfToken" value="<%= csrfToken %>"/>
                                        <div class="default-form-box mb-20">
                                            <label>First Name</label>
                                            <input required title="Wrong name We only accept character A-z and max 50 character" minlength="1" maxlength="50" type="text" name="firstname" value="<c:out value="${sessionScope.loginedUser.getFirstname()}"/>">
                                        </div>
                                        <div class="default-form-box mb-20" >
                                            <label>Last Name</label>
                                            <input required title="Wrong name We only accept character A-z and max 50 character" minlength="1" maxlength="50" type="text" name="lastname" value="<c:out value="${sessionScope.loginedUser.getLastname()}"/>">
                                        </div>
                                        <div class="default-form-box mb-20">
                                            <label>Phone Number</label>
                                            <input required title="Wrong Number We only accept character 0-9 and max 10 character" minlength="9" maxlength="12" type="text" name="phone" value="<c:out value="${sessionScope.loginedUser.getPhone()}"/>">
                                        </div>
                                        <div class="default-form-box mb-20">
                                            <label>Number House</label>
                                            <input required title="Wrong Number We only accept character 0-9 and max 10 character" minlength="1" maxlength="50"  type="text" name="number" value="<c:out value="${requestScope.address.getNumber()}"/>">
                                        </div>
                                        <div class="default-form-box mb-20">
                                            <label>Street</label>
                                            <input required title="Wrong Street We only accept character A-z and 0-9 and max 50 character" minlength="1" maxlength="50" type="text" name="street" value="<c:out value="${requestScope.address.getStreet()}"/>">
                                        </div>
                                        <div class="default-form-box mb-20">
                                            <label>Commune</label>
                                            <input required title="Wrong Commune We only accept character A-z and 0-9 and max 50 character" minlength="1" maxlength="50" type="text" name="commune" value="<c:out value="${requestScope.address.getCommune()}"/>">
                                        </div>
                                        <div class="default-form-box mb-20">
                                            <label>District</label>
                                            <input required title="Wrong District We only accept character A-z and 0-9 and max 50 character"  minlength="1" maxlength="50" type="text" name="district" value="<c:out value="${requestScope.address.getDistrict()}"/>">
                                        </div>
                                        <div class="default-form-box mb-20">
                                            <label>Province</label>
                                            <input required title="Wrong Province We only accept character A-z and max 50 character" minlength="1" maxlength="50" type="text" name="province" value="<c:out value="${requestScope.address.getProvince()}"/>">
                                        </div>
                                        <div class="default-form-box mb-20">
                                            <label>Image</label>
                                            <label style="width:100%;" for="img" class="input-preview"></label>
                                            <input hidden type="file" name="img"  id="img" class="input-preview__src" value="${sessionScope.loginedUser.getImage()}"/>
                                        </div>
                                        <input hidden required type="text" name="urlImage"  id="urlImage" class="input-preview__src" value="${sessionScope.loginedUser.getImage()}"/>
                                        <div id="loading" class="spinner-border hidden-load" role="status">
                                            <span class="sr-only">Loading...</span>
                                        </div>
                                        <div class="save_button mt-3">
                                            <button class="btn" type="submit">Save</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="change-password">
                        <h3>Change password </h3>
                        <div class="login">
                            <div class="login_form_container">
                                <div class="account_login_form">
                                    <form action="/updatepassword" method="post">
                                        <input type="hidden" name="csrfToken" value="<%= csrfToken %>"/>
                                        <div class="default-form-box mb-20">
                                            <label>Current Password</label>
                                            <input required type="password" name="userpassword">
                                        </div>
                                        <div class="default-form-box mb-20">
                                            <label>New Password</label>
                                            <input maxlength="200" minlength="8" required type="password" name="userpasswordnew">
                                        </div>
                                        <div class="default-form-box mb-20">
                                            <label>Retype New Password</label>
                                            <input maxlength="200" minlength="8" required type="password" name="userretypepasswordnew">
                                        </div>
                                        <div class="save_button mt-3">
                                            <button class="btn" type="submit">Confirm</button>
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
<!-- account area start -->

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

<c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/updatepassword'}">
    <script type="text/javascript">
        document.getElementById("dashboard").classList.remove("active");
        document.getElementById("orders").classList.remove("active");
        document.getElementById("address").classList.remove("active");
        document.getElementById("update-information").classList.remove("active");
        document.getElementById("change-password").classList.add("active");

        document.getElementById("dashbordtab").classList.remove("active");
        document.getElementById("Orderstab").classList.remove("active");
        document.getElementById("Addresstab").classList.remove("active");
        document.getElementById("UpdateIn4").classList.remove("active");
        document.getElementById("changePassword").classList.add("active");
    </script>
</c:if>
<script>
    function loadOrder() {
        $.ajax({
            url: "/OrderAPI",
            type: "get",
            data: {},
            success: function (response) {
                var row = document.querySelector('tbody');
                row.innerHTML = response;

            },
            error: function (xhr) {
                alert("Loading data not success. Please comeback later !!")
            }

        })
    }

</script>
<!-- Use the minified version files listed below for better performance and remove the files listed above -->
<!-- <script src="assets/js/vendor/vendor.min.js"></script>
<script src="assets/js/plugins/plugins.min.js"></script> -->

<!-- Main Js -->
<script src="assets/js/main.js"></script>
<script src="assets/js/myaccount.js"></script>
<script src="assets/js/myaccountfirebase.js"></script>
</body>
</html>