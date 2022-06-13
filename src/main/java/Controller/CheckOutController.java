package Controller;

import DTO.OrderProductDTO;
import DTO.UserAccountDTO;
import Model.OrderProductsEntity;
import Model.SalesOrdersEntity;
import Model.UsersEntity;
import Utils.ApplicationUtils;
import Utils.SingletonServiceUltils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CheckOutController", value = "/CheckOutController")
public class CheckOutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();
        UserAccountDTO userAccountDTO = ApplicationUtils.getLoginedUser(request);
        String Scartid= request.getParameter("cartid");
        if(Scartid==null)
        {
            if(userAccountDTO!=null  )
            {
                UsersEntity usersEntity = SingletonServiceUltils.getUserDAOImpl().getOneById(userAccountDTO.getId());
                if((SingletonServiceUltils.getCcTransactionDAOImpl().getAllSaleOrderbyUserId(usersEntity.getId()).size() < SingletonServiceUltils.getSalesOrderDAOImpl().getAllbyUserId(userAccountDTO.getId()).size())) {
                    SalesOrdersEntity salesOrdersEntity = SingletonServiceUltils.getSalesOrderDAOImpl().getOnebyUserId(usersEntity.getId());
                    List<OrderProductDTO> orderProductDTOList = this.getOrderProductDTOList(salesOrdersEntity);
                    BigDecimal sumsubtotal = SingletonServiceUltils.getOrderProductDAOImpl().SumSubTotalBySaleId(salesOrdersEntity.getId());
                    session.setAttribute("sumsubtotal", sumsubtotal);
                    session.setAttribute("orderProductDTOList", orderProductDTOList);
                    session.setAttribute("salesOrdersEntity", salesOrdersEntity);
                }

                Cookie[] cookies = request.getCookies();
                for (Cookie cookie: cookies
                ) {
                    if(cookie.getName().equals("products") || cookie.getName().equals("numOfProducts")) {
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    }
                }

                RequestDispatcher rd=request.getRequestDispatcher("checkout.jsp");
                rd.forward(request,response);
            }
            //chua login
            else
            {
                RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
                rd.forward(request,response);
            }

        }
        else {
            int Icartid= Integer.parseInt(Scartid);
            SalesOrdersEntity salesOrdersEntity=SingletonServiceUltils.getSalesOrderDAOImpl().getOnebyId(Icartid);
            List<OrderProductDTO> orderProductDTOList = this.getOrderProductDTOList(salesOrdersEntity);
            BigDecimal sumsubtotal = SingletonServiceUltils.getOrderProductDAOImpl().SumSubTotalBySaleId(salesOrdersEntity.getId());
            session.setAttribute("sumsubtotal", sumsubtotal);
            session.setAttribute("orderProductDTOList", orderProductDTOList);
            session.setAttribute("salesOrdersEntity", null);
            RequestDispatcher rd=request.getRequestDispatcher("checkout.jsp");
            rd.forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private List<OrderProductDTO> getOrderProductDTOList(SalesOrdersEntity salesOrdersEntity)
    {
        List<OrderProductDTO> orderProductDTOList=new ArrayList<>();
        List<OrderProductsEntity> orderProductsEntityList= SingletonServiceUltils.getOrderProductDAOImpl().getAllbySaleOrderId(salesOrdersEntity.getId());
        for (OrderProductsEntity order: orderProductsEntityList
        ) {
            orderProductDTOList.add(this.handleDataOneOrderProduct(order));
        }
        return  orderProductDTOList;
    }
    private OrderProductDTO handleDataOneOrderProduct(OrderProductsEntity orderProductsEntity)
    {
        OrderProductDTO orderProductDTO=new OrderProductDTO();
        String name = orderProductsEntity.getName();
        String description = orderProductsEntity.getDescription();
        BigDecimal price = orderProductsEntity.getPrice();
        int quantity =orderProductsEntity.getQuantity();
        BigDecimal subtotal=orderProductsEntity.getSubtotal();
        String colorname=orderProductsEntity.getColorname();
        String image=orderProductsEntity.getImage();
        SalesOrdersEntity salesOrdersEntity=orderProductsEntity.getSalesOrdersEntity();
        orderProductDTO.setName(name);
        orderProductDTO.setDescription(description);
        orderProductDTO.setQuantity(quantity);
        orderProductDTO.setOrder_id(salesOrdersEntity.getId());
        orderProductDTO.setSubtotal(subtotal);
        orderProductDTO.setPrice(price);
        orderProductDTO.setColorname(colorname);
        orderProductDTO.setImage(image);
        return orderProductDTO;
    }
}
