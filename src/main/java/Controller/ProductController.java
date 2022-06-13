package Controller;

import Business.TopLimitProductBusiness;
import DTO.*;
import Model.ColorsEntity;
import Model.ProductsEntity;
import Model.ReviewsEntity;
import Model.UsersEntity;
import Utils.SingletonServiceUltils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/singleproduct")
public class ProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        resp.setContentType("text/htm");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String productIdString= req.getParameter("productId");
        int productId = Integer.parseInt(productIdString);

        ProductsEntity productsEntity = SingletonServiceUltils.getProductDAOImpl().getProductbyID(productId);
        SingleProductDTO singleProductDTO = this.handleSingleProductDTO(productsEntity);

        int categoryId = productsEntity.getCategoriesEntity().getId();

        req.setAttribute("productId", productId);
        req.setAttribute("singleProductDTO", singleProductDTO);
        req.setAttribute("Top8Product_categories", TopLimitProductBusiness.handleDataTopLimitProducts_productDisplayApiDTO(SingletonServiceUltils.getProductDAOImpl().getTopLimitProductByCategorytID_Except(categoryId, productId, 8)));
        req.setAttribute("MaxInc", singleProductDTO.getQuantity());

        RequestDispatcher rd= req.getRequestDispatcher("/single-product.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private List<ColorDTO> getColorDTOList(ProductsEntity productsEntity) {
        List<ColorDTO> colorDTOList = new ArrayList<>();
        for (ColorsEntity item: SingletonServiceUltils.getColorDAOImpl().getAllColorsByProductId(productsEntity.getId())) {
            ColorDTO colorDTO = new ColorDTO(item.getId(), item.getName());
            colorDTOList.add(colorDTO);
        }
        return colorDTOList;
    }

    private List<ReviewOfUserDTO> getReviewOfUserDTOList(ProductsEntity productsEntity) {
        List<ReviewOfUserDTO> reviewOfUserDTOList = new ArrayList<>();
        for (ReviewsEntity item:SingletonServiceUltils.getReviewDAOImpl().getAllbyProductId(productsEntity.getId())) {
            UsersEntity usersEntity = SingletonServiceUltils.getUserDAOImpl().getOneByReviewId(item.getId());
            String fullname = usersEntity.getFirstName() + " " + usersEntity.getLastName();
            String image = usersEntity.getImage();
            int id = usersEntity.getId();
            ReviewOfUserDTO reviewOfUserDTO = new ReviewOfUserDTO(id,
                    item.getRating(), item.getComment(), fullname, image);
            reviewOfUserDTOList.add(reviewOfUserDTO);
        }
        return  reviewOfUserDTOList;
    }

    private SingleProductDTO handleSingleProductDTO(ProductsEntity productsEntity) {
        SingleProductDTO singleProductDTO;
        int id = productsEntity.getId();
        String name = productsEntity.getName();
        String description = productsEntity.getDescription();
        BigDecimal regularPrice = productsEntity.getRegularPrice();
        BigDecimal discountPrice = productsEntity.getDiscountPrice();
        int quantity = productsEntity.getQuantity();
        String image = productsEntity.getImage();
        int discount_percent = productsEntity.getDiscount_percent();

        String informationProduct = productsEntity.getInformation();
        InformationProductDTO informationProductDTO = new InformationProductDTO(informationProduct);

        boolean status = productsEntity.getProductStatusesEntity().isId();
        int categoryId = productsEntity.getCategoriesEntity().getId();
        String categoryName = productsEntity.getCategoriesEntity().getName();
        int avgReview = SingletonServiceUltils.getReviewDAOImpl().getAVGRatingbyProductId(id);
        List<ColorDTO> colorDTOList = this.getColorDTOList(productsEntity);
        List<ReviewOfUserDTO> reviewOfUserDTOList = this.getReviewOfUserDTOList(productsEntity);
        int totalReviews = SingletonServiceUltils.getReviewDAOImpl().getAllbyProductId(id).size();

        singleProductDTO = new SingleProductDTO(id, name, description, regularPrice, discountPrice, quantity,
                image, discount_percent, informationProductDTO, status, categoryId, categoryName, colorDTOList, reviewOfUserDTOList, avgReview, totalReviews);

        return singleProductDTO;
    }
}
