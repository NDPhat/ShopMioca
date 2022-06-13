package DTO.JSON;

public class CategoryJSON {
    int Id = -1;
    String Name = "None";
    String Image = "None";
    public CategoryJSON() {
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


}
