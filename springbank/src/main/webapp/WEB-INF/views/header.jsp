<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
* {
	margin: 0 auto;
}

a {
	text-decoration: none;
	color: black;
}

h1, h3, h5 {
	text-align: center;
	padding: 30px 0;
	color: lightslategrey;
}

.header {
	height: 150px;
	border-bottom: 1px solid lightgray;
}

.navi {
	display: flex;
	flex-direction: row;
}
</style>


<!--     세션에서 사용자 정보를 가져온다 -->
<%
	String id=(String)session.getAttribute("id");
%>


<div class="header">
	<i><a href="/"><h1>KOSTA BANK</h1></a></i>
	<div class="navi">
		<div class="navi_left" style="text-align: left;">
			<a href="makeAccount">계좌개설 |</a> <a href="deposit">입금 |</a> <a
				href="withdraw">출금 |</a> <a href="accountInfo">계좌조회 |</a> <a
				href="allAccountInfo">전체계좌조회</a>
		</div>
		<div class="navi_right" style="text-align: right;">
			<%
			if (id == null) {
			%>
			<a href="login">로그인 |</a> <a href="join">회원가입</a>
			<%
			} else {
			%>
			<%=id%>님 환영합니다. | <a href="logout">로그아웃</a>
			<%
			}
			%>
		</div>
	</div>
</div>