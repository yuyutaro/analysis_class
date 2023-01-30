<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.user.User" %>
<%
    User currentUser = (User)session.getAttribute("currentUser");
%>
<html>
<head>
    <title>ユーザー情報変更画面</title>
</head>
<body>
    <h1>ここはユーザー情報変更画面です</h1>
    <form action="/user/update" method="post">
        <ul>
            <li><p>氏名</p><input type="text" name="name" value="${currentUser.getName()}" required /></li>
            <li><p>メールアドレス</p><input type="email" name="email" value="${currentUser.getEmail()}" required /></li>
            <li><p>古いパスワード</p><input type="password" name="old_password" minlength="6" placeholder="現在のパスワード" /></li>
            <li><p>新しいパスワード</p><input type="password" name="new_password" minlength="6" placeholder="新しいパスワード" /></li>
        </ul>
        <button type="submit" name="button">送信</button>
    </form>
</body>
</html>