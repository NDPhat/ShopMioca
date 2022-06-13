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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static Constant.WebConstant.IMAGE_NULL_URL;

@WebServlet("/admin/editproduct")
public class AdminEditProductController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("Id");
        System.out.println("hello"+id);
        try{
            int idProduct = Integer.parseInt(id);
            ProductsEntity productsEntity = SingletonServiceUltils.getProductDAOImpl().getProductbyID(idProduct);
            List<CategoriesEntity> categoriesEntityList = SingletonServiceUltils.getCategoryDAOImpl().getAll();
            List<TagsEntity> tagsEntityList = SingletonServiceUltils.getTagDAOImpl().getAll();
            List<ColorsEntity> colorsEntityList = SingletonServiceUltils.getColorDAOImpl().getAll();
            if(productsEntity != null){
                InformationProductDTO info = null;
                if(productsEntity.getImage() == null)
                {
                    productsEntity.setImage(IMAGE_NULL_URL);

                }
                info = new InformationProductDTO(productsEntity.getInformation());
                TagsEntity tagsEntity = productsEntity.getTagsEntities().stream().collect(Collectors.toList()).get(0);
                List<ColorsEntity> colorsEntitySet = productsEntity.getColorsEntities().stream().collect(Collectors.toList());
                req.setAttribute("listcolor",colorsEntityList);
                req.setAttribute("tag",tagsEntity);
                req.setAttribute("colorproduct",colorsEntitySet);
                req.setAttribute("listtag",tagsEntityList);
                req.setAttribute("listcate",categoriesEntityList);
                req.setAttribute("product",productsEntity);
                req.setAttribute("info",info);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/Admin/EditProduct.jsp");
                dispatcher.forward(req,resp);
                return;
            }
            else {
                resp.sendRedirect("/404");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        resp.sendRedirect("/404");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CSRFUltils.doAction(req,resp);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("idproduct");
        int idProduct = Integer.parseInt(id);
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

            if (productColors != null) {
                for(String item: productColors){
                    System.out.println(item);
                }
            }

            CategoriesEntity cate = SingletonServiceUltils.getCategoryDAOImpl().findById(Integer.parseInt(productCategory));
            InformationProductDTO info = new InformationProductDTO(productWeight,productDimension,productMaterial,productOrtherinfo);
            ProductsEntity productsEntity = SingletonServiceUltils.getProductDAOImpl().getProductbyID(idProduct);
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

            productsEntity.setPublish(false);
            SingletonServiceUltils.getProductDAOImpl().update(productsEntity);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        resp.sendRedirect("/admin/editproduct?Id="+idProduct);
    }
}
