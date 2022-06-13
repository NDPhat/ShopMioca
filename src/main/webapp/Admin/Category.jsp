<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11/11/2021
  Time: 08:50 am
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
        <div class="page-header" >
            <h1 class="page-header__title">Categories <span class="text-grey" id="number-category">(50)</span></h1>
        </div>
        <div class="page-tools">
            <div class="page-tools__breadcrumbs">
                <div class="breadcrumbs">
                    <div class="breadcrumbs__container">
                        <ol class="breadcrumbs__list">
                            <li class="breadcrumbs__item">
                                <a class="breadcrumbs__link" href="/admin">
                                    <svg class="icon-icon-home breadcrumbs__icon">
                                        <use xlink:href="#icon-home"></use>
                                    </svg>
                                    <svg class="icon-icon-keyboard-right breadcrumbs__arrow">
                                        <use xlink:href="#icon-keyboard-right"></use>
                                    </svg>
                                </a>
                            </li>
                            <li class="breadcrumbs__item disabled"><a class="breadcrumbs__link" href="#"><span>Admin</span>
                                <svg class="icon-icon-keyboard-right breadcrumbs__arrow">
                                    <use xlink:href="#icon-keyboard-right"></use>
                                </svg></a>
                            </li>
                            <li class="breadcrumbs__item active"><span class="breadcrumbs__link">Categories</span>
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
                    <div class="page-tools__right-item"><a class="button-icon" href="#"><span class="button-icon__icon">
                          <svg class="icon-icon-import">
                            <use xlink:href="#icon-import"></use>
                          </svg></span></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="toolbox">
            <div class="toolbox__row row gutter-bottom-xs">
                <div class="toolbox__left col-12 col-lg">
                    <div class="toolbox__left-row row row--xs gutter-bottom-xs">
                        <div class="form-group form-group--inline col-12 col-sm-auto">
                            <label class="form-label">Show</label>
                            <div class="input-group input-group--white input-group--append">
                                <input class="input input--select" type="text" value="10" size="1" data-toggle="dropdown" readonly><span class="input-group__arrow">
                            <svg class="icon-icon-keyboard-down">
                              <use xlink:href="#icon-keyboard-down"></use>
                            </svg></span>
                                <div class="dropdown-menu dropdown-menu--right dropdown-menu--fluid js-dropdown-select">
                                    <a class="dropdown-menu__item active show-item" href="#" tabindex="0" data-value="10">10</a>
                                    <a class="dropdown-menu__item show-item" href="#" tabindex="0" data-value="15">15</a>
                                    <a class="dropdown-menu__item show-item" href="#" tabindex="0" data-value="20">20</a>
                                    <a class="dropdown-menu__item show-item" href="#" tabindex="0" data-value="25">25</a>
                                    <a class="dropdown-menu__item show-item" href="#" tabindex="0" data-value="50">50</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-auto">
                    <button class="button-add button-add--blue" data-modal="#addcategory"><span class="button-add__icon">
                                    <svg class="icon-icon-plus">
                                    <use xlink:href="#icon-plus"></use>
                                    </svg></span><span class="button-add__text"></span>
                    </button>
                </div>
            </div>
        </div>
        <div style="display: flex; flex: 0; justify-content: space-between; ">
            <div class="table-wrapper" style="width:65%;">
                <div class="table-wrapper__content table-collapse scrollbar-thin scrollbar-visible" data-simplebar>
                    <table class="table table--spaces">
                        <colgroup>
                            <col width="90px">
                            <col >
                            <col >
                            <col >
                            <col width="100px">
                        </colgroup>
                        <thead class="table__header" style="background-color: white;">
                        <tr class="table__header-row">
                            <th class="table__th-sort"><span class="align-middle">ID</span>
                            </th>
                            <th class="table__th-sort"><span class="align-middle">Category Name</span>
                            </th>
                            <th class="table__th-sort" style="text-align: center;"><span class="align-middle" style="text-align: center;">Edit</span></th>
                        </tr>
                        </thead>
                        <tbody id="category-table">

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
            <div class="table-wrapper" style="width:30%;  background-color: white; padding: 2rem;">
                <form class="add-category__form" action="/admin/categories" method="post">
                    <input type="hidden" name="csrfToken" value="<%= csrfToken %>"/>
                    <input id="method" name="method" value="edit" hidden >
                    <div class="row row--md">
                        <div class=" col-12 add-category__slider category-image" id="addcategorySliderEdit" style="display: flex; justify-content: center;">
                            <div class="modal-account__upload profile-upload js-profile-upload  category-image-size">
                                <input id="urlImage-edit" name="urlimage" value="" hidden required>
                                <input class="profile-upload__input" id="upload-file-edit" type="file" name="file_upload" accept="image/png, image/jpeg">
                                <svg class="profile-upload__thumbnail" viewBox="0 0 252 272" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                                    <g filter="url(#filter0-edit)">
                                        <path d="M55 199H197V221C197 221 153.752 224 126 224C98.248 224 55 221 55 221V199Z" fill="white" />
                                    </g>
                                    <g filter="url(#filter1-edit)">
                                        <path d="M18.235 43.2287C19.2494 23.1848 35.1848 7.24941 55.2287 6.23501C76.8855 5.13899 104.551 4 126 4C147.449 4 175.114 5.13898 196.771 6.23501C216.815 7.24941 232.751 23.1848 233.765 43.2287C234.861 64.8855 236 92.5512 236 114C236 135.449 234.861 163.114 233.765 184.771C232.751 204.815 216.815 220.751 196.771 221.765C175.114 222.861 147.449 224 126 224C104.551 224 76.8855 222.861 55.2287 221.765C35.1848 220.751 19.2494 204.815 18.235 184.771C17.139 163.114 16 135.449 16 114C16 92.5512 17.139 64.8855 18.235 43.2287Z"
                                              fill="url(#pattern1-edit)" />
                                    </g>
                                    <path class="profile-upload__overlay" opacity="0.6" d="M18.235 43.2287C19.2494 23.1848 35.1848 7.24941 55.2287 6.23501C76.8855 5.13899 104.551 4 126 4C147.449 4 175.114 5.13899 196.771 6.23501C216.815 7.24941 232.751 23.1848 233.765 43.2287C234.861 64.8855 236 92.5512 236 114C236 135.449 234.861 163.114 233.765 184.771C232.751 204.815 216.815 220.751 196.771 221.765C175.114 222.861 147.449 224 126 224C104.551 224 76.8855 222.861 55.2287 221.765C35.1848 220.751 19.2494 204.815 18.235 184.771C17.139 163.114 16 135.449 16 114C16 92.5512 17.139 64.8855 18.235 43.2287Z"
                                          fill="#44566C" />
                                    <defs>
                                        <filter id="filter0-edit" x="23" y="183" width="206" height="89" filterUnits="userSpaceOnUse" color-interpolation-filters="sRGB">
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
                                        <filter id="filter1-edit" x="0" y="0" width="252" height="252" filterUnits="userSpaceOnUse" color-interpolation-filters="sRGB">
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
                                        <pattern id="pattern1-edit" patternContentUnits="objectBoundingBox" width="1" height="1">
                                            <use xlink:href="#profileImageAddPlaceholder" transform="scale(0.00142857)" />
                                            <use xlink:href="#profileImageAdd-edit" transform="scale(0.00142857)" />
                                        </pattern>
                                        <image id="profileImageAddPlaceholder" width="700" height="700" xlink:href='../Admin/assets/img/content/upload-placeholder.svg' />
                                        <image id="profileImageAdd-edit" class="profile-upload__image" width="700" height="700" xlink:href="" />
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
                        </div>
                        <div class="col-12 form-group form-group--lg" style="display: flex; justify-content: center;">
                            <a class="button button--primary button--load" id="send-edit"><span class="button__icon button__icon--left">
                                        <svg class="icon-icon-refresh">
                                            <use xlink:href="#icon-refresh"></use>
                                        </svg></span><span class="button__text">Update</span>
                            </a>
                        </div>
                        <div class="col-12 form-group form-group--lg">
                            <label class="form-label form-label--sm">category ID </label>
                            <div  class="input-group">
                                <input id="categoryId" name="categoryId"  class="input" type="number" placeholder="Id Category" required readonly>
                            </div>
                        </div>
                        <div class="col-12 form-group form-group--lg">
                            <label class="form-label form-label--sm">Category Name </label>
                            <div class="input-group">
                                <input id="categoryName" name="categoryName"class="input" type="text" placeholder="Name Category" required>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 form-group form-group--lg" style="display: flex; justify-content: center;">
                        <button class="button button--primary button--block" type="submit" ><span class="button__text">Edit</span>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</main>
</div>
<div class="modal modal--panel modal--right" id="addcategory">
    <div class="modal__overlay" data-dismiss="modal"></div>
    <div class="modal__wrap">
        <div class="modal__window scrollbar-thin" data-simplebar>
            <div class="modal__content">
                <div class="modal__header">
                    <div class="modal__container">
                        <h2 class="modal__title">Add Category</h2>
                    </div>
                </div>
                <div class="modal__body">
                    <div class="modal__container">
                        <form class="add-category__form" action="/admin/categories" method="post">
                            <input type="hidden" name="csrfToken" value="<%= csrfToken %>"/>
                            <input id="method2" name="method" value="create" hidden >
                            <div class="row row--md">
                                <div class=" col-12 add-category__slider category-image" id="addcategorySlider" style="display: flex; justify-content: center;">
                                    <div class="modal-account__upload profile-upload js-profile-upload  category-image-size">
                                        <input id="urlImage" name="urlimage" value="" hidden required>
                                        <input class="profile-upload__input" id="upload-file" type="file" accept="image/png, image/jpeg">
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
                                                <image id="profileImageAddPlaceholder" width="700" height="700" xlink:href='../Admin/assets/img/content/upload-placeholder.svg' />
                                                <image id="profileImageAdd" class="profile-upload__image" width="700" height="700" xlink:href="" />
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
                                </div>
                                <div class="col-12 form-group form-group--lg" style="display: flex; justify-content: center;">
                                    <a class="button button--primary button--load" id="send"><span class="button__icon button__icon--left">
                                        <svg class="icon-icon-refresh">
                                            <use xlink:href="#icon-refresh"></use>
                                        </svg></span><span class="button__text">Update</span>
                                    </a>
                                </div>
                                <div class="col-12 form-group form-group--lg">
                                    <label class="form-label form-label--sm">category name</label>
                                    <div class="input-group">
                                        <input name="Name" class="input" type="text" placeholder="Category Name" required>
                                    </div>
                                </div>
                            </div>
                            <div class="add-category__submit" style="display: flex; justify-content: center;">
                                <div class="modal__footer-button" style="display: flex; justify-content: center;">
                                    <button class="button button--primary button--block" type="submit"><span class="button__text">Create</span>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal modal-compact modal-success scrollbar-thin" id="addcategorySuccess" data-simplebar>
    <div class="modal__overlay" data-dismiss="modal"></div>
    <div class="modal__wrap">
        <div class="modal__window">
            <div class="modal__content">
                <div class="modal__body">
                    <div class="modal__container">
                        <img class="modal-success__icon" src="../Admin/assets/img/content/checked-success.svg" alt="#">
                        <h4 class="modal-success__title">category was added</h4>
                    </div>
                </div>
                <div class="modal-compact__buttons">
                    <div class="modal-compact__button-item">
                        <button class="modal-compact__button button" data-dismiss="modal" data-modal="#addcategory"><span class="button__text">Add new category</span>
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
<script>
    <c:if test="${requestScope.Message != null && requestScope.Message != '' }">
    alert("${requestScope.Message}");
    </c:if>
</script>
<script src="../Admin/assets/js/custom/category.js"></script>
<script src="../Admin/assets/js/custom/firebasecategory.js"></script>
<jsp:include page="footerAdmin.jsp"/>