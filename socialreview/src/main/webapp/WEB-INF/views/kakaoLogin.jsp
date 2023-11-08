<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>카카오 로그인</title>
</head>
<body>
    <a href="https://kauth.kakao.com/oauth/authorize?client_id={client_id}&redirect_uri=http://localhost:8088/kakaologin&response_type=code">
        <img src="/resources/kakao.png" alt=""/>
    </a>
</body>
</html>
