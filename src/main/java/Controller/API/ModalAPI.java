package Controller.API;

import Business.TopLimitProductBusiness;
import DTO.ProductDisplayApiDTO;
import Model.ColorsEntity;
import Model.ProductsEntity;
import Model.TagsEntity;
import Utils.SingletonServiceUltils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

@WebServlet(urlPatterns = {"/api-modal"})
public class ModalAPI extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String s_productsEntityID = request.getParameter("ID");
        int i_productsEntityID = Integer.parseInt(s_productsEntityID);

        List<ProductDisplayApiDTO> maxList = TopLimitProductBusiness.handleDataTopLimitProducts_productDisplayApiDTO(SingletonServiceUltils.getProductDAOImpl().getAll());
        ProductDisplayApiDTO max = Collections.max(maxList);
        BigDecimal maxValue = max.isProductStatus() ? max.getDiscountPrice() : max.getRegularPrice();

        this.loadModal(response, i_productsEntityID, maxValue);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    private void loadModal(HttpServletResponse response, int i_productsEntityID, BigDecimal max) throws IOException {
        ProductDisplayApiDTO productDisplayModalDTO = this.createNewProductDTOByProductId(i_productsEntityID);
        try (PrintWriter out = response.getWriter()) {
            out.println("<div class=\"col-lg-6 col-sm-12 col-xs-12 mb-lm-30px mb-md-30px mb-sm-30px\">\n" +
                    "                        <!-- Swiper -->\n" +
                    "                        <div class=\"swiper-container gallery-top\">\n" +
                    "                            <div class=\"swiper-slide\">\n" +
                    "                                <img class=\"img-responsive m-auto\"\n" +
                    "                                     src=\"" + productDisplayModalDTO.getImage() + "\" alt=\"\">\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                    <div class=\"col-lg-6 col-sm-12 col-xs-12\" data-aos=\"fade-up\" data-aos-delay=\"200\">\n" +
                    "                        <div class=\"product-details-content quickview-content\">\n" +
                    "                            <h2>" + productDisplayModalDTO.getName() + "</h2>\n" +
                    "                            <div class=\"pricing-meta\">\n" +
                    "                                <ul class=\"d-flex\">\n" +
                                                        this.priceDiscount(productDisplayModalDTO.isProductStatus(), productDisplayModalDTO.getDiscountPrice(), productDisplayModalDTO.getRegularPrice())  +
                    "                                </ul>\n" +
                    "                            </div>\n" +
                    "                            <div class=\"pro-details-rating-wrap\">\n" +
                    "                                <div class=\"rating-product\">\n" +
                                                        this.loadRating(productDisplayModalDTO.getAvgReview()) +
                    "                                </div>\n" +
                    "                                <span class=\"read-review\"><a class=\"reviews\" onclick=\"() => {event.preventDefault();}\" style=\"cursor: pointer;\">( " + productDisplayModalDTO.getTotalReviews() + " Review )</a></span>\n" +
                    "                            </div>\n" +
                    "                            <div class=\"stock mt-30px\">\n" +
                                                    this.loadStockStatus(productDisplayModalDTO.getQuantity()) +
                    "                            </div>\n" +
                    "\n" +
                    "                            <div class=\"color-group mt-30px\">\n" +
                                                    this.loadColor(productDisplayModalDTO.getColorsName()) +
                    "                            </div>\n" +
                    "\n" +
                    "\n" +
                    "                            <p class=\"mt-20px mb-0\">" + productDisplayModalDTO.getDescription().split("\\.")[0]  + "</p>\n" +
                    "                            <div class=\"pro-details-quality\">\n" +
                    "                                <div class=\"pro-details-cart\">\n" +
                    "                                    <button class=\"add-cart\" onclick=\"window.location.href='/singleproduct?productId=" + productDisplayModalDTO.getId() + "'\">Detail</button>\n" +
                    "                                </div>\n" +
                    "                                <div class=\"pro-details-compare-wishlist pro-details-wishlist \">\n" +
                    "                                    <a href=\"/comingsoon\"><i class=\"pe-7s-like\"></i></a>\n" +
                    "                                </div>\n" +
                    "                            </div>\n" +
                    "                            <div class=\"pro-details-categories-info pro-details-same-style d-flex\">\n" +
                    "                                <span>Categories: </span>\n" +
                    "                                <ul class=\"d-flex\">\n" +
                    "                                    <li>\n" +
                    "                                        <a href=\"/shop?redi=" + productDisplayModalDTO.getCategoriesId() + "\">" + productDisplayModalDTO.getCategoriesName() + "</a>\n" +
                    "                                    </li>\n" +
                    "                                </ul>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                    </div>");
        }
    }

        private String loadColor(Set<String> colorsName) {
        String html = "";

        List<String> colorsNameList = new ArrayList<>(colorsName);
        
        for (int i = 0; i < colorsNameList.size(); i++) {
            html += "<a id=\"color-" + i + "\" class=\"color-block\" style=\"background: " +
                    colorsNameList.get(i).toString().trim() + "\" onclick=\"onClickColor(event, this.id);\"></a>\n";
        }

        return html;
    }

    private String loadStockStatus(int quantity) {
        String html = "<span class=\"avallabillty\">Availability: <span class=\"in-stock\"><i\n" +
                "class=\"fa fa-check\"></i>In Stock</span></span>\n";

        if (quantity == 0) {
            html = "<span class=\"avallabillty\">Availability: <span class=\"out-of-stock\"><i\n" +
                    "class=\"fa fa-times\"></i>Out Of Stock</span></span>\n";
        }

        return html;
    }

    private String loadRating(int avgReview) {
        String html = "";
        for (int i = 1; i <= avgReview; i++) {
            html += "<i class=\"fa fa-star\" style=\"color: #ffde00\"></i>\n";
        }

        for (int i = avgReview + 1; i <= 5; i++) {
            html += "<i class=\"fa fa-star\" style=\"color: #bcbebf\"></i>\n";
        }
        return html;
    }

    private String priceDiscount(boolean productStatus, BigDecimal productDiscountPrice, BigDecimal productRegularPrice) {
        String htmlTag = "";
        if (productStatus) {
            htmlTag += "<li class=\"new-price\">$" + productDiscountPrice + "</li>\n";
            htmlTag += "<li class=\"old-price\"><del>$" + productRegularPrice  + "</del></li>\n";
        }
        else {
            htmlTag += "<li class=\"new-price\">$" + productRegularPrice + "</li>\n";
        }
        return htmlTag;
    }

    private ProductDisplayApiDTO createNewProductDTOByProductId(int productId) {
        ProductDisplayApiDTO productDisplayModalDTO;

        ProductsEntity productsEntity = SingletonServiceUltils.getProductDAOImpl().getProductbyID(productId);
        String name = productsEntity.getName();
        String description = productsEntity.getDescription();
        BigDecimal regularPrice = productsEntity.getRegularPrice();
        BigDecimal discountPrice = productsEntity.getDiscountPrice();
        Integer quantity = productsEntity.getQuantity();
        String image = productsEntity.getImage();
        Integer discout_percent = productsEntity.getDiscount_percent();
        String information = productsEntity.getInformation();

        Set<String> tagsName = new HashSet<>();
        for (TagsEntity item:SingletonServiceUltils.getTagDAOImpl().getAllTagsByProductId(productId)) {
            tagsName.add(item.getName());
        }

        Boolean status = productsEntity.getProductStatusesEntity().isId();
        String category = productsEntity.getCategoriesEntity().getName();

        Set<String> colorsName = new HashSet<>();
        List<Integer> colorsId = new ArrayList<>();
        for (ColorsEntity item:SingletonServiceUltils.getColorDAOImpl().getAllColorsByProductId(productId)) {
            colorsName.add(item.getName());
            colorsId.add(item.getId());
        }

        int totalReviews = SingletonServiceUltils.getReviewDAOImpl().getAllbyProductId(productId).size();
        int avgReview = SingletonServiceUltils.getReviewDAOImpl().getAVGRatingbyProductId(productId);

        int categoryId = productsEntity.getCategoriesEntity().getId();

        productDisplayModalDTO = new ProductDisplayApiDTO(productId, name, description, regularPrice, discountPrice, quantity, image,
                discout_percent, information, tagsName, status, category, categoryId, colorsName, totalReviews, avgReview, colorsId);

        return  productDisplayModalDTO;
    }
}
