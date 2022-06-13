<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 12/11/2021
  Time: 04:36 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*"%>
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
            <h1 class="page-header__title">Edit Product</h1>
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
                            <li class="breadcrumbs__item"><a class="breadcrumbs__link" href="products.html"><span>Products</span>
                                <svg class="icon-icon-keyboard-right breadcrumbs__arrow">
                                    <use xlink:href="#icon-keyboard-right"></use>
                                </svg></a>
                            </li>
                            <li class="breadcrumbs__item active"><span class="breadcrumbs__link">Edit Product</span>
                            </li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
        <div class="card add-product card--content-center">
            <div class="card__wrapper">
                <div class="card__container">
                    <form class="add-product__form" action="/admin/editproduct" method="post">
                        <input type="hidden" name="csrfToken" value="<%= csrfToken %>"/>
                        <div class="add-product__row">
                            <input id="idproduct" name="idproduct" value="${requestScope.product.getId()}" hidden>
                            <div class="add-product__slider product-image" id="addProductSlider">
                                <div class="modal-account__upload profile-upload js-profile-upload  product-image-size">
                                    <input id="urlImage" name="urlimage" value="${requestScope.product.getImage()}" hidden>
                                    <input class="profile-upload__input" id="upload-file" type="file" name="file_upload" accept="image/png, image/jpeg">
                                    <svg class="profile-upload__thumbnail" viewBox="0 0 252 272" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                                        <g filter="url(#filter0)">
                                            <path d="M55 199H197V221C197 221 153.752 224 126 224C98.248 224 55 221 55 221V199Z" fill="white" />
                                        </g>
                                        <g filter="url(#filter1)">
                                            <path d="M18.235 43.2287C19.2494 23.1848 35.1848 7.24941 55.2287 6.23501C76.8855 5.13899 104.551 4 126 4C147.449 4 175.114 5.13898 196.771 6.23501C216.815 7.24941 232.751 23.1848 233.765 43.2287C234.861 64.8855 236 92.5512 236 114C236 135.449 234.861 163.114 233.765 184.771C232.751 204.815 216.815 220.751 196.771 221.765C175.114 222.861 147.449 224 126 224C104.551 224 76.8855 222.861 55.2287 221.765C35.1848 220.751 19.2494 204.815 18.235 184.771C17.139 163.114 16 135.449 16 114C16 92.5512 17.139 64.8855 18.235 43.2287Z"
                                                  fill="url(#pattern1)" />
                                        </g>
                                        <path class="profile-upload__overlay" opacity="0.6" d="M18.235 43.2287C19.2494 23.1848 35.1848 7.24941 55.2287 6.23501C76.8855 5.13899 104.551 4 126 4C147.449 4 175.114 5.13899 196.771 6.23501C216.815 7.24941 232.751 23.1848 233.765 43.2287C234.861 64.8855 236 92.5512 236 114C236 135.449 234.861 163.114 233.765 184.771C232.751 204.815 216.815 220.751 196.771 221.765C175.114 222.861 147.449 224 126 224C104.551 224 76.8855 222.861 55.2287 221.765C35.1848 220.751 19.2494 204.815 18.235 184.771C17.139 163.114 16 135.449 16 114C16 92.5512 17.139 64.8855 18.235 43.2287Z"
                                              fill="#44566C" />
                                        <defs>
                                            <filter id="filter0" x="23" y="183" width="206" height="89" filterUnits="userSpaceOnUse" color-interpolation-filters="sRGB">
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
                                            <filter id="filter1" x="0" y="0" width="252" height="252" filterUnits="userSpaceOnUse" color-interpolation-filters="sRGB">
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
                                            <pattern id="pattern1" patternContentUnits="objectBoundingBox" width="1" height="1">
                                                <use xlink:href="#profileImageAddPlaceholder" transform="scale(0.00142857)" />
                                                <use xlink:href="#profileImageAdd" transform="scale(0.00142857)" />
                                            </pattern>
                                            <image id="profileImageAddPlaceholder" width="700" height="700"  xlink:href="" />
                                            <image id="profileImageAdd" class="profile-upload__image" width="700" height="700" xlink:href='<c:out value="${requestScope.product.getImage()}"/>' />
                                        </defs>
                                    </svg>
                                    <div class="profile-upload__label">
                                        <svg class="icon-icon-camera" width="50px" height="50px">
                                            <use xlink:href="#icon-camera"></use>
                                        </svg>
                                        <p class="mb-0">Click & Drop
                                            <br>to change photo</p>
                                    </div>
                                </div>
                                <div class="col-auto">
                                    <a class="button button--primary button--load" id="send"><span class="button__icon button__icon--left">
                                <svg class="icon-icon-refresh">
                                  <use xlink:href="#icon-refresh"></use>
                                </svg></span><span class="button__text">Update</span>
                                    </a>
                                </div>
                            </div>
                            <div class="add-product__right">
                                <div class="row row--md">
                                    <div class="col-12 form-group form-group--lg">
                                        <label class="form-label">Product Name</label>
                                        <div class="input-group">
                                            <input name="name" class="input" type="text" placeholder="" value="${requestScope.product.getName()}"  required>
                                        </div>
                                    </div>
                                    <div class="col-12 form-group form-group--lg">
                                        <label class="form-label">Description</label>
                                        <div class="input-editor">
                                            <input name="description" class="input" type="text" placeholder="" value="${requestScope.product.getDescription()}"  required>
                                        </div>
                                    </div>
                                    <div class="col-12 form-group form-group--lg">
                                        <label  class="form-label">Category</label>
                                        <div class="input-group input-group--append">
                                            <select name="category" class="input js-input-select input--fluid" data-placeholder="">
                                                <c:forEach items="${requestScope.listcate}" var="item">
                                                    <c:if test="${item.getId() == requestScope.product.getCategoriesEntity().getId()}">
                                                        <option value="${item.getId()}"  selected>${item.getName()}
                                                        </option>
                                                    </c:if>
                                                    <c:if test="${item.getId() != requestScope.product.getCategoriesEntity().getId()}">
                                                        <option value="${item.getId()}">${item.getName()}
                                                        </option>
                                                    </c:if>
                                                </c:forEach>
                                            </select><span class="input-group__arrow">
                              <svg class="icon-icon-keyboard-down">
                                <use xlink:href="#icon-keyboard-down"></use>
                              </svg></span>
                                        </div>
                                    </div>
                                    <div class="col-12 col-md-6 form-group form-group--lg">
                                        <label class="form-label">Price</label>
                                        <div class="input-group input-group--prepend">
                                            <div class="input-group__prepend"><span class="input-group__symbol">$</span>
                                            </div>
                                            <input name="price" class="input" type="number" min="0" max="999999999" placeholder="" value="${requestScope.product.getRegularPrice()}" required>
                                        </div>
                                    </div>
                                    <div class="col-12 col-md-6 form-group form-group--lg">
                                        <label class="form-label">Discount</label>
                                        <div class="input-group input-group--prepend">
                                            <div class="input-group__prepend"><span class="input-group__symbol">%</span>
                                            </div>
                                            <input name="discount" class="input" type="number" min="0" max="100" placeholder="${requestScope.product.getDiscount_percent()}" value="10" required>
                                        </div>
                                    </div>
                                    <div class="col-12 form-group form-group--lg">
                                        <label class="form-label">Quantity</label>
                                        <div class="input-group input-group--prepend">
                                            <div class="input-group__prepend"><span class="input-group__symbol">+</span>
                                            </div>
                                            <input name="quantity" class="input" type="number" min="1000" max="1000000000" placeholder="" value="${requestScope.product.getQuantity()}" required>
                                        </div>
                                    </div>
                                    <div class="col-12 form-group form-group--lg">
                                        <label class="form-label">Tags</label>
                                        <div class="input-group input-group--append">
                                            <select name="tag" class="input js-input-select input--fluid" data-placeholder="">
                                                <c:forEach items="${requestScope.listtag}" var="item">
                                                    <c:if test="${item.equals(requestScope.tag) == true}">
                                                        <option value="${item.getId()}" selected>${item.getName()}
                                                        </option>
                                                    </c:if>
                                                    <c:if test="${item.equals(requestScope.tag) == false}">
                                                        <option value="${item.getId()}">${item.getName()}
                                                        </option>
                                                    </c:if>
                                                </c:forEach>
                                            </select><span class="input-group__arrow">
                              <svg class="icon-icon-keyboard-down">
                                <use xlink:href="#icon-keyboard-down"></use>
                              </svg></span>
                                        </div>
                                    </div>

                                    <div class="col-12 form-group form-group--lg">
                                        <label class="form-label">Colors</label>
                                        <select name="colors" class="input js-input-tags" multiple="multiple" data-placeholder=""  required>
                                            <c:forEach items="${requestScope.listcolor}" var="item" >
                                                <c:if test="${requestScope.colorproduct.contains(item) == true}">
                                                    <option value="${item.getId()}" selected>${item.getName()}
                                                    </option>
                                                </c:if>
                                                <c:if test="${requestScope.colorproduct.contains(item) == false}">
                                                    <option value="${item.getId()}">${item.getName()}
                                                    </option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-12 form-group form-group--lg">
                                        <label class="form-label form-label--sm">Weight</label>
                                        <div class="input-group">
                                            <input name="weight" class="input" type="text" value="${requestScope.info.getWeight()}" required>
                                        </div>
                                    </div>
                                    <div class="col-12 form-group form-group--lg">
                                        <label class="form-label form-label--sm">Dimensions</label>
                                        <div class="input-group">
                                            <input name="dimensions"class="input" type="text"  value="${requestScope.info.getDimensions()}" required>
                                        </div>
                                    </div>
                                    <div class="col-12 form-group form-group--lg">
                                        <label class="form-label form-label--sm">Materials</label>
                                        <div class="input-group">
                                            <input name="material" class="input" type="text" value="${requestScope.info.getMaterials()}" required>
                                        </div>
                                    </div>
                                    <div class="col-12 form-group form-group--lg">
                                        <label class="form-label form-label--sm">OtherInfo</label>
                                        <div class="input-group">
                                            <input name="ortherinfo" class="input" type="text" value="${requestScope.info.getOtherInfo()}" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal__footer-button">
                                    <button type="submit" class="button button--primary button--block"><span class="button__text">Edit</span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>
<div class="modal modal-compact modal-success" id="addProductSuccess">
    <div class="modal__overlay" data-dismiss="modal"></div>
    <div class="modal__wrap">
        <div class="modal__window">
            <div class="modal__content">
                <div class="modal__body">
                    <div class="modal__container">
                        <img class="modal-success__icon" src="../Admin/assets/img/content/checked-success.svg" alt="#">
                        <h4 class="modal-success__title">Product was Edited</h4>
                    </div>
                </div>
                <div class="modal-compact__buttons">
                    <div class="modal-compact__button-item">
                        <button class="modal-compact__button button" data-dismiss="modal" data-modal="#addProduct"><span class="button__text">Edit new product</span>
                        </button>
                    </div>
                    <div class="modal-compact__button-item">
                        <button class="modal-compact__button button" data-dismiss="modal"><span class="button__text">Close</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../Admin/assets/js/custom/firebase.js"></script>
<jsp:include page="footerAdmin.jsp"/>
