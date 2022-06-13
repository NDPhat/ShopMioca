package Business.Admin;

import DTO.JSON.ProductJSON;
import DTO.JSON.ReviewJSON;
import Model.ReviewsEntity;
import Utils.SingletonServiceUltils;

import java.util.ArrayList;
import java.util.List;

public class ReviewJSONBussiness {
    public static List<ReviewJSON> getListReview(){
        List <ReviewJSON> reviewJSONList = new ArrayList<>();
        List<ReviewsEntity> entityList = SingletonServiceUltils.getReviewDAOImpl().getAll();
        for(ReviewsEntity review : entityList){
            ReviewJSON temp_review = getReview(review);
            reviewJSONList.add(temp_review);
        }
        reviewJSONList.sort((ReviewJSON a, ReviewJSON b)->a.getId()- b.getId());
        return reviewJSONList;
    }
    public static ReviewJSON getReview(ReviewsEntity review){
        ReviewJSON reviewJSON = new ReviewJSON();

        if(review != null) {
            String productName = "None";
            String Author = "None";
            if(review.getProductsEntity() != null){
                productName = review.getProductsEntity().getName();
            }

            if(review.getUsersEntity() != null){
                Author = review.getUsersEntity().getFirstName();
            }
            reviewJSON.setId(review.getId());
            reviewJSON.setProductName(productName);
            reviewJSON.setAuthor(Author);
            reviewJSON.setReview(review.getComment());
            reviewJSON.setRating(review.getRating());
        }
        return reviewJSON;
    }
}
