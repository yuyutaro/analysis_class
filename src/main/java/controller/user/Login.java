package controller.user;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        User currentUser = user.selectUserByEmail(email);
        if (currentUser != null) {
            if (currentUser.getPassWord().equals(user.getPassWord())) { //currentUserはemailをもとに持ってきたUser情報．userは入力したUser情報
                //セッションスコープにインスタンスを保存
                //"currnetUser"という名前でcurrentUserインスタンスを保存
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", currentUser);
                System.out.println("ログイン成功");
            } else {
                System.out.println("ログイン失敗");
            }
        } else {
            System.out.println("ユーザー情報は見当たりませんでした");
        }
        //登録が完了したらログイン画面にリダイレクト
        response.sendRedirect("/user/mypage");
    }
}