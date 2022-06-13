package Utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DTO.UserAccountDTO;

public class ApplicationUtils {

    private static int REDIRECT_ID = 0;

    private static final Map<Integer, String> id_uri_map = new HashMap<Integer, String>();
    private static final Map<String, Integer> uri_id_map = new HashMap<String, Integer>();

    // Lưu trữ thông tin người dùng vào Session.
    public static void storeLoginedUser(HttpServletRequest request, UserAccountDTO loginedUser) {
        SessionUtils.getInstace().putValue(request,"loginedUser",loginedUser);
    }

    // Lấy thông tin người dùng lưu trữ trong Session.
    public static UserAccountDTO getLoginedUser(HttpServletRequest request) {
        UserAccountDTO loginedUser =(UserAccountDTO) SessionUtils.getInstace().getValue(request,"loginedUser");
        return loginedUser;
    }

    public static int storeRedirectAfterLoginUrl(String requestUri) {
        Integer id = uri_id_map.get(requestUri);
        if (id == null) {
            id = REDIRECT_ID++;
            uri_id_map.put(requestUri, id);
            id_uri_map.put(id, requestUri);
            return id;
        }
        return id;
    }

    public static String getRedirectAfterLoginUrl(int redirectId) {
        String url = id_uri_map.get(redirectId);
        if (url != null) {
            return url;
        }
        return null;
    }
}
