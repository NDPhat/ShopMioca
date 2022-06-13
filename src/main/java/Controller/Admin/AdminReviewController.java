package Controller.Admin;

import Model.ReviewsEntity;
import Utils.CSRFUltils;
import Utils.SingletonServiceUltils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/review")
public class AdminReviewController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Admin/Review.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CSRFUltils.doAction(req,resp);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String id =   req.getParameter("Id");
        if(id != null){
            int idreview = Integer.parseInt(id);
            ReviewsEntity reviewsEntity = SingletonServiceUltils.getReviewDAOImpl().getOneById(idreview);
            if(reviewsEntity != null)
            {
                SingletonServiceUltils.getReviewDAOImpl().delete(reviewsEntity);
            }
        }
        return;
    }
}
