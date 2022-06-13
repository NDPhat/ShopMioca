package Controller;

import Business.TopLimitProductBusiness;
import Model.CategoriesEntity;
import Utils.SingletonServiceUltils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/home", "/trang-chu", "/nha", "/index", ""})
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        //Create new list all category
        //Call method getAll to return list in Class STATIC CategoryDAO
        List<CategoriesEntity> categoriesEntityList = SingletonServiceUltils.getCategoryDAOImpl().getAll();

        //Create new list top 4 popular - means have more product in class STATIC CategoryDAO
        List<CategoriesEntity> categoriesTop4EntityList = SingletonServiceUltils.getCategoryDAOImpl().top4PopularCategories();

        //set data to JSP
        int categoriesEntityList_firstIndex = categoriesEntityList.get(0).getId();
        int categoriesTop4EntityList_firstIndex = categoriesTop4EntityList.get(0).getId();

        req.setAttribute("categoriesEntityList_firstIndex", categoriesEntityList_firstIndex);
        req.setAttribute("categoriesTop4EntityList_firstIndex", categoriesTop4EntityList_firstIndex);

        req.setAttribute("categoriesEntityList", categoriesEntityList);
        req.setAttribute("categoriesTop4EntityList", categoriesTop4EntityList);
        req.setAttribute("Top8Product_categoriesTop4EntityList_tab1", TopLimitProductBusiness.handleDataTopLimitProducts_productDisplayApiDTO(SingletonServiceUltils.getProductDAOImpl().getTopLimitProductByCategorytID(categoriesTop4EntityList.get(0).getId(),8)));
        req.setAttribute("Top8Product_categoriesTop4EntityList_tab2", TopLimitProductBusiness.handleDataTopLimitProducts_productDisplayApiDTO(SingletonServiceUltils.getProductDAOImpl().getTopLimitProductByCategorytID(categoriesTop4EntityList.get(1).getId(), 8)));
        req.setAttribute("Top8Product_categoriesTop4EntityList_tab3", TopLimitProductBusiness.handleDataTopLimitProducts_productDisplayApiDTO(SingletonServiceUltils.getProductDAOImpl().getTopLimitProductByCategorytID(categoriesTop4EntityList.get(2).getId(), 8)));
        req.setAttribute("Top8Product_categoriesTop4EntityList_tab4", TopLimitProductBusiness.handleDataTopLimitProducts_productDisplayApiDTO(SingletonServiceUltils.getProductDAOImpl().getTopLimitProductByCategorytID(categoriesTop4EntityList.get(3).getId(), 8)));


        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
