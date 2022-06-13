package Controller;

import Business.AccountBusiness;
import Utils.CSRFUltils;
import Utils.SingletonServiceUltils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public @WebServlet("/forgetpass")
class ForgetPasswordController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("mail");
        String newPass = req.getParameter("newpass");

        String message="Some things have problem";
        boolean isSuccess = false;
        if(email==null||newPass==null)
        {
            message = "Change Password Fail Token unvald";
        }
        else if(AccountBusiness.ChangePasswordForForget(email,newPass) == true)
        {
            message = "Change Password Successfully";
            isSuccess= true;
        }
        else{
            message = "Change Password Fail Token unvald";
        }
        DirectEror(message,isSuccess,req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CSRFUltils.doAction(req,resp);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String message="";
        Boolean isSuccess =false;
        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String retypepassword = req.getParameter("retypepassword");

            if(email == null || email.length() < 1 )
            {
                message = "Mail is required";
            }
            else if(password == null || password.length() < 8 )
            {
                message = "password must longer than 8";
            }
            else if(SingletonServiceUltils.getUserDAOImpl().getOneByEmail(email) == null)
            {
                message = "email is not existed";
            }
            else {
                AccountBusiness.ForgetPassword(email,password);
                message = "Please check your mail to confirm change pass!";
                isSuccess =true;
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
                = req.getServletContext().getRequestDispatcher("/login.jsp");
        dispatcher.forward(req, resp);
        return;
    }
}

