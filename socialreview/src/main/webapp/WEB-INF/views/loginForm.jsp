<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>카카오 로그인</title>
</head>
<body>
    <a href="https://kauth.kakao.com/oauth/authorize?client_id={client_id}&redirect_uri=http://localhost:8088/kakaologin&response_type=code">
        <img src="/resources/kakao.png" alt=""/>
    </a>
    <a href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id={client_id}&redirect_uri=http://localhost:8088/naverlogin&state=1234">
        <img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/>
    </a>

</body>
</html>
