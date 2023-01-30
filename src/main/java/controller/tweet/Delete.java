package controller.tweet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.tweet.Tweet;
@WebServlet("/tweet/delete")
public class Delete extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // formタグからではないがURLにTweetのidがリクエストパラメーターとして埋め込まれている
        String string_tweet_id = request.getParameter("id");
        Tweet.deleteTweet(string_tweet_id);
        response.sendRedirect("/tweet/index");
    }
    // このサーブレットはTweet一覧を表示させるためのものなのでdoPostは使わない
    // protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    // }
}