
package Controller;

    import Business.AccountBusiness;
    import DAO.AccountDAOImpl;
    import DTO.AddressDTO;
    import DTO.UserAccountDTO;
    import Model.AccountsEntity;
    import Utils.CSRFUltils;
    import Utils.SingletonServiceUltils;

    import javax.servlet.RequestDispatcher;
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;

@WebServlet("/updatepassword")
public class ChangePasswordController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.sendRedirect("/myaccount");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CSRFUltils.doAction(req,resp);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        Boolean isSuccess = false;
        String message="";
        try {
            UserAccountDTO userAccountDTO =(UserAccountDTO) req.getSession().getAttribute("loginedUser");
            if (userAccountDTO != null)
            {
                String userpassword = req.getParameter("userpassword");
                String userpasswordnew = req.getParameter("userpasswordnew");
                String userretypepasswordnew = req.getParameter("userretypepasswordnew");
                int id= userAccountDTO.getId();
                AccountsEntity acc = AccountBusiness.CheckPassword(id,userpassword);
                if(acc != null)
                {
                    if(userpasswordnew.equals(userretypepasswordnew) == true){
                        acc.setPassword(userpasswordnew);
                        if(SingletonServiceUltils.getAccountDAOImpl().update(acc) != null)
                        {
                            message = "Change Password Successfully";
                            isSuccess= true;
                        }
                        else{
                            message = "Change Password Fail when update to DB";
                        }

                    }
                    else {
                        message = "Re Passowrd don't Match";
                    }
                }
                else {
                    message="Password don't correct";
                }
            }
            else
            {
                resp.sendRedirect("/login");
                return;

            }
            AddressDTO address = AccountBusiness.getaddressDTO(userAccountDTO.getId());
            if( address == null)
                address = new AddressDTO();
            req.setAttribute("address",address);
            DirectEror(address,isSuccess,message,req,resp);
        }catch (Exception e){
            System.out.println("Update info failed: "+e);
        }

    }
    public void DirectEror(AddressDTO address,Boolean isSuccess,String message,HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{

        req.setAttribute("address",address);
        req.setAttribute("isSuccess", isSuccess);
        req.setAttribute("message", message);
        RequestDispatcher dispatcher //
                = req.getServletContext().getRequestDispatcher("/my-account.jsp");
        dispatcher.forward(req, resp);
        return;
    }
}
