package Controller.Admin;

import DTO.AddressDTO;
import DTO.InformationProductDTO;
import Model.*;
import Utils.CSRFUltils;
import Utils.SingletonServiceUltils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static Constant.WebConstant.IMAGE_NULL_URL;

@WebServlet("/admin/editorder")
public class AdminEditOrderController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("Id");
        try{
            int idtrans = Integer.parseInt(id);
            CcTransactionsEntity transactionsEntity = SingletonServiceUltils.getCcTransactionDAOImpl().findById(idtrans);
            if(transactionsEntity != null){
                InformationProductDTO info = null;

                SalesOrdersEntity ordersEntity = transactionsEntity.getSalesOrdersEntity();
                UsersEntity users = ordersEntity.getUsersEntity();
                List<OrderProductsEntity> entityList = ordersEntity.getOrderProductsEntities().stream().collect(Collectors.toList());
                AddressDTO addressDTO = new AddressDTO(users.getAddress());
                String address = addressDTO.getNumber() +", "+addressDTO.getStreet()+", "+addressDTO.getCommune()+", "+addressDTO.getDistrict()+", "+addressDTO.getProvince();
                req.setAttribute("address",address);
                req.setAttribute("trans",transactionsEntity);
                req.setAttribute("user",users);
                req.setAttribute("ListProduct",entityList);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/Admin/EditOrder.jsp");
                dispatcher.forward(req,resp);
                return;
            }
            else {
                resp.sendRedirect("/404");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        resp.sendRedirect("/404");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CSRFUltils.doAction(req,resp);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String id =   req.getParameter("Id");
        String status = req.getParameter("status");
        if(id != null){
            int idTran = Integer.parseInt(id);
            CcTransactionsEntity transactionsEntity = SingletonServiceUltils.getCcTransactionDAOImpl().findById(idTran);
            if(transactionsEntity != null)
            {
                transactionsEntity.setStatus(status);
                SingletonServiceUltils.getCcTransactionDAOImpl().update(transactionsEntity);
            }
        }
        resp.sendRedirect("/admin/editorder?Id="+id);
        return;
    }
}
