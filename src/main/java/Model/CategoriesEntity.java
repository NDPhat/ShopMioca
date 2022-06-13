package Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "\"categories\"", schema = "public")
public class CategoriesEntity {
    private int id;
    private String name;
    private String image;
    private Set<ProductsEntity> productsEntities;

    //one to many category-->product
    @OneToMany(mappedBy = "categoriesEntity",fetch = FetchType.LAZY)
    public Set<ProductsEntity> getProductsEntities(){
        return this.productsEntities;
    }
    public void setProductsEntities(Set<ProductsEntity> productsEntities){
        this.productsEntities= productsEntities;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "image", nullable = false, length = 255)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoriesEntity that = (CategoriesEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return image != null ? image.equals(that.image) : that.image == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
