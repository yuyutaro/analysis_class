<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ツイート登録画面</title>
</head>
<body>
    <h1>ここはツイート登録画面です</h1>
    <form action="/tweet/post" method="post">
        <ul>
            <li><p>ツイート内容</p><input type="text" name="content" placeholder="こんにちは" required /></li>
        </ul>
        <button type="submit" name="button">ツイート</button>
    </form>
</body>
</html>