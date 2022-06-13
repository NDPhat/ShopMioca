package Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(false);
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie: cookies) {
            if(cookie.getName().equals("products") || cookie.getName().equals("numOfProducts")) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                resp.addCookie(cookie);
            }
        }
        if(session != null)
        {
            session.removeAttribute("loginedUser");
        }

        resp.sendRedirect("/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    public void DirectEror(String errorMessage,HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        req.setAttribute("errorMessage", errorMessage);
        System.out.println("Alo:"+errorMessage);
        RequestDispatcher dispatcher //
                = req.getServletContext().getRequestDispatcher("/login.jsp");
        dispatcher.forward(req, resp);
        return;
    }
}
