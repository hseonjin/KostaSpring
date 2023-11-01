<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
#label {
	text-align: center;
	padding-right: 10px;
}
p {
	text-align: center;
	margin: 20px 0 10px 0;
}
form {
    border: 1px solid gray;
    width: 300px;
    margin: 0 auto;
    padding: 20px;
}
</style>
<jsp:include page="header.jsp"/>

<div class="container">
	<h3>회원가입</h3>
	<form action="join" method="post">
		<table>
			<tr>
				<td class="label">이름</td>
				<td><input type="text" name="name" id="name"></td>
			</tr>
			<tr>
				<td class="label">아이디</td>
				<td><input type="text" name="id" id="id"></td>
			</tr>
			<tr>
				<td class="label">비밀번호</td>
				<td><input type="password" name="password" id="password"></td>
			</tr>
			<tr>
				<td class="label">이메일</td>
				<td><input type="text" name="email" id="email"></td>
			</tr>
			<tr>
				<td class="label">주소</td>
				<td><input type="text" name="address" id="address"></td>
			</tr>
		</table>
		<p><input type="submit" value="회원가입"></p>
	</form>
</div>