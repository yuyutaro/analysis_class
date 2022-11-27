<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ログイン画面</title>
</head>
<body>
    <h1>ここはログイン画面です</h1>
    <form action="/user/login" method="post">
        <ul>
            <li><p>メールアドレス</p><input type="email" name="email" placeholder="メールアドレス" required /></li>
            <li><p>パスワード</p><input type="password" name="password" minlength="6" placeholder="パスワード" required /></li>
        </ul>
        <button type="submit" name="button">送信</button>
    </form>
</body>
</html>