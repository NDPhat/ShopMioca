package Controller.Admin;

import Business.RegisterBusiness;
import Utils.CSRFUltils;
import Utils.SingletonServiceUltils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/createaccount")
public class AdminCreateAccountController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Admin/CreateAdmin.jsp");
        dispatcher.forward(req,resp);
        return;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CSRFUltils.doAction(req,resp);
        String message="";
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        boolean isSuccess = false;
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            String retypepassword = req.getParameter("retypepassword");
            String role = req.getParameter("role");
            if(email == null || email.length() < 1 )
            {
                message = "Mail is required";
            }
            else if(username == null || username.length() < 8 )
            {
                message = "username must longer than 8";
            }
            else if(password == null || password.length() < 8 )
            {
                message = "password must longer than 8";
            }
            else if(SingletonServiceUltils.getAccountDAOImpl().CheckUsernameExist(username))
            {
                message = "username is existed";
            }
            else if(SingletonServiceUltils.getUserDAOImpl().getOneByEmail(email) != null)
            {
                message = "email is existed";
            }
            else {
                int roleid = Integer.parseInt(role);
                Boolean hasRegister = RegisterBusiness.regiterUser(username,password,email,roleid);
                message = "Please check your mail to active account!!";
                isSuccess =  true;
            }
        }catch (Exception e){
            System.out.println("Registe failed: "+e);
        }
        DirectEror(message,isSuccess,req,resp);
    }

    public void DirectEror(String Message,boolean isSuccess,HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        req.setAttribute("Message", Message);
        req.setAttribute("isSuccess", isSuccess);
        RequestDispatcher dispatcher //
                = req.getServletContext().getRequestDispatcher("/Admin/CreateAdmin.jsp");
        dispatcher.forward(req, resp);
        return;
    }
}
