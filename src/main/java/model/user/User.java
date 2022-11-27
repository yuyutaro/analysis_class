package model.user;
import model.Default;
import java.sql.Timestamp;
public class User extends Default {
    public final static String currentUserKey = "currentUser";
    private String name;
    private String email;
    private String password;
    //新規登録時コンストラクタ
    public User(
            Integer id,
            String name,
            String email,
            String password,
            Timestamp createdAt,
            Timestamp updatedAt
    ){
        super(id, createdAt, updatedAt);
        //親クラスのコンストラクタを呼び出す
        this.name = name;
        this.email = email;
        this.password= password;
    }
    //setメソッド
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassWord(String password) { this.password = password; }
    //getメソッド
    public String getName() { return this.name; }
    public String getEmail() { return this.email; }
    public String getPassWord() { return this.password; }
    //controller/User/SignUpUser.javaからの呼び出し
    public void insertUser(){
        Repository.insertUser(this);
    }

//controller/User/Login.javaからの呼び出し
    public User selectUserByEmail(String email){
        User currentUser = Repository.selectUserByEmail(email);
        return currentUser;
    } 
}