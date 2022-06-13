package DTO.JSON;

public class CustomerJSON {
    int Id = -1;
    String Name = "None";
    String Image = "None";
    String mail = "None";
    String Location = "None";
    int Orders = 0;
    String Register = "01.01.2001";
    String Status = "InActive";
    public CustomerJSON() {
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getOrders() {
        return Orders;
    }

    public void setOrders(int orders) {
        Orders = orders;
    }

    public String getRegister() {
        return Register;
    }

    public void setRegister(String register) {
        Register = register;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

}
