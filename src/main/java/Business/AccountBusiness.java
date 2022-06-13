package Business;

import DTO.AddressDTO;
import Model.AccountsEntity;
import Model.UsersEntity;
import Utils.MailUtils;
import Utils.SingletonServiceUltils;
import Utils.TokenUltils;

public class AccountBusiness {
    public static AddressDTO getaddressDTO(int id){
        UsersEntity user = SingletonServiceUltils.getUserDAOImpl().getOneById(id);
        if(user == null)
            return null;
        else{
            AddressDTO address = new AddressDTO(user.getAddress());
            return address;
        }
    }

    public static boolean UpdateInfo(String firstname,String lastname ,int id,String phone,String img,AddressDTO addressDTO)
    {
        UsersEntity users =(UsersEntity) SingletonServiceUltils.getUserDAOImpl().getOneById(id);
        String address = addressDTO.toString();
        users.setFirstName(firstname);
        users.setLastName(lastname);
        users.setAddress(address);
        users.setPhone(phone);
        users.setImage(img);
        UsersEntity userUpdate = SingletonServiceUltils.getUserDAOImpl().update(users);
        return userUpdate!=null;
    }
    public static boolean UpdateInfoVer2(String firstname,String lastname,String phone ,int id,AddressDTO addressDTO)
    {
        UsersEntity users =(UsersEntity) SingletonServiceUltils.getUserDAOImpl().getOneById(id);
        String address = addressDTO.toString();
        users.setFirstName(firstname);
        users.setPhone(phone);
        users.setLastName(lastname);
        users.setAddress(address);
        UsersEntity userUpdate = SingletonServiceUltils.getUserDAOImpl().update(users);
        return userUpdate!=null;
    }
    public static AccountsEntity CheckPassword(int id, String old_pass)
    {
        UsersEntity users = SingletonServiceUltils.getUserDAOImpl().getOneById(id);
        AccountsEntity acc = users.getAccountsEntity();
        if(old_pass.equals(acc.getPassword()))
            return acc;
        return null;
    }
    public static void ForgetPassword(String email, String newpass)
    {
        MailUtils mail = new MailUtils(email);
        mail.sendMaiForget(newpass);
    }
    public static boolean ChangePasswordForForget(String tokenemail, String tokennewpass)
    {
        String email = TokenUltils.getPlanText(tokenemail);
        String pass = TokenUltils.getPlanText(tokennewpass);
        if(email == null || pass == null)
            return false;
        UsersEntity user = SingletonServiceUltils.getUserDAOImpl().getOneByEmail(email);
        if(user == null)
            return false;

        AccountsEntity acc = user.getAccountsEntity();
        acc.setPassword(pass);

        if(SingletonServiceUltils.getAccountDAOImpl().update(acc) == null)
            return false;
        return true;
    }
}
