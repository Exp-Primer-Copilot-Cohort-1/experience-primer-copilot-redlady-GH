<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>board/view 페이지</h2>
	<table border="1">
		<tr>
			<th>글제목</th>
			<td>${vo.title }</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${vo.wdate }</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${vo.hit }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${vo.content}</td>
		</tr>
	</table>
	
	<button onclick="location.href='modify.do?bidx=${vo.bidx }'">수정</button>
	<button onclick="document.delfrm.submit();">삭제</button>
	<button onclick="location.href='list.do'">목록으로</button>
	<form name="delfrm" action="delete.do" method="post">
		<input type="hidden" name="bidx" value="${vo.bidx}">
	</form>
	<!-- 삭제버튼 클릭시 해당 데이터 삭제후 list 페이지로 리다이렉트 하세요. -->
	
</body>
</html>




