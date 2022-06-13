package DTO;

public class InformationProductDTO {
    private String Weight = "No information";
    private String Dimensions = "No information";
    private String Materials = "No information";
    private String OtherInfo = "No information";

    public InformationProductDTO(String informationProduct) {
        String[] parts = informationProduct.split("\\|");
        int lengthparts = parts.length;
        if(parts != null)
        {
            if(lengthparts > 0)
                this.Weight = parts[0];
            if(lengthparts > 1)
                this.Dimensions = parts[1];
            if(lengthparts > 2)
                this.Materials = parts[2];
            if(lengthparts > 3)
                this.OtherInfo = parts[3];
        }
    }

    public InformationProductDTO() {
    }

    public InformationProductDTO(String weight, String dimensions, String materials, String otherInfo) {
        Weight = weight;
        Dimensions = dimensions;
        Materials = materials;
        OtherInfo = otherInfo;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getDimensions() {
        return Dimensions;
    }

    public void setDimensions(String dimensions) {
        Dimensions = dimensions;
    }

    public String getMaterials() {
        return Materials;
    }

    public void setMaterials(String materials) {
        Materials = materials;
    }

    public String getOtherInfo() {
        return OtherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        OtherInfo = otherInfo;
    }

    @Override
    public String toString() {
        return this.Weight+"|"+this.Dimensions+"|"+this.Materials+"|"+this.OtherInfo+"|";
    }
}
