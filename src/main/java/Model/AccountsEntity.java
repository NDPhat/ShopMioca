package Model;

import org.hibernate.annotations.Generated;

import javax.persistence.*;

@Entity
@Table(name = "\"accounts\"", schema = "public")
public class AccountsEntity {
    private int id;
    private String username;
    private String password;
    private RolesEntity rolesEntity;
    private  UsersEntity usersEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    public RolesEntity getRolesEntity() {
        return rolesEntity;
    }
    public void setRolesEntity(RolesEntity role) {
        this.rolesEntity= role;
    }

    @OneToOne(mappedBy = "accountsEntity",fetch = FetchType.EAGER)
    public UsersEntity getUsersEntity() {
        return usersEntity;
    }
    public void setUsersEntity(UsersEntity usersEntity) {
        this.usersEntity = usersEntity;
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
    @Column(name = "username", nullable = false, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountsEntity that = (AccountsEntity) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        return password != null ? password.equals(that.password) : that.password == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }


}
