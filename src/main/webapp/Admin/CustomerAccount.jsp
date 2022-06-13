<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 12/11/2021
  Time: 04:36 pm
  To change this template use File | Settings | File Templates.
--%>
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

<script type="text/javascript">
    document.onreadystatechange = function () {
        var state = document.readyState;
        if (state == 'complete') {
            fnInit("csrfToken", "<%= csrfToken %>");
        }
    };
</script>
<jsp:include page="headerAdmin.jsp"/>
    <main class="page-content">
    <div class="container">
        <div class="page-header">
            <h1 class="page-header__title">Account</h1>
        </div>
        <div class="page-tools">
            <div class="page-tools__breadcrumbs">
                <div class="breadcrumbs">
                    <div class="breadcrumbs__container">
                        <ol class="breadcrumbs__list">
                            <li class="breadcrumbs__item">
                                <a class="breadcrumbs__link" href="index.html">
                                    <svg class="icon-icon-home breadcrumbs__icon">
                                        <use xlink:href="#icon-home"></use>
                                    </svg>
                                    <svg class="icon-icon-keyboard-right breadcrumbs__arrow">
                                        <use xlink:href="#icon-keyboard-right"></use>
                                    </svg>
                                </a>
                            </li>
                            <li class="breadcrumbs__item disabled"><a class="breadcrumbs__link" href="#"><span>Customer Manager</span>
                                <svg class="icon-icon-keyboard-right breadcrumbs__arrow">
                                    <use xlink:href="#icon-keyboard-right"></use>
                                </svg></a>
                            </li>
                            <li class="breadcrumbs__item active"><span class="breadcrumbs__link">Account</span>
                            </li>
                        </ol>
                    </div>
                </div>
            </div>
            <div class="page-tools__right">
                <div class="page-tools__right-row">
                    <div class="page-tools__right-item" onclick="bancustom(${requestScope.users.getId()})"><a class="button-icon" href="#"><span class="button-icon__icon">
                      <svg class="icon-icon-task">
                        <use xlink:href="#icon-task"></use>
                      </svg></span></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="customer-account">
            <div class="customer-account__item-1 customer-profile customer-card card">
                <div class="card__wrapper">
                    <div class="card__container">
                        <div class="customer-profile__balance">
                            <div id="role-name" class="label label--primary label--lg" style="background-color:burlywood;"><c:out value="${requestScope.role}"/></div>
                        </div>
                        <div class="card__body">
                            <div class="customer-profile__avatar">
                                <svg viewBox="0 0 252 272" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                                    <g filter="url(#filter0_dd)">
                                        <path d="M55 199H197V221C197 221 153.752 224 126 224C98.248 224 55 221 55 221V199Z" fill="white" />
                                    </g>
                                    <g filter="url(#filter1_dd)">
                                        <path d="M18.235 43.2287C19.2494 23.1848 35.1848 7.24941 55.2287 6.23501C76.8855 5.13899 104.551 4 126 4C147.449 4 175.114 5.13898 196.771 6.23501C216.815 7.24941 232.751 23.1848 233.765 43.2287C234.861 64.8855 236 92.5512 236 114C236 135.449 234.861 163.114 233.765 184.771C232.751 204.815 216.815 220.751 196.771 221.765C175.114 222.861 147.449 224 126 224C104.551 224 76.8855 222.861 55.2287 221.765C35.1848 220.751 19.2494 204.815 18.235 184.771C17.139 163.114 16 135.449 16 114C16 92.5512 17.139 64.8855 18.235 43.2287Z"
                                              fill="url(#pattern0)" />
                                    </g>
                                    <defs>
                                        <filter id="filter0_dd" x="23" y="183" width="206" height="89" filterUnits="userSpaceOnUse" color-interpolation-filters="sRGB">
                                            <feFlood flood-opacity="0" result="BackgroundImageFix" />
                                            <feColorMatrix in="SourceAlpha" type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0" />
                                            <feOffset dy="8" />
                                            <feGaussianBlur stdDeviation="8" />
                                            <feColorMatrix type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.25 0" />
                                            <feBlend mode="normal" in2="BackgroundImageFix" result="effect1_dropShadow" />
                                            <feColorMatrix in="SourceAlpha" type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0" />
                                            <feOffset dy="16" />
                                            <feGaussianBlur stdDeviation="16" />
                                            <feColorMatrix type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.25 0" />
                                            <feBlend mode="normal" in2="effect1_dropShadow" result="effect2_dropShadow" />
                                            <feBlend mode="normal" in="SourceGraphic" in2="effect2_dropShadow" result="shape" />
                                        </filter>
                                        <filter id="filter1_dd" x="0" y="0" width="252" height="252" filterUnits="userSpaceOnUse" color-interpolation-filters="sRGB">
                                            <feFlood flood-opacity="0" result="BackgroundImageFix" />
                                            <feColorMatrix in="SourceAlpha" type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0" />
                                            <feOffset dy="12" />
                                            <feGaussianBlur stdDeviation="8" />
                                            <feColorMatrix type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.2 0" />
                                            <feBlend mode="normal" in2="BackgroundImageFix" result="effect1_dropShadow" />
                                            <feColorMatrix in="SourceAlpha" type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0" />
                                            <feOffset dy="2" />
                                            <feGaussianBlur stdDeviation="2" />
                                            <feColorMatrix type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.05 0" />
                                            <feBlend mode="normal" in2="effect1_dropShadow" result="effect2_dropShadow" />
                                            <feBlend mode="normal" in="SourceGraphic" in2="effect2_dropShadow" result="shape" />
                                        </filter>
                                        <pattern id="pattern0" patternContentUnits="objectBoundingBox" width="1" height="1">
                                            <use xlink:href="#profileImage" transform="scale(0.00142857)" />
                                        </pattern>
                                        <image id="profileImage" width="700" height="700" xlink:href="<c:out  value="${requestScope.users.getImage()}"/>" />
                                    </defs>
                                </svg>
                            </div>
                            <h4 class="customer-profile__title"><c:out  value="${requestScope.users.getLastName()}"/>  <c:out  value="${requestScope.users.getFirstName()}"/></h4>
                            <div class="customer-profile__balance">
                                <c:if test="${requestScope.users.getActive() != null && requestScope.users.getActive() == true}">
                                    <div class="label label--primary label--lg active-color">Active</div>
                                </c:if>
                                <c:if test="${requestScope.users.getActive() == null || requestScope.users.getActive() == false}">
                                    <div class="label label--primary label--lg non-active-color">Unactive</div>
                                </c:if>
                                <c:if test="${requestScope.users.getBanned() == null || requestScope.users.getBanned() == false}">
                                    <div class="label label--primary label--lg active-color">Live</div>
                                </c:if>
                                <c:if test="${requestScope.users.getBanned() != null && requestScope.users.getBanned() == true}">
                                    <div class="label label--primary label--lg non-active-color">Banned</div>
                                </c:if>
                            </div>
                        </div>
                        <div class="card__footer">
                            <div class="card__container">
                                <ul class="customer-profile__address">
                                    <li>
                                        <svg class="icon-icon-location">
                                            <use xlink:href="#icon-location"></use>
                                        </svg><c:out  value="${requestScope.address.getDistrict()}"/>, <c:out  value="${requestScope.address.getProvince()}"/>
                                    </li>
                                    <li>
                                        <svg class="icon-icon-email">
                                            <use xlink:href="#icon-email"></use>
                                        </svg> <a href="mailto:#">
                                        <c:out  value="${requestScope.users.getEmail()}"/></a>
                                    </li>
                                    <li>
                                        <svg class="icon-icon-phone">
                                            <use xlink:href="#icon-phone"></use>
                                        </svg> <a href="tel:#"><c:out  value="${requestScope.users.getPhone()}"/></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="customer-account__item-2 customer-details customer-card card">
                <div class="card__wrapper">
                    <div class="card__container">
                        <div class="card__header">
                            <div class="card__header-left">
                                <h3 class="card__header-title">Account Details</h3>
                            </div>
                            <div class="customer-card__header-right">
                                <button class="customer-card__btn-task">
                                    <svg class="icon-icon-task">
                                        <use xlink:href="#icon-task"></use>
                                    </svg>
                                </button>
                            </div>
                        </div>
                        <div class="card__body">
                            <ul class="customer-details__list">
                                <li>
                                    <div class="row row--xs justify-content-between">
                                        <div class="col-auto text-grey">First Name</div>
                                        <div class="col-auto"><c:out  value="${requestScope.users.getFirstName()}"/></div>
                                    </div>
                                </li>
                                <li>
                                    <div class="row row--xs justify-content-between">
                                        <div class="col-auto text-grey">Last Name</div>
                                        <div class="col-auto"><c:out  value="${requestScope.users.getLastName()}"/></div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="customer-account__item-4 customer-shipping customer-card card">
                <div class="card__wrapper">
                    <div class="card__container">
                        <div class="card__header">
                            <div class="card__header-left">
                                <h3 class="card__header-title">Shipping Address</h3>
                            </div>
                            <div class="customer-card__header-right">
                                <button class="customer-card__btn-task">
                                    <svg class="icon-icon-task">
                                        <use xlink:href="#icon-task"></use>
                                    </svg>
                                </button>
                            </div>
                        </div>
                        <div class="card__body">
                            <ul class="customer-shipping__list">
                                <li>
                                    <div class="row row--xs justify-content-between">
                                        <div class="col-auto text-grey">House Number</div>
                                        <div class="col-auto"><c:out value="${requestScope.address.getNumber()}"/></div>
                                    </div>
                                </li>
                                <li>
                                    <div class="row row--xs justify-content-between">
                                        <div class="col-auto text-grey">Street</div>
                                        <div class="col-auto"><c:out value="${requestScope.address.getStreet()}"/></div>
                                    </div>
                                </li>
                                <li>
                                    <div class="row row--xs justify-content-between">
                                        <div class="col-auto text-grey">Commune</div>
                                        <div class="col-auto"><c:out value="${requestScope.address.getCommune()}"/></div>
                                    </div>
                                </li>
                                <li>
                                    <div class="row row--xs justify-content-between">
                                        <div class="col-auto text-grey">District</div>
                                        <div class="col-auto"><c:out value="${requestScope.address.getDistrict()}"/></div>
                                    </div>
                                </li>
                                <li>
                                    <div class="row row--xs justify-content-between">
                                        <div class="col-auto text-grey">Province</div>
                                        <div class="col-auto"><c:out value="${requestScope.address.getProvince()}"/></div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="customer-account__item-5 customer-shipping customer-card card">
                <div class="card__wrapper">
                    <div class="card__container">
                        <div class="card__header">
                            <div class="card__header-left">
                                <h3 class="card__header-title">Orders List</h3>
                            </div>
                            <div class="customer-card__header-right">
                                <button class="customer-card__btn-task">
                                    <svg class="icon-icon-task">
                                        <use xlink:href="#icon-task"></use>
                                    </svg>
                                </button>
                            </div>
                        </div>
                        <div class="card__body">
                            <div class="table-wrapper">
                                <div class="table-wrapper__content table-collapse scrollbar-thin scrollbar-visible" data-simplebar>
                                    <table  class="table table--lines">
                                        <colgroup>
                                            <col width="150px">
                                            <col width="16%">
                                            <col>
                                            <col>
                                            <col>
                                            <col>
                                            <col>
                                        </colgroup>
                                        <thead class="table__header">
                                        <tr class="table__header-row">
                                            </th>
                                            <th class="d-none d-lg-table-cell"><span>Order ID</span>
                                            </th>
                                            <th class="table__th-sort"><span class="align-middle">Total</span>
                                            </th>
                                            <th class="table__th-sort"><span class="align-middle">Date</span>
                                            </th>
                                            <th class="table__th-sort d-none d-sm-table-cell"><span class="align-middle">Status</span>
                                            </th>
                                            <th class="table__actions">Detail</th>
                                        </tr>
                                        </thead>
                                        <tbody id="order-table">


                                        </tbody>
                                    </table>
                                </div>
                                <div class="table-wrapper__footer">
                                    <div class="row">
                                        <div class="table-wrapper__show-result col text-grey" id="info-show">
                                        </div>
                                        <div class="table-wrapper__pagination col-auto">
                                            <ol class="pagination">
                                                <li class="pagination__item" id="previous">
                                                    <a class="pagination__arrow pagination__arrow--prev" >
                                                        <svg class="icon-icon-keyboard-left">
                                                            <use xlink:href="#icon-keyboard-left"></use>
                                                        </svg>
                                                    </a>
                                                </li>
                                                <li class="pagination__item active" id="pagination_first"  data-value=1><a class="pagination__link">1</a>
                                                </li>
                                                <li class="pagination__item pagination__item--dots" id="pagination_space_1">...</li>
                                                <li class="pagination__item" id="pagination_2" data-value = 2><a class="pagination__link" >2</a>
                                                </li>
                                                <li class="pagination__item" id="pagination_3" data-value = 3><a class="pagination__link" >3</a>
                                                </li>
                                                <li class="pagination__item" id="pagination_4" data-value = 4><a class="pagination__link" >4</a>
                                                </li>
                                                <li class="pagination__item pagination__item--dots" id="pagination_space_2">...</li>
                                                <li class="pagination__item" id="pagination_last" data-value = 10><a class="pagination__link" >10</a>
                                                </li>
                                                <li class="pagination__item" id="next">
                                                    <a class="pagination__arrow pagination__arrow--next">
                                                        <svg class="icon-icon-keyboard-right">
                                                            <use xlink:href="#icon-keyboard-right"></use>
                                                        </svg>
                                                    </a>
                                                </li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<script>
    function bancustom(id){
        $.ajax({
            type : "POST",
            data : {
                Id : id,
                csrfToken:"<%=csrfToken%>"
            },
            url : "/admin/customer"
        });
        location.reload();
    }
</script>
<script src="../Admin/assets/js/custom/customerAccount.js"></script>
<jsp:include page="footerAdmin.jsp"/>
