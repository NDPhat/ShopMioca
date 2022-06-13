package Controller.API;

import Business.Admin.CustomerJSONBusiness;
import Business.TopLimitProductBusiness;
import DTO.JSON.CustomerJSON;
import DTO.ProductDisplayApiDTO;
import DTO.ProductDisplayDTO;
import Model.ProductsEntity;
import Utils.CSRFUltils;
import Utils.SingletonServiceUltils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = {"/api-paging-shop"})
public class PagingShopAPI extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String startPosString = request.getParameter("startPos");
        int startPos = Integer.parseInt(startPosString);

        List<ProductsEntity> productsEntityList = SingletonServiceUltils.getProductDAOImpl().getTopLimitProduct(9, startPos);
        List<ProductDisplayApiDTO> productDisplayApiDTOList = TopLimitProductBusiness.handleDataTopLimitProducts_productDisplayApiDTO(productsEntityList);
        this.LoadListProductInPage(response, productDisplayApiDTOList);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CSRFUltils.doAction(request,response);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String startPosString = request.getParameter("startPos");
        int startPos = Integer.parseInt(startPosString);

        List<ProductsEntity> productsEntityList = SingletonServiceUltils.getProductDAOImpl().getTopLimitProduct(9, startPos);
        List<ProductDisplayApiDTO> productDisplayApiDTOList = TopLimitProductBusiness.handleDataTopLimitProducts_productDisplayApiDTO(productsEntityList);
        this.LoadListProductInpage_another(response, productDisplayApiDTOList);
    }

    private void LoadListProductInpage_another(HttpServletResponse response, List<ProductDisplayApiDTO> productDisplayApiDTOList) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            for (ProductDisplayApiDTO item:productDisplayApiDTOList) {
                out.println("<div class=\"shop-list-wrapper\">\n" +
                        "                                            <div class=\"row\">\n" +
                        "                                                <div class=\"col-md-5 col-lg-5 col-xl-4\">\n" +
                        "                                                    <div class=\"product\">\n" +
                        "                                                        <div class=\"thumb\">\n" +
                        "                                                            <a href=\"singleproduct?productId=" + item.getId() + "\" class=\"image\">\n" +
                        "                                                                <img src=\"" + item.getImage() + "\"\n" +
                        "                                                                    alt=\"Product\" />\n" +
                        "                                                                <img class=\"hover-image\"\n" +
                        "                                                                    src=\"" + item.getImage() + "\"\n" +
                        "                                                                    alt=\"Product\" />\n" +
                        "                                                            </a>\n" +
                        "                                                            <span class=\"badges\">\n" +
                                                                                        this.loadThumbOfProduct(item.getTagsName(), item.isProductStatus(), item.getDiscount_percent())+
                        "                                                            </span>\n" +
                        "                                                        </div>\n" +
                        "                                                    </div>\n" +
                        "                                                </div>\n" +
                        "                                                <div class=\"col-md-7 col-lg-7 col-xl-8\">\n" +
                        "                                                    <div class=\"content-desc-wrap\">\n" +
                        "                                                        <div class=\"content\">\n" +
                        "                                                               <div class=\"rating-product\">\n" +
                                                                                            this.loadRating(item.getAvgReview()) +
                        "                                                               </div>\n" +
                        "                                                            <h5 class=\"title\"><a href=\"singleproduct?productId=" + item.getId() + "\">" + item.getName() + "\n" +
                        "                                                            </a></h5>\n" +
                        "                                                            <p>" + item.getDescription().split("\\.")[0] + "</p>\n" +
                        "                                                        </div>\n" +
                        "                                                        <div class=\"box-inner\">\n" +
                        "                                                            <span class=\"price\">\n" +
                                                                                        this.priceDiscount(item.isProductStatus(), item.getDiscountPrice(), item.getRegularPrice()) +
                        "                                                            </span>\n" +
                        "                                                            <div class=\"actions\">\n" +
                        "                                                                <a href=\"/comingsoon\" class=\"action wishlist\"\n" +
                        "                                                                    title=\"Wishlist\"><i class=\"pe-7s-like\"></i></a>\n" +
                        "                                                                <a onclick=\"onClickLoadData(" + item.getId() + ", 2);\"" +
                        "                                                                    href=\"#\" class=\"action quickview\"\n" +
                        "                                                                    data-link-action=\"quickview\" title=\"Quick view\"\n" +
                        "                                                                    data-bs-toggle=\"modal\"\n" +
                        "                                                                    data-bs-target=\"#exampleModal\"><i\n" +
                        "                                                                        class=\"pe-7s-search\"></i></a>\n" +
                        "                                                                <a href=\"/comingsoon\" class=\"action compare\"\n" +
                        "                                                                    title=\"Compare\"><i class=\"pe-7s-refresh-2\"></i></a>\n" +
                        "                                                            </div>\n" +
                        "                                                            <button onclick=\"onClickAddToCart(" + item.getId() + ", " + item.getQuantity() + ", " + item.getColorsId().get(0) + ")\" title=\"Add To Cart\" class=\" add-to-cart\">Add\n" +
                        "                                                To Cart</button>\n" +
                        "                                                        </div>\n" +
                        "                                                    </div>\n" +
                        "                                                </div>\n" +
                        "                                            </div>\n" +
                        "                                        </div>");
            }
        }
    }

    private void LoadListProductInPage(HttpServletResponse response, List<ProductDisplayApiDTO> productDisplayApiDTOList) throws IOException {
        try(PrintWriter out = response.getWriter()) {
            for (ProductDisplayApiDTO item:productDisplayApiDTOList) {
                out.println("<div class=\"col-lg-4 col-md-6 col-sm-6 col-xs-6 mb-30px\">\n" +
                        "                                <!-- Single Prodect -->\n" +
                        "                                <div class=\"product\">\n" +
                        "                                    <div class=\"thumb\">\n" +
                        "                                        <a href=\"singleproduct?productId=" + item.getId() + "\" class=\"image\">\n" +
                        "                                            <img src=\"" + item.getImage() + "\" alt=\"Product\" />\n" +
                        "                                            <img class=\"hover-image\" src=\"" + item.getImage() + "\"\n" +
                        "                                                 alt=\"Product\" />\n" +
                        "                                        </a>\n" +
                        "                                        <span class=\"badges\">\n" +
                                                                    this.loadThumbOfProduct(item.getTagsName(), item.isProductStatus(), item.getDiscount_percent())  +
                        "                                        </span>\n" +
                        "                                        <div class=\"actions\">\n" +
                        "                                            <a href=\"/comingsoon\" class=\"action wishlist\" title=\"Wishlist\"><i\n" +
                        "                                                    class=\"pe-7s-like\"></i></a>\n" +
                        "                                            <a onclick=\"onClickLoadData(" + item.getId() + ", 2);\" href=\"#\" class=\"action quickview\"\n" +
                        "                                               title=\"Quick view\" data-bs-toggle=\"modal\" data-link-action=\"quickview\"\n" +
                        "                                               data-bs-target=\"#exampleModal\"><i class=\"pe-7s-look\"></i></a>\n" +
                        "                                            <a href=\"/comingsoon\" class=\"action compare\" title=\"Compare\"><i\n" +
                        "                                                    class=\"pe-7s-refresh-2\"></i></a>\n" +
                        "                                        </div>\n" +
                        "                                    </div>\n" +
                        "                                    <div class=\"content\">\n" +
                        "                                        <div class=\"rating-product\">\n" +
                                                                   this.loadRating(item.getAvgReview()) +
                        "                                        </div>\n" +
                        "                                        <h5 class=\"title\"><a href=\"singleproduct?productId=" + item.getId() + "\">" + item.getName() + "\n" +
                        "                                        </a>\n" +
                        "                                        </h5>\n" +
                        "                                        <span class=\"price\">\n" +
                                                                    this.priceDiscount(item.isProductStatus(), item.getDiscountPrice(), item.getRegularPrice()) +
                        "                                        </span>\n" +
                        "                                    </div>\n" +
                        "                                    <button onclick=\"onClickAddToCart(" + item.getId() + ", " + item.getQuantity() + ", " + item.getColorsId().get(0) + ")\" title=\"Add To Cart\" class=\" add-to-cart\">Add\n" +
                        "                                                To Cart</button>\n" +
                        "                                </div>\n" +
                        "                            </div>\n"
                );
            }
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
}
