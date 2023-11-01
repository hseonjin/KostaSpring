<%@page import="java.util.List"%>
<%@ page import="com.kosta.bank.dto.Account" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
 #tbl_top {
     background: lightgrey;
 }

table {
    margin: 0 auto;
    width: 800px;
    border: 1px solid lightgrey;
    border-bottom: none;
}
tr, td {
	text-align: center;
	padding: 10px;
}

</style>

<% List<Account> accs = (List<Account>) request.getAttribute("accs"); %>

<jsp:include page="header.jsp"/>
<div class="container">
    <h3>전체 계좌 조회</h3>
    <table>
       <tr id="tbl_top">
           <th>순서</th>
           <th>계좌번호</th>
           <th>이름</th>
           <th>잔액</th>
           <th>계좌종류</th>
           <th>등급</th>
       </tr>
	    <% for(int i=0; i<accs.size(); i++) { %>
	    	<tr>
		         <td><%=i+1 %></td>
		         <td><%=accs.get(i).getId() %></td>
		         <td><%=accs.get(i).getName() %></td>
		         <td><%=accs.get(i).getBalance()%></td>
		         <td><%=accs.get(i).getType() %></td>
		         <td><%=accs.get(i).getGrade() %></td>
	    	</tr>
	    <% } %>
    </table> 
</div>