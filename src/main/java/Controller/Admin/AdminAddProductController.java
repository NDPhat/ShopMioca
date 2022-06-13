package Controller.Admin;

import DTO.InformationProductDTO;
import Model.*;
import Utils.CSRFUltils;
import Utils.SingletonServiceUltils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/admin/addproduct")
public class AdminAddProductController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        try{
            List<CategoriesEntity> categoriesEntityList = SingletonServiceUltils.getCategoryDAOImpl().getAll();
            List<TagsEntity> tagsEntityList = SingletonServiceUltils.getTagDAOImpl().getAll();
            List<ColorsEntity> colorsEntityList = SingletonServiceUltils.getColorDAOImpl().getAll();

            req.setAttribute("listcolor",colorsEntityList);
            req.setAttribute("listtag",tagsEntityList);
            req.setAttribute("listcate",categoriesEntityList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/Admin/AddProduct.jsp");
            dispatcher.forward(req,resp);
            return;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CSRFUltils.doAction(req,resp);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String message = "Add successfully";
        try{
            String productImage= req.getParameter("urlimage");
            String productName = req.getParameter("name");
            String productDescription = req.getParameter("description");
            String productCategory = req.getParameter("category");
            String productPrice = req.getParameter("price");
            String productDiscount = req.getParameter("discount");
            String productQuantity = req.getParameter("quantity");
            String productTag = req.getParameter("tag");
            String[] productColors = req.getParameterValues("colors");
            String productWeight = req.getParameter("weight");
            String productDimension = req.getParameter("dimensions");
            String productMaterial = req.getParameter("material");
            String productOrtherinfo = req.getParameter("ortherinfo");
            LocalDateTime date =  LocalDateTime.now();
            if (productColors != null) {
                for(String item: productColors){
                    System.out.println(item);
                }
            }

            if (SingletonServiceUltils.getProductDAOImpl().getProductIdByName(productName) != -1) {
                message = "product name is existing";
                this.DirectEror(message, false, req, resp);
                return;
            }

            CategoriesEntity cate = SingletonServiceUltils.getCategoryDAOImpl().findById(Integer.parseInt(productCategory));
            InformationProductDTO info = new InformationProductDTO(productWeight,productDimension,productMaterial,productOrtherinfo);
            ProductsEntity productsEntity = new ProductsEntity();
            productsEntity.setName(productName);
            productsEntity.setImage(productImage);
            productsEntity.setDescription(productDescription);
            productsEntity.setCategoriesEntity(cate);
            productsEntity.setRegularPrice(BigDecimal.valueOf(Integer.parseInt(productPrice)));
            productsEntity.setDiscount_percent(Integer.parseInt(productDiscount));
            productsEntity.setQuantity(Integer.parseInt(productQuantity));
            productsEntity.setInformation(info.toString());
            Set<TagsEntity> tagsEntityList = new HashSet<>();
            TagsEntity tag = SingletonServiceUltils.getTagDAOImpl().findById(Integer.parseInt(productTag));
            tagsEntityList.add(tag);
            productsEntity.setTagsEntities(tagsEntityList);
            Set<ColorsEntity> colorsEntitySet = new HashSet<>();
            if (productColors != null) {
                for (String item : productColors) {
                    ColorsEntity colors = SingletonServiceUltils.getColorDAOImpl().findById(Integer.parseInt(item));
                    colorsEntitySet.add(colors);
                }
            }
            productsEntity.setColorsEntities(colorsEntitySet);


            if(Integer.parseInt(productDiscount) != 0){
                ProductStatusesEntity statuses = SingletonServiceUltils.getProductStatusDAOImpl().getByBoolean(true);
                productsEntity.setProductStatusesEntity(statuses);
                double tempPriceDiscount = Integer.parseInt(productsEntity.getRegularPrice().toString()) -(Integer.parseInt(productsEntity.getRegularPrice().toString()) * productsEntity.getDiscount_percent() /100);
                BigDecimal PriceDiscount = BigDecimal.valueOf(tempPriceDiscount);
                productsEntity.setDiscountPrice(PriceDiscount);
            }
            else
            {
                ProductStatusesEntity statuses = SingletonServiceUltils.getProductStatusDAOImpl().getByBoolean(false);
                productsEntity.setProductStatusesEntity(statuses);
                double tempPriceDiscount = Integer.parseInt(productsEntity.getRegularPrice().toString());
                BigDecimal PriceDiscount = BigDecimal.valueOf(tempPriceDiscount);
                productsEntity.setDiscountPrice(PriceDiscount);
            }
            productsEntity.setPublish(true);
            productsEntity.setAdddate(Date.from(date.atZone(ZoneId.systemDefault()).toInstant()));
            SingletonServiceUltils.getProductDAOImpl().insert(productsEntity);
            DirectEror(message, true, req, resp);
            return;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void DirectEror(String Message,boolean isSuccess,HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        List<CategoriesEntity> categoriesEntityList = SingletonServiceUltils.getCategoryDAOImpl().getAll();
        List<TagsEntity> tagsEntityList = SingletonServiceUltils.getTagDAOImpl().getAll();
        List<ColorsEntity> colorsEntityList = SingletonServiceUltils.getColorDAOImpl().getAll();

        req.setAttribute("Message", Message);
        req.setAttribute("isSuccess", isSuccess);
        req.setAttribute("listcolor",colorsEntityList);
        req.setAttribute("listtag",tagsEntityList);
        req.setAttribute("listcate",categoriesEntityList);
        RequestDispatcher dispatcher //
                = req.getServletContext().getRequestDispatcher("/Admin/AddProduct.jsp");
        dispatcher.forward(req, resp);
        return;
    }
}
