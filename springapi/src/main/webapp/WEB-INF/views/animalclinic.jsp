<%--
  Created by IntelliJ IDEA.
  User: 선진
  Date: 2023-11-06
  Time: 오후 2:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"  %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<c:set var="path" value="${pageContext.request.contextPath}" />

<html>
<head>
    <title>home</title>
</head>
<body>
<div class="container mt-3">
    <h2>서울시 동물병원 인허가 정보</h2>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>사업장명</th>
                <th>영업상태명</th>
                <th>전화번호</th>
                <th>주소</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${acList}" var="ac">
                <tr data-x="{ac.x}" data-y="${ac.y}">
                    <td>${ac.bplcNm}</td>
                    <td>${ac.trdStateNm}</td>
                    <td>${ac.siteTel}</td>
                    <td>${ac.rdnwhlAddr}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <ul class="pagination justify-content-center">
    <c:choose>
        <c:when test="${pageInfo.curPage>1}">
            <li class="page-item"><a class="page-link" href="${path}/clinic/${pageInfo.curPage-1}">Previous</a></li>
        </c:when>
        <c:otherwise>
            <li class="page-item"><a class="page-link" href="#" style="pointer-events: none">Previous</a></li>
        </c:otherwise>
    </c:choose>

        <c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" var="i">
            <li class="page-item"><a class="page-link" href="${path}/clinic/${i}">${i}</a></li>
        </c:forEach>

    <c:choose>
        <c:when test="${pageInfo.curPage<pageInfo.allPage}">
            <li class="page-item"><a class="page-link" href="${path}/clinic/${pageInfo.curPage+1}">Next</a></li>
        </c:when>
        <c:otherwise>
            <li class="page-item"><a class="page-link" href="#" style="pointer-events: none">Next</a></li>
        </c:otherwise>
    </c:choose>

    </ul>
</div>
</body>
</html>
