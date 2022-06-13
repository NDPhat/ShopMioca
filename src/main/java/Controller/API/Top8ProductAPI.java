package Controller.API;

import DTO.ProductDisplayApiDTO;
import Model.ColorsEntity;
import Model.ProductsEntity;
import Model.TagsEntity;
import Utils.SingletonServiceUltils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/api-top8product"})
public class Top8ProductAPI extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String s_categoriesEntityID = request.getParameter("ID");
        try (PrintWriter out = response.getWriter()) {
            if (s_categoriesEntityID.length() > 10) {
                out.println("");
            }
        }
        int i_categoriesEntityID = Integer.parseInt(s_categoriesEntityID);
        this.loadTop8ProductByCategoryID(response, i_categoriesEntityID);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    private void loadTop8ProductByCategoryID(HttpServletResponse response, int categoryID) {
        List<ProductsEntity> top8ProductsEntityList = SingletonServiceUltils.getProductDAOImpl().getTopLimitProductByCategorytID(categoryID, 8);

        try (PrintWriter out = response.getWriter()) {
            for (ProductsEntity productsEntity : top8ProductsEntityList) {
                ProductDisplayApiDTO productDisplayApiDTO = this.createNewProductDTOByProductId(productsEntity.getId());
                out.println(    "                   <div class=\"col-lg-4 col-xl-3 col-md-6 col-sm-6 col-xs-6 mb-30px\">\n" +
                                "                                <!-- Single Prodect -->\n" +
                                "                                <div class=\"product\">\n" +
                                "                                    <div class=\"thumb\">\n" +
                                "                                        <a href=\"singleproduct?productId=" + productDisplayApiDTO.getId() + "\" class=\"image\">\n" +
                                "                                            <img src=\"" + productDisplayApiDTO.getImage() + "\" alt=\"Product\" />\n" +
                                "                                            <img class=\"hover-image\" src=\"" + productDisplayApiDTO.getImage() + "\"\n" +
                                "                                                 alt=\"Product\" />\n" +
                                "                                        </a>\n" +
                                "                                        <span class=\"badges\">\n" +
                                                                            this.loadThumbOfProduct(productDisplayApiDTO.getTagsName(), productDisplayApiDTO.isProductStatus(), productDisplayApiDTO.getDiscount_percent())  +
                                "                                        </span>\n" +
                                "                                        <div class=\"actions\">\n" +
                                "                                            <a href=\"/comingsoon\" class=\"action wishlist\" title=\"Wishlist\"><i\n" +
                                "                                                    class=\"pe-7s-like\"></i></a>\n" +
                                "                                            <a onclick=\"onClickLoadData(" + productDisplayApiDTO.getId() + ", 2);\" href=\"#\" class=\"action quickview\"\n" +
                                "                                               title=\"Quick view\" data-bs-toggle=\"modal\" data-link-action=\"quickview\"\n" +
                                "                                               data-bs-target=\"#exampleModal\"><i class=\"pe-7s-look\"></i></a>\n" +
                                "                                            <a href=\"/comingsoon\" class=\"action compare\" title=\"Compare\"><i\n" +
                                "                                                    class=\"pe-7s-refresh-2\"></i></a>\n" +
                                "                                        </div>\n" +
                                "                                    </div>\n" +
                                "                                    <div class=\"content\">\n" +
                                "                                        <div class=\"rating-product\">\n" +
                                                                            this.loadRating(productDisplayApiDTO.getAvgReview()) +
                                "                                        </div>\n" +
                                "                                        <h5 class=\"title\"><a href=\"singleproduct?productId=" + productDisplayApiDTO.getId() + "\">" + productDisplayApiDTO.getName() + "\n" +
                                "                                        </a>\n" +
                                "                                        </h5>\n" +
                                "                                        <span class=\"price\">\n" +
                                                                            this.priceDiscount(productDisplayApiDTO.isProductStatus(), productDisplayApiDTO.getDiscountPrice(), productDisplayApiDTO.getRegularPrice()) +
                                "                                        </span>\n" +
                                "                                    </div>\n" +
                                "                                    <button onclick=\"onClickAddToCart(" + productDisplayApiDTO.getId() + ", " + productDisplayApiDTO.getQuantity() + ", " + productDisplayApiDTO.getColorsId().get(0) + ")\" title=\"Add To Cart\" class=\" add-to-cart\">Add\n" +
                        "                                                To Cart</button>\n" +
                                "                                </div>\n" +
                                "                            </div>\n"
                );
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String priceDiscount(boolean productStatus, BigDecimal productDiscountPrice, BigDecimal productRegularPrice) {
        String htmlTag = "";
        if (productStatus) {
            htmlTag += "<span class=\"new\">$" + productDiscountPrice + "</span>\n";
            htmlTag += "<span class=\"old\">$" + productRegularPrice  + "</span>\n";
        }
        else {
            htmlTag += "<span class=\"new\">$" + productRegularPrice  + "</span>\n";
        }
        return htmlTag;
    }

    private String loadThumbOfProduct(Set<String> tagNames, boolean status, int discount_percent) {
        String htmlTag = "";

        //set status discout
        if (status) {
            htmlTag += "<span class=\"sale\">-" + discount_percent + "%</span>\n";
        }

        //set tag for product
        for (String item : tagNames) {
            htmlTag += "<span  class=\"new\">" + item + "</span>\n";
        }

        return htmlTag;
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

    private ProductDisplayApiDTO createNewProductDTOByProductId(int productId) {
        ProductDisplayApiDTO productDisplayModalDTODTO;

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

        productDisplayModalDTODTO = new ProductDisplayApiDTO(productId, name, description, regularPrice, discountPrice, quantity, image,
                discout_percent, information, tagsName, status, category, categoryId, colorsName, totalReviews, avgReview, colorsId);

        return  productDisplayModalDTODTO;
    }
}
