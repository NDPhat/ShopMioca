package Business.Admin;

import DTO.AddressDTO;
import DTO.JSON.CustomerJSON;
import Model.UsersEntity;
import Utils.SingletonServiceUltils;

import java.util.ArrayList;
import java.util.List;

import static Constant.WebConstant.IMAGE_NULL_URL;

public class CustomerJSONBusiness {
    public static List<CustomerJSON> getListCustomer(){
        List <CustomerJSON> customerJSONList = new ArrayList<>();
        List<UsersEntity> entityList = SingletonServiceUltils.getUserDAOImpl().getAll();
        for(UsersEntity user : entityList){
            CustomerJSON temp_user = getCustomer(user);
            customerJSONList.add(temp_user);
        }
        customerJSONList.sort((CustomerJSON a,CustomerJSON b)-> a.getId() - b.getId());
        return customerJSONList;
    }
    public static CustomerJSON getCustomer(UsersEntity user){
        if(user != null)
        {

            String address = "none";
            CustomerJSON userJSON = new CustomerJSON();
            String name = user.getFirstName() + " "+ user.getLastName();
            name = name.replace("null","");
            String Image = IMAGE_NULL_URL;
            String active = "Live";

            int Oders = SingletonServiceUltils.getCcTransactionDAOImpl().getAllbyUserIdandStatus(user.getId()).size();

            if(name.equals(" "))
                name ="none";
            if(user.getBanned()!=null && user.getBanned() == true)
                active = "Banned";
            if(user.getAddress() != null)
            {
                AddressDTO addressDTO = new AddressDTO(user.getAddress());
                address = addressDTO.getDistrict() +", "+addressDTO.getProvince();

            }
            if(user.getImage()!=null)
                Image= user.getImage();

            userJSON.setLocation(address);
            userJSON.setImage(Image);
            userJSON.setId(user.getId());
            userJSON.setName(name);
            userJSON.setStatus(active);
            userJSON.setMail(user.getEmail());
            userJSON.setOrders(Oders);
            return userJSON;
        }
        return null;
    }

}
