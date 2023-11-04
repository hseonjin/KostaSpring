<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

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
    width: 400px;
    margin: 0 auto;
    padding: 20px;
}
table {
margin: 0 auto;}

</style>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
	$(function() {
		let isIdCheck = false;
		$("#checkid").click(function() {
			$.ajax({
				url: "idcheck",
				type:"post",
				data:{id:$('#id').val()},
				success: function(res) {
					if(res=="notexist") { // 아이디 사용 가능
						isIdCheck=true;
						alert("사용가능한 아이디입니다.");
					} else {
						alert("아이디가 중복됩니다.");
					}
				},
				error: function(err) {
					console.log(err);
				}
			})
		})
		$("#id").change(function() {
			isIdCheck = false;
		})

		$("#form").submit(function(e) {
			if(isIdCheck==false) {
				alert("아이디 중복체크하세요");
				e.preventDefault();
			}
		})
	})
</script>

<jsp:include page="main.jsp"/>

<div class="container">
	<h3>회원가입</h3>
	<form action="join" method="post" id="form">
		<table>
			<tr>
				<td class="label">이름</td>
				<td><input type="text" name="name" id="name" required="required"></td>
			</tr>
			<tr>
				<td class="label">아이디</td>
				<td><input type="text" name="id" id="id" required="required"></td>
				<td><input type="button" name="checkid" id="checkid" value="중복체크"></td>
			</tr>
			<tr>
				<td class="label">비밀번호</td>
				<td><input type="password" name="password" id="password" required="required"></td>
			</tr>
			<tr>
				<td class="label">이메일</td>
				<td><input type="text" name="email" id="email" required="required"></td>
			</tr>
			<tr>
				<td class="label">주소</td>
				<td><input type="text" name="address" id="address" required="required"></td>
			</tr>
		</table>
		<p><input type="submit" value="회원가입"></p>
	</form>
</div>