package filter;
import model.user.User;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
@WebFilter("/*")
public class AuthenticateFilter implements Filter {
    public void destroy() {
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        // ex) "/users/sessions/new"
        String servletPath = httpServletRequest.getServletPath();
        HttpSession session = httpServletRequest.getSession();
        User currentUser = User.getCurrentUser(httpServletRequest);
        
        // 新規登録画面にアクセスしたとき
        if (servletPath.equals("/user/signup")) {
            if (currentUser == null) {
                chain.doFilter(req, resp);
            } else {
                // リダイレクト
                httpServletResponse.sendRedirect("/user/mypage");
            }
        }
        // ログイン画面にアクセスしたとき
        else if (servletPath.equals("/user/login")) {
            if (currentUser == null) {
                chain.doFilter(req, resp);
            } else {
                // リダイレクト
                httpServletResponse.sendRedirect("/users/mypage");
            }
        }
          // トップ画面にアクセスしたとき
        else if (servletPath.equals("/")) {
            if (currentUser == null) {
                chain.doFilter(req, resp);
            } else {
                // リダイレクト
                httpServletResponse.sendRedirect("/user/mypage");
            }
        }
        // ログインしないとダメな画面にアクセスしたとき
        else {
            if (currentUser == null) {
                // リダイレクト
                httpServletResponse.sendRedirect("/");
            } else {
                chain.doFilter(req, resp);
            }
        }
    }
    public void init(FilterConfig config) throws ServletException {
    }
}