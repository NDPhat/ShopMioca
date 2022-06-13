package Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static Constant.WebConstant.ROLE_ADMIN;
import static Constant.WebConstant.ROLE_USER;

public class SecurityConfig {



    private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();

    static {
        init();
    }
    private static void init() {
        // Cấu hình cho vai trò "user".
        List<String> urlPatterns1 = new ArrayList<String>();
        urlPatterns1.add("/CheckOutController");
        urlPatterns1.add("/myaccount");
        mapConfig.put(ROLE_USER, urlPatterns1);

        // Cấu hình cho vai trò "admin".
        List<String> urlPatterns2 = new ArrayList<String>();
        urlPatterns2.add("/admin");
        urlPatterns2.add("/admin/addproduct");
        urlPatterns2.add("/admin/categories");
        urlPatterns2.add("/admin/createaccount");
        urlPatterns2.add("/admin/customeraccount");
        urlPatterns2.add("/admin/customer");
        urlPatterns2.add("/admin/editproduct");
        urlPatterns2.add("/admin/order");
        urlPatterns2.add("/admin/product");
        urlPatterns2.add("/admin/review");

        mapConfig.put(ROLE_ADMIN, urlPatterns2);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }

}