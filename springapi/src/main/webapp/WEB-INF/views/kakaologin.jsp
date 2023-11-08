<%--
  Created by IntelliJ IDEA.
  User: 선진
  Date: 2023-11-07
  Time: 오후 2:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="https://kauth.kakao.com/oauth/authorize?client_id=c95c7f73f119e07ce4e82a038f1d7883&
            redirect_uri=http://localhost:8088/kakaologin&response_type=code">
    <img src="${path}/resources/kakao.png" alt=""/>
</a>
</body>
</html>
