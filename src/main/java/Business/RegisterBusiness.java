package Business;

import DAO.RoleDAOImpl;
import Model.AccountsEntity;
import Model.RolesEntity;
import Model.UsersEntity;
import Utils.MailUtils;
import Utils.SingletonServiceUltils;
import Utils.TokenUltils;

public class RegisterBusiness {
    public static boolean regiterUser(String username, String password,String email,int roleid){

        RolesEntity role = SingletonServiceUltils.getRoleDAOImpl().getOneByID(roleid);

        if(role == null)
        {
            role = new RolesEntity();
            role.setName("user");
            SingletonServiceUltils.getRoleDAOImpl().insert(role);
        }

        AccountsEntity acc = new AccountsEntity();
        UsersEntity user = new UsersEntity();

        acc.setUsername(username);
        acc.setPassword(password);
        acc.setRolesEntity(role);

        user.setEmail(email);
        user.setActive(false);
        user.setAccountsEntity(acc);
        user.setBanned(false);
        AccountsEntity account = (AccountsEntity) SingletonServiceUltils.getAccountDAOImpl().insert(acc);

        if(account != null)
        {
            UsersEntity usersEntity = (UsersEntity) SingletonServiceUltils.getUserDAOImpl().insert(user);
            if(usersEntity != null){
                MailUtils mailUtils = new MailUtils(email);
                mailUtils.sendMaiRegister();
                return  true;
            }
            else {
                SingletonServiceUltils.getAccountDAOImpl().delete(account);
                return false;
            }
        }
        return false;
    }

    public static boolean Active(String token){
        String email = TokenUltils.getPlanText(token);
        UsersEntity user = SingletonServiceUltils.getUserDAOImpl().getOneByEmail(email);
        if(user == null)
            return false;
        user.setActive(true);
        SingletonServiceUltils.getUserDAOImpl().update(user);
        return true;
    }
}
