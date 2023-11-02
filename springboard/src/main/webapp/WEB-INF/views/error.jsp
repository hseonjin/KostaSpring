<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="main.jsp"/>
<body>
 <h3><%=request.getAttribute("err") %></h3> <!-- AccountInfo에 의해 에러 메시지 출력 -->
</body>
</html>