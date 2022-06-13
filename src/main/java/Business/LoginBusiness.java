package Business;

import DAO.AccountDAOImpl;
import DAO.DAOImpl.AccountDAO;
import DTO.UserAccountDTO;
import Model.AccountsEntity;
import Model.RolesEntity;
import Model.UsersEntity;
import Utils.SingletonServiceUltils;

public class LoginBusiness {
    public static UserAccountDTO getUserLogin(String username, String password){
        AccountsEntity acc =  SingletonServiceUltils.getAccountDAOImpl().Login(username, password);
        if(acc == null )
            return null;
        UsersEntity user =  acc.getUsersEntity();
        RolesEntity role = acc.getRolesEntity();
        return new UserAccountDTO(user,role);
    }

    public static boolean checkActive(String username)
    {
        AccountsEntity acc = SingletonServiceUltils.getAccountDAOImpl().getOneByUsername(username);
        if(acc == null)
            return false;
        else
            return acc.getUsersEntity().getActive();
    }

    public static boolean checkBanned(String username)
    {
        AccountsEntity acc = SingletonServiceUltils.getAccountDAOImpl().getOneByUsername(username);
        if(acc == null || acc.getUsersEntity().getBanned() == null)
            return false;
        else
            return acc.getUsersEntity().getBanned();
    }
}
