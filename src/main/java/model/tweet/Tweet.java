package model.tweet;
import model.Default;
import java.sql.Timestamp;
public class Tweet extends Default {
    private String content;
    // TweetはUserのIdを外部キーに持つのでusersIdとしてクラス変数を定義しておく
    private Integer usersId;
    public Tweet(
        Integer id,
        String content,
        Timestamp createdAt,
        Integer usersId
    ){
        // TweetはUpdateができないようにするのでupdatedAtは継承しない
        super(id, createdAt);
        this.content = content;
        this.usersId = usersId;
    }
    // getter
    public String getContent() {
        return this.content;
    }
    public Integer getUsersId() {
        return this.usersId;
    }
    // Tweet登録用のメソッド
    public void insertTweet(){
        Repository.insertTweet(this);
    }
}