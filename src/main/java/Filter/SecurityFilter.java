package Filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.UserAccountDTO;
import Request.UserRoleRequestWrapper;
import Utils.ApplicationUtils;
import Utils.SecurityUtils;

@WebFilter("/*")
public class SecurityFilter implements Filter {

    public SecurityFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();
        UserAccountDTO loginedUser = ApplicationUtils.getLoginedUser(request);

        if (servletPath.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest wrapRequest = request;

        if (loginedUser != null) {
            // User Name
            String userName = loginedUser.getFirstname();
            // Các vai trò (Role).
            Set<String> roles = loginedUser.getRoles();

            // Gói request cũ bởi một Request mới với các thông tin userName và Roles.
            wrapRequest = new UserRoleRequestWrapper(userName, roles, request);
        }

        // Các trang bắt buộc phải đăng nhập.
        if (SecurityUtils.isSecurityPage(request)) {

            // Nếu người dùng chưa đăng nhập,
            // Redirect (chuyển hướng) tới trang đăng nhập.
            if (loginedUser == null) {
                String requestUri = request.getRequestURI();
                // Lưu trữ trang hiện tại để redirect đến sau khi đăng nhập thành công.
                int redirectId = ApplicationUtils.storeRedirectAfterLoginUrl(requestUri);

                response.sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
                return;
            }

            // Kiểm tra người dùng có vai trò hợp lệ hay không?
            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
            if (!hasPermission) {
                response.sendRedirect("/faq");
                return;
            }
        }

        chain.doFilter(wrapRequest, response);
        return;
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

}
