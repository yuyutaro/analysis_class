<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>トップ画面</title>
</head>
<body>
    <h1>ここはログイン画面です</h1>
    <form action="/user/signup" method="post">
        <ul>
            <li><p>氏名</p><input type="text" name="name" placeholder="氏名" required /></li>
            <li><p>メールアドレス</p><input type="email" name="email" placeholder="メールアドレス" required /></li>
            <li><p>パスワード</p><input type="password" name="password" minlength="6" placeholder="パスワード" required /></li>
        </ul>
        <button type="submit" name="button">登録</button>
    </form>
</body>
</html>