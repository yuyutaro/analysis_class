package controller.user;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.user.User;
@WebServlet("/user/login")
public class Login extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //signupにGETリクエストがきたら/WEB-INF/user/login.jspへ
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user/login.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //signupにPOSTリクエストがきたら＝リクエストパラメータが送信されたら
        //requestオブジェクトの文字エンコーディング
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //requestオブジェクトから登録情報の取り出し
        User user = new User(
                null,
                null,
                email,
                password,
                null,
                null
        );
        if (user.authenticateUser(request)) {   //ログインが成功したら
            response.sendRedirect("/user/mypage");
        } else {
            //ログインが失敗したらフォワード
            request.setAttribute("couldNotSignIn", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}