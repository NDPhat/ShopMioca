package Controller;

import Business.TopLimitProductBusiness;
import DTO.*;
import Model.CategoriesEntity;
import Model.ColorsEntity;
import Model.ProductsEntity;
import Model.TagsEntity;
import Utils.SingletonServiceUltils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(urlPatterns = {"/shop", "/cua-hang", "/shopController"})
public class ShopController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String redi = request.getParameter("redi");
        String colorRedi = request.getParameter("colorRedi");
        String tagRedi = request.getParameter("tagRedi");

        List<CategoriesEntity> categoriesEntityList = SingletonServiceUltils.getCategoryDAOImpl().getAll();
        List<ColorsEntity> colorsEntityList = SingletonServiceUltils.getColorDAOImpl().getAll();
        List<TagsEntity> tagsEntityList = SingletonServiceUltils.getTagDAOImpl().getAll();

        List<CategoriesShopDTO> categoriesShopDTOList = this.handleCategoriesShopDTOList(categoriesEntityList);
        List<ColorShopDTO> colorShopDTOList = this.handleColorShopDTO(colorsEntityList);
        List<TagShopDTO> tagShopDTOList = this.handleTagShopDTO(tagsEntityList);
        List<ProductDisplayApiDTO> productDisplayApiDTOList = TopLimitProductBusiness.handleDataTopLimitProducts_productDisplayApiDTO(SingletonServiceUltils.getProductDAOImpl().getTopLimitProduct(9,0));

        List<ProductsEntity> productsEntityList = SingletonServiceUltils.getProductDAOImpl().getAll();
        List<ProductDisplayApiDTO> maxList = TopLimitProductBusiness.handleDataTopLimitProducts_productDisplayApiDTO(productsEntityList);
        ProductDisplayApiDTO max = Collections.max(maxList);
        BigDecimal maxValue = max.isProductStatus() ? max.getDiscountPrice() : max.getRegularPrice();

        double totalPages = Math.ceil((double)SingletonServiceUltils.getProductDAOImpl().getAll().size() / 9);

        request.setAttribute("allProductSize", productsEntityList.size());
        request.setAttribute("AllProduct", productDisplayApiDTOList);
        request.setAttribute("categoriesShopDTOList", categoriesShopDTOList);
        request.setAttribute("colorShopDTOList", colorShopDTOList);
        request.setAttribute("tagShopDTOList", tagShopDTOList);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("max", maxValue);
        request.setAttribute("redi", redi);
        request.setAttribute("colorRedi", colorRedi);
        request.setAttribute("tagRedi", tagRedi);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("shop-left-sidebar.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private List<TagShopDTO> handleTagShopDTO(List<TagsEntity> tagsEntityList) {
        List<TagShopDTO> tagShopDTOList = new ArrayList<>();
        for (TagsEntity item:tagsEntityList) {
            TagShopDTO tagShopDTO;
            int id = item.getId();
            String name = item.getName();
            int size = SingletonServiceUltils.getProductDAOImpl().getAllProductByTagId(id).size();
            tagShopDTO = new TagShopDTO(id, name, size);
            tagShopDTOList.add(tagShopDTO);
        }
        return tagShopDTOList;
    }

    private List<ColorShopDTO> handleColorShopDTO(List<ColorsEntity> colorsEntityList) {
        List<ColorShopDTO> colorShopDTOList = new ArrayList<>();
        for (ColorsEntity item:colorsEntityList) {
            ColorShopDTO colorShopDTO;
            int id = item.getId();
            String name = item.getName();
            int size = SingletonServiceUltils.getProductDAOImpl().getAllProductByColorId(id).size();
            colorShopDTO = new ColorShopDTO(id, name, size);
            colorShopDTOList.add(colorShopDTO);
        }
        return colorShopDTOList;
    }

    private List<CategoriesShopDTO> handleCategoriesShopDTOList(List<CategoriesEntity> categoriesEntityList) {
        List<CategoriesShopDTO> categoriesShopDTOList = new ArrayList<>();
        for (CategoriesEntity item:categoriesEntityList) {
            CategoriesShopDTO categoriesShopDTO;
            int id = item.getId();
            String name = item.getName();
            int size = item.getProductsEntities().size();
            categoriesShopDTO = new CategoriesShopDTO(id, name, size);
            categoriesShopDTOList.add(categoriesShopDTO);
        }
        return categoriesShopDTOList;
    }
}
