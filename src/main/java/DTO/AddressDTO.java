package DTO;

public class AddressDTO {
    private String Number = "No info";
    private String Street = "No info";
    private String Commune = "No info";
    private String District = "No info";
    private String Province = "No info";

    public AddressDTO() {
    }

    public AddressDTO(String number, String street, String commune, String district, String province) {
        this.Number = number;
        this.Street = street;
        this.Commune = commune;
        this.District = district;
        this.Province = province;
    }

    public AddressDTO(String address) {
        if(address != null){
            String[] parts = address.split("\\|");
            int lengthparts = parts.length;
            if(parts != null)
            {
                if(lengthparts > 0)
                    this.Number = parts[0];
                if(lengthparts > 1)
                    this.Street = parts[1];
                if(lengthparts > 2)
                    this.Commune = parts[2];
                if(lengthparts > 3)
                    this.District = parts[3];
                if(lengthparts > 4)
                    this.Province = parts[4];
            }
        }
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getCommune() {
        return Commune;
    }

    public void setCommune(String commune) {
        Commune = commune;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    @Override
    public String toString(){
        return this.Number+"|"+this.Street+"|"+this.Commune+"|"+this.District+"|"+this.Province+"|";
    }
}

