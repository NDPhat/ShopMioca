package Controller;

import DTO.OrderProductDTO;
import DTO.UserAccountDTO;
import Model.CcTransactionsEntity;
import Model.OrderProductsEntity;
import Utils.MailUtils;
import Utils.SingletonServiceUltils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@WebServlet(name = "PlaceOrderController", value = "/PlaceOrderController")
public class PlaceOrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession();
        String Ssaleid= request.getParameter("sale_orderid");

        if(Ssaleid!="") {

            int Isaleid=Integer.parseInt(Ssaleid);
            CcTransactionsEntity ccTransactionsEntity = new CcTransactionsEntity();
            ccTransactionsEntity.setSalesOrdersEntity(SingletonServiceUltils.getSalesOrderDAOImpl().getOnebyId(Isaleid));
            ccTransactionsEntity.setAmount(SingletonServiceUltils.getOrderProductDAOImpl().SumSubTotalBySaleId(Isaleid));
            ccTransactionsEntity.setTransdate(Date.from(Instant.now()));
            ccTransactionsEntity.setStatus("Pending");
            SingletonServiceUltils.getCcTransactionDAOImpl().insert(ccTransactionsEntity);

            UserAccountDTO User =(UserAccountDTO)session.getAttribute("loginedUser");
            String name = User.getFirstname() + " " + User.getLastname();
            List<OrderProductDTO> listorder = (List<OrderProductDTO>) session.getAttribute("orderProductDTOList");

            MailUtils tempmail = new MailUtils(User.getEmail());
            tempmail.sendMailBilling(ccTransactionsEntity,listorder,name);
            session.removeAttribute("sumsubtotal");
            session.removeAttribute("orderProductDTOList");
            session.removeAttribute("salesOrdersEntity");
            response.sendRedirect("/thankyou");
        }
        else {
            response.sendRedirect("/myaccount");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
