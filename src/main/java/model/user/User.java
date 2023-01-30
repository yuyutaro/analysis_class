package model.user;
import model.Default;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    //setter
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassWord(String password) { this.password = password; }
    //getter
    public String getName() { return this.name; }
    public String getEmail() { return this.email; }
    public String getPassWord() { return this.password; }
    //controller/User/SignUpUser.javaからの呼び出し
    public void insertUser(){
        this.hashPassword();
        Repository.insertUser(this);
    }
    // controller/User/Update.javaからの呼び出し
    public void updateUser(){
        this.hashPassword();
        Repository.updateUser(this);
    }
    //User認証の機構
    public boolean authenticateUser(HttpServletRequest request) {
        //Mailをもとにユーザーが存在するか調べる
        User persistedUser = Repository.selectUserByEmail(this.email);
        if (persistedUser == null) {    //Mailをもつユーザーがいなければ
            return false;
        }
        //ここからはMailをもつユーザーがいればの話
        this.hashPassword();    //入力されたパスワードをハッシュ化
        if (this.password.equals(persistedUser.password)) { //ハッシュ化したものとDBのパスワードが一致すれば
            //ログインしているかどうかの情報を切り替える
            HttpSession session = request.getSession(); //セッションを作って
            session.setAttribute(currentUserKey, persistedUser);    //セッションスコープにユーザー情報保存
            return true;
        } else {    //パスワードが違ったらfalseを返す
            return false;
        }
    }
    //controller/User/Login.javaからの呼び出し
    public User selectUserByEmail(String email){
        User currentUser = Repository.selectUserByEmail(email);
        return currentUser;
    }
    //ハッシュ化
    public void hashPassword(){
        this.password=getHash(this.email,this.password);
    }
    private String getHash(String mail, String password) {
        final String HASH_ALGORITHM = "SHA-256";
        final int STRETCH_COUNT = 1024;
        final String FIXED_SALT = "vBjRGHZ6awqJL9JDQuNftAzaPSnHszQN";
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String salt = mail + FIXED_SALT;
        messageDigest.update(salt.getBytes());
        byte[] hashed = messageDigest.digest(password.getBytes());
        for (int i = 0; i < STRETCH_COUNT; i++) {
            hashed = messageDigest.digest(hashed);
        }
        return getHexString(hashed);
    }
    private String getHexString(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            stringBuffer.append(Integer.toHexString((bytes[i] >> 4) & 0x0f));
            stringBuffer.append(Integer.toHexString(bytes[i] & 0x0f));
        }
        return stringBuffer.toString();
    }
    // フィルター用にstaticメソッドにしておく
    public static User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (User) session.getAttribute(currentUserKey);
    }
    // セッションスコープからcurrentUserを取り除く
    public static void logoutUesr(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(currentUserKey);
    }
}