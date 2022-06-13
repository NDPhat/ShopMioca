package Controller.Admin;

import Model.CategoriesEntity;
import Utils.CSRFUltils;
import Utils.SingletonServiceUltils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/categories")
public class AdminCategoryController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Admin/Category.jsp");
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
            String method = req.getParameter("method");
            if (method.equals("create") == true){
                String urlimage = req.getParameter("urlimage");
                String Name = req.getParameter("Name");
                if(SingletonServiceUltils.getCategoryDAOImpl().CheckNameExist(Name) == true)
                {
                    message = "name category is existing";
                }
                else {
                    CategoriesEntity cate = new CategoriesEntity();
                    cate.setImage(urlimage);
                    cate.setName(Name);
                    SingletonServiceUltils.getCategoryDAOImpl().insert(cate);
                    message = "add new category successful";
                }
            }
            else if (method.equals("edit") == true) {
                String idnum = req.getParameter("categoryId");
                String urlimage = req.getParameter("urlimage");
                String Name = req.getParameter("categoryName");
                if(idnum== null || idnum.equals("")){
                    message = "Id not found";
                }
                else{
                    int id = Integer.parseInt(idnum);
                    CategoriesEntity categoriesEntity = SingletonServiceUltils.getCategoryDAOImpl().findById(id);

                    if(categoriesEntity == null){
                        message = "Id is not existing";
                    }
                    else if (SingletonServiceUltils.getCategoryDAOImpl().CheckNameExist(Name) == true &&
                            Name.equals(categoriesEntity.getName()) == false) {

                        message = "name category is existing";
                    } else {
                        categoriesEntity.setImage(urlimage);
                        categoriesEntity.setName(Name);
                        SingletonServiceUltils.getCategoryDAOImpl().update(categoriesEntity);
                        message = "edit category successful";
                    }
                }
            }
        }catch (Exception e){
            System.out.println("Registe failed: "+e);
            message = "Something was wrong";
        }
        DirectEror(message,isSuccess,req,resp);
    }

    public void DirectEror(String Message,boolean isSuccess,HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        req.setAttribute("Message", Message);
        req.setAttribute("isSuccess", isSuccess);
        RequestDispatcher dispatcher //
                = req.getServletContext().getRequestDispatcher("/Admin/Category.jsp");
        dispatcher.forward(req, resp);
        return;
    }
}
