package controller.user;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.user.User;
@WebServlet("/user/update")
public class Update extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //signupにGETリクエストがきたら/WEB-INF/user/sighUp.jspへ
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user/update.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //signupにPOSTリクエストがきたら＝リクエストパラメータが送信されたら
        //requestオブジェクトの文字エンコーディング
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String old_password = request.getParameter("old_password");
        String new_password = request.getParameter("new_password");
        User currentUser = (User)request.getSession().getAttribute("currentUser");
        
        if(currentUser.getPassWord().equals(old_password)){
            User user = new User(
                    currentUser.getId(),
                    name,
                    email,
                    null,
                    null,
                    null
                );
            if(new_password!=null) {
                // 新パスワードを入力したとき
                user.setPassWord(new_password);
            } else {
                // 新パスワードを入力しなかったとき
                user.setPassWord(old_password);
            }
            user.updateUser();
        } else {
            System.out.print("パスワード不一致");
        }
        
        //登録が完了したらログイン画面にリダイレクト
        response.sendRedirect("/user/mypage");
    }
}