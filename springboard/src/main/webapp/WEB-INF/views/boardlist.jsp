<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판글목록</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <style>
h3, h5 {
	text-align: center;
}

table {
	margin: 0 auto;
	width: 800px
}

#tbl_top {
	background: orange;
	text-align: center;
}

#emptyArea {
	margin: auto;
	width: 800px;
	text-align: center;
}

#emptyArea a {
	display: inline-block;
	width: 20px;
	height: 20px;
	background-color: ligthgray;
	text-decoration: none
}

#emptyArea .select {
	color: orange;
}
#tbl_content {
	text-align: center;
}
table {border: 1px solid gray;}
</style>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
	
	function callBtn(num) {
		var keyword = $("#keyword").val();
		if(keyword!=null && keyword.trim()!='') {
			$('#page').val(num);
			$('#searchform').submit();
		}
	}
</script>

	
</head>

<body>
<jsp:include page="main.jsp"></jsp:include>
    <div class="container">
        <h3>글 목록 &nbsp; 
        <c:if test="${user ne Empty }">
        <a href="${contextPath}/boardwrite">글쓰기</a>
        </c:if>
        </h3>
        <form action="search" method="post">
        <input type="hidden" name="page" id="page" value="1">	
        <h5>
            <select name="type">
                <option id="all" value="all">선택</option>
                <option id="subject" value="subject" ${type eq 'subject' ? 'selected': ''}>제목</option>
                <option id="writer" value="writer" ${type eq 'writer' ? 'selected': ''}>작성자</option>
                <option id="content" value="content" ${type eq 'content' ? 'selected': ''}>내용</option>
            </select>
            <input type="text" name="keyword" id="keyword" value="${keyword }" />
            <input type="submit" value="검색" />
        </h5>
        </form>
        <table>
            <tr id="tbl_top">
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>날짜</th>
                <th>조회수</th>
                <th>삭제</th>
     
			<c:forEach items="${boardList }" var="board">
				<tr id="tbl_content">
					<td>${board.num }</td>
<%-- 					<td><a href="boarddetail?num=${board.num}">${board.subject }</a></td> --%>
					<td><a href="${contextPath}/boarddetail/${board.num}">${board.subject }</a></td>
					<td>${board.writer }</td>
					<td>${board.writedate  }</td>
					<td>${board.viewcount  }</td>
					<td>
					<c:if test="${user.id == board.writer }">
<%--						<a href="boarddelete/${board.num }/${pageInfo.curPage}">삭제</a>--%>
						<a href="${contextPath}/boarddelete/${board.num }/${pageInfo.curPage}">삭제</a>
					</c:if>
					</td>
				</tr>
			</c:forEach>
        </table> 
        <div id="emptyArea">
        	<c:choose> 
				<c:when test="${pageInfo.curPage>1}">
					<a href="${contextPath}/boardlist?page=${pageInfo.curPage-1}">&lt;</a>
				</c:when>
				<c:otherwise>
					&lt;
				</c:otherwise>
			</c:choose>
        	
			<c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" var="i">
				<c:choose>
					<c:when test="${res.pageInfo.curPage==i}">
						<a href="${contextPath}/boardlist?page=${i}" class="select" onclick="callBtn(${i});return ${keyword==null};">${i}</a>&nbsp;
					</c:when>
					<c:otherwise>
						<a href="${contextPath}/boardlist?page=${i}" class="btn" onclick="callBtn(${i});return ${keyword==null};">${i}</a>&nbsp;
					</c:otherwise>
					
				</c:choose>
				
			</c:forEach>
        	
			<c:choose>  
				<c:when test="${pageInfo.curPage<pageInfo.allPage}">
					<a href="${contextPath}/boardlist?page=${pageInfo.curPage+1}">&gt;</a>
				</c:when>
				<c:otherwise>
					&gt
				</c:otherwise>
			</c:choose>
        </div>
    </div>
</body>

</html>