package Business.Admin;

import DTO.JSON.ProductJSON;
import Model.ProductsEntity;
import Utils.SingletonServiceUltils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductJSONBusiness {
    public static List<ProductJSON> getListProduct(){
        List <ProductJSON> productJSONList = new ArrayList<>();
        List<ProductsEntity> entityList = SingletonServiceUltils.getProductDAOImpl().getAll();
        for(ProductsEntity product : entityList){
            ProductJSON temp_product = getProduct(product);
            productJSONList.add(temp_product);
        }
        productJSONList.sort((ProductJSON a,ProductJSON b)->a.getId()- b.getId());
        return productJSONList;
    }
    public static ProductJSON getProduct(ProductsEntity product){
        ProductJSON productJSON = new ProductJSON();
        if(product != null) {

            if((product != null))
            {
                productJSON.setId(product.getId());
                productJSON.setName(product.getName() != null ? product.getName() : "none");
                productJSON.setCategory(product.getCategoriesEntity().getName() != null ? product.getCategoriesEntity().getName() : "none");
                productJSON.setPrice(product.getRegularPrice() != null ? product.getRegularPrice() : BigDecimal.valueOf(0));
                productJSON.setDate(product.getAdddate() != null ? new SimpleDateFormat("dd MMM yyyy hh:mm:ss").format(product.getAdddate()) : "01.01.2021");
                productJSON.setStatus(product.getPublish()== null || product.getPublish()== false  ? "Hidden" : "Publish");
            }

        }
        return productJSON;
    }
}
