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
            <h3 class="page-header__subtitle d-lg-none">Order Details</h3>
            <h1 class="page-header__title">Invoice <span class="text-grey">#10</span></h1>
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
                            <li class="breadcrumbs__item disabled"><a class="breadcrumbs__link" href="#"><span>E-commerce</span>
                                <svg class="icon-icon-keyboard-right breadcrumbs__arrow">
                                    <use xlink:href="#icon-keyboard-right"></use>
                                </svg></a>
                            </li>
                            <li class="breadcrumbs__item"><a class="breadcrumbs__link" href="orders.html"><span>Orders</span>
                                <svg class="icon-icon-keyboard-right breadcrumbs__arrow">
                                    <use xlink:href="#icon-keyboard-right"></use>
                                </svg></a>
                            </li>
                            <li class="breadcrumbs__item active"><span class="breadcrumbs__link">Invoice</span>
                            </li>
                        </ol>
                    </div>
                </div>
            </div>
            <div class="page-tools__right">
                <div class="page-tools__right-row">
                    <div class="page-tools__right-item"><a class="button-icon" href="#"><span class="button-icon__icon">
                      <svg class="icon-icon-print">
                        <use xlink:href="#icon-print"></use>
                      </svg></span></a>
                    </div>
                    <div class="page-tools__right-item">
                        <button class="button-icon" type="button"><span class="button-icon__icon">
                      <svg class="icon-icon-trash">
                        <use xlink:href="#icon-trash"></use>
                      </svg></span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="card card-order order-invoice">
            <div class="card__wrapper">
                <div class="card__container">
                    <div class="card__header">
                        <div class="row gutter-bottom-xs justify-content-between flex-grow-1">
                            <div class="col">
                                <h2 class="order-invoice__title"><b class="text-uppercase">Order</b> <br> #<c:out value="${requestScope.trans.getId()}"/></h2>
                                <div class="order-invoice__address"><a href="/admin/customeraccount?Id=${requestScope.user.getId()}"><span class="text-grey-dark"><c:out value="${requestScope.user.getLastName()}"/> <c:out value="${requestScope.user.getFirstName()}"/></span></a>
                                    <br><c:out value="${requestScope.address}"/>
                                    <br> <a href="#">+<c:out value="${requestScope.user.getPhone()}"/></a>
                                    <br> <a href="#"><c:out value="${requestScope.user.getEmail()}"/></a>
                                </div>
                            </div>
                            <div class="order-invoice__header-right col-12 col-md-auto">
                                <form class="order-status__form" action="/admin/editorder" method="POST">
                                    <input type="hidden" name="csrfToken" value="<%= csrfToken %>"/>
                                    <div class="input-group input-group--append">
                                        <input value="${requestScope.trans.getId()}" name="Id" type="text" hidden>
                                        <select id="selcectstatus" class="input js-input-select input--fluid" name="status" data-placeholder="">
                                            <option value="Pending" selected="selected">Pending
                                            </option>
                                            <option value="Complete">Complete
                                            </option>
                                            <option value="Processing">Processing
                                            </option>
                                            <option value="Canceled">Canceled
                                            </option>
                                        </select><span class="input-group__arrow">
                                                    <svg class="icon-icon-keyboard-down">
                                                    <use xlink:href="#icon-keyboard-down"></use>
                                                    </svg></span>
                                    </div>
                                    <script>
                                        document.getElementById('selcectstatus').value = "${requestScope.trans.getStatus()}"
                                    </script>
                                    <div class="order-status__submit">
                                        <div class="row justify-content-center">
                                            <div class="col-auto">
                                                <button class="button button--primary" type="submit"><span class="button__text">Change Status</span>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card-order__order card-order__invoice-table scrollbar-thin scrollbar-visible" data-simplebar>
                    <table class="table table--lines table--groups table--striped">
                        <colgroup>
                            <col class="colgroup-1">
                            <col >
                            <col class="colgroup-2">
                            <col class="colgroup-3">
                            <col>
                        </colgroup>
                        <thead class="table__header">
                        <tr>
                            <th><span class="text-nowrap">order</span>
                            </th>
                            <th><span class="text-nowrap">Color</span>
                            </th>
                            <th class="text-center"><span>PRICE</span>
                            </th>
                            <th class="text-center"><span>QUANTITY</span>
                            </th>
                            <th><span>TOTAL</span>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${requestScope.ListProduct}">
                            <tr class="table__row">
                                <td class="table__td"><span class="text-light-theme"><c:out value="${item.getName()}"/></span>
                                </td>
                                <td class="table__td text-center text-dark-theme"><c:out value="${item.getColorname()}"/></td>
                                <td class="table__td text-center text-dark-theme">$<c:out value="${item.getPrice()}"/></td>
                                <td class="table__td text-center text-light-theme"><c:out value="${item.getQuantity()}"/></td>
                                <td class="table__td text-nowrap text-dark-theme">$<c:out value="${item.getSubtotal()}"/></td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
                <div class="card-order__footer-total">
                    <div class="card__container">
                        <div class="row gutter-bottom-sm justify-content-end">
                            <div class="col-auto">
                                <ul class="card-order__total">
                                    <li class="card-order__total-item card-order__total-footer">
                                        <div class="card-order__total-title">total:</div>
                                        <div class="card-order__total-value">$<c:out value="${requestScope.trans.getAmount()}"/></div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<jsp:include page="footerAdmin.jsp"/>
