package DTO;

import Config.SecurityConfig;
import Model.AccountsEntity;
import Model.RolesEntity;
import Model.UsersEntity;

import java.util.*;

import static Constant.WebConstant.ROLE_ADMIN;
import static Constant.WebConstant.ROLE_USER;

public class UserAccountDTO {
    private int id;
    private String email;
    private String lastname;
    private String firstname;
    private Set<String> roles;
    private  AddressDTO address;
    private  String phone;
    private String image;


    public UserAccountDTO() {
    }


    public UserAccountDTO(int id, String email, String lasname, String firstname, Set<String> roles) {
        this.id = id;
        this.email = email;
        this.lastname = lasname;
        this.firstname = firstname;
        this.roles = new HashSet<String>();
        for(String role : roles){
            this.roles.add(role);
        }
    }
    public UserAccountDTO(int id, String email, String lastname, String firstname, Set<String> roles, AddressDTO address, String phone) {
        this.id = id;
        this.email = email;
        this.lastname = lastname;
        this.firstname = firstname;
        this.roles = roles;
        this.address = address;
        this.phone = phone;
    }

    public UserAccountDTO(UsersEntity user, RolesEntity role) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstname = user.getFirstName();
        this.lastname = user.getLastName();
        this.image = user.getImage();
        this.phone = user.getPhone();
        this.roles = new HashSet<String>();
        if(role != null)
        {
            this.roles.add(ROLE_USER);
            if(role.getName().equals("admin"))
                this.roles.add(ROLE_ADMIN);
        }

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lasname) {
        this.lastname = lasname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

}
