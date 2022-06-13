package Business;

import DTO.ProductDisplayApiDTO;
import DTO.ProductDisplayDTO;
import Model.ColorsEntity;
import Model.ProductsEntity;
import Model.TagsEntity;
import Utils.SingletonServiceUltils;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TopLimitProductBusiness {
    public static List<ProductDisplayApiDTO> handleDataTopLimitProducts_productDisplayApiDTO(List<ProductsEntity> topLimitProductsInTopLimit) {
        List<ProductsEntity> productsEntityList = topLimitProductsInTopLimit;
        List<ProductDisplayApiDTO> productDisplayApiDTOList = new ArrayList<>();
        for (ProductsEntity item:productsEntityList) {
            ProductDisplayApiDTO productDisplayDTO = TopLimitProductBusiness.handleDataOneProduct_productDisplayApiDTO(item.getId());
            productDisplayApiDTOList.add(productDisplayDTO);
        }
        return productDisplayApiDTOList;
    }

    public static ProductDisplayApiDTO handleDataOneProduct_productDisplayApiDTO(int productId) {
        ProductDisplayApiDTO productDisplayModalDTO;

        ProductsEntity productsEntity = SingletonServiceUltils.getProductDAOImpl().getProductbyID(productId);
        String name = productsEntity.getName();
        String description = productsEntity.getDescription();
        BigDecimal regularPrice = productsEntity.getRegularPrice();
        BigDecimal discountPrice = productsEntity.getDiscountPrice();
        Integer quantity = productsEntity.getQuantity();
        String image = productsEntity.getImage();
        Integer discout_percent = productsEntity.getDiscount_percent();
        String information = productsEntity.getInformation();

        Set<String> tagsName = new HashSet<>();
        for (TagsEntity item:SingletonServiceUltils.getTagDAOImpl().getAllTagsByProductId(productId)) {
            tagsName.add(item.getName());
        }

        Boolean status = productsEntity.getProductStatusesEntity().isId();
        String category = productsEntity.getCategoriesEntity().getName();

        Set<String> colorsName = new HashSet<>();
        List<Integer> colorsId = new ArrayList<>();
        for (ColorsEntity item:SingletonServiceUltils.getColorDAOImpl().getAllColorsByProductId(productId)) {
            colorsName.add(item.getName());
            colorsId.add(item.getId());
        }

        int totalReviews = SingletonServiceUltils.getReviewDAOImpl().getAllbyProductId(productId).size();
        int avgReview = SingletonServiceUltils.getReviewDAOImpl().getAVGRatingbyProductId(productId);

        int categoryId = productsEntity.getCategoriesEntity().getId();

        productDisplayModalDTO = new ProductDisplayApiDTO(productId, name, description, regularPrice, discountPrice, quantity, image,
                discout_percent, information, tagsName, status, category, categoryId, colorsName, totalReviews, avgReview, colorsId);

        return  productDisplayModalDTO;
    }

    public static List<ProductDisplayDTO> handleDataTopLimitProductsTab(List<ProductsEntity> topLimitProductsInTopLimitCategories) {
        List<ProductsEntity> productsEntityList = topLimitProductsInTopLimitCategories;
        List<ProductDisplayDTO> productDisplayDTOList = new ArrayList<>();
        for (ProductsEntity item:productsEntityList) {
            ProductDisplayDTO productDisplayDTO = TopLimitProductBusiness.handleDataOneProductTab(item);
            productDisplayDTOList.add(productDisplayDTO);
        }
        return productDisplayDTOList;
    }

    public static ProductDisplayDTO handleDataOneProductTab(ProductsEntity productsEntity) {
        ProductDisplayDTO productDisplayDTO;
        int id = productsEntity.getId();
        String name = productsEntity.getName();
        BigDecimal regularPrice = productsEntity.getRegularPrice();
        BigDecimal discountPrice = productsEntity.getDiscountPrice();
        String image = productsEntity.getImage();
        int discount_percent = productsEntity.getDiscount_percent();
        boolean status = productsEntity.getProductStatusesEntity().isId();
        int avgReview = SingletonServiceUltils.getReviewDAOImpl().getAVGRatingbyProductId(productsEntity.getId());
        String information = productsEntity.getDescription();
        List<String> tagsName = new ArrayList<>();
        for (TagsEntity item:SingletonServiceUltils.getTagDAOImpl().getAllTagsByProductId(id)) {
            tagsName.add(item.getName());
        }
        int quantity = productsEntity.getQuantity();

        productDisplayDTO = new ProductDisplayDTO(id, name, regularPrice, discountPrice, image,
                discount_percent, tagsName, status, avgReview, information, quantity);

        return productDisplayDTO;
    }
}
