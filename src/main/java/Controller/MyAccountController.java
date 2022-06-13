package Controller;

import Business.AccountBusiness;
import DTO.AddressDTO;
import DTO.UserAccountDTO;
import Utils.CSRFUltils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/myaccount")
public class MyAccountController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        UserAccountDTO userAccountDTO =(UserAccountDTO) req.getSession().getAttribute("loginedUser");
        if(userAccountDTO == null)
            resp.sendRedirect("/login");
        else{
            int id =userAccountDTO.getId();
            AddressDTO address = AccountBusiness.getaddressDTO(id);
            if( address == null)
                address = new AddressDTO();
            req.setAttribute("address",address);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/my-account.jsp");
            dispatcher.forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CSRFUltils.doAction(req,resp);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String path = "/login";
        Boolean isSuccess = false;
        String error="";
        try {
            UserAccountDTO userAccountDTO =(UserAccountDTO) req.getSession().getAttribute("loginedUser");
            if (userAccountDTO != null)
            {
                String firstname = req.getParameter("firstname");
                String lastname = req.getParameter("lastname");
                String number = req.getParameter("number");
                String street = req.getParameter("street");
                String commune = req.getParameter("commune");
                String district = req.getParameter("district");
                String province = req.getParameter("province");
                String phone = req.getParameter("phone");
                String img = req.getParameter("urlImage");

                AddressDTO addressDTO = new AddressDTO(number,street,commune,district,province);
                int id= userAccountDTO.getId();
                if(AccountBusiness.UpdateInfo(firstname,lastname,id,phone,img,addressDTO)==true)
                {
                    userAccountDTO.setFirstname(firstname);
                    userAccountDTO.setLastname(lastname);
                    userAccountDTO.setImage(img);
                    userAccountDTO.setPhone(phone);
                    isSuccess = true;
                    req.getSession().removeAttribute("loginedUser");
                    req.removeAttribute("address");
                    req.getSession().setAttribute("loginedUser",userAccountDTO);
                    req.setAttribute("address",addressDTO);
                }
            }
            DirectEror(isSuccess,req,resp);
        }catch (Exception e){
            System.out.println("Update info failed: "+e);
        }

    }
    public void DirectEror(Boolean isSuccess,HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        req.setAttribute("isSuccess", isSuccess);
        RequestDispatcher dispatcher //
                = req.getServletContext().getRequestDispatcher("/my-account.jsp");
        dispatcher.forward(req, resp);
        return;
    }
}
