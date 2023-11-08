<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인 회원 정보</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body style="width: 800px; margin:3rem auto">
  <h2>${userInfo.nickname}님의 정보</h2>
  <table class="table" style="margin:1rem auto">
      <tbody>
          <tr>
              <th>ID</th><td>${userInfo.id}</td>
          </tr>
          <tr>
              <th>Nickname</th><td>${userInfo.nickname}</td>
          </tr>
          <tr>
              <th>Email</th><td>${userInfo.email}</td>
          </tr>
          <tr>
              <th>ProfileImage</th><td><img src="${userInfo.profile_image}" style="width:30%"></td>
          </tr>
      </tbody>
  </table>
</body>
</html>
