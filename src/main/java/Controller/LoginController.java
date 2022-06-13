package Controller;

import Business.LoginBusiness;
import DTO.AddressDTO;
import DTO.UserAccountDTO;
import Model.OrderProductsEntity;
import Model.SalesOrdersEntity;
import Model.UsersEntity;
import Utils.ApplicationUtils;
import Utils.CSRFUltils;
import Utils.SingletonServiceUltils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        UserAccountDTO user = ApplicationUtils.getLoginedUser(req);
        if(user != null)
        {
            resp.sendRedirect(req.getContextPath() + "/myaccount");
            return;
        }
        else
        {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
            dispatcher.forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!CSRFUltils.doAction(req, resp)) {
            String errorMessage = "CSRF not valid";
            DirectEror(errorMessage,req,resp);
            return;
        }

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        UserAccountDTO userAccount = LoginBusiness.getUserLogin(userName,password);
        if (userAccount == null) {
            String errorMessage = "Invalid userName or Password";
            DirectEror(errorMessage,req,resp);
            return;
        }

        if(!LoginBusiness.checkActive(userName))
        {
            String errorMessage = "Your account haven't actived! Please Check Your mail!";
            DirectEror(errorMessage,req,resp);
            return;
        }

        if(LoginBusiness.checkBanned(userName))
        {
            String errorMessage = "Your account has been banned! Please contact with Admin!";
            DirectEror(errorMessage,req,resp);
            return;
        }
        ApplicationUtils.storeLoginedUser(req, userAccount);
        //
//        int redirectId = -1;
//        try {
//            redirectId = Integer.parseInt(req.getParameter("redirectId"));
//        } catch (Exception e) {
//            System.out.println("request don't have redirectId");
//        }


//        String requestUri = ApplicationUtils.getRedirectAfterLoginUrl(redirectId);
        String requestUri = null;
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie: cookies
        ) {
            if(cookie.getName().equals("products") || cookie.getName().equals("numOfProducts")) {
                cookie.setHttpOnly(true);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                resp.addCookie(cookie);
            }
        }
        UserAccountDTO userAccountDTO = ApplicationUtils.getLoginedUser(req);
        if(userAccountDTO != null) {
            int userId = userAccountDTO.getId();
            SalesOrdersEntity salesOrdersEntity = SingletonServiceUltils.getSalesOrderDAOImpl().getOneByUserIdNotCheckOut(userId);
            if(salesOrdersEntity != null) {
                List<OrderProductsEntity> orderProductsEntities = SingletonServiceUltils.getOrderProductDAOImpl().getAllbySaleOrderId(salesOrdersEntity.getId());
                if(!orderProductsEntities.isEmpty()) {
                    Cookie cookie = new Cookie("numOfProducts", String.valueOf(orderProductsEntities.size()));
                    cookie.setHttpOnly(true);
                    cookie.setMaxAge(60 * 60 * 24);
                    cookie.setPath("/");
                    resp.addCookie(cookie);
                }
            }
        }
        if (requestUri != null) {
            resp.sendRedirect(requestUri);
        } else {
            if(userAccount.getRoles().size() ==1)
                resp.sendRedirect(req.getContextPath() + "/");
            else if(userAccount.getRoles().size() ==2)
                resp.sendRedirect(req.getContextPath() + "/admin");
            UsersEntity usersEntity = SingletonServiceUltils.getUserDAOImpl().getOneById(userAccount.getId());

            if(usersEntity.getAddress()!=null)
            {
                AddressDTO addressDTO=new AddressDTO(usersEntity.getAddress());
                userAccount.setAddress(addressDTO);
                userAccount.setPhone(usersEntity.getPhone());
                userAccount.setImage(usersEntity.getImage());
                req.getSession().setAttribute("loginedUser",userAccount);

            }
        }
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
