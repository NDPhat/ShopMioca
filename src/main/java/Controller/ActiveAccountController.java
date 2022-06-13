package Controller;

import Business.RegisterBusiness;
import Utils.TokenUltils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public @WebServlet("/ActiveAccount")
class ActiveAccountController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String token = req.getParameter("key");
        RegisterBusiness.Active(token);
        System.out.println("welcom "+ TokenUltils.getPlanText(token));
        resp.sendRedirect(req.getContextPath() + "/account");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

