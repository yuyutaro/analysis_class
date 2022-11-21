package controller.user;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.user.User;
@WebServlet("/user/signup")
public class SignUp extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //signupにGETリクエストがきたら/WEB-INF/user/sighUp.jspへ
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user/signup.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //signupにPOSTリクエストがきたら＝リクエストパラメータが送信されたら
        //requestオブジェクトの文字エンコーディング
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //requestオブジェクトから登録情報の取り出し
        User user = new User(
                null,
                name,
                email,
                password,
                null,
                null
        );
        user.insertUser();
        //リクエストスコープにインスタンスを保存
        //"user"という名前でuserインスタンスを保存
        request.setAttribute("user",user);
        //登録が完了したらログイン画面にリダイレクト
        response.sendRedirect("/");
    }
}