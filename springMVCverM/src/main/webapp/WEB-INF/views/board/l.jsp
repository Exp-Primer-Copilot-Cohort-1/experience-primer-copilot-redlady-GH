<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ page import="java.util.*" %>
<%@ page import="edu.study.vo.BoardVO" %>
<%
//	List<BoardVO> list = (List<BoardVO>)request.getAttribute("datalist");
%> --%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h2>board/list 페이지</h2>
		<form action="list.do" method="get">
			<select name="searchType">
				<option value="title" <c:if test="${param.searchType eq 'title' }">selected</c:if>>제목</option>
				<option value="content" <c:if test="${param.searchType eq 'content' }">selected</c:if>>내용</option>
			</select>
			<input type="text" name="searchVal" value="${param.searchVal}" >
			<button>검색</button>
		</form>
		
		<table border="1">
		<%-- 
		<%
			for(BoardVO vo : list){
				%>
				<tr>
					<td><%=vo.getTitle() %></td>
					<td><%=vo.getWriter() %></td>
					<td><%=vo.getContent() %></td>
				</tr>
				<%
			}
		%> --%>
		</table>
		
		<table border="1">
		<c:forEach items="${datalist}" var="vo">
			<tr>
				<td>${vo.bidx}</td>
				<td><a href="view.do?bidx=${vo.bidx}">${vo.title}</a></td>
				<td>${vo.wdate}</td>
				<td>${vo.name}</td>
			</tr>
		</c:forEach>
		</table>
		
		<!-- 로그인 시에만 출력되도록 수정하세요 -->
		<c:if test="${login != null }">
			<button onclick="location.href='write.do'">등록</button>
		</c:if>
	</body>
</html>