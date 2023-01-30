<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>ツイート一覧</title>
</head>
<body>
    <c:forEach var="tweet" items="${tweets}">
        <div style="padding: 10px; margin-bottom: 10px; border:1px solid #333333;">
            <p>id: ${tweet.getId()}</p>
            <p>${tweet.getContent()}</p>
            <p>users_id: ${tweet.getUsersId()}</p>
            <button onclick="location.href='/tweet/delete?id=${tweet.getId()}'">削除</button>
        </div>
    </c:forEach>
    <p><button onclick="location.href='/tweet/post'">ツイートする</button></p>
    <p><button onclick="location.href='/tweet/index'">ツイート一覧</button></p>
</body>
</html>