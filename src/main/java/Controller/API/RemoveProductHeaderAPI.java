package Controller.API;

import DTO.ColorDTO;
import DTO.ProductDisplayCartDTO;
import DTO.UserAccountDTO;
import Model.OrderProductsEntity;
import Model.ProductsEntity;
import Model.SalesOrdersEntity;
import Utils.ApplicationUtils;
import Utils.SingletonServiceUltils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@WebServlet(urlPatterns = {"/api-remove-product-header"})
public class RemoveProductHeaderAPI extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Cookie[] cookies = request.getCookies();
        boolean existed = false;
        String path = request.getServletPath();
        switch (path) {
            case "/api-header-cart-list":

        }
        List<ProductDisplayCartDTO> productDisplayCartDTOList = new ArrayList<>();
        UserAccountDTO userAccountDTO = ApplicationUtils.getLoginedUser(request);
        if(userAccountDTO == null) {
            for (Cookie cookie:cookies) {
                if(cookie.getName().equals("products")) {
                    existed = true;
                    String[] context = cookie.getValue().split("p");
                    for (String s:context) {
                        String[] productAndColor = s.split("q");
                        ProductsEntity productsEntity = SingletonServiceUltils.getProductDAOImpl().getProductbyID(Integer.parseInt(productAndColor[0]));
                        String colorName = SingletonServiceUltils.getColorDAOImpl().getNameColorbyColorsId(Integer.parseInt(productAndColor[1]));
                        ColorDTO colorDTO = new ColorDTO(Integer.parseInt(productAndColor[1]), colorName);
                        ProductDisplayCartDTO productDisplayCartDTO = new ProductDisplayCartDTO(productsEntity.getId(), productsEntity.getImage(), productsEntity.getName(),
                                colorDTO, productsEntity.getDiscountPrice());
                        productDisplayCartDTOList.add(productDisplayCartDTO);
                    }
                    break;
                }
            }

            if (!existed) {
                try(PrintWriter out = response.getWriter()) {
                    out.println("<h2>Empty Cart</h2>");
                }
            }
            else {
                for (int i = 0; i < productDisplayCartDTOList.size(); i++) {
                    int quantity = 1;
                    for (int j = i + 1; j < productDisplayCartDTOList.size(); j++) {
                        if(productDisplayCartDTOList.get(i).getId() == productDisplayCartDTOList.get(j).getId()
                                && productDisplayCartDTOList.get(i).getColorDTO().getId() == productDisplayCartDTOList.get(j).getColorDTO().getId()) {
                            quantity++;
                            productDisplayCartDTOList.remove(j);
                            j--;
                            productDisplayCartDTOList.get(i).setQuantity(quantity);
                        }
                    }
                }
            }
        }
        else {
            List<OrderProductsEntity> orderProductsEntityList = SingletonServiceUltils.getOrderProductDAOImpl().getOrderProductListWithUserId(userAccountDTO.getId());
            if(orderProductsEntityList.isEmpty()) {
                try(PrintWriter out = response.getWriter()) {
                    out.println("<h2>Empty Cart</h2>");
                }
            }
            else {
                for (OrderProductsEntity o:
                        orderProductsEntityList) {
                    int colorId = SingletonServiceUltils.getColorDAOImpl().getColorIdByName(o.getColorname());
                    int productId = SingletonServiceUltils.getProductDAOImpl().getProductIdByName(o.getName());
                    ProductDisplayCartDTO productDisplayCartDTO = new ProductDisplayCartDTO(productId, o.getImage(), o.getName(), colorId, o.getColorname(), o.getPrice(), o.getQuantity());
                    productDisplayCartDTOList.add(productDisplayCartDTO);
                }
            }
        }

        this.removeProductHeader(request, response);
        this.handleHeaderCartList(response, productDisplayCartDTOList);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void handleHeaderCartList(HttpServletResponse response, List<ProductDisplayCartDTO> productDisplayCartDTOList) throws IOException {
        try(PrintWriter out = response.getWriter()) {
            for (ProductDisplayCartDTO item:productDisplayCartDTOList) {
                out.println("<li>\n" +
                        "       <a href=\"/singleproduct?productId=" + item.getId() + "\" class=\"image\">\n" +
                        "          <img src=\"" + item.getImage() + "\" alt=\"Cart product Image\">\n" +
                        "       </a>\n" +
                        "       <div class=\"content\">\n" +
                        "           <a href=\"/singleproduct?productId=" + item.getId() + "\" class=\"title\">" + item.getName() + "</a>\n" +
                        "           <span class=\"quantity-price\">Color: <a href=\"shop?colorRedi=" + item.getColorDTO().getId() + "\" style=\"cursor: pointer\" class=\"title\">" + item.getColorDTO().getName() + "</a></span>\n" +
                        "           <span class=\"quantity-price\">" + item.getQuantity() + " x <span class=\"amount\">$" + item.getPrice() + "</span></span>\n" +
                        "           <a style=\"cursor: pointer;\" onclick=\"onRemove(" + item.getId() + ", " + item.getColorDTO().getId() + ", " + item.getQuantity() + ")\" class=\"remove\">x</a>\n" +
                        "       </div>\n" +
                        "    </li>");
            }
        }
    }

    public void updateIncreQuantityProduct(int productId, int quantity)
    {
        ProductsEntity productsEntity = SingletonServiceUltils.getProductDAOImpl().getProductbyID(productId);
        productsEntity.setQuantity(productsEntity.getQuantity() + quantity);
        SingletonServiceUltils.getProductDAOImpl().update(productsEntity);
    }

    private void removeProductHeader(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        String productId = request.getParameter("productId");
        String colorId = request.getParameter("colorId");
        String quantity = request.getParameter("quantity");

        UserAccountDTO userAccountDTO = ApplicationUtils.getLoginedUser(request);
        updateIncreQuantityProduct(Integer.valueOf(productId), Integer.valueOf(quantity));
        String contextJoined = "";
        int num = 0;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies
        ) {
            if(cookie.getName().equals("products")) {
                String[] context = cookie.getValue().split("p");
                List<String> list = new ArrayList<String>(Arrays.asList(context));
                String productAndColor = productId + "q" + colorId;
                list.removeAll(Collections.singleton(productAndColor));
                context = list.toArray(new String[0]);
                contextJoined = String.join("p", context);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            if(cookie.getName().equals("numOfProducts")) {
                num = Integer.parseInt(cookie.getValue()) - 1;
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }

        }
        if(userAccountDTO == null) {
            if(contextJoined.length() >= 3) {

                Cookie cookie = new Cookie("numOfProducts", String.valueOf(num));
                cookie.setMaxAge(60 * 60 * 24);
                cookie.setPath("/");
                response.addCookie(cookie);

                cookie = new Cookie("products", contextJoined);
                cookie.setMaxAge(60 * 60 * 24);
                cookie.setPath("/");
                response.addCookie(cookie);
                //response.sendRedirect("/cart");
            }
            else {
                //response.sendRedirect("/cart/clear");
            }
        }
        else {
            int userId = userAccountDTO.getId();
            SalesOrdersEntity salesOrdersEntity = SingletonServiceUltils.getSalesOrderDAOImpl().getOneByUserIdNotCheckOut(userId);
            SingletonServiceUltils.getOrderProductDAOImpl().deleteSingleProduct(salesOrdersEntity.getId(), Integer.parseInt(productId), Integer.parseInt(colorId));
            if(num != 0) {
                Cookie cookie = new Cookie("numOfProducts", String.valueOf(num));
                cookie.setMaxAge(60 * 60 * 24);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            //response.sendRedirect("/cart");
        }
    }
}
