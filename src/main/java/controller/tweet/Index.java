package controller.tweet;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.tweet.Tweet;
@WebServlet("/tweet/index")
public class Index extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Tweet.indexTweets()でTweet一覧を取得してきてArrayList<Tweet>型のtweetsに格納
        ArrayList<Tweet> tweets = Tweet.indexTweets();
        // 取得したtweetsをその名前のままリクエストスコープに保存
        request.setAttribute("tweets", tweets);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/tweet/index.jsp");
        dispatcher.forward(request, response);
    }
    // このサーブレットはTweet一覧を表示させるためのものなのでdoPostは使わない
    // protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    // }
}