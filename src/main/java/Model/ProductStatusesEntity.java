package Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "\"product_statuses\"", schema = "public")
public class ProductStatusesEntity {
    private boolean id;
    private String name;
    private Set<ProductsEntity> productsEntities;
    //one to many productstatus-->product
    @OneToMany(mappedBy = "productStatusesEntity",fetch = FetchType.LAZY)

    public Set<ProductsEntity> getProductsEntities(){
        return this.productsEntities;
    }
    public void setProductsEntities(Set<ProductsEntity> productEntities){
        this.productsEntities = productEntities;
    }



    @Id
    @Column(name = "id", nullable = false)
    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductStatusesEntity that = (ProductStatusesEntity) o;

        if (id != that.id) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = (id ? 1 : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
