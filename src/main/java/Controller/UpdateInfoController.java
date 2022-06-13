package Controller;

import Business.AccountBusiness;
import DTO.AddressDTO;
import DTO.UserAccountDTO;
import Utils.ApplicationUtils;
import Utils.CSRFUltils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateInfoController", value = "/UpdateInfoController")
public class UpdateInfoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!CSRFUltils.doAction(request, response)) {
            String errorMessage = "CSRF not valid";
            DirectEror(false,request,response);
            return;
        }
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = "/CheckOutController";
        Boolean isSuccess = false;
        String error="";
        try {
            UserAccountDTO userAccountDTO = ApplicationUtils.getLoginedUser(request);

            if (userAccountDTO != null)
            {
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String number = request.getParameter("number");
                String street = request.getParameter("street");
                String commune = request.getParameter("commune");
                String district = request.getParameter("district");
                String province = request.getParameter("province");
                String phone = request.getParameter("phone");
                AddressDTO addressDTO = new AddressDTO(number,street,commune,district,province);
                int id= userAccountDTO.getId();
                if(AccountBusiness.UpdateInfoVer2(firstname,lastname,phone,id,addressDTO)==true)
                {
                    userAccountDTO.setFirstname(firstname);
                    userAccountDTO.setLastname(lastname);
                    userAccountDTO.setAddress(addressDTO);
                    userAccountDTO.setPhone(phone);
                    isSuccess = true;
                    request.getSession().removeAttribute("loginedUser");
                    request.removeAttribute("address");
                    request.getSession().setAttribute("loginedUser",userAccountDTO);
                    request.setAttribute("address",addressDTO);
                }
            }
            DirectEror(isSuccess,request,response);
        }catch (Exception e){
            System.out.println("Update info failed: "+e);
        }
    }
    public void DirectEror(Boolean isSuccess,HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        req.setAttribute("isSuccess", isSuccess);
        RequestDispatcher dispatcher
                = req.getServletContext().getRequestDispatcher("/checkout.jsp");
        dispatcher.forward(req, resp);
        return;
    }
}
