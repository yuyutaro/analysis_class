package controller.tweet;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.tweet.Tweet;
import model.user.User;
@WebServlet("/tweet/post")
public class NewPost extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //signupにGETリクエストがきたら/WEB-INF/tweet/post.jspへ
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/tweet/post.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //signupにPOSTリクエストがきたら＝リクエストパラメータが送信されたら
        //requestオブジェクトの文字エンコーディング
        request.setCharacterEncoding("UTF-8");
        String content = request.getParameter("content");
        HttpSession session = request.getSession();
        User currentUser = (User)session.getAttribute("currentUser");
        //requestオブジェクトから登録情報の取り出し
        Tweet tweet = new Tweet(
            null,
            content,
            null,
            currentUser.getId()
        );
        tweet.insertTweet();
        
        //登録が完了したらログイン画面にリダイレクト
        response.sendRedirect("/user/mypage");
    }
}