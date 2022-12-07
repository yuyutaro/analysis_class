package model.tweet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
}