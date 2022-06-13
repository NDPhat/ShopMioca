package Business.Admin;

import DTO.JSON.CategoryJSON;
import Model.CategoriesEntity;
import Utils.SingletonServiceUltils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static Constant.WebConstant.IMAGE_NULL_URL;

public class CategoryJSONBusiness {
    public static List<CategoryJSON> getListCategory(){
        List <CategoryJSON> CategoryJSONList = new ArrayList<>();
        List<CategoriesEntity> entityList = SingletonServiceUltils.getCategoryDAOImpl().getAll();
        for(CategoriesEntity cate: entityList){
            CategoryJSON temp_cate = getCategory(cate);
            CategoryJSONList.add(temp_cate);
        }
        CategoryJSONList.sort((CategoryJSON a,CategoryJSON b)->a.getId() -b.getId());
        return CategoryJSONList;
    }
    public static CategoryJSON getCategory(CategoriesEntity cate){
        if(cate != null)
        {
            CategoryJSON cateJSON = new CategoryJSON();
            String name = cate.getName();
            name = name.replace("null","");
            String Image = IMAGE_NULL_URL;

            if(cate.getImage()!=null)
                Image= cate.getImage();
            cateJSON.setImage(Image);
            cateJSON.setId(cate.getId());
            cateJSON.setName(name);
            return cateJSON;
        }
        return null;
    }

}
