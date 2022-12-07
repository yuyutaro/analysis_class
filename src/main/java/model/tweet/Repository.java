package model.tweet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import lib.mysql.Client;
public class Repository extends Client {
    public static void insertTweet(Tweet tweet) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            //SQL文の用意
            String sql = "insert into tweets "+
                    "(content, created_at, users_id)"+
                    "values(?, ?, ?)";
            connection = createConnection();
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, tweet.getContent());
            stmt.setTimestamp(2, currentTime);
            stmt.setInt(3, tweet.getUsersId());
            
            stmt.executeUpdate();
            return;
            
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, stmt, rs);
        }
    }
    public static ArrayList<Tweet> indexTweets() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            //SQL文の用意
            String sql = "select * from tweets ";
            connection = createConnection();
            // 一旦rsにselect文の結果が格納される
            rs = connection.prepareStatement(sql).executeQuery();
            // スコープの都合上，外で定義
            ArrayList<Tweet> tweets = new ArrayList<>();
            while(rs.next()) {  // ResultSetがある限り繰り返す
                // tweetを1つずつ取り出してtweetsに加える
                Tweet tweet = new Tweet(
                    rs.getInt("id"),
                    rs.getString("content"),
                    rs.getTimestamp("created_at"),
                    rs.getInt(("users_id"))
                );
                tweets.add(tweet);
            }
            return tweets;
            
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            close(connection, stmt, rs);
        } 
    }
}