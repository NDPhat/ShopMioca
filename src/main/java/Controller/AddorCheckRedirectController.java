package Controller;

import DAO.OrderProductDAOImpl;
import DAO.ProductDAOImpl;
import DAO.SalesOrderDAOImpl;
import DTO.ProductDisplayCartDTO;
import DTO.SalesOrdersDTO;
import DTO.UserAccountDTO;
import Model.OrderProductsEntity;
import Model.ProductsEntity;
import Model.SalesOrdersEntity;
import Model.UsersEntity;
import Utils.ApplicationUtils;
import Utils.CSRFUltils;
import Utils.HibernateUtils;
import Utils.SingletonServiceUltils;
import org.checkerframework.checker.units.qual.C;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Application;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.sql.Date;
import java.util.*;

@WebServlet(name = "AddorCheckRedirectController", urlPatterns = {"/AddorCheckRedirectController", "/AddorCheckRedirectController/editQuantity", "/AddorCheckRedirectController/removeProduct"})
public class AddorCheckRedirectController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        try {
            switch (path) {
                case "/AddorCheckRedirectController/editQuantity":
                    editQuantityOfProduct(request, response);
                    break;
                case "/AddorCheckRedirectController/removeProduct":
                    removeProduct(request, response);
                    break;
                default:
                    processRequest(request, response, "", 1);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CSRFUltils.doAction(request,response);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        processRequest(request, response, "", 1);
    }

    public void updateIncreQuantityProduct(int productId, int quantity)
    {
        ProductsEntity productsEntity = SingletonServiceUltils.getProductDAOImpl().getProductbyID(productId);
        productsEntity.setQuantity(productsEntity.getQuantity() + quantity);
        SingletonServiceUltils.getProductDAOImpl().update(productsEntity);
    }

    private void removeProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String productId = request.getParameter("productId");
        String colorId = request.getParameter("colorId");
        String quantity = request.getParameter("quantity");
        if(productId.length() >10){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error404.jsp");
            return;
        }
        if(colorId.length() >10){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error404.jsp");
            return;
        }
        if(quantity.length() >10){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error404.jsp");
            return;
        }
        UserAccountDTO userAccountDTO = ApplicationUtils.getLoginedUser(request);
        String contextJoined = "";
        int num = 0;
        Cookie[] cookies = request.getCookies();
        updateIncreQuantityProduct(Integer.valueOf(productId), Integer.valueOf(quantity));
        for (Cookie cookie: cookies
        ) {
            if(cookie.getName().equals("products")) {
                String[] context = cookie.getValue().split("p");
                List<String> list = new ArrayList<String>(Arrays.asList(context));
                String productAndColor = productId + "q" + colorId;
                list.removeAll(Collections.singleton(productAndColor));
                context = list.toArray(new String[0]);
                contextJoined = String.join("p", context);
                cookie.setHttpOnly(true);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            if(cookie.getName().equals("numOfProducts")) {
                num = Integer.parseInt(cookie.getValue()) - 1;
                cookie.setHttpOnly(true);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }

        }
        if(userAccountDTO == null) {
            if(contextJoined.length() >= 3) {

                Cookie cookie = new Cookie("numOfProducts", String.valueOf(num));
                cookie.setHttpOnly(true);
                cookie.setMaxAge(60 * 60 * 24);
                cookie.setPath("/");
                response.addCookie(cookie);

                cookie = new Cookie("products", contextJoined);
                cookie.setHttpOnly(true);
                cookie.setMaxAge(60 * 60 * 24);
                cookie.setPath("/");
                response.addCookie(cookie);
                response.sendRedirect("/cart");
            }
            else {
                response.sendRedirect("/cart/clear");
            }
        }
        else {
            int userId = userAccountDTO.getId();
            SalesOrdersEntity salesOrdersEntity = SingletonServiceUltils.getSalesOrderDAOImpl().getOneByUserIdNotCheckOut(userId);
            SingletonServiceUltils.getOrderProductDAOImpl().deleteSingleProduct(salesOrdersEntity.getId(), Integer.parseInt(productId), Integer.parseInt(colorId));
            if(num != 0) {
                Cookie cookie = new Cookie("numOfProducts", String.valueOf(num));
                cookie.setHttpOnly(true);
                cookie.setMaxAge(60 * 60 * 24);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            response.sendRedirect("/cart");
        }
    }

    private void redirectCurrentPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getParameter("path");
        if (path.trim().equals("home")) {
            response.sendRedirect("/home");
        }
        else if (path.trim().equals("single")){
            String curProductId = request.getParameter("curProductId");
            String temp = "/singleproduct?productId=" + curProductId;
            response.sendRedirect(temp);
        }
        else if (path.trim().equals("cart")) {
            response.sendRedirect("/cart");
        }
        else {
//            String redi = request.getParameter("redi");
//            String colorRedi = request.getParameter("colorRedi");
//            String tagRedi = request.getParameter("tagRedi");
//            String temp = "/shop?redi=" + redi +"&colorRedi=" + colorRedi + "&tagRedi=" + tagRedi;
            String temp = "/shop";
            response.sendRedirect(temp);
        }
    }

    public void updateDecreQuantityProduct(int productId, int quantity)
    {
        ProductsEntity productsEntity = SingletonServiceUltils.getProductDAOImpl().getProductbyID(productId);
        productsEntity.setQuantity(productsEntity.getQuantity() - quantity);
        SingletonServiceUltils.getProductDAOImpl().update(productsEntity);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String quantityInCart, int flag)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String productId = request.getParameter("productId");
        String colorId = request.getParameter("colorId");
        String quantity = "";
        if(productId.length() >10){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error404.jsp");
            return;
        }
        if(colorId.length() >5){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error404.jsp");
            return;
        }

        if(quantityInCart == "")
            quantity = request.getParameter("quantity");
        else
            quantity = quantityInCart;

        if(quantity.length() >10){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error404.jsp");
            return;
        }

        UserAccountDTO userAccountDTO = ApplicationUtils.getLoginedUser(request);
        if(userAccountDTO == null) {
            Cookie[] cookies = request.getCookies();
            String context = "";
            for (Cookie cookie: cookies
            ) {
                if(cookie.getName().equals("products")) {
                    context += cookie.getValue();
                    cookie.setHttpOnly(true);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
            String productIds = "";
            for (int i = 0; i < Integer.parseInt(quantity); i++) {
                if(i == 0) {
                    productIds += productId + "q" + colorId;
                }
                else {
                    productIds = productIds + "p" + productId + "q" + colorId;
                }
            }
            if(context.isEmpty()) {
                context += productIds;
            }
            else {
                context = context + "p" + productIds;
            }
            Cookie cookie = new Cookie("products", context);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24);
            response.addCookie(cookie);

            String[] str = cookie.getValue().split("p");
            HashMap<String,Integer> hashmap = new HashMap<String,Integer>();

            //use for loop to pull the elements of array to hashmap's key
            for (int j = 0; j < str.length; j++) {
                hashmap.put(str[j], j);
            }

            cookie = new Cookie("numOfProducts", String.valueOf(hashmap.keySet().size()));
            cookie.setHttpOnly(true);
            cookie.setMaxAge(60 * 60 * 24);
            cookie.setPath("/");
            response.addCookie(cookie);

            if (flag == 1) {
                updateDecreQuantityProduct(Integer.valueOf(productId), Integer.valueOf(quantity));
            }
            redirectCurrentPage(request, response);
        }
        else {
            int userId = userAccountDTO.getId();
            SalesOrdersEntity salesOrdersEntity = SingletonServiceUltils.getSalesOrderDAOImpl().getOneByUserIdNotCheckOut(userId);

            if (flag == 1) {
                updateDecreQuantityProduct(Integer.valueOf(productId), Integer.valueOf(quantity));
            }

            if(salesOrdersEntity == null) {
                java.util.Date utilDate = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                SalesOrdersDTO salesOrdersDTO = new SalesOrdersDTO(sqlDate, BigDecimal.valueOf(0), userId);
                int saleOrderId = SingletonServiceUltils.getSalesOrderDAOImpl().addSaleOrder(salesOrdersDTO);

                List<OrderProductsEntity> orderProductsEntities = SingletonServiceUltils.getOrderProductDAOImpl().getAllbySaleOrderId(saleOrderId);
                Cookie cookie = new Cookie("numOfProducts", String.valueOf(orderProductsEntities.size()));
                cookie.setHttpOnly(true);
                cookie.setMaxAge(60 * 60 * 24);
                cookie.setPath("/");
                response.addCookie(cookie);

                SingletonServiceUltils.getOrderProductDAOImpl().addOrderProduct(saleOrderId, Integer.parseInt(productId), Integer.parseInt(colorId), Integer.parseInt(quantity));
                redirectCurrentPage(request, response);
            }
            else {
                SingletonServiceUltils.getOrderProductDAOImpl().addOrderProduct(salesOrdersEntity.getId(), Integer.parseInt(productId), Integer.parseInt(colorId), Integer.parseInt(quantity));

                List<OrderProductsEntity> orderProductsEntities = SingletonServiceUltils.getOrderProductDAOImpl().getAllbySaleOrderId(salesOrdersEntity.getId());
                Cookie cookie = new Cookie("numOfProducts", String.valueOf(orderProductsEntities.size()));
                cookie.setHttpOnly(true);
                cookie.setMaxAge(60 * 60 * 24);
                cookie.setPath("/");
                response.addCookie(cookie);

                redirectCurrentPage(request, response);
            }
        }
    }
    private void editQuantityOfProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String productId = request.getParameter("productId");
        String quantity = request.getParameter("quantity");
        String colorId = request.getParameter("colorId");
        String inputQuantity = request.getParameter("inputQuantity");
        Integer diffQuantity = (Integer.parseInt(inputQuantity) - Integer.parseInt(quantity));
        UserAccountDTO userAccountDTO = ApplicationUtils.getLoginedUser(request);
        if (diffQuantity < 0) {
            updateIncreQuantityProduct(Integer.valueOf(productId), diffQuantity*-1);
        }
        else {
            updateDecreQuantityProduct(Integer.valueOf(productId), diffQuantity);
        }

        if(diffQuantity > 0) {
            if(userAccountDTO == null)
                processRequest(request, response, diffQuantity.toString(), 0);
            else {
                int userId = userAccountDTO.getId();
                SalesOrdersEntity salesOrdersEntity = SingletonServiceUltils.getSalesOrderDAOImpl().getOneByUserIdNotCheckOut(userId);
                SingletonServiceUltils.getOrderProductDAOImpl().addOrderProduct(salesOrdersEntity.getId(), Integer.parseInt(productId), Integer.parseInt(colorId), diffQuantity);

                response.sendRedirect("/cart");
            }
        }
        else {
            if(userAccountDTO == null) {
                Cookie[] cookies = request.getCookies();
                String context = "";
                for (Cookie cookie: cookies
                ) {
                    if(cookie.getName().equals("products")) {
                        context += cookie.getValue();
                        cookie.setHttpOnly(true);
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
                if(diffQuantity < 0) {
                    diffQuantity = -diffQuantity;
                    String[] contextProducts = context.split("p");
                    String newContext = "";
                    for (int i = 0; i < contextProducts.length; i++) {
                        String[] productAndColor = contextProducts[i].split("q");
                        if(productAndColor[0].equals(productId) && productAndColor[1].equals(colorId)) {
                            if(diffQuantity > 0)
                                diffQuantity--;
                            else {
                                if(newContext.isEmpty()) {
                                    newContext = contextProducts[i];
                                }
                                else {
                                    newContext += "p" + contextProducts[i];
                                }
                            }
                        }
                        else{
                            if(newContext.isEmpty()) {
                                newContext = contextProducts[i];
                            }
                            else {
                                newContext += "p" + contextProducts[i];
                            }
                        }
                    }
                    Cookie cookie = new Cookie("products", newContext);
                    cookie.setMaxAge(60 * 60 * 24);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
            else {
                int userId = userAccountDTO.getId();
                SalesOrdersEntity salesOrdersEntity = SingletonServiceUltils.getSalesOrderDAOImpl().getOneByUserIdNotCheckOut(userId);
                SingletonServiceUltils.getOrderProductDAOImpl().addOrderProduct(salesOrdersEntity.getId(), Integer.parseInt(productId), Integer.parseInt(colorId), diffQuantity);
            }
            response.sendRedirect("/cart");
        }
    }
}