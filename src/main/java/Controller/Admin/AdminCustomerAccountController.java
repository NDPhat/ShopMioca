package Controller.Admin;

import DTO.AddressDTO;
import Model.RolesEntity;
import Model.UsersEntity;
import Utils.SingletonServiceUltils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static Constant.WebConstant.IMAGE_NULL_URL;

@WebServlet("/admin/customeraccount")
public class AdminCustomerAccountController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("Id");
        System.out.println("hello"+id);
        try{
            int idCustomer = Integer.parseInt(id);
            UsersEntity users = SingletonServiceUltils.getUserDAOImpl().getOneById(idCustomer);

            if(users != null){
                if(users.getImage() == null)
                    users.setImage(IMAGE_NULL_URL);
                AddressDTO addressDTO = new AddressDTO(users.getAddress());
                RolesEntity role = users.getAccountsEntity().getRolesEntity();
                req.setAttribute("users",users);
                req.setAttribute("address",addressDTO);
                req.setAttribute("role",role.getName());
                RequestDispatcher dispatcher = req.getRequestDispatcher("/Admin/CustomerAccount.jsp");
                dispatcher.forward(req,resp);
                return;
            }
            else {
                resp.sendRedirect("/admin/customer");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
