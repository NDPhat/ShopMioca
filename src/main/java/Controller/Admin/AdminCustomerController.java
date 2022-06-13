package Controller.Admin;

import Model.UsersEntity;
import Utils.CSRFUltils;
import Utils.SingletonServiceUltils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/customer")
public class AdminCustomerController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Admin/Customer.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CSRFUltils.doAction(req,resp);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("Id");
        if(id != null)
        {
            try{
                int idCustomer = Integer.parseInt(id);
                UsersEntity users = SingletonServiceUltils.getUserDAOImpl().getOneById(idCustomer);
                if(users != null){
                    Boolean ban = users.getBanned();
                    users.setBanned(!ban);
                    SingletonServiceUltils.getUserDAOImpl().update(users);
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
