package Controller;

import DAO.ProductDAOImpl;
import DAO.ReviewDAOImpl;
import DTO.UserAccountDTO;
import Model.ProductsEntity;
import Model.ReviewsEntity;
import Model.UsersEntity;
import Utils.CSRFUltils;
import Utils.SingletonServiceUltils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ReviewController", value = "/ReviewController")
public class ReviewController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!CSRFUltils.doAction(request,response)){
            return;
        }
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String URL = "/singleproduct?productId=";

        HttpSession session = request.getSession();
        UserAccountDTO userAccountDTO = (UserAccountDTO)session.getAttribute("loginedUser");
        String productIdString = request.getParameter("productId");
        String ratingString = request.getParameter("rate");
        String commentString = request.getParameter("review");

        int rating = Integer.parseInt(ratingString);
        int productId = Integer.parseInt(productIdString);

        UsersEntity usersEntity = SingletonServiceUltils.getUserDAOImpl().getOneById(userAccountDTO.getId());
        ProductsEntity productsEntity = SingletonServiceUltils.getProductDAOImpl().getProductbyID(productId);

        ReviewsEntity reviewsEntity = new ReviewsEntity();
        reviewsEntity.setComment(commentString);
        reviewsEntity.setUsersEntity(usersEntity);
        reviewsEntity.setProductsEntity(productsEntity);
        reviewsEntity.setRating(rating);

        try {
            SingletonServiceUltils.getReviewDAOImpl().insert(reviewsEntity);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        URL += productId;
        response.sendRedirect(URL);
    }
}
