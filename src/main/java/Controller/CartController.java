package Controller;

import DAO.ColorDAOImpl;
import DAO.DAOImpl.ProductDAO;
import DTO.ProductDisplayApiDTO;
import DTO.ProductDisplayCartDTO;
import DTO.ProductDisplayDTO;
import DTO.UserAccountDTO;
import Model.OrderProductsEntity;
import Model.ProductsEntity;
import Model.SalesOrdersEntity;
import Utils.ApplicationUtils;
import Utils.SingletonServiceUltils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "CartControl", urlPatterns = {"/cart", "/cart/clear", "/cart/editAll"})
public class CartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();

        try {
            switch (path) {
                case "/cart/clear":
                    clearCart(req, resp);
                    break;
                case "/cart/editAll":
                    editAllProductInCart(req, resp);
                    break;
                default:
                    processingCart(req, resp);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void updateIncreQuantityProduct(int productId, int quantity)
    {
        ProductsEntity productsEntity = SingletonServiceUltils.getProductDAOImpl().getProductbyID(productId);
        productsEntity.setQuantity(productsEntity.getQuantity() + quantity);
        SingletonServiceUltils.getProductDAOImpl().update(productsEntity);
    }

    public void updateDecreQuantityProduct(int productId, int quantity)
    {
        ProductsEntity productsEntity = SingletonServiceUltils.getProductDAOImpl().getProductbyID(productId);
        productsEntity.setQuantity(productsEntity.getQuantity() - quantity);
        SingletonServiceUltils.getProductDAOImpl().update(productsEntity);
    }

    private void processingCart(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        resp.setContentType("text/html;charset=UTF-8");
        Cookie[] cookies = req.getCookies();
        List<ProductDisplayCartDTO> productDisplayCartDTOList;
        UserAccountDTO userAccountDTO = ApplicationUtils.getLoginedUser(req);
        BigDecimal total = new BigDecimal(0);
        if(userAccountDTO == null) {
            if(req.getAttribute("productDisplayCartDTOList") == null) {
                productDisplayCartDTOList = new ArrayList<ProductDisplayCartDTO>();
                boolean exist = false;
                for (Cookie cookie: cookies
                ) {
                    if(cookie.getName().equals("products")) {
                        exist = true;
                        String[] context = cookie.getValue().split("p");
                        for (String s: context
                        ) {
                            // idx:0 means productId, idx=1 means colorId
                            String[] productAndColor = s.split("q");
                            ProductsEntity productsEntity = SingletonServiceUltils.getProductDAOImpl().getProductbyID(Integer.parseInt(productAndColor[0]));
                            String colorName = SingletonServiceUltils.getColorDAOImpl().getNameColorbyColorsId(Integer.parseInt(productAndColor[1]));
                            ProductDisplayCartDTO productDisplayCartDTO = new ProductDisplayCartDTO(productsEntity.getId(), productsEntity.getImage(), productsEntity.getName(),
                                    productsEntity.getDiscountPrice(), Integer.parseInt(productAndColor[1]), colorName);
                            productDisplayCartDTOList.add(productDisplayCartDTO);
                        }
                        break;
                    }
                }
                // if cookie doesn't exist, then redirect user to empty-cart
                if(exist == false) {
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("empty-cart.jsp");
                    requestDispatcher.forward(req, resp);
                    return;
                }
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
            else {
                productDisplayCartDTOList = (List<ProductDisplayCartDTO>) req.getAttribute("productDisplayCartDTOList");
            }
        }
        else {
            if(req.getAttribute("productDisplayCartDTOList") == null) {
                List<OrderProductsEntity> orderProductsEntityList = SingletonServiceUltils.getOrderProductDAOImpl().getOrderProductListWithUserId(userAccountDTO.getId());
                if(orderProductsEntityList.isEmpty()) {
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("empty-cart.jsp");
                    requestDispatcher.forward(req, resp);
                    return;
                }
                productDisplayCartDTOList = new ArrayList<ProductDisplayCartDTO>();
                for (OrderProductsEntity o:
                        orderProductsEntityList) {
                    int colorId = SingletonServiceUltils.getColorDAOImpl().getColorIdByName(o.getColorname());
                    int productId = SingletonServiceUltils.getProductDAOImpl().getProductIdByName(o.getName());
                    ProductDisplayCartDTO productDisplayCartDTO = new ProductDisplayCartDTO(productId, o.getImage(), o.getName(), colorId, o.getColorname(), o.getPrice(), o.getQuantity());
                    productDisplayCartDTOList.add(productDisplayCartDTO);
                }
            }
            else {
                productDisplayCartDTOList = (List<ProductDisplayCartDTO>) req.getAttribute("productDisplayCartDTOList");
            }
        }
        double sum = 0;
        for (ProductDisplayCartDTO productDisplayCartDTO: productDisplayCartDTOList
        ) {
            double totalOnProduct = productDisplayCartDTO.getPrice().doubleValue() * productDisplayCartDTO.getQuantity();
            productDisplayCartDTO.setTotal(BigDecimal.valueOf(totalOnProduct));
            sum += totalOnProduct;
        }
        total = BigDecimal.valueOf(sum);
        req.setAttribute("productDisplayCartDTOList", productDisplayCartDTOList);
        req.setAttribute("total", total);



        RequestDispatcher requestDispatcher = req.getRequestDispatcher("cart.jsp");
        requestDispatcher.forward(req, resp);
    }
    private  List<ProductDisplayCartDTO> makeProductDTO(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException{

        Cookie[] cookies = req.getCookies();
        List<ProductDisplayCartDTO> productDisplayCartDTOList = new ArrayList<ProductDisplayCartDTO>();
        UserAccountDTO userAccountDTO = ApplicationUtils.getLoginedUser(req);
        if(userAccountDTO == null) {
            for (Cookie cookie: cookies
            ) {
                if(cookie.getName().equals("products")) {
                    String[] context = cookie.getValue().split("p");
                    for (String s: context
                    ) {
                        // idx:0 means productId, idx=1 means colorId
                        String[] productAndColor = s.split("q");
                        ProductsEntity productsEntity = SingletonServiceUltils.getProductDAOImpl().getProductbyID(Integer.parseInt(productAndColor[0]));
                        String colorName = SingletonServiceUltils.getColorDAOImpl().getNameColorbyColorsId(Integer.parseInt(productAndColor[1]));
                        ProductDisplayCartDTO productDisplayCartDTO = new ProductDisplayCartDTO(productsEntity.getId(), productsEntity.getImage(), productsEntity.getName(),
                                productsEntity.getDiscountPrice(), Integer.parseInt(productAndColor[1]), colorName);
                        productDisplayCartDTOList.add(productDisplayCartDTO);
                    }
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    resp.addCookie(cookie);
                    break;
                }
            }
            for (int i = 0; i < productDisplayCartDTOList.size(); i++) {
                int quantity = 1;
                for (int j = i + 1; j < productDisplayCartDTOList.size(); j++) {
                    if(productDisplayCartDTOList.get(i).getId() == productDisplayCartDTOList.get(j).getId() &&
                            productDisplayCartDTOList.get(i).getColorDTO().getId() ==  productDisplayCartDTOList.get(j).getColorDTO().getId()) {
                        quantity++;
                        productDisplayCartDTOList.remove(j);
                        j--;
                        productDisplayCartDTOList.get(i).setQuantity(quantity);
                    }
                }
            }
        }
        else {
            List<OrderProductsEntity> orderProductsEntityList = SingletonServiceUltils.getOrderProductDAOImpl().getOrderProductListWithUserId(userAccountDTO.getId());
            productDisplayCartDTOList = new ArrayList<ProductDisplayCartDTO>();
            for (OrderProductsEntity o:
                    orderProductsEntityList) {
                int colorId = SingletonServiceUltils.getColorDAOImpl().getColorIdByName(o.getColorname());
                int productId = SingletonServiceUltils.getProductDAOImpl().getProductIdByName(o.getName());
                ProductDisplayCartDTO productDisplayCartDTO = new ProductDisplayCartDTO(productId, o.getImage(), o.getName(), colorId, o.getColorname(), o.getPrice(), o.getQuantity());
                productDisplayCartDTOList.add(productDisplayCartDTO);
            }
        }
        return productDisplayCartDTOList;
    }
    private void clearCart(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        UserAccountDTO userAccountDTO = ApplicationUtils.getLoginedUser(request);
        if(userAccountDTO == null) {
            Cookie[] cookies = request.getCookies();
            String context = "";
            ArrayList<ProductDisplayCartDTO> productDisplayCartDTOList = new ArrayList<ProductDisplayCartDTO>();
            for (Cookie cookie: cookies) {
                if(cookie.getName().equals("products")) {
                    String[] contextCookie = cookie.getValue().split("p");
                    for (String s: contextCookie) {
                        // idx:0 means productId, idx=1 means colorId
                        String[] productAndColor = s.split("q");
                        ProductsEntity productsEntity = SingletonServiceUltils.getProductDAOImpl().getProductbyID(Integer.parseInt(productAndColor[0]));
                        String colorName = SingletonServiceUltils.getColorDAOImpl().getNameColorbyColorsId(Integer.parseInt(productAndColor[1]));
                        ProductDisplayCartDTO productDisplayCartDTO = new ProductDisplayCartDTO(productsEntity.getId(), productsEntity.getImage(), productsEntity.getName(),
                                productsEntity.getDiscountPrice(), Integer.parseInt(productAndColor[1]), colorName);
                        productDisplayCartDTOList.add(productDisplayCartDTO);
                    }
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

                    for (ProductDisplayCartDTO item : productDisplayCartDTOList) {
                        this.updateIncreQuantityProduct(item.getId(), item.getQuantity());
                    }

                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
                if(cookie.getName().equals("numOfProducts")) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
        else {
            int userId = userAccountDTO.getId();
            SalesOrdersEntity salesOrdersEntity = SingletonServiceUltils.getSalesOrderDAOImpl().getOneByUserIdNotCheckOut(userId);

            for (OrderProductsEntity item : SingletonServiceUltils.getOrderProductDAOImpl().getAllbySaleOrderId(salesOrdersEntity.getId())) {
                int id = SingletonServiceUltils.getProductDAOImpl().getProductIdByName(item.getName());
                this.updateIncreQuantityProduct(id, item.getQuantity());
            }

            SingletonServiceUltils.getOrderProductDAOImpl().clearAllBySaleOrderId(salesOrdersEntity.getId());
        }
        response.sendRedirect("/cart");
    }

    private void editAllProductInCart(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        response.setContentType("text/html");
        String inputQuantities = request.getParameter("inputQuantities");
        System.out.println(inputQuantities);
        //{"name":["5","4","1"]}
        String[] a = inputQuantities.split(":");
        String b = a[1].replaceAll("[}{\"\\[\\]]", "");
        String[] inputQuantityList = b.split(",");
        List<ProductDisplayCartDTO> productDisplayCartDTOList = makeProductDTO(request, response);
        String productIds = "";
        UserAccountDTO userAccountDTO = ApplicationUtils.getLoginedUser(request);
        if(userAccountDTO == null) {
            for (int i = 0; i < productDisplayCartDTOList.size(); i++) {
                int diff = Integer.parseInt(inputQuantityList[i]) - productDisplayCartDTOList.get(i).getQuantity();
                if (diff < 0) {
                    this.updateIncreQuantityProduct(productDisplayCartDTOList.get(i).getId(), diff * -1);
                }
                else {
                    this.updateDecreQuantityProduct(productDisplayCartDTOList.get(i).getId(), diff);
                }
                productDisplayCartDTOList.get(i).setQuantity(Integer.parseInt(inputQuantityList[i]));
                for (int j = 0; j < Integer.parseInt(inputQuantityList[i]); j++) {
                    if(productIds.isEmpty()) {
                        productIds += productDisplayCartDTOList.get(i).getId() + "q" + productDisplayCartDTOList.get(i).getColorDTO().getId();
                    }
                    else {
                        productIds = productIds + "p" + productDisplayCartDTOList.get(i).getId() + "q" + productDisplayCartDTOList.get(i).getColorDTO().getId();
                    }
                }
            }


            Cookie cookie = new Cookie("products", productIds);
            cookie.setMaxAge(60 * 60 * 24);
            cookie.setPath("/");
            response.addCookie(cookie);

            request.setAttribute("productDisplayCartDTOList", productDisplayCartDTOList);
            response.sendRedirect("/cart");
        }
        else {
            int userId = userAccountDTO.getId();
            SalesOrdersEntity salesOrdersEntity = SingletonServiceUltils.getSalesOrderDAOImpl().getOneByUserIdNotCheckOut(userId);
            for (int i = 0; i < productDisplayCartDTOList.size(); i++) {
                int qty = Integer.parseInt(inputQuantityList[i]);
                int diff = qty - productDisplayCartDTOList.get(i).getQuantity();
                if (diff < 0) {
                    this.updateIncreQuantityProduct(productDisplayCartDTOList.get(i).getId(), diff * -1);
                }
                else {
                    this.updateDecreQuantityProduct(productDisplayCartDTOList.get(i).getId(), diff);
                }
                SingletonServiceUltils.getOrderProductDAOImpl().addOrderProduct(salesOrdersEntity.getId(), productDisplayCartDTOList.get(i).getId(), productDisplayCartDTOList.get(i).getColorDTO().getId(), diff);
                productDisplayCartDTOList.get(i).setQuantity(qty);
            }
            response.sendRedirect("/cart");
        }
    }
}