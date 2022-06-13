package Model;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name = "\"roles\"", schema = "public")
public class RolesEntity {
    private int id;
    private String name;
    private Set<AccountsEntity> accountsEntities;


    @OneToMany(mappedBy = "rolesEntity",fetch = FetchType.LAZY)
    public Set<AccountsEntity> getAccounts(){
        return this.accountsEntities;
    }
    public void setAccounts(Set<AccountsEntity> accounts){
        this.accountsEntities = accounts;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

        RolesEntity that = (RolesEntity) o;

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
