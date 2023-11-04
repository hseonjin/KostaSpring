<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판글수정</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js""></script>
    <script src=" jquery.lightbox-0.5.js"></script>
    <style>
        h2 {
            text-align: center;
        }

        table {
            margin: 0 auto;
            /* width: 450px; */
        }

        .left {
            width: 150px;
            background: orange;
        }

        .right {
            width: 300px;
            background: skyblue;
        }

        #btn {
            margin-top: 10px;
            text-align: center;
        }
    </style>
    <script>
        window.onload = function() {
            const fileDom = document.querySelector("#file");
            const imageBox = document.querySelector("#image-box");
            fileDom.addEventListener('change', ()=>{
                const imageSrc = URL.createObjectURL(fileDom.files[0]);
                imageBox.src = imageSrc;
            })
        }
    </script>
</head>

<body>
<jsp:include page="main.jsp"/>
    <div class="container">
        <h2>게시판글수정</h2>
        <form action="${contextPath}/boardmodify" method="post" enctype="multipart/form-data">
        <input type="hidden" name="num" value="${board.num }"/>
        <input type="hidden" name="fileurl" value="${board.fileurl }"/>
            <table>
                <tr>
                    <td class="left"><label for="writer">글쓴이</label></td>
                    <td class="right"><input type="text" name="writer" id="writer" value="${board.writer }" readonly="readonly"></td>
                </tr>
                <tr>
                    <td class="left"><label for="subject">제 목</label></td>
                    <td class="right"><input type="text" name="subject" id="subject" value="${board.subject }"></td>
                </tr>
                <tr>
                    <td class="left"><label for="content">내 용</label></td>
                    <td><textarea id="content" name="content" cols="40" rows="15">${board.content }</textarea></td>
                </tr>
                <tr>
                    <td class="left"><label for="writer">이미지 파일 첨부</label></td>
                    <td class="right">
					<c:choose>
						<c:when test="${board.fileurl eq Empty}">
							<img src="${contextPath}/resources/img/image.png"  width="100px" height="100px" id="image-box"/>
						</c:when>
						<c:otherwise>
							<img src="${contextPath}/image/${board.fileurl}"  width="100px" height="100px" id="image-box"/>
						</c:otherwise>
					</c:choose>
					<input name="file" type="file" id="file" accept="image/*"/>

                    </td>
                </tr>
                
            </table>
        <div id="btn">
            <input type="submit" value="수정">&nbsp;&nbsp;
            <a href="${contextPath}/boardlist">목록</a>
        </div>
        </form>

    </div>
</body>

</html>