package Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "\"tags\"", schema = "public")
public class TagsEntity {
    private int id;
    private String name;
    private  Set<ProductsEntity> productsEntities;


    //One to many tag -->Product_tag

    @ManyToMany()
    @JoinTable(name = "product_tags",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    public Set<ProductsEntity> getProductsEntities(){
        return this.productsEntities;
    }
    public void setProductsEntities(Set<ProductsEntity> productsEntities){
        this.productsEntities= productsEntities;
    }


    //many to many tag--product


    @Id
    @Column(name = "id", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagsEntity that = (TagsEntity) o;

        if (id != that.id) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
